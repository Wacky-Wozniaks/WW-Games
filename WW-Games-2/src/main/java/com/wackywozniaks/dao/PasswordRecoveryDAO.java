package com.wackywozniaks.dao;

/**
 * This is the user data access object which handles queries to the database, etc in regards to Password Recovery. 
 * 
 * @author WackyWozniaks Company
 * @version 04/21/2017
 */
public interface PasswordRecoveryDAO {
	
	/**
	 * Takes an email and a hash and adds a record to the database so that that password can be reset (for forgotten passwords).
	 * 
	 * @param email The email of the user who wants to change passwords.
	 * @param hash The hash sent out in the email for verification.
	 * @return Whether it was successful.
	 */
	public boolean addPasswordRecovery(String email, String hash);
	
	/**
	 * Sets the activity of a password recovery link to false in the database.
	 * 
	 * @param hash The hash associated with the link.
	 * @return Whether it was successful.
	 */
	public boolean verifyPasswordRecovery(String hash);
	
	/**
	 * Gets the email associated with the given hash.
	 * 
	 * @param hash The hash associated with the desired email.
	 * @return The email associated with the hash.
	 */
	public String getEmail(String hash);
	
	/**
	 * Finds whether a given hash is associated with an active link.
	 * 
	 * @param hash The hash used to find the record set.
	 * @return Whether the link is active.
	 */
	public boolean isActiveHash(String hash);

}
