import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		//write Binary Large Object to DB:
		Examples.writeBLOB();
		
		//read Binary Large Object to DB:
		Examples.readBLOB();
		
	}

}
