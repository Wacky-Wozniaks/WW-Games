package com.wackywozniaks.servlets;

import java.io.IOException;

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
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(request.getParameter("pw").equals(request.getParameter("pw2"))) {
				UserBean user = new UserBean();
				user.setEmail(request.getParameter("un"));
				user.setPassword(request.getParameter("pw"));
				user.setFirstName(request.getParameter("fn"));
				user.setLastName(request.getParameter("ln"));
				user = UserDAO.newUser(user);
				if(user.isValid()) {
					HttpSession session = request.getSession(true); 
					session.setAttribute("currentSessionUser",user);
					response.sendRedirect("login");
				}
			}
			  
			response.sendRedirect("signup");
		} 
		catch (Throwable theException) {
			System.out.println(theException);
		}
			
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
