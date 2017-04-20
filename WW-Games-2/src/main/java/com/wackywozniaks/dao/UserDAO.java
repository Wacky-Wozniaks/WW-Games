package com.wackywozniaks.dao;

import java.util.List;

import com.wackywozniaks.dto.SignupBean;
import com.wackywozniaks.entity.User;

/**
 * 
 * This is the user data access object which handles queries to the database, etc in regards to Users. 
 * 
 * @author WackyWozniaks Company
 * @version 04/05/2017
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
	
	public void changePassword(String email, String newPassword);
	
}
