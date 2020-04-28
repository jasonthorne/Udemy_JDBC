import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionDemo {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			//---------------------------------------------------
			//1 - load the properties file:
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("DB_login.properties"));
			
			//---------------------------------------------------
			//2 - read the properties file:
			
			String dburl = properties.getProperty("dburl");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			
			
			System.out.println("Connecting to database...");
			System.out.println("DB url: " + dburl);
			System.out.println("User: " + user);
			
			//---------------------------------------------------
			//3 - get a connection to the db:
			
			connection = DriverManager.getConnection(dburl, user, password);
			
			System.out.println("Connection successfull!\n");
			
			//---------------------------------------------------
			//4 - test connection:
			
			//prepare statement:
			statement = connection.createStatement(); //create a statement using connection
			String sql = "select * from employees";
			resultSet = statement.executeQuery(sql); //execute statement, storing results in resultSet
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	

	}

}
