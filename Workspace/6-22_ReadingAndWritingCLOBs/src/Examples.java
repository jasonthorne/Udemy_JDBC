import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Examples {
	
	static void writeCLOB() throws SQLException {
		
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
			
			File file = new File("SampleCLOB.txt");
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

}
