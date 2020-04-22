import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String coulmnNamePattern = null;
		String[] types = null;
		
		Connection connection = null;
		ResultSet resultSet = null;
		
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
			//3 - get list of all of the tables used in schema: +++++++++++
			
			System.out.println("\nlist of tables:");
			System.out.println("-----------------------");
			
			resultSet = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("TABLE_NAME"));
			}
			
			//-------------------------
			//4 - get list of all of the columns used in schema: +++++++++++
			
			System.out.println("\nlist of columns:");
			System.out.println("-----------------------");
			
			resultSet = databaseMetaData.getColumns(catalog, schemaPattern, "employees", coulmnNamePattern);
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("COLUMN_NAME"));
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally { //++++++++++++++++++++++++Dont forget to close these!!! 
			connection.close(); 
			//resultSet.close();
		}
		
	}
	
	
	

}
