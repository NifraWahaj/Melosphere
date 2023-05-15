package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ArtistLogOut")
public class ArtistLogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   HttpSession session = request.getSession();
		   session.removeAttribute("AName");
		   session.invalidate();
		   
		    RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.html");
			request.setAttribute("status","success"); 
			dispatcher.forward(request, response);
		}



}
