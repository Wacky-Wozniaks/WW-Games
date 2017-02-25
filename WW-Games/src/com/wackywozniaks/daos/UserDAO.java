package com.wackywozniaks.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import com.wackywozniaks.beans.UserBean;
import com.wackywozniaks.controllers.ConnectionController;

/**
 * 
 * @author WackyWozniaks Company
 *
 */
public class UserDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	
	public static UserBean login(UserBean bean) { //preparing some objects for connection
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from users where email='" + username + "'";
		//System.out.println("Your user name is " + username);
		//System.out.println("Your password is " + password);
		//System.out.println("Query: "+searchQuery);
		try { //connect to DB\
			currentCon = ConnectionController.getConnection();
			stmt = currentCon.createStatement();	
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next(); // if user does not exist set the isValid variable to false
			if(!BCrypt.checkpw(password, rs.getString("password")) || !more) {
				//System.out.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			} //if user exists set the isValid variable to true
			else if(more) { 
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				//System.out.println("Welcome " + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			}
		} 
		catch(Exception ex) { 
			//System.out.println("Log In failed: An Exception has occurred! " + ex);
		} //some exception handling
		finally { 
			if(rs != null) { 
				try { 
					rs.close();
				}
				catch (Exception e) {}
				rs = null;
			}
			if(stmt != null) {
				try {
					stmt.close();
				}
				catch(Exception e) {}
				stmt = null;
			}
			if(currentCon != null) {
				try {
					currentCon.close();
				}
				catch(Exception e) {}
				currentCon = null;
			}
		}
		return bean;
	} 

}
