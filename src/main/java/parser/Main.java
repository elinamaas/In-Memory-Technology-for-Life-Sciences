package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import evaluation.Evaluation;
import opennlp.tools.util.InvalidFormatException;

public class Main 
{	
	private static String trainingFolderPath = "res/trainingSet";
	private static String testFolderPath = "res/testingSet";

public static void main(String[] args) throws SQLException, InvalidFormatException, IOException {
	
	PutFilesToDB putFilesToDB = new PutFilesToDB();
	System.out.println("Start inserting into training table...");
	putFilesToDB.buildTrainindTable(trainingFolderPath);
	System.out.println("Finished");
	System.out.println("Start inserting into test table...");
	putFilesToDB.buildTestTable(testFolderPath);
	System.out.println("Finished");
	DatabaseManagement dbMan = new DatabaseManagement();
	System.out.println("Start making indexfor training set...");
	dbMan.buildIndexTraining();
	System.out.println("Finished");
	System.out.println("Start making indexfor training set...");
	dbMan.buildIndexTest();
	System.out.println("Finished");
	Evaluation evaluation = new Evaluation();
	System.out.println("Starting evaluation");
	evaluation.runEvaluation();
	System.out.println("Please run evaluation script");
}
}
