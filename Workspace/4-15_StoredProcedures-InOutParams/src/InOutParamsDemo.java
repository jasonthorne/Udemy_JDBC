import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class InOutParamsDemo {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		CallableStatement callableStatement = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			
			//-------------------------
			// 1 - get connection to db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//-------------------------
			// 2 - prepare the stored procedure call:
			callableStatement = connection.prepareCall("{call greet_the_department(?)}");
			
			//-------------------------
			//3 - set the parameters:
			
			String department = "Enginnering";
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register the out param
			callableStatement.setString(1, department); //set the out param
			
			//-------------------------
			//4 - call the stored procedure:
			System.out.println("Calling stored procedure : greet_the_department('" + department  + "')");
			
			callableStatement.execute(); //execute statement +++++++++++++
			
			//-------------------------
			//5 - get the value of the INOUT param:
			String statementResult = callableStatement.getString(1);
			
			System.out.println("the result is: " + statementResult);
			
		

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
