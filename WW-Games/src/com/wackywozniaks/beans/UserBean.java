package com.wackywozniaks.beans;

/**
 * 
 * @author WackyWozniaks Company
 *
 */
public class UserBean {
	
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private boolean verified;
	public boolean valid;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isVerified()
	{
		return verified;
	}
	
	public void setVerified(boolean newVerified)
	{
		verified = newVerified;
	}
	
	public boolean isValid() { 
		return valid;
	}
	
	public void setValid(boolean newValid) {
		valid = newValid;
	} 
	 
}
