package com.wackywozniaks.dao;

import java.util.List;

import com.wackywozniaks.dto.LoginBean;
import com.wackywozniaks.entity.User;

/**
 * 
 * This is the user data access object which handles queries to the database, etc. 
 * 
 * @author WackyWozniaks Company
 * @version 03/31/2017
 */
public interface UserDAO {
	
	//public void setDataSource(DataSource dataSource);
	
	public void createUser(LoginBean user);
	
	public User getUser(Long id);
	
	public User getUser(String email);
	
	public List<User> listUsers();
	
}
