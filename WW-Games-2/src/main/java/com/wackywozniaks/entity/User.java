package com.wackywozniaks.entity;

/**
 * Information regarding a certain user.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/05/2017
 */
public class User {
	
	private Long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Boolean verified;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
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
	
	public Boolean isVerified() {
		return verified;
	}
	
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

}
