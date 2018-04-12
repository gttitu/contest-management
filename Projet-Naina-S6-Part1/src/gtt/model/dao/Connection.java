package gtt.model.dao;

import java.sql.DriverManager;

public class Connection {
	
	// STATIC METHODS :
	
	public static java.sql.Connection get(String driver, String url, String username, String password) {
		
		java.sql.Connection result = null;
		
		try {
			
			System.out.println("Loading driver ... ");
			Class.forName(driver);
			System.out.println("Opening connection ...");
			result = DriverManager.getConnection(url, username, password);
			System.out.println("Ready to use !");
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		} return result;
		
	}
	
	public static void close(java.sql.Connection connection) {
		
		try {
			
			System.out.println("Closing connection ...");
			connection.close();
			System.out.println("Connection closed !");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
