package com.wackywozniaks.dto;

/**
 * Response for Change Password
 * 
 * @author WackyWozniaks Company
 * @version 05/17/2017
 */
public class ChangePasswordResponseBean {
	
	private boolean isOkay;
	private String message;
	
	/**
	 * @return the isOkay
	 */
	public boolean isOkay() {
		return isOkay;
	}
	
	/**
	 * @param isOkay the isOkay to set
	 */
	public void setOkay(boolean isOkay) {
		this.isOkay = isOkay;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
