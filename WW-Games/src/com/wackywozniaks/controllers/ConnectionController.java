package com.wackywozniaks.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			Logger.getLogger(ConnectionController.class.getName()).log(Level.WARNING, e.getMessage(), e);
			System.out.println(e);
		}
		return con;
	}
	
	// TODO Uncomment before uploading to Amazon!
	/**
	 * Connects to the Database when running on the elastic beanstalk.
	 * 
	 * Original code provided by http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-rds.html#java-rds-tomcat
	 * Edited by the Wacky Wozniaks Company
	 * 
	 * @return The Connection
	 */
	/*public static Connection getConnection() {
	    if (System.getProperty("RDS_HOSTNAME") != null) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbName = System.getProperty("RDS_DB_NAME");
				String userName = System.getProperty("RDS_USERNAME");
				String password = System.getProperty("RDS_PASSWORD");
				String hostname = System.getProperty("RDS_HOSTNAME");
				String port = System.getProperty("RDS_PORT");
				String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
				Connection con = DriverManager.getConnection(jdbcUrl);
				return con;
			}
			catch (ClassNotFoundException | SQLException e) {}
	    }
	    
	    return null;
	  }*/

}
