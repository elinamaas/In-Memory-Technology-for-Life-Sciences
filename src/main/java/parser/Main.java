package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import opennlp.tools.util.InvalidFormatException;

public class Main 
{	
	private static String folderPath = "res/trainingSet";
//	private static String folderPath = "res/testingSet";

public static void main(String[] args) throws SQLException, InvalidFormatException, IOException {
	
	try {
		File folder = new File(folderPath);
		System.out.println("Start parsing.");
		Parser p = new Parser(folder);
		p.parse();
		System.out.println("Finished parsing.");	
					
		DatabaseManagement dbMan = new DatabaseManagement();			
		dbMan.truncateTable();
		System.out.println("Inserting texts.");
		Iterator it = p.getMapedDocs().entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        String parts[] = pairs.getKey().toString().split(".xml");
	        String docId = parts[0];
	        String text = pairs.getValue().toString();
	        dbMan.insertDocText(parts[0], text);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		System.out.println("Finished.");
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
}
