package evaluation;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import opennlp.tools.util.InvalidFormatException;

public class EvaluationMain 
{	
	private static String folderPath = "res/testingSet/";
	private static String resulFolderPath = "res/results/";

public static void main(String[] args) throws SQLException, InvalidFormatException, IOException {
	
	DatabaseManagement dbMan = new DatabaseManagement();			
	File folder = new File(folderPath);
	File[] listOfFiles = folder.listFiles();
	for (File xmlFile : listOfFiles){
		String name = xmlFile.getName();
		String docId = name.split(".xml")[0];
		ResultSet resultSelect = dbMan.selectIdentifier(docId);
		XMLWriter configFile = new XMLWriter();
		String outputFile = resulFolderPath + docId +".xml";
		configFile.setFile(outputFile);
		try {
			configFile.saveConfig(resultSelect, xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		System.out.println("Finished."); 
	}
}
