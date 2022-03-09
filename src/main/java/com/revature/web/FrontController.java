package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This method will be responsible for determining what resource 
 * the client is requesting
 * It will take in the URL and capture only the end part
 * after "/FrontController/"
 * 
 * Once it captures the destination it will call another class called RequestHelper
 * which will supply the right functionality
 */
		

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. Save the URI and rewrite it
		final String URI = request.getRequestURI().replace("/FrontController/", ""); // leaves nothing but end part
		
		// 2. set up a switch case statement in which we call the appropriate
		// functionality based on the URI returned
		switch(URI) {
		
		case "login":
			// call some processLogin() functionality and pass the requset and response objects
			RequestHelper.processLogin(request,  response);
			break;
		case "employees":
			break;
		
		}  
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
