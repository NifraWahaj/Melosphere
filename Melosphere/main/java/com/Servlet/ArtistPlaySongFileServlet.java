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


@WebServlet("/ArtistViewUploadedSong")
public class ArtistPlaySongFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Song S = new Song();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		S.setSName(request.getParameter("SongName"));
		InputStream inputStream = null;
        response.setContentType("audio/mpeg");
            try {
            	inputStream=S.listenSong(false);
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
			    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				e.printStackTrace();
			}
        //}
	}

	

}