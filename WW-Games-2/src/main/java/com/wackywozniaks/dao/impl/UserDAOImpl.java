package com.wackywozniaks.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dto.LoginBean;
import com.wackywozniaks.entity.User;
import com.wackywozniaks.mapper.UserMapper;

@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {
	
	//private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	//@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createUser(LoginBean user) {
		
	}

	public User getUser(Long id) {
		String sql = "select * from Users where id = ?";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
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
			user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	public List<User> listUsers() {
		String sql = "select * from Users";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users;
	}

}
