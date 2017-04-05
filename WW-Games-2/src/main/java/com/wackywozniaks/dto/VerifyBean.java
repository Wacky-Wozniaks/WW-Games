package com.wackywozniaks.dto;

/**
 * Stores Verification information.
 * 
 * @author WackyWozniaks Company
 */
public class VerifyBean {
	
	private String password;
	private String email;
	private String hash;
	
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
	
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	
	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	 
}
