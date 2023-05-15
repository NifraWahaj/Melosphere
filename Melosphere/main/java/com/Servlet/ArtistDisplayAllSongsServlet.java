package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import com.Music.Song;
import com.melosphere.Artist;
import com.melosphere.User;

/**
 * Servlet implementation class ArtistDisplayAllSongsServlet
 */
@WebServlet("/ArtistDisplayAllSongsDummy")
public class ArtistDisplayAllSongsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	    User A = new Artist(); 
		A.setName((String) request.getSession(false).getAttribute("AName"));
		
		List<Song> AllSongs;
		try {
			AllSongs = A.getSongsByArtist();
			request.setAttribute("AllSongs", AllSongs);
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher("ArtistViewUploadedSong.jsp");
			request.setAttribute("status","success"); 
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		


		}


}
