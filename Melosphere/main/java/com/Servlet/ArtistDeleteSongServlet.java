package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import com.melosphere.Artist;


@WebServlet("/ArtistDeleteSong")
public class ArtistDeleteSongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Artist A = new Artist();
		A.setName((String) request.getSession(false).getAttribute("AName"));
		String encodedSName = request.getParameter("SName");
		String decodedSName = URLDecoder.decode(encodedSName, "UTF-8");

		if(A.deleteSong(decodedSName))
		{
			PrintWriter out = response.getWriter();
			out.println("Song Deleted Successfully! Reload"); 
		    out.close();
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.println("ERROR: Can not delete song"); 
		    out.close();
		}
	}


}
