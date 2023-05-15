package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.melosphere.*;

public class ListenerDisplayProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User L = new Listener();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		L.setName((String) request.getSession(false).getAttribute("LName"));
		L.getProfile();
		
		request.setAttribute("Name", L.getName());
		request.setAttribute("Email", L.getEmail());
		request.setAttribute("Password", L.getPassword());
		

		
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("ListenerProfile.jsp");
		request.setAttribute("status","success"); 
		dispatcher.forward(request, response);
	}


}
