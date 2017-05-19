package com.wackywozniaks.dto;

/**
 * Transfers data for changing a password from the profile page
 * 
 * @author WackyWozniaks Company
 * @version 05/17/2017
 */
public class ProfileChangePasswordBean {
	
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;
	
	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	
	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword1
	 */
	public String getNewPassword1() {
		return newPassword1;
	}

	/**
	 * @param newPassword1 the newPassword1 to set
	 */
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	/**
	 * @return the newPassword2
	 */
	public String getNewPassword2() {
		return newPassword2;
	}

	/**
	 * @param newPassword2 the newPassword2 to set
	 */
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

}
