import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Examples {
	
	static void writeCLOB() throws SQLException {
		
		System.out.println("writeCLOB()\n");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		FileReader fileReader = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			//---------------------------------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//---------------------------------------------------
			//2 - prepare statement:
			String sql = "UPDATE employees SET resume=? WHERE email='john.doe@foo.com'";
			preparedStatement =  connection.prepareStatement(sql);
			
			
			//---------------------------------------------------
			//3 - set parameter for CLOB file name:
			
			/////////////File file = new File("SampleCLOB.txt");
			File file = new File("SampleCLOB.json");
			fileReader = new FileReader(file);
			preparedStatement.setCharacterStream(1, fileReader); //fileReader file set as param at pos 1
			
			System.out.println("reading input file: " + file.getAbsolutePath());
			
			//---------------------------------------------------
			//4 - execute statement:
			
			System.out.println("Storing CLOB: " + file + " in db: ");
			System.out.println(sql);
			
			preparedStatement.execute(); //execute statement
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	static void readCLOB() throws SQLException {
		
		System.out.println("=====================");
		System.out.println("readCLOB()\n");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null; //resultSet stores the results of query(s)
		
		Reader reader = null; //READER not fileReader :P ++++++++
		FileWriter fileWriter = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			//---------------------------------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//---------------------------------------------------
			//2 - create & execute statement:
			statement = connection.createStatement();
			String sql= "SELECT resume FROM employees WHERE email='john.doe@foo.com'";
			resultSet = statement.executeQuery(sql);
			
			//---------------------------------------------------
			//3 - set up a handle to the file:
			
			///////File file = new File("CLOB_fromDB.txt"); //path to where we want file
			File file = new File("CLOB_fromDB.json"); //path to where we want file
			fileWriter = new FileWriter(file);
			
			if(resultSet.next()) { //if resultSet has result
				
				reader = resultSet.getCharacterStream("resume"); //add to reader the character stream "resume" within resultSet
				System.out.println("Reading CLOB from db");
				System.out.println(sql);
				
				int charByte;
				
				while((charByte = reader.read()) > 0) { //while int returned from reader.read() isn't 0 (is still reading chars)
					fileWriter.write(charByte); //write character byte currently stored in char
				}
				
			}
			fileWriter.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
		
}
