package com.wackywozniaks.dao;

import java.util.List;

import com.wackywozniaks.dto.SignupBean;
import com.wackywozniaks.entity.User;

/**
 * This is the user data access object which handles queries to the database, etc in regards to Users. 
 * 
 * @author WackyWozniaks Company
 * @version 04/30/2017
 */
public interface UserDAO {
	
	//public void setDataSource(DataSource dataSource);
	
	/**
	 * Creates a new user.
	 * 
	 * @param signupBean The user to be created.
	 */
	public void createUser(SignupBean signupBean);
	
	/**
	 * Gets a user based on its ID.
	 * 
	 * @param id the ID of the user to get
	 * @return The User with the id, null if none exists.
	 */
	public User getUser(Long id);
	
	/**
	 * Gets a user based on its email.
	 * 
	 * @param email the email of the user to get
	 * @return The User with the email, null if none exists.
	 */
	public User getUser(String email);
	
	/**
	 * Returns a list of all users.
	 * 
	 * @return The list of all users
	 */
	public List<User> listUsers();
	
	/**
	 * Takes a user who is attempting to verify his/her email and verifies it.
	 * 
	 * @param email The email of the user to verify.
	 */
	public void verifyUser(String email);
	
	/**
	 * Changes the password of a user.
	 * 
	 * @param email The email of the user.
	 * @param newPassword The hashed new password.
	 */
	public void changePassword(String email, String newPassword);
	
	/**
	 * Gets the number of points for a user.
	 * 
	 * @param email The email of the user.
	 * @return The number of points the user has, null if user does not exist.
	 */
	public Integer getPoints(String email);
	
	/**
	 * Adds the given number of points to a user.
	 * 
	 * @param email The email of the user.
	 * @param points The number of points to add.
	 * @return Whether the action was successful.
	 */
	public boolean addPoints(String email, int points);
	
}
