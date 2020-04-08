import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUpdateDemo {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		
		try {
			
			//1 - get a connection to the DB:
			connection = DriverManager.getConnection(dbUrl, user, pswrd); 
			
			//-------------------------------------
			

			//2 - Create a statement:
			statement = connection.createStatement();
			
			//-------------------------------------
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			if (resultSet != null){
				resultSet.close(); //close result set!! +++++++++++
			}
		}
		
		

	}

}
