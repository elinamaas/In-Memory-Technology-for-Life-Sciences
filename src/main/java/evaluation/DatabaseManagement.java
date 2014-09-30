package evaluation;

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
	
	
	public ResultSet  selectIdentifier (String doc_id) throws SQLException{
		String query = "select \"TA_TOKEN\",\"TA_TYPE\",\"TA_OFFSET\"  from \"DE_INDENTIFICATION\".\"$TA_TESTSET_INDEX\" where ID = '"+ doc_id +"'";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet resultList = pstmt.executeQuery();
		return resultList;
		
	}
	
}
