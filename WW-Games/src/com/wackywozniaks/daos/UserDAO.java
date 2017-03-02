package com.wackywozniaks.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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
 * @author WackyWozniaks Company
 *
 */
public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static String url = "http://localhost:8080/WW-Games/";
	
	public static UserBean newUser(UserBean bean)
	{
		bean.setValid(false);
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
			if(rs.next()) //next returns true if there is a next row
			{
				// TODO send error message
			}
			else
			{
				if(!email.endsWith("@mxschool.edu"))
				{
					//TODO send error message
				}
				else
				{
					String password = bean.getPassword();
					if(!meetsRequirements(password))
					{
						// TODO send error message
					}
					else
					{
						String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
						String updateQuery = "insert into users values(default, \'" + email + "\', \'" + hashed + "\', \'" + bean.getFirstName() + 
								"\', \'" + bean.getLastName() + "\', false)";
						stmt.executeUpdate(updateQuery);
						sendEmail(bean);
					}
				}
			}
		}
		catch(SQLException e)
		{
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
	
	private static boolean meetsRequirements(String password)
	{
		if(password.length() < 8) return false; //password must be at least 8 characters
		return true;
	}
	
	//Adapted from https://www.tutorialspoint.com/jsp/jsp_sending_email.htm
	//with gmail help from http://stackoverflow.com/questions/15597616/sending-email-via-gmail-smtp-server-in-java
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
		catch (MessagingException mex) {}
	}
	
	public static UserBean verify(UserBean bean, String url)
	{
		String email = bean.getEmail();
		if(!BCrypt.checkpw(email, url))
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
					bean.setFirstName(first);
					bean.setLastName(last);
				}
			}
		}
		catch(SQLException e)
		{
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
	
	public static UserBean login(UserBean bean) { //preparing some objects for connection
		Statement stmt = null;
		String email = bean.getEmail();
		String password = bean.getPassword();
		String searchQuery = "select * from users where email='" + email + "'";
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
