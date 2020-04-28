import java.sql.SQLException;

public class Main {
	
	/**
	 * 
	 * CLOB - Character Large Object
	 * 
	 * - Collection of large character data, stored as a single entity
	 * 
	 * - typically used to store large text documents - (plain text or XML)
	 * 
	 * - Note that db support for clobs is NOT UNIVERSAL!
	 * 
	 * - uses the LONGTEXT data type, which has support for up to 4 gigs of characters.
	 * @throws SQLException 
	 * 
	 */

	public static void main(String[] args) throws SQLException {
		
		//write CLOB to DB:
		Examples.writeCLOB();
		
		//read CLOB to DB:
		Examples.readCLOB();
		

	}

}
