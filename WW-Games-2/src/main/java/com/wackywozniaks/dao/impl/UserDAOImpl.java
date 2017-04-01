package com.wackywozniaks.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dto.LoginBean;
import com.wackywozniaks.entity.User;
import com.wackywozniaks.mapper.UserMapper;

public class UserDAOImpl implements UserDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void createUser(LoginBean user) {
		
	}

	public User getUser(Long id) {
		String sql = "select * from Users where id = ?";
		User user = null;
		try {
			user = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}
	
	public User getUser(String email) {
		String sql = "select * from Users where email = ?";
		User user = null;
		try {
			user = jdbcTemplateObject.queryForObject(sql, new Object[]{email}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	public List<User> listUsers() {
		String sql = "select * from Users";
		List<User> users = jdbcTemplateObject.query(sql, new UserMapper());
		return users;
	}

}
