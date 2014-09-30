package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class PutFilesToDB {
	
	private static String trainingFolderPath = "res/trainingSet";
	private static String testFolderPath = "res/testingSet";
	
	public PutFilesToDB(){
		
	};
	
	public void buildTrainindTable(String path) throws SQLException{
		try {
			File folder = new File(path);
			System.out.println("Start parsing.");
			Parser p = new Parser(folder);
			p.parse();
			System.out.println("Finished parsing.");	
						
			DatabaseManagement dbMan = new DatabaseManagement();			
			dbMan.truncateTrainingTable();
			System.out.println("Inserting texts.");
			Iterator it = p.getMapedDocs().entrySet().iterator();
			while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        String parts[] = pairs.getKey().toString().split(".xml");
		        String docId = parts[0];
		        String text = pairs.getValue().toString();
		        dbMan.insertDocTextTraining(parts[0], text);
		        it.remove(); // avoids a ConcurrentModificationException
		    }
			
			System.out.println("Finished.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void buildTestTable(String path) throws SQLException{
		try {
			File folder = new File(path);
			System.out.println("Start parsing.");
			Parser p = new Parser(folder);
			p.parse();
			System.out.println("Finished parsing.");	
						
			DatabaseManagement dbMan = new DatabaseManagement();			
			dbMan.truncateTestTable();
			System.out.println("Inserting texts.");
			Iterator it = p.getMapedDocs().entrySet().iterator();
			while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        String parts[] = pairs.getKey().toString().split(".xml");
		        String docId = parts[0];
		        String text = pairs.getValue().toString();
		        dbMan.insertDocTextTest(parts[0], text);
		        it.remove(); // avoids a ConcurrentModificationException
		    }
			
			System.out.println("Finished.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
