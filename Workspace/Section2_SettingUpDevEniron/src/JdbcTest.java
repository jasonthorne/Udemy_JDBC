
import java.sql.*;


/**
 * 
 * NOTE that the mysql-connector-java.jar MUSt be included in here and added to project build path. (properties > build path)
 *
 */

public class JdbcTest {
	
	public static void main(String[] args) throws SQLException{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			//1 - get a connection to the DB:
				
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student"); //(db url, user, password)
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "iamroot"); //logging in with root acc
			System.out.println("DB connection sucessfull!\n");
			
			//-------------------------------------------------
			//2 - Create a statement:
			
			//statement objects are used for executing sql queries
			statement = connection.createStatement();
			
			//-------------------------------------------------
			//3 - Execute an SQL query:
			
			resultSet = statement.executeQuery("select * from employees");
			
			//-------------------------------------------------
			//4 - Process the result set:
			
			//resultSet is initially placed BEFORE the first row of data 
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + " " + resultSet.getString("first_name"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
