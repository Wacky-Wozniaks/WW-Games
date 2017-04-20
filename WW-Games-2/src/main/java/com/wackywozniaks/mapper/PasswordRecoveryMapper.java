package com.wackywozniaks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wackywozniaks.entity.PasswordRecovery;

/**
 * Takes the raw data from a query for a Password Recovery instance and maps it to a password recovery object.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/19/2017
 */
public class PasswordRecoveryMapper implements RowMapper<PasswordRecovery> {
	
	public PasswordRecovery mapRow(ResultSet rs, int fowNum) throws SQLException {
		if(rs.getString("email") == null) {
			return null;
		}
		
		PasswordRecovery passwordRecovery = new PasswordRecovery();
		passwordRecovery.setId(rs.getLong("id"));
		passwordRecovery.setEmail(rs.getString("email"));
		passwordRecovery.setHash(rs.getString("hash"));
		passwordRecovery.setActive(rs.getBoolean("active"));
		
		return passwordRecovery;
	}
	
}
