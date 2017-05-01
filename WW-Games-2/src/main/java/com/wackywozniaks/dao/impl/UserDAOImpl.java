package com.wackywozniaks.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dto.SignupBean;
import com.wackywozniaks.email.SendEmail;
import com.wackywozniaks.entity.User;
import com.wackywozniaks.mapper.UserMapper;

/**
 * The Implementation of the UserDAO interface.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/30/2017
 */
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
	public void createUser(SignupBean signupBean) {
		String sql = "insert into users values(default, ?, ?, ?, ?, false, default)";
		try {
			jdbcTemplate.update(sql, new Object[]{signupBean.getEmail(), BCrypt.hashpw(signupBean.getPassword(), BCrypt.gensalt()), signupBean.getFirstName(), signupBean.getLastName()});
			SendEmail.sendVerificationEmail(signupBean);
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
		}
		
	}
	
	@Override
	public User getUser(Long id) {
		String sql = "select * from Users where id = ?";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
		return user;
	}
	
	@Override
	public User getUser(String email) {
		String sql = "select * from Users where email = ?";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
		return user;
	}
	
	@Override
	public List<User> listUsers() {
		String sql = "select * from Users";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users;
	}
	
	@Override
	public void verifyUser(String email) {
		String sql = "update users set verified = true where email = ?";
		try {
			jdbcTemplate.update(sql, new Object[]{email});
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
		}
		
	}

	@Override
	public void changePassword(String email, String newPassword) {
		String sql = "update users set password = ? where email = ?";
		try {
			jdbcTemplate.update(sql, new Object[]{BCrypt.hashpw(newPassword, BCrypt.gensalt()), email});
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
		}
	}

	@Override
	public Integer getPoints(String email) {
		String sql = "select points from users where email = ?";
		try {
			Integer num = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
			return num;
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public boolean addPoints(String email, int points) {
		Integer original = getPoints(email);
		if(original == null) {
			return false;
		}
		int newPoints = original + points;
		
		String sql = "update users set points = ? where email = ?";
		try {
			jdbcTemplate.update(sql, new Object[]{newPoints, email});
			return true;
		}
		catch(EmptyResultDataAccessException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return false;
		}
	}

}
