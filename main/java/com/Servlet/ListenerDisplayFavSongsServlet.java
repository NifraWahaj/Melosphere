package com.Servlet;
import com.melosphere.*;
import com.Music.Song;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.melosphere.Listener;
import java.net.URLDecoder;
import java.net.URLEncoder;
@WebServlet("/ListenerFavDummy")
public class ListenerDisplayFavSongsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Listener L = new Listener(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		L.setName((String) request.getSession(false).getAttribute("LName"));
		RequestDispatcher dispatcher = null;
		try {
			List<Song> Favs = L.DisplayFavSongs();
			request.setAttribute("favsongs", Favs);
			dispatcher = request.getRequestDispatcher("ListenerFavouriteSongs.jsp");
			request.setAttribute("status","success"); 
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



}
