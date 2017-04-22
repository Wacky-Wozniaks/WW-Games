package com.wackywozniaks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wackywozniaks.dao.PasswordRecoveryDAO;
import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.dto.ChangePasswordBean;
import com.wackywozniaks.dto.ForgotPasswordBean;
import com.wackywozniaks.dto.LoginBean;
import com.wackywozniaks.dto.SignupBean;
import com.wackywozniaks.dto.VerifyBean;
import com.wackywozniaks.email.SendEmail;
import com.wackywozniaks.entity.User;

/**
 * This class is the controller for logins, signups, and email verifications.
 * 
 * @author Wacky Wozniaks
 * @version 04/21/2017
 */
@Controller
public class LoginController /*extends WWController*/ {
	
	//@Autowired
	private UserDAO userDAOImpl /*= (UserDAO) (new ClassPathXmlApplicationContext("Beans.xml")).getBean("userDAOImpl")*/;
	
	private PasswordRecoveryDAO passwordRecoveryDAOImpl;
	
	//@Autowired
	private ApplicationContext context;
	
	/**
	 * This page takes the GET request associated with "/login" and serves up the login page.
	 * 
	 * @param model Variables to be passed to the web page.
	 * @return Login Page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String initLogin(Model model) {
        model.addAttribute("msg", "Please Enter Your Login Details");
        return "login";
    }
	
	/**
	 * Takes the login page POST request (the login form).
	 * 
	 * @param model Variables to be passed to the web page.
	 * @param loginBean The values from the form.
	 * @param request The HttpServletRequest from the page.
	 * @return The appropriate redirect.
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("loginBean") LoginBean loginBean, HttpServletRequest request) {
		if(loginBean == null) {
			model.addAttribute("error", "Invalid Username and/or Password");
	        return "login";
		}
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(loginBean.getEmail());
		if(user == null) {
			model.addAttribute("error", "Invalid Username and/or Password");
	        return "login";
		}
		else if(BCrypt.checkpw(loginBean.getPassword(), user.getPassword())) {
			if(user.isVerified()) {
				HttpSession session = request.getSession(true); 
				session.setAttribute("currentSessionUser", user.getEmail());
		        return "redirect:home";
			}
			else {
				model.addAttribute("error", "Your Email is Not Verified");
		        return "login";
			}
		}
		else {
			model.addAttribute("error", "Invalid Username and/or Password");
	        return "login";
		}
	}
	
	/**
	 * This page takes the GET request associated with "/signup" and serves up the signup page.
	 * 
	 * @param model Variables to be passed to the web page.
	 * @return Signup Page
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String initSignup(Model model) {
		return "signup";
	}
	
	/**
	 * Takes the signup page POST request (the signup form).
	 * 
	 * @param model Variables to be passed to the web page.
	 * @param signupBean The values from the form.
	 * @return The appropriate redirect.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Model model, SignupBean signupBean) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		
		User user = userDAOImpl.getUser(signupBean.getEmail());
		if(user != null) {
			model.addAttribute("error", "This Email is Already Attached to an Account");
			return "signup";
		}
		else if(!signupBean.getEmail().endsWith("@mxschool.edu")) {
			model.addAttribute("error", "Please Sign Up With You Middlesex Email");
			return "signup";
		}
		else if(!signupBean.getPassword().equals(signupBean.getPassword2())) {
			model.addAttribute("error", "Passwords Must Match");
			return "signup";
		}
		else if(!meetsRequirements(signupBean.getPassword())) {
			model.addAttribute("error", "Passwords Must Be At Least 8 Characters");
			return "signup";
		}
		else {
			userDAOImpl.createUser(signupBean);
			model.addAttribute("msg", "Please Check & Verify Your Email");
	        return "redirect:login";
		}
	}
	
	/**
	 * Takes a password and sees if it meets the requirements for a password.
	 * 
	 * @param password The password to be checked,
	 * @return Whether the password meets requirements
	 */
	private static boolean meetsRequirements(String password) {
		if(password.length() < 8) return false; //password must be at least 8 characters
		return true;
	}
	
	/**
	 * This page takes the GET request associated with "/verify" and serves up the verify page.
	 * 
	 * @param model Variables to be passed to the web page.
	 * @return Verify Page
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String initVerify(Model model) {
		return "verify";
	}
	
	/**
	 * Takes the verify page POST request (the verify form).
	 * 
	 * @param model Variables to be passed to the web page.
	 * @param verifyBean The values from the form.
	 * @param request The HttpServletRequest from the page.
	 * @return The appropriate redirect.
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String verify(Model model, VerifyBean verifyBean, HttpServletRequest request) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(verifyBean.getEmail());
		
		System.out.println(verifyBean.getEmail());
		System.out.println(user.getEmail());
		
		if(verifyBean.getHash() == null) {
			model.addAttribute("error", "Invalid Verification Link");
	        return "verify";
		}
		else if(!BCrypt.checkpw(verifyBean.getEmail(), verifyBean.getHash()))  {
			model.addAttribute("error", "Incorrect Verification Link");
	        return "verify";
		}
		else if(user == null) {
			model.addAttribute("error", "2");
	        return "verify";
		}
		else if(!BCrypt.checkpw(verifyBean.getPassword(), user.getPassword())) {
			model.addAttribute("error", "3");
	        return "verify";
		}
		else {
			userDAOImpl.verifyUser(user.getEmail());
			HttpSession session = request.getSession(true); 
			session.setAttribute("currentSessionUser", user.getEmail());
	        return "redirect:home";
		}
	}
	
	/**
	 * Takes the request to logout, invalidates the session, and then redirects to the login page.
	 * 
	 * @param model Variables to be passed to the web page.
	 * @param request
	 * @return A redirect to the login page.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true); 
		session.invalidate();
        return "redirect:login";
	}
	
	/**
	 * This page takes the GET request associated with "/forgotPassword" and serves up the forgotPassword page.
	 * 
	 * @param model The variables to be passed to the page
	 * @return The forgot password page
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String initForgotPassword(Model model) {
		return "forgotPassword";
	}
	
	/**
	 * Takes the request to reset the password, checks whether it is a valid user, then sends a reset email.
	 * 
	 * @param model The variables to be passed to the page
	 * @param forgotPasswordBean The contents of the form
	 * @return The forgot password page with a redirect.
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPassword(Model model, @ModelAttribute("forgotPasswordBean") ForgotPasswordBean forgotPasswordBean) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		passwordRecoveryDAOImpl = (PasswordRecoveryDAO) context.getBean("passwordRecoveryDAOImpl");
		
		User user = userDAOImpl.getUser(forgotPasswordBean.getEmail());
		if(user != null) {
			String hash = BCrypt.hashpw(forgotPasswordBean.getEmail() + ((int) Math.random() * 100000000), BCrypt.gensalt()); //something random
			passwordRecoveryDAOImpl.addPasswordRecovery(forgotPasswordBean.getEmail(), hash);
			SendEmail.sendRecoveryEmail(forgotPasswordBean, user, hash);
		}
		
		model.addAttribute("msg", "If there is an account associated with that email, an email was sent.");
		return "forgotPassword";
	}
	
	/**
	 * This page takes the GET request associated with "/changePassword" and serves up the changePassword page.
	 * 
	 * @param model
	 * @return The changePassword page.
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String initChangePassword(Model model) {
		return "changePassword";
	}
	
	/**
	 * Takes the request to change the password, and if all conditions are met, changes it and invalidates the reset link.
	 * 
	 * @param model The variables to be passed to the page
	 * @param changePasswordBean The information in the form
	 * @param request
	 * @return The appropriate web page
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, ChangePasswordBean changePasswordBean, HttpServletRequest request) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		passwordRecoveryDAOImpl = (PasswordRecoveryDAO) context.getBean("passwordRecoveryDAOImpl");
		
		if(changePasswordBean.getHash() == null || changePasswordBean.getHash().equals("")) {
			model.addAttribute("error", "Password Change Failed");
			return "changePassword";
		}
		else if(!changePasswordBean.getPassword1().equals(changePasswordBean.getPassword2())) {
			model.addAttribute("error", "Passwords Do Not Match");
			return "changePassword";
		}
		else if(!passwordRecoveryDAOImpl.isActiveHash(changePasswordBean.getHash())) {
			model.addAttribute("error", changePasswordBean.getHash());
			return "changePassword";
		}
		else if(!meetsRequirements(changePasswordBean.getPassword1())) {
			model.addAttribute("error", "Passwords Must Be At Least 8 Characters");
			return "changePassword";
		}
		else {
			String email = passwordRecoveryDAOImpl.getEmail(changePasswordBean.getHash());
			userDAOImpl.changePassword(email, changePasswordBean.getPassword1());
			HttpSession session = request.getSession(true); 
			session.setAttribute("currentSessionUser", email);
	        return "redirect:home";
		}
	}
	
}
