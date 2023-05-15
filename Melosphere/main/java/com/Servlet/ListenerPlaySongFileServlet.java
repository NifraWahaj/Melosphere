package com.Servlet;
import com.melosphere.*;
import com.Music.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@SuppressWarnings("unused")
@WebServlet({/*"/PlaySongFile","/ListenerPlaySongDisplay",*/"/ListenerHomePage"})
public class ListenerPlaySongFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Song S = new Song();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the file ID from the request parameter
		S.setSName(request.getParameter("SongName"));
		InputStream inputStream = null;
		// Set the content type of the response to "audio/mpeg"
        response.setContentType("audio/mpeg");
            try {
            	inputStream=S.listenSong(true);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) 
				{
				    outputStream.write(buffer, 0, bytesRead);
				}
				    inputStream.close();
				    outputStream.flush();
				    
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				// Song not found
			    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        //}
	}

	

}
