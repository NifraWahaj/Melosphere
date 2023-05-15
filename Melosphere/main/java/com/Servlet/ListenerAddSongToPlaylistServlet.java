package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import com.melosphere.Listener;

/**
 * Servlet implementation class ListenerAddSongToPlaylistServlet
 */
public class ListenerAddSongToPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String LName = (String) request.getSession(false).getAttribute("LName");
		String encodedAName = request.getParameter("AName");
		String decodedAName = URLDecoder.decode(encodedAName, "UTF-8");
		String encodedsongName = request.getParameter("songName");
		String decodedsongName = URLDecoder.decode(encodedsongName, "UTF-8");
		String encodedPName = request.getParameter("PName");
		String decodedPName = URLDecoder.decode(encodedPName, "UTF-8");
		
		Listener L = new Listener();
		L.setName(LName);
		try {
			if(L.addSongToPlaylist(decodedPName, decodedsongName, decodedAName))
			{
				PrintWriter out = response.getWriter();
				out.println("Add Song To Playlist"); 
			    out.close();
			}
			else
			{
				PrintWriter out = response.getWriter();
			    out.println("Song Not Found"); 
		        out.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
