package com.Servlet;
import com.melosphere.*;
import com.Music.*;
import com.dao.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
@WebServlet("/ArtistTopStreamsDisplay")
public class ArtistTopStreamsDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User A = new Artist();
		List<Song> songs = null;
		HttpSession session = request.getSession();
	    A.setName((String) session.getAttribute("AName"));
	    
	    try {
	        songs = A.getTopStreamsforArtist();
	    } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
			e.printStackTrace();
		}
        request.setAttribute("songs", songs);
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("ArtistHomePage.jsp");
		request.setAttribute("status","success"); 
		dispatcher.forward(request, response);
	}
}
