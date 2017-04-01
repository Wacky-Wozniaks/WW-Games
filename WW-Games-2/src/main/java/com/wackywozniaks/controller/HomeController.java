package com.wackywozniaks.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.entity.User;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private UserDAOImpl userDAOImpl;
	private ApplicationContext context;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest request) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser((String) request.getSession().getAttribute("currentSessionUser"));
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        return "userLogged";
    }

}
