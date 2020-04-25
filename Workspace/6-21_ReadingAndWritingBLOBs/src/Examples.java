import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Examples {
	
	
	static void writeBLOB() throws SQLException{
		
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
		
		
		
		
	}
		
	

}
