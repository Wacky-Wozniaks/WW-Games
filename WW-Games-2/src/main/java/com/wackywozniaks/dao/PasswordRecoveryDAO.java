package com.wackywozniaks.dao;

public interface PasswordRecoveryDAO {
	
	public boolean addPasswordRecovery(String email, String hash);
	
	public boolean verifyPasswordRecovery(String hash);
	
	public String getEmail(String hash);
	
	public boolean isActiveHash(String hash);

}
