package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import com.Music.Song;
import com.melosphere.*;
@WebServlet("/ListenerArtistViewDummy")
public class ListenerArtistProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encodedAName = request.getParameter("AName");
		String decodedAName = URLDecoder.decode(encodedAName, "UTF-8");
		String encodedABio = request.getParameter("ABio");
		String decodedABio = URLDecoder.decode(encodedABio, "UTF-8");
		RequestDispatcher dispatcher = null;
	    User A = new Artist(); 
	    A.setName(decodedAName);
	    if(decodedABio.equals("Dummy"))
	    {
	    	try {
	    		decodedABio = A.getBioFromDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    try {
			List<Song> TopStreamed = A.getTopStreamsforArtist();
			List<Song> AllSongs = A.getSongsByArtist();
			request.setAttribute("AName", decodedAName);
			request.setAttribute("ABio", decodedABio);
			request.setAttribute("TopStreamed", TopStreamed);
			request.setAttribute("AllSongs", AllSongs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    
	    boolean isFollowing = false;
	    Listener L = new Listener();
	    L.setName((String) request.getSession(false).getAttribute("LName"));
		try {
			if(L.checkfollowArtist(A.getName()))
			{
				isFollowing = true;
			}
			else
			{
				isFollowing = false;
			}
		request.setAttribute("isFollowing", isFollowing);
		dispatcher = request.getRequestDispatcher("ListenerArtistViewProfile.jsp");
		request.setAttribute("status","success"); 
		dispatcher.forward(request, response);
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
