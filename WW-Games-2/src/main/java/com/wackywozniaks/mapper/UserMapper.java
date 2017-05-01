package com.wackywozniaks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wackywozniaks.entity.User;

/**
 * Takes the raw data from a query for a user and maps it to a user object.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/30/2017
 */
public class UserMapper implements RowMapper<User> {
	
	public User mapRow(ResultSet rs, int fowNum) throws SQLException {
		if(rs.getString("email") == null) {
			return null;
		}
		
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setVerified(rs.getBoolean("verified"));
		user.setPoints(rs.getInt("points"));
		
		return user;
	}
	
}
