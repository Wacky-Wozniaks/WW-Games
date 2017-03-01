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
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/VerifyServlet")
public class VerifyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyServlet()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String url = request.getParameter("hash");
		
		UserBean bean = new UserBean();
		bean.setEmail(request.getParameter("em"));
		bean.setPassword(request.getParameter("pw"));
		
		bean = UserDAO.verify(bean, url);
		if(bean.isValid())
		{
			HttpSession session = request.getSession(true); 
			session.setAttribute("currentSessionUser",bean);
			response.sendRedirect("userLogged"); //logged-in page
		}
	}

}
