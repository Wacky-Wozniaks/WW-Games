package com.wackywozniaks.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wackywozniaks.beans.UserBean;
import com.wackywozniaks.daos.UserDAO;

/**
 * Servlet implementation class LoginServlet
 * 
 * This code was based off http://met.guc.edu.eg/OnlineTutorials/JSP%20-%20Servlets/Full%20Login%20Example.aspx but was
 * heavily modified by the Wacky Wozniaks Company.
 * 
 * @author WackyWozniaks Company
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Takes the login request of the server and handles it.g
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserBean user = new UserBean();
			user.setEmail(request.getParameter("un"));
			user.setPassword(request.getParameter("pw"));
			user = UserDAO.login(user);
			
			if (user.isValid()) {
				if(user.isVerified())
				{
					HttpSession session = request.getSession(true); 
					session.setAttribute("currentSessionUser", user);
					response.sendRedirect("userLogged"); //logged-in page
				}
				else response.sendRedirect("verifyEmail");
			} 
			  
			response.sendRedirect("login");
		} 
		catch (Throwable e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			System.out.println(e);
		}
			
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
