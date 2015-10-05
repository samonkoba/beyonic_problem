package com.beyonic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {

	private static final String USERNAME  = "root";
	private static final String PASSWORD  = "";
	
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/beyonic";
	
	
	
	
	
	public static Connection getConnection() throws SQLException{
		
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch (Exception e) {
	// TODO: handle exception
}
		
		
		
		Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
		
		return conn;
	}
}
