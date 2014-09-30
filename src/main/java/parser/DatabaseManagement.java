package parser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

import opennlp.tools.util.Span;

public class DatabaseManagement {

	private String user = "SYSTEM";
	private String password = "manager";
	private String host = "vm-mariana-seminar.eaalab.hpi.uni-potsdam.de";
	private String port = "30015";
	
	private Connection con;
			
	public DatabaseManagement() throws SQLException {
		super();
		this.connectToDatabase();
	}

	public void connectToDatabase() throws SQLException{
	    con = DriverManager.getConnection("jdbc:sap://"+host+":"+port, user, password);
	}
	
	public void truncateTrainingTable() throws SQLException{
		Statement stmt = con.createStatement();
	    stmt.executeUpdate("truncate table \"DE_INDENTIFICATION\".\"TRAINING_SET\"");

	}
	
	public void truncateTestTable() throws SQLException{
		Statement stmt = con.createStatement();
	    stmt.executeUpdate("truncate table \"DE_INDENTIFICATION\".\"TEST_SET\"");

	}
	
	public void insertDocTextTraining(String fileName, String text) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement(("insert into \"DE_INDENTIFICATION\".\"TRAINING_SET\" (\"ID\", \"TEXT\") VALUES ('" + fileName + "','" + text +"')"));

		pstmt.execute();	 
	}
	
	public void insertDocTextTest(String fileName, String text) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement(("insert into \"DE_INDENTIFICATION\".\"TEST_SET\" (\"ID\", \"TEXT\") VALUES ('" + fileName + "','" + text +"')"));

		pstmt.execute();	 
	}
	
	public void buildIndexTraining () throws SQLException{
		PreparedStatement pstmt = con.prepareStatement("CREATE FULLTEXT INDEX \"DE_INDENTIFICATION\".\"TRAININGSET_INDEX\" "
				+ "ON \"DE_INDENTIFICATION\".\"TRAINING_SET\"(TEXT) "
				+ "CONFIGURATION 'phi_idRules.xml' TEXT ANALYSIS on ");
		pstmt.execute();
				
	}
	
	public void buildIndexTest () throws SQLException{
		PreparedStatement pstmt = con.prepareStatement("CREATE FULLTEXT INDEX \"DE_INDENTIFICATION\".\"TESTSET_INDEX\" "
				+ " ON \"DE_INDENTIFICATION\".\"TEST_SET\"(TEXT) "
				+ "CONFIGURATION 'phi_idRules.xml' TEXT ANALYSIS on ");
		pstmt.execute();
				
	}
	
}
