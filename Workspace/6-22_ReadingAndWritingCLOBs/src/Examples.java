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
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
