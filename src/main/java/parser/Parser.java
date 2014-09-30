package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class Parser {
//	private ArrayList<String> docStrings = new ArrayList<String>();
	private HashMap<String, StringBuilder> docs = new HashMap<String, StringBuilder>();
    private File directory;
    private XMLInputFactory factory;


	public Parser(File directory) throws FileNotFoundException {
        this.directory = directory;
	}
	
	public void parseFile(File inputFile){
        FileInputStream fileInputStream;
        
        StringBuilder content = new StringBuilder();
        try {
        	
            fileInputStream = new FileInputStream(inputFile);
            XMLStreamReader parser = factory.createXMLStreamReader(fileInputStream);
            
            while(parser.hasNext()){

            	switch (parser.next()) {
            	                    
	                case XMLStreamConstants.CHARACTERS:
	                	content.append(parser.getText().trim());
	                    break;
	                    
	                case XMLStreamConstants.END_ELEMENT:
	                	if(parser.getLocalName().equalsIgnoreCase("text")){
	                		Preprocessing.replaceApostrophe (content);
	                		docs.put(inputFile.getName(), Preprocessing.replaceApostrophe (content));
		                    content = new StringBuilder();
	                	}
	                    break;
            	} // end switch
            }

            parser.close();
        } catch (XMLStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
	}
	
    private void parseAllFilesInDirectory(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
            	parseAllFilesInDirectory(fileEntry);
            } else {
//                System.out.println("Parsing file: " + fileEntry.getName());
                parseFile(fileEntry);
            }
        }
    }
    
    public void parse(){
    	this.factory = XMLInputFactory.newInstance();
    	this.parseAllFilesInDirectory(this.directory);
    }

    public HashMap<String, StringBuilder> getMapedDocs(){
    	return this.docs;
    }

}
