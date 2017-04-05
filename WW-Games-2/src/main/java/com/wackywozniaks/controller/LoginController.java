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

import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.dto.LoginBean;
import com.wackywozniaks.dto.SignupBean;
import com.wackywozniaks.dto.VerifyBean;
import com.wackywozniaks.entity.User;

@Controller
public class LoginController /*extends WWController*/ {
	
	//@Autowired
	private UserDAO userDAOImpl /*= (UserDAO) (new ClassPathXmlApplicationContext("Beans.xml")).getBean("userDAOImpl")*/;
	
	//@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String initLogin(Model model) {
        model.addAttribute("msg", "Please Enter Your Login Details");
        return "login";
    }
	
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
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String initSignup(Model model) {
		return "signup";
	}
	
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
	
	private static boolean meetsRequirements(String password) {
		if(password.length() < 8) return false; //password must be at least 8 characters
		return true;
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String initVerify(Model model) {
		return "verify";
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String verify(Model model, VerifyBean verifyBean, HttpServletRequest request) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(verifyBean.getEmail());
		
		if(verifyBean.getHash() == null) {
			model.addAttribute("error", "Invalid Verification Link");
	        return "verify";
		}
		else if(!BCrypt.checkpw(verifyBean.getEmail(), verifyBean.getHash()))  {
			model.addAttribute("error", verifyBean.getHash());
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
	
}
