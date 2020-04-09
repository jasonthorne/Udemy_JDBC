import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Remember to import mysql-connector jar!
 */

/**
 * Prepared statements can also be used for insert, update and deletes (using .executeUpdate()) +++++++++++++++
 */

public class PreparedStatementDemo {

	public static void main(String[] args)  throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null; //note this is a PREPARED statement ++++++++++++
		ResultSet resultSet = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			//1 - get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//-------------------------------------
			
			//2 - prepare statement:
			preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ? AND department = ? ");
			
			
			//-------------------------------------
			
			//3 - set the parameters for the statement (at the position required):
			
			preparedStatement.setDouble(1, 8000);
			preparedStatement.setString(2, "Legal");
			
			//-------------------------------------
			
			//4 - display the result set:
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("first_name") 
						+ " | " + resultSet.getString("last_name") 
						+ " | " + resultSet.getString("department")
						+ " | " + resultSet.getString("salary"));
			}
			
			
			//================================================================
			System.out.println("\nreuse the prepared statement:");
			
			
			//5 - REset the parameters for the statement (at the position required):
			
			preparedStatement.setDouble(1, 25000);
			preparedStatement.setString(2, "HR");
			
			//-------------------------------------
			
			//6 - display the result set:
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("first_name") 
						+ " | " + resultSet.getString("last_name") 
						+ " | " + resultSet.getString("department")
						+ " | " + resultSet.getString("salary"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
