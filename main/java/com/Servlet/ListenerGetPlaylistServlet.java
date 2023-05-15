package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import com.melosphere.*;
@WebServlet("/ListenerGetPlaylistDummy")
public class ListenerGetPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User L = new Listener();	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	       // L.createPlaylist(LEGACY_DO_HEAD).setAName((String) request.getSession(false).getAttribute("lName"));
		    List<Playlist> Playlist2  = L.displayPlaylists((String) request.getSession(false).getAttribute("LName"));
			request.setAttribute("ListenerPlaylists", Playlist2);
			

	        RequestDispatcher dispatcher = null;
	        dispatcher = request.getRequestDispatcher("ListenerDisplayPlaylist.jsp");
			request.setAttribute("status","success"); 
			dispatcher.forward(request, response);
	}
		

}
