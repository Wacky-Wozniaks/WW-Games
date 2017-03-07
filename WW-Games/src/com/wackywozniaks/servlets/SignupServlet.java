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
 * Servlet implementation class SignupServlet
 * 
 * @author WackyWozniaks Company
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Sends the user information given at signup and sends it to UserDAO to send the information to the database.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean redirected = false;
			if(request.getParameter("pw").equals(request.getParameter("pw2"))) {
				UserBean user = new UserBean();
				user.setEmail(request.getParameter("un"));
				user.setPassword(request.getParameter("pw"));
				user.setFirstName(request.getParameter("fn"));
				user.setLastName(request.getParameter("ln"));
				user = UserDAO.newUser(user);
				if(user.isValid()) {
					redirected = true;
					//HttpSession session = request.getSession(true); 
					//session.setAttribute("currentSessionUser",user);
					response.sendRedirect("verifyEmail");
				}
			}
			if(!redirected) response.sendRedirect("signup");
		} 
		catch (Throwable e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			System.out.println(e);
		}
			
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
