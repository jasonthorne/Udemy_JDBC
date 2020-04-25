import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Examples {
	
	
	static void writeBLOB() throws SQLException{
		
		System.out.println("writing BLOB to DB:\n");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		FileInputStream fileInputStream = null;
		
		try {
			
			//---------------------------------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//--------------------------------------------------
			//2 - prepare statement:
			String sql = "UPDATE employees SET resume=? WHERE email='john.doe@foo.com'";
			preparedStatement = connection.prepareStatement(sql);
			
			//--------------------------------------------------
			//3 - set parameter for pdf file: //++++++++++++++Most important section!! ++++++++++
			
			File file = new File("TestPDF.pdf"); //file object holding path to file
			fileInputStream = new FileInputStream(file); //add file obj to new FileInputStream obj
			
			preparedStatement.setBinaryStream(1, fileInputStream); //add input stream to preparedStatement's binaryStream
			
			System.out.println("Reading input file: " + file.getAbsolutePath());
			
			//--------------------------------------------------
			//4 - execute statement:
			
			System.out.println("Storing pdf in db: " + file);
			
			preparedStatement.executeUpdate(); //++++++++++++++++++++++++++execute statement
			
			System.out.println("completed pdf upload!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	} //end of writeBLOB
	
	
	static void readBLOB() throws SQLException{
		
		System.out.println("-----------------------");
		System.out.println("reading BLOB from DB:\n");
		
		Connection connection = null;
		Statement statement = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		
		ResultSet resultSet = null;
		
		try {
			
			//---------------------------------------------------
			//1 - get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//--------------------------------------------------
			//2 - Execute statement:
			
			statement = connection.createStatement();
			String sql = "SELECT resume FROM employees WHERE email='john.doe@foo.com'";
			resultSet = statement.executeQuery(sql);
			
			//-------------------------------------------------- +++++++++++++++++
			//3 - Set up a handle to the file
			
			File file = new File("PDF_from_DB.pdf"); //file obj containing path to file to be made
			fileOutputStream = new FileOutputStream(file); //create a FileOutputStream obj, passsing in file path object
			
			//if result set has a result:
			if(resultSet.next()) {
				
				//fill input stream with binary stream from resultSet:
				inputStream = resultSet.getBinaryStream("resume"); 
				
				System.out.println("reading pdf from db");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024]; //create byte array to read bytes
				
				while(inputStream.read(buffer) > 0) { //while inputStream.read() still has bytes to read (passing in 1024 at a time) 
					
					fileOutputStream.write(buffer); //continue writing file, 1024 bytes at a time
				}
				
				System.out.println("Saved to file: " + file.getAbsolutePath());
				System.out.println("file created!! ");
				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	

}
