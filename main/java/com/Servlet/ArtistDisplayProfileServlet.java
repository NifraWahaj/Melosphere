package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.Music.Song;
import com.melosphere.Artist;
import com.melosphere.Listener;
import com.melosphere.User;

/**
 * Servlet implementation class ArtistDisplayProfileServlet
 */
@WebServlet("/ArtistProfileDummy")
public class ArtistDisplayProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User A = new Artist();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String S=(String) request.getSession(false).getAttribute("AName");

		A.setName(S);
		
		A.getProfile();
		
		request.setAttribute("Name", A.getName());
		request.setAttribute("Email", A.getEmail());
		request.setAttribute("Password", A.getPassword());
		

		
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("ArtistProfile.jsp");
		request.setAttribute("status","success"); 
		dispatcher.forward(request, response);
	}

}
