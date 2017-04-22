package com.wackywozniaks.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wackywozniaks.dao.PasswordRecoveryDAO;
import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.entity.PasswordRecovery;
import com.wackywozniaks.mapper.PasswordRecoveryMapper;

/**
 * The Implementation of the PasswordRecoveryDAO interface.
 * 
 * @author WackyWozniaks Company
 * @version 04/21/2017
 */
public class PasswordRecoveryDAOImpl implements PasswordRecoveryDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addPasswordRecovery(String email, String hash) {
		String sql = "insert into passwordRecovery values(default, ?, ?, true)";
		try {
			jdbcTemplate.update(sql, new Object[]{email, hash});
			return true;
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean verifyPasswordRecovery(String hash) {
		String sql1 = "select * from passwordRecovery where hash = ?";
		
		PasswordRecovery pr = null;
		try {
			pr = jdbcTemplate.queryForObject(sql1, new Object[]{hash}, new PasswordRecoveryMapper());
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return false;
		}
		
		if(pr == null) {
			return false;
		}
		
		String sql2 = "update passwordRecovery set active = false where hash = ?";
		try {
			jdbcTemplate.update(sql2, new Object[]{hash});
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return false;
		}
		
		return true;
	}

	@Override
	public String getEmail(String hash) {
		String sql = "select * from passwordRecovery where hash = ?";
		PasswordRecovery pr = null;
		
		try {
			pr = jdbcTemplate.queryForObject(sql, new Object[]{hash}, new PasswordRecoveryMapper());
			return pr.getEmail();
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public boolean isActiveHash(String hash) {
		String sql = "select * from passwordRecovery where hash = ?";
		PasswordRecovery pr = null;
		
		try {
			pr = jdbcTemplate.queryForObject(sql, new Object[]{hash}, new PasswordRecoveryMapper());
			if(pr.isActive() != null && pr.isActive().equals(true)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return false;
		}
	}

}
