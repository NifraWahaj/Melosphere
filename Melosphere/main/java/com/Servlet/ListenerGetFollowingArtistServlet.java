package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.melosphere.Artist;
import com.melosphere.Listener;

@WebServlet("/ListenerGetFollowingArtistDummy")
public class ListenerGetFollowingArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Listener L = new Listener();
		L.setName((String) request.getSession(false).getAttribute("LName"));
		try {
			List<Artist> FollowingArtist = L.getFollowingArtists();
			request.setAttribute("FollowingArtist", FollowingArtist);
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher("ListenerDisplayFollowingArtist.jsp");
			request.setAttribute("status","success"); 
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
