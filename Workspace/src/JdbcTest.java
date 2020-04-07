
import java.sql.*;



public class JdbcTest {
	
	public static void main(String[] args) throws SQLException{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			//1 - get a connection to the DB:
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			System.out.println("DB connection sucessfull!\n");
			
			
			//2 - Create a statement:
			
			statement = connection.createStatement();
			
			
			//3 - Execute an SQL query:
			
			resultSet = statement.executeQuery("select * from employees");
			
			//4 - Process the result set:
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + " " + resultSet.getString("first_name"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
