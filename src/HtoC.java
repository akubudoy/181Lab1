import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtoC {

	private String title, date, content, cP, pageUrl;
	
	public String scrapeTitle() {
        return title;
    }

    public String scrapeDate() {
        return date;
    }

    public String scrapeContent() {
        return content;
    }
    
    public String scrapeLink(){
		return cP;
	}

    @SuppressWarnings("deprecation")
    public HtoC(File fInput) {
        WebClient client = new WebClient();
        client.setJavaScriptEnabled(false);
        client.setCssEnabled(false);
        client.setThrowExceptionOnFailingStatusCode(false);
        client.setThrowExceptionOnScriptError(false);
        client.setPrintContentOnFailingStatusCode(false);

        cP = fInput.toString();
        System.out.println(cP);
        pageUrl = cP;

        HtmlPage currentPage = null;
        try {
            currentPage = client.getPage(fInput.toURL().toString());
            
            HtmlElement hTitle = (HtmlElement) currentPage.getFirstByXPath("//div[@id=\"content\"]/h1");
            title = hTitle.asText();

            HtmlElement hDate = (HtmlElement) currentPage.getFirstByXPath("//div[@id=\"content\"]/p");
            date = hDate.asText();

            HtmlElement hContent = (HtmlElement) currentPage.getFirstByXPath("//div[@id=\"content\"]/div[@class=\"base\"]");
            content = hContent.asText();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int saveAsCSV(File output) throws IOException{
        LinkedHashSet<String> words = new LinkedHashSet<String>();
        Scanner reader = new Scanner(content);
        while(reader.hasNext()){
            String next = reader.next();
            char lastChar = next.charAt(next.length()-1);
            next = next.replaceAll("[.,()\"]","");
            if(next.replaceAll("\\W","").length() > 0) words.add(next);
        }
            if (!output.exists()) {
                output.createNewFile();
            }

            FileWriter fw = new FileWriter(output.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator<String> itr = words.iterator();
            while (itr.hasNext()) {
                String word = (String) itr.next();
                bw.write(word);
                if (itr.hasNext()) bw.write(",");
            }
            bw.close();
            reader.close();
        return words.size();
    }
}
