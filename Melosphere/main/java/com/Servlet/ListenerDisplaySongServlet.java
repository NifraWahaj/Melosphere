package com.Servlet;
import com.melosphere.*;
import com.Music.*;
import com.dao.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ListenerDisplaySongs")
public class ListenerDisplaySongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	List<Song> songs = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    try {
	        songs = SongDao.getRandomSongs();
	    } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
			e.printStackTrace();
		}
        request.setAttribute("songs", songs);
        
        List<Artist> artists = null;
	    try {
	    	
	        artists = ArtistDao.getRandomArtists();
	    } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
			e.printStackTrace();
		}
        request.setAttribute("artists", artists);

		
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("ListenerHomePage.jsp");
		request.setAttribute("status","success"); 
		dispatcher.forward(request, response);
	}

}