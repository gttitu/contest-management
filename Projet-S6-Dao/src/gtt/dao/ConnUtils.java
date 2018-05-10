package gtt.dao;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnUtils {
	
	// STATIC METHODS :
	
	public static Connection get() throws Exception {
		
		String driver =   "com.mysql.jdbc.Driver", 
			   url =      "jdbc:mysql://127.0.0.1:3306/contest-management", 
			   username = "root", 
			   password = "root";
		
		Class.forName(driver);
		
		return DriverManager.getConnection(url, username, password);
		
	}

}
