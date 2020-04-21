
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
	
	/**
	 *
	 * A TRANSACTION is ONE or MORE SQL statements executed together:
	 * - either all of the statements are executed, leading to a COMMIT
	 * - or NONE of the statements are executed, leading to a ROLLBACK 
	 * 
	 * AutoCommit in JDBC is turned on by default. 
	 * However we want it off here, as we want the developer (or user) to control whether the statement is committed or not.  
	 * 
	 */

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			//---------------------------------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//--------------------------------------------------
			//2 - turn OFF auto commit: +++++++++++++++++IMPORTANT!! 
			connection.setAutoCommit(false);
			
			//--------------------------------------------------
			//3 - Show salaries BEFORE 
			
			System.out.println("Salaries BEFORE:");
			
			System.out.println("\nHR:");
			showSalaries("HR", connection);
			
			System.out.println("\nEngineering:");
			showSalaries("Engineering", connection);
			
			//--------------------------------------------------
			//4 - TRANSACTION step 1 - Delete all HR employees:
			
			statement = connection.createStatement(); //create a statement
			statement.executeUpdate("delete FROM employees WHERE department = 'HR'"); //execute an update query using that statement
			
			
			//5 - TRANSACTION step 2 - Set all engineering salaries to 300000:
			
			statement.executeUpdate("update employees SET salary = '300000' WHERE department = 'Engineering'"); //execute a update query using statement
			
			System.out.println("Transaction steps are ready");
			
			//--------------------------------------------------
			//6 - ask user if okay to save:
			
			boolean canSave = true;
			
			if(canSave) {
				connection.commit(); //coomity connection
				System.out.println("Transaction COMMITED");
			}else {
				connection.rollback(); //rollback connection
				System.out.println("Transaction ROLLED BACK");
			}
			
			
			//--------------------------------------------------
			//7 - Show salaries AFTER 
			
			System.out.println("\nSalaries AFTER:");
			
			System.out.println("\nHR:");
			showSalaries("HR", connection);
			
			System.out.println("\nEngineering:");
			showSalaries("Engineering", connection);
			
			//--------------------------------------------------le fin
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	static void showSalaries(String department, Connection connection) { 
		
		/**
		 * prob best to have prepared statement here use a stored procedure in in db instead 
		 * (allowing this autocommit obv as its only a selection)
		 */
		
		try {
			
			//prepare statement:
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE department = ? ");
			
			 //set the parameters for the statement (at the position required):
			preparedStatement.setString(1, department);
			
			//display the result set:
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("first_name") 
						+ " | " + resultSet.getString("last_name") 
						+ " | " + resultSet.getString("department")
						+ " | " + resultSet.getString("salary"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
