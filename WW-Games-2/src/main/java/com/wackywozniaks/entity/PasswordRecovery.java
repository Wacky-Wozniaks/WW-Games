package com.wackywozniaks.entity;

/**
 * Information regarding a certain password recovery instance.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/19/2017
 */
public class PasswordRecovery {
	
	private Long id;
	private String email;
	private String hash;
	private Boolean active;
	
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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
