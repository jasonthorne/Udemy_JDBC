import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Examples {
	
	
	static void MetaDataBasicInfo() throws SQLException{
		
		/**
		 * Database Metadata gives you info regarding your DB
		 */
		
		Connection connection = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try{
			
			//--------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//-------------------------
			//2 - get meta data: 
			
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			
			//-------------------------
			//3 - display info about db:
			
			System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
			
			//-------------------------
			//4 - display info about JDBC driver:
			
			System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}

		
	}
	
	//-------------------------------------------------------------
	
	static void SchemaInfo() throws SQLException{
		
		System.out.println("sup!");
		
	}
	
	
	

}
