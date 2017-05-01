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

/**
 * The home page for the user.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/05/2017
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	private UserDAOImpl userDAOImpl;
	private ApplicationContext context;
	
	/**
	 * Takes the GET request associated with "/home" and serves the user home page.
	 * 
	 * @param model The variable to be passed to the web page.
	 * @param request The HttpServletRequest for the page.
	 * @return The user home page.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("points", user.getPoints());
		model.addAttribute("level", (int) (user.getPoints() / 25) + 1);
		model.addAttribute("levelPoints", 25 - (user.getPoints() % 25));
		model.addAttribute("levelPercent", (int) ((user.getPoints() % 25) / .25));
        return "userLogged";
    }

}
