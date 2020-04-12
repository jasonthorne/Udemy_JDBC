import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InputParamsDemo {
	
	/**
	 * 
	 *CallableStatemnt is used to call a 'StoredProcedure' in the db (passing it params if needed)
	 */

	public static void main(String[] args) throws SQLException {
	
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			//--------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//-------------------------
			//2 - show original salaries (B4 storedProcedure call):
			
			displayDeptartmentSalaries(connection, "engineering");
			
			//-------------------------
			//3 - PREPARE the storedProcedure call:

			//callableStatement is defined to call name of method, with 2 input param placeholders declared to be passed to it on call +++++++++++++++++++
			callableStatement = connection.prepareCall("{call increase_salaries_for_department(?,?)}"); 
			
			//-------------------------
			//4 - set the parameters for the statement (at the position required):
			
			String dept = "engineering";
			int increaseAmount = 1000; 
			
			callableStatement.setString(1, "engineering");
			callableStatement.setDouble(2, increaseAmount);
			
			//-------------------------
			//5 - Call stored procedure:
			
			System.out.println("Calling stored procedure: increase_salaries_for_department(" + dept + ", " + increaseAmount + ")");
			
			callableStatement.execute(); //stored procedure called here +++++++
			
			//-------------------------
			//6 - show NEW salaries AFTER storedProcedure call:
			
			displayDeptartmentSalaries(connection, "engineering");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	
	static void displayDeptartmentSalaries(Connection conn, String dept) {
		
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select salary FROM employees WHERE department = '" + dept + "'");
	
			while(resultSet.next()) {
				System.out.println("salary from " + dept + ": " + resultSet.getString("salary"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
