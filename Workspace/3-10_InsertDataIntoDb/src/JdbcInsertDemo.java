import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JdbcInsertDemo {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			
			//1 - get a connection to the DB:
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student"); //(db url, user, password)
			
			//-------------------------------------
			
			//2 - Create a statement:
			
			//statement objects are used for executing sql queries
			statement = connection.createStatement();
			
			//-------------------------------------
			
			//3 - Insert a new Employee:
			
			System.out.println("Inserting a new Employee into database");
			
			//int rowsEffected = statement.executeUpdate( //thhis call returns the number of rows effected +++++++++++++
			statement.executeUpdate(
					
					"insert into employees (last_name, first_name, email, department, salary)" 
					+ "values ('Blogs', 'Joe', 'blogs.joe@email.com', 'HR', 33000.00)"
			
					);
			
			//-------------------------------------
			
			//4 - Verify insert by getting a list of names:
			
			resultSet = statement.executeQuery("select * from employees order by last_name");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + " " + resultSet.getString("first_name"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			if (resultSet != null){
				resultSet.close(); //close reult set!! +++++++++++
			}
		}
		
		

	}

}
