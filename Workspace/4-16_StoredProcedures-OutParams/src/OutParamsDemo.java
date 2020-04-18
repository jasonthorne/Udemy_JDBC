import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class OutParamsDemo {

	public static void main(String[] args) throws SQLException{
	
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			// 1 - get connection to db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//-------------------------
			
			// 2 - prepare the stored procedure call:
			callableStatement = connection.prepareCall("{call get_count_for_department(?,?)}");
			
			//-------------------------
			//3 - set the parameters (used in the stored procedure):
			
			String department = "HR";
			callableStatement.setString(1, department); //set the INPUT param
			callableStatement.registerOutParameter(2, Types.INTEGER); //register the OUT param (the int declared at pos 2 of stored procedure)
			
			//-------------------------
			//4 - call the stored procedure:
			System.out.println("Calling stored procedure : get_count_for_department('" + department  + ", ?')");
			
			callableStatement.execute(); //execute statement +++++++++++++
			
			//-------------------------
			//5 - get the value of the OUT param:
			int statementResult = callableStatement.getInt(2);
			
			System.out.println("the result is: " + statementResult);
			

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
