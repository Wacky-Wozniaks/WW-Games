package com.wackywozniaks.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.dto.ChangePasswordResponseBean;
import com.wackywozniaks.dto.ProfileChangePasswordBean;
import com.wackywozniaks.entity.User;

/**
 * Controls all the changes to the profile
 * 
 * @author WackyWozniaks Company
 * @version 05/17/2017
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
	
	private UserDAO userDAOImpl;
	
	private ApplicationContext context;
	
	/**
	 * This page takes the GET request associated with "/profile" and serves up the profile page.
	 * 
	 * @param model Variables to be passed to the web page.
	 * @return Profile Page
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String initLogin(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("email", user.getEmail());
        return "profile";
    }
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ChangePasswordResponseBean changePassword(@RequestBody ProfileChangePasswordBean bean, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		ChangePasswordResponseBean response = new ChangePasswordResponseBean();
		if(!BCrypt.checkpw(bean.getOldPassword(), user.getPassword())) {
			response.setMessage("Password Incorrect");
			response.setOkay(false);
		}
		else if(!bean.getNewPassword1().equals(bean.getNewPassword2())) {
			response.setMessage("Passwords Do Not Match");
			response.setOkay(false);
		}
		else if(!LoginController.meetsRequirements(bean.getNewPassword1())) {
			response.setMessage("Password Must Be At Least 8 Characters");
			response.setOkay(false);
		}
		else {
			userDAOImpl.changePassword(currSessionUser, bean.getNewPassword1());
			response.setMessage("Password Changed");
			response.setOkay(true);
		}
		return response;
    }

}
