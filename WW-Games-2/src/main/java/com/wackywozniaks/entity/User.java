package com.wackywozniaks.entity;

/**
 * Information regarding a certain user.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/30/2017
 */
public class User {
	
	private Long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Boolean verified;
	private Integer points;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the verified
	 */
	public Boolean isVerified() {
		return verified;
	}

	/**
	 * @param verified the verified to set
	 */
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

}
