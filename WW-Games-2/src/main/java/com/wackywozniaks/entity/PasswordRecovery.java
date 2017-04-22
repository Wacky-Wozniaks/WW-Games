package com.wackywozniaks.entity;

/**
 * Information regarding a certain password recovery instance.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/21/2017
 */
public class PasswordRecovery {
	
	private Long id;
	private String email;
	private String hash;
	private Boolean active;
	
	/**
	 * Gets the id of the record set.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id of the record set
	 * 
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the email for the record set.
	 * 
	 * @return The email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email for the record set.
	 * 
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the verification hash for the record set
	 * 
	 * @return The hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Sets the verification hash for the record set
	 * 
	 * @param hash the hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Gets the activity of the hash
	 * 
	 * @return The activity
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * Sets the activity of the hash
	 * 
	 * @param active the activity
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

}
