package com.wackywozniaks.dto;

/**
 * The data transfer object for changing a password.
 * 
 * @author WackyWozniaks Company
 * @version 04/21/2017
 */
public class ChangePasswordBean {
	
	private String hash;
	private String password1;
	private String password2;
	
	/**
	 * Gets the hash
	 * 
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	
	/**
	 * Sets the hash
	 * 
	 * @param hash The hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Gets the first entered password
	 * 
	 * @return The first entered password
	 */
	public String getPassword1() {
		return password1;
	}

	/**
	 * Sets the first entered password
	 * 
	 * @param password1 The first entered password
	 */
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	/**
	 * Gets the second entered password
	 * 
	 * @return The second entered password
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * Sets the second entered password
	 * 
	 * @param password2 The second entered password
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
}
