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
			
			//3 - display employee's initial info:
			displayEmployee(statement, "John", "Doe");
			
			//-------------------------------------
			
			//4 - UPDATE the employee's info:
			statement.executeUpdate(
					"UPDATE employees"
					+ " SET email='newEmail@email.com'"
					+ " WHERE last_name = 'Doe' AND first_name='John'"		
					);
			
			//-------------------------------------
			
			//5 - display employee's NEW info:
			displayEmployee(statement, "John", "Doe");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			if (resultSet != null){
				resultSet.close(); //close result set!! +++++++++++
			}
		}
		
		

	}
	
	static void displayEmployee(Statement statement, String firstName, String lastName) {
		
		ResultSet resultSet = null;
		
		try {
		
			//REMEMBER to include the SINGLE quotes around the search params!! ++++++++++++++++++
			resultSet = statement.executeQuery("select * from employees where first_name = '" + firstName + "' and last_name = '" + lastName + "'");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + " | " + resultSet.getString("first_name") + " | " + resultSet.getString("email"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Uh oh!!");
			e.printStackTrace();
		}
		
		
	}

}
