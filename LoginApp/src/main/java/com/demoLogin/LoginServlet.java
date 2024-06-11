package com.demoLogin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.demoLogin.service.LoginService;

import dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId, password;
		userId = request.getParameter("userId");
		password= request.getParameter("password");
		
		//check if userId and password are valid
		
		LoginService loginService = new LoginService();
		boolean result = loginService.authenticate(userId, password);
		if(result) {
			User user = loginService.getUserDetail(userId);
//			request.getSession().setAttribute("user", user);
			request.setAttribute("user",user);
			// now we are going to jsp file to get this value from the session
//			response.sendRedirect("success.jsp"); // this one will send another request to success.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp") ; // where we dispatch the control to
			dispatcher.forward(request, response); // success.jsp will get the request and response now
			// since we dispatch the control and not redirect to view, we can user request scope now
			return;
		}else {
			response.sendRedirect("login.jsp");
			return;
		}
		// since there are two different requests, we are not able to user request scope
		// application scope will also not be valid here because it will be used across different users while this user's info cannot be used for other user 
	    // the only option is session scope
		// to get the user from the Service class, we save the user in the session
	}

}
