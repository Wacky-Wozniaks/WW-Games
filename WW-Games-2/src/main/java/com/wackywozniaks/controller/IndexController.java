package com.wackywozniaks.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Takes the base (undirected) site and directs it.
 * 
 * @author Wacky Wozniaks Company
 * @version 04/05/2017
 */
@Controller
public class IndexController {

	/**
	 * Takes the GET request associated with "/" and redirects the user.
	 * 
	 * @param response The response to be sent to the page.
	 * @return The login page.
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String test(HttpServletResponse response) throws IOException {
		return "redirect:login";
	}
}
