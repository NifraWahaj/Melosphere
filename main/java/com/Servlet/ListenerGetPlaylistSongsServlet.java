package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.melosphere.Listener;
import com.melosphere.Playlist;
import com.melosphere.User;


@WebServlet("/ListenerShowPlaylistSongsDummy")
public class ListenerGetPlaylistSongsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String LName = (String) request.getSession(false).getAttribute("LName");
		String playlistName = request.getParameter("PName");
        String playlistDescription = request.getParameter("PDes");
        
        User L = new Listener();
        L.setName(LName);
        Playlist P = L.GetPlaylistSongs(playlistName,playlistDescription);
       
        request.setAttribute("playlist", P);
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("PlaylistPage.jsp");
		request.setAttribute("status","success"); 
		dispatcher.forward(request, response);
		
		
	}



}
