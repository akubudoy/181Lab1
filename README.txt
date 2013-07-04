HTML to CSV file Java Program
by James Gordo BSEC
CSC181 Task 1

--What are the dependency packages of your program? What are these functions?
In this Program, I have used the HTMLunit, Java utililies(java.util) and 
input output(java.io) libraries. Using the HTMLunit, i implemented the
getFirstByXPath function to scrape informations that I need. With the help
of java utilies library, I implemented the LinkedHashSet to store the contents
on separate cells in the csv file.

--How to run your program? What are constraints of your program?
1. Simply extract this compressed folder. Open and compile it on Eclipse.
    You can also run the JAR file automatically just by clicking it directly.

2. Click the Select File Button and choose the file you wanted to convert.
    Note that only htm, html files are acceptable.

3. Click the CONVERT & SAVE button, enter the filename in this format
    YOURFILENAMEHERE.csv

--What should be and inputs and what are the expected outputs.
This program will only accept htm or html files and produces the converted
csv file.