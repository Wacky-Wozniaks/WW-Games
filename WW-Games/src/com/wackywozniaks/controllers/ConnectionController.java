package com.wackywozniaks.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wackywozniaks.daos.UserDAO;

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
	    if (System.getenv("RDS_HOSTNAME") != null) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbName = System.getenv("RDS_DB_NAME");
				String userName = System.getenv("RDS_USERNAME");
				String password = System.getenv("RDS_PASSWORD");
				String hostname = System.getenv("RDS_HOSTNAME");
				String port = System.getenv("RDS_PORT");
				String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
				Connection con = DriverManager.getConnection(jdbcUrl);
				return con;
			}
			catch (ClassNotFoundException | SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			}
	    }
	    
	    return null;
	  }

}
