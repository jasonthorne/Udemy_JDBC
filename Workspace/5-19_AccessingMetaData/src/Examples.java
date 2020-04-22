import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Examples {
	
	
	static void metaDataBasicInfo() throws SQLException{
		
		System.out.println("========================");
		System.out.println("metaDataBasicInfo()\n");
		
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
	
	static void schemaInfo() throws SQLException{
		
		System.out.println("\n========================");
		System.out.println("schemaInfo()\n");
		
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
			
			System.out.println("list of tables:");
			System.out.println("-----------------------");
			
			//"demo" is name of schema +++++++++++++++++++++
			resultSet = databaseMetaData.getTables("demo", schemaPattern, tableNamePattern, types);
			
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
	
	
	//-------------------------------------------------------------
	
	static void resultSetMetaData() throws SQLException{
		
		System.out.println("\n========================");
		System.out.println("resultSetMetaData()\n");
		
		/**
		 * Pulling metaData from a resultSet
		 */
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pswrd = "student";
		
		try {
			
			//--------------------------
			//1- get a connection to the db:
			connection = DriverManager.getConnection(dbUrl, user, pswrd);
			
			//--------------------------
			//2- Run a query:
			
			statement = connection.createStatement(); //create a statement from the connection
			
			//store results of a statement query in resultSet:
			resultSet = statement.executeQuery("SELECT id, last_name, first_name, salary FROM employees");
			
			//--------------------------
			//3- get resultSet metaData: ++++++++++++++
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			
			//--------------------------
			//4- Display metadata info:
			
			int columnCount = resultSetMetaData.getColumnCount();
			System.out.println("column count: " + columnCount + "\n");
			
			//columns in JDBC are 1 based, so we have to start this loop at 1 ++++++++++++++++++++++
			for (int i=1;i<=columnCount;i++) {
				System.out.println("column name: " + resultSetMetaData.getColumnName(i));
				System.out.println("type: " + resultSetMetaData.getColumnTypeName(i));
				System.out.println("is nullable?: " + resultSetMetaData.isNullable(i));
				System.out.println("is autoIncrement?: " + resultSetMetaData.isAutoIncrement(i) + "\n");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	


}
