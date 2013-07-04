import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
 


public class HtoCUI extends JFrame {

	private JPanel contentPane;
	private JTextField link;
	private HtoC scraper;
	private JPanel mainPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HtoCUI frame = new HtoCUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HtoCUI() {
		setTitle("HTML to CSV Converter  |   James T. Gordo");
		
		 final JFileChooser openChooser = new JFileChooser();
	        FileNameExtensionFilter openFilter = new FileNameExtensionFilter(
	                "HTML document (*.htm,*.html)", "htm", "html");
	        openChooser.setFileFilter(openFilter);

	        final JFileChooser saveChooser = new JFileChooser();
	        FileNameExtensionFilter saveFilter = new FileNameExtensionFilter(
	                "Comma-Separated Values (CSV) file (*.csv)", "csv");
	        saveChooser.setFileFilter(saveFilter);		    	    
	        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("SELECT A FILE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = openChooser.showOpenDialog(mainPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                	scraper = new HtoC(openChooser.getSelectedFile());
                	link.setText(scraper.scrapeLink()); 
                }
			}
		});
		
		btnNewButton.setBounds(301, 108, 123, 38);
		contentPane.add(btnNewButton);
		
		link = new JTextField();
		link.setBounds(20, 108, 278, 38);
		contentPane.add(link);
		link.setColumns(10);
		
		JButton btnSaveConvertedFile = new JButton("CONVERT & SAVE");
		btnSaveConvertedFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSaveConvertedFile.addActionListener(new ActionListener() {
			  @Override
	            public void actionPerformed(ActionEvent event) {
	                int returnVal = saveChooser.showSaveDialog(mainPanel);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    
	                        try {
	                        	scraper.saveAsCSV(saveChooser.getSelectedFile());
							} 
	                        catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        
	                        
	                    }
	                
			  }
		});
		btnSaveConvertedFile.setBounds(141, 176, 142, 38);
		contentPane.add(btnSaveConvertedFile);
		
		JLabel lblhtmlDocumenthtmhtml = new JLabel("HTML document (*.htm,*.html)\", \"htm\", \"html\"; files ONLY");
		lblhtmlDocumenthtmhtml.setForeground(new Color(255, 0, 0));
		lblhtmlDocumenthtmhtml.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblhtmlDocumenthtmhtml.setBounds(30, 144, 287, 24);
		contentPane.add(lblhtmlDocumenthtmhtml);
		
		JLabel lblYourfilenamecsv = new JLabel("YOURFILENAME.csv");
		lblYourfilenamecsv.setForeground(Color.RED);
		lblYourfilenamecsv.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblYourfilenamecsv.setBounds(164, 213, 287, 24);
		contentPane.add(lblYourfilenamecsv);
		
		JLabel lblHtmlToCsv = new JLabel("HTML to CSV Converter");
		lblHtmlToCsv.setForeground(new Color(0, 0, 0));
		lblHtmlToCsv.setFont(new Font("League Gothic", Font.PLAIN, 40));
		lblHtmlToCsv.setBounds(88, 30, 404, 67);
		contentPane.add(lblHtmlToCsv);
	}

}
