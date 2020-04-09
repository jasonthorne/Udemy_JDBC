import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDeleteDemo {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		
		try {
			
			//Get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//create a statement:
			statement = connection.createStatement();
			
			//display employee's initial info:
			displayEmployee(statement, "John", "Doe"); //shows nothing if already removed (duh!)
			
			//delete employee:
			statement.executeUpdate(
					"DELETE FROM employees "
					+ "WHERE first_name = 'John' AND last_name = 'DOE'"
					);
			
			//display employee AFTER removal:
			displayEmployee(statement, "John", "Doe"); //shows nothing if already removed (duh!)
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	
	static void displayEmployee(Statement statement, String fName, String lName) {
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery("select * FROM employees WHERE first_name = '" + fName + "' AND last_name = '" + lName + "'");
			if(resultSet.next()){
				while(resultSet.next()) {
					System.out.println(resultSet.getString("last_name") + " | " + resultSet.getString("first_name") + " | " + resultSet.getString("email"));
				}
			}
			else { System.out.println("no entry");	}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
