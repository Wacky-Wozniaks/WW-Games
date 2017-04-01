package com.wackywozniaks.dto;

/**
 * Stores user information.
 * 
 * @author WackyWozniaks Company
 */
public class LoginBean {
	
	private String password;
	private String email;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	 
}
