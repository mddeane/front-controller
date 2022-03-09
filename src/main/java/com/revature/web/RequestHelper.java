package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {

	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper(); // import from jackson

	// this method will be called by the FrontController when a user requests the login resources
	// we will extract the parameters fro the forwarded request  
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 1. extract parameters from the request (username and password)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 2. call the confirmLogin method in the EmployeeService...
		Employee e = eserv.confirmLogin(username, password);
		
		// 3. ...
		if (e != null) {
			
			// grab the session, ad the user to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			
			// print out the user's data with the printwriter
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in</h3>");
			out.println(om.writeValueAsString(e));
		} else {
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
			response.setStatus(204); // 2xx level code means successful connection to the server - but no content to be returned
		}
		
	}

	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 1. set the content type of the response to be text/html
		response.setContentType("text/html");
		
		// 2. call the findAll() method on the server which calls the dao to return a list of all employees in the DB
		List<Employee> allEmployees = eserv.findAll();
		
		// 3. transform the list of java objects returned into a JSON string using Jackson Object Mapper
		String jsonString = om.writeValueAsString(allEmployees);
		
		// 4. Print that string to the screen using the printwriter
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
}
