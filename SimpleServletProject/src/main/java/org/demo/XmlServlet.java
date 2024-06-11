package org.demo;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(description = "a simple servlet", urlPatterns= {"/XmlServletpath"},initParams = @WebInitParam(name="defaultUser",value="John Doe"))
public class XmlServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("XML servlet called");
		//1 - set the  type of the response
		response.setContentType("text/html");
		// 2 - getWriter 
		PrintWriter out = response.getWriter();
		
		// 3 -if request has this parameter then get it and pass to out variable
		String userName = request.getParameter("userName");
		//use HttpSession to remember user data when program is executed
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		if(userName != "" && userName != null) {
			session.setAttribute("savedUserName", userName);
		//user ServletContext to remember user data across all sessions and servlets
			context.setAttribute("saveUserName",userName);
		}
		// 4- use the writer to write out to the view
		out.println("<h3> Hello " + userName + "</h3>");
		//since session is an Object, we can downcast it to any type
		out.println("Session parameter has username as"+ (String)session.getAttribute("savedUserName"));
		out.println("Context parameter has username as"+ (String)context.getAttribute("savedUserName"));
		// get the initParams value Jone Doe
		out.println("Init parameter has default username as"+ this.getServletConfig().getInitParameter("defaultUser"));
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("XML servlet called");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// if request has this parameter then get it and pass to out variable
		String userName = request.getParameter("userName");
		String name = request.getParameter("name");
		out.println("<h3> Hello from POST " + userName + "</h3>" +" your name is: " + name);
		//occupation parameter
		String occup = request.getParameter("occupation");
		out.println("You are a " + occup);
	}

}
