package com.Servlet;
import com.melosphere.*;
import com.Music.*;
import com.dao.ArtistDao;
import com.dao.SongDao;

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
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ListenerCreatePlaylist")
public class ListenerCreatePlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String LName = (String) request.getSession(false).getAttribute("LName");
        String playlistName = request.getParameter("PName");
        String playlistDescription = request.getParameter("PDescription");

 
        User L = new Listener();

        try {
            if(L.createPlaylist(LName,playlistName,playlistDescription))
            {
            	PrintWriter out = response.getWriter();
				out.println("Your Playlist Has Been Created"); 
			    out.close();
            }
            else
            {
            	PrintWriter out = response.getWriter();
				out.println("ERROR: There was an error with creating playlist: Playlist Name or Description might be too long"); 
			    out.close();
            	
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception here
        }
        
 
        // Redirect the user to a confirmation page
        response.sendRedirect("ListenerCreatePlaylist.jsp");
        
    }
}
