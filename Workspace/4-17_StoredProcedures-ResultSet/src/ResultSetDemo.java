import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetDemo {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null; 
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		
		try {
			
			//--------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//-------------------------
			//2 - prepare the stored procedure call: 
			
			//CallableStatemnt is used to call a 'StoredProcedure' in the db (passing it params if needed)
			callableStatement = connection.prepareCall("{call get_employees_for_department(?)}"); //name of storted procedure in db, declared with one input param
			
			//-------------------------
			//3 - set the parameter (being passed to the stored procedure, via the statement):
			
			String department = "Engineering";
			callableStatement.setString(1, department);
			
			//-------------------------
			//4 - Call the stored procedure:
			
			System.out.println("Calling stored procedure : get_employees_for_department('" + department + "')");
			
			callableStatement.execute(); //execute statement +++++++++++++
			
			//-------------------------
			//5 - Get the result set:
			
			resultSet = callableStatement.getResultSet();
			
			//-------------------------
			//6 - Display the result set:
			
			while(resultSet.next()) {
				System.out.println("Name: " + resultSet.getString("first_name") + ". Dept: " + resultSet.getString("department"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
