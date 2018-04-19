package gtt.test;

import gtt.model.dao.Connection;

public class Program {
	
	public static void main(String[] args) {
		
		java.sql.Connection connection = initConnection();
		System.out.println("**************************************************");
		
		
		
		System.out.println("**************************************************");
		Connection.close(connection);
		
	}
	
	static java.sql.Connection initConnection() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/contest-management";
		return  Connection.get(driver, url, "root", "root");
		
	}

}
