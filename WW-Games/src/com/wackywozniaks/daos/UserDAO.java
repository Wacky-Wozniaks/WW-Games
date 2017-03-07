package com.wackywozniaks.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;

import com.wackywozniaks.beans.UserBean;
import com.wackywozniaks.controllers.ConnectionController;

/**
 * 
 * This is the user data access object which handles queries to the database, etc. 
 * 
 * This code was based off http://met.guc.edu.eg/OnlineTutorials/JSP%20-%20Servlets/Full%20Login%20Example.aspx but was
 * heavily modified by the Wacky Wozniaks Company.
 * 
 * @author WackyWozniaks Company
 *
 */
public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	// TODO Uncomment before uploading to Amazon!
	//static String url = "http://wackywozniaks.com/";
	static String url = "http://localhost:8080/WW-Games/";
	
	/**
	 * This method takes in a user bean with new information and makes a new user if possible. Returns a valid bean if successful.
	 * 
	 * @param bean the original bean
	 * @return the modified bean
	 */
	public static UserBean newUser(UserBean bean)
	{
		bean.setVerified(false);
		Statement stmt = null;
		try
		{
			currentCon = ConnectionController.getConnection();
			stmt = currentCon.createStatement();
			
			//verify that there is not already a user with the same email
			String email = bean.getEmail();
			email.trim(); //removing possible whitespace
			String searchQuery = "select email from users where email = \'" + email + "\'";
			
			rs = stmt.executeQuery(searchQuery);
			String password = bean.getPassword();
			if(!rs.next() && email.endsWith("@mxschool.edu") && meetsRequirements(password)) //next returns true if there is a next row
			{
				String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
				String updateQuery = "insert into users values(default, \'" + email + "\', \'" + hashed + "\', \'" + bean.getFirstName() + 
						"\', \'" + bean.getLastName() + "\', false)";
				stmt.executeUpdate(updateQuery);
				sendEmail(bean);
				bean.setValid(true);
			}
			else bean.setValid(false);
		}
		catch(SQLException e)
		{
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			// TODO exception handing
		}
		finally
		{
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					stmt = null;
				}
			}
			if(currentCon != null)
			{
				try
				{
					currentCon.close();
				}
				catch(SQLException e)
				{
					currentCon = null;
				}
			}
		}
		return bean;
	}
	
	/**
	 * Checks whether a password meets the defined set of requirements
	 * 
	 * @param password the password
	 * @return Whether the password meets requirements
	 */
	private static boolean meetsRequirements(String password)
	{
		if(password.length() < 8) return false; //password must be at least 8 characters
		return true;
	}
	
	/**
	 * Sends a verification email
	 * 
	 * Adapted from https://www.tutorialspoint.com/jsp/jsp_sending_email.htm with gmail help 
	 * from http://stackoverflow.com/questions/15597616/sending-email-via-gmail-smtp-server-in-java
	 * 
	 * @param bean the user to be verified
	 */
	private static void sendEmail(UserBean bean)
	{
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("prrpapm", "mxclubwebsite");
			}
		});
		
		try
		{
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("prrpapm@gmail.com"));
			String email = bean.getEmail();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			String link = url + "verify?hash=" + BCrypt.hashpw(email, BCrypt.gensalt());
			message.setSubject("Verify Your Email for Wacky-Wozniaks");
			message.setText("Hi " + bean.getFirstName() + ",\r\nIn order to create an account on Wacky-Wozniaks, you need to verify that this email is yours. "
					+ "Please use the link below and enter your password to comfirm your email.\r\n" + link);
			
			Transport.send(message);
		}
		catch (MessagingException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
		}
	}
	
	/**
	 * Verifies the email of a user. Returns a valid bean if successful.
	 * 
	 * @param bean The user to be verified
	 * @param hash The hash provided in the link
	 * @return The modified user
	 */
	public static UserBean verify(UserBean bean, String hash)
	{
		String email = bean.getEmail();
		if(!BCrypt.checkpw(email, hash))
		{
			// TODO send error message
			bean.setValid(false);
			return bean;
		}
		
		Statement stmt = null;
		try
		{
			currentCon = ConnectionController.getConnection();
			stmt = currentCon.createStatement();
			
			String query = "select * from users where email = \'" + email + "\'";
			rs = stmt.executeQuery(query);
			
			if(!rs.next()) // if there is no user with that email
			{
				//TODO send error message
				bean.setValid(false);
			}
			else
			{
				String password = rs.getString("password"), first = rs.getString("first_name"), last = rs.getString("last_name");
				if(!BCrypt.checkpw(bean.getPassword(), password))
				{
					//TODO send error message
					bean.setValid(false);
				}
				else
				{
					String update = "update users set verified = true where email = \'" + email + "\'";
					stmt.executeUpdate(update);
					bean.setValid(true);
					bean.setVerified(true);
					bean.setFirstName(first);
					bean.setLastName(last);
				}
			}
		}
		catch(SQLException e)
		{
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			e.printStackTrace();
		}
		finally
		{
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					stmt = null;
				}
			}
			if(currentCon != null)
			{
				try
				{
					currentCon.close();
				}
				catch(SQLException e)
				{
					currentCon = null;
				}
			}
		}
		return bean;
	}
	
	/**
	 * Logs a user in. Returns a valid bean if successful.
	 * 
	 * @param bean The user to log in.
	 * @return The modified bean.
	 */
	public static UserBean login(UserBean bean) { //preparing some objects for connection
		Statement stmt = null;
		String email = bean.getEmail();
		String password = bean.getPassword();
		String searchQuery = "select * from users where email = \'" + email + "\'";
		//String searchQuery = "select * from users where email='" + email;
		
		try { //connect to DB\
			currentCon = ConnectionController.getConnection();
			stmt = currentCon.createStatement();	
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next(); // if user does not exist set the isValid variable to false
			System.out.println(rs.getBoolean("verified"));
			if(more && !rs.getBoolean("verified")) bean.setVerified(false);
			else if(more) bean.setVerified(true);
			
			if(!BCrypt.checkpw(password, rs.getString("password")) || !more) {
				//System.out.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			} //if user exists set the isValid variable to true
			else if(more) { 
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			}
		} 
		catch(Exception e) { 
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
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
