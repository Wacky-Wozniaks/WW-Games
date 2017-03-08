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
	
	// TODO Uncomment before uploading to Amazon!
	/**
	 * Connects to the Database when running on the elastic beanstalk.
	 * 
	 * Original code provided by http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-rds.html#java-rds-tomcat
	 * Edited by the Wacky Wozniaks Company
	 * 
	 * @return The Connection
	 */
	public static Connection getConnection() {
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
	  }

}
