package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Connect to SQL database
public class DBConnection {

	private static Connection conn; //use Connection class to declare conn

	public static Connection getConnection() {
		if( conn != null ){
			return conn;
		}
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/assignment3";
			String user = "root";
			String password = "StaticShock";
			Class.forName( driver );
			conn = DriverManager.getConnection( url, user, password );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return conn;
	}

	public static void closeConnection( Connection conn ) {
		if( conn == null )
			return;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
