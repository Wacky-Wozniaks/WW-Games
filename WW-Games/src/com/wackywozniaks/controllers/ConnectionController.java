package com.wackywozniaks.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages connections to the database.
 * 
 * @author WackyWozniaks Company
 */
public class ConnectionController {
	
	static Connection con;
	static String url;
	
	private static final String DB_NAME = "Wacky-Wozniaks", ADMIN = "postgres", PASS = "password";
	
	/**
	 * Connects to the database.
	 * 
	 * @return The connection
	 */
	public static Connection getConnection() {
		try {
			String url = "jdbc:postgresql://localhost:5432/" + DB_NAME; // assuming "DataSource" is your DataSource name
			Class.forName("org.postgresql.Driver");
			try {
				con = DriverManager.getConnection(url, ADMIN, PASS);
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		return con;
	}

}
