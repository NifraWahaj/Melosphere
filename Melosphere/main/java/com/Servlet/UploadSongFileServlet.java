package com.Servlet;
import com.melosphere.*;
import com.Music.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("unused")
@WebServlet("/ArtistUploadSongFile")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 10, // 2MB
	    maxFileSize = 1024 * 1024 * 20, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class UploadSongFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Song S = new Song();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = null;
        Part filePart = request.getPart("SongFile");
        if (filePart != null) 
        {
            inputStream = filePart.getInputStream();
        }
        S.setSName(request.getParameter("SongName"));
        S.setGenre(request.getParameter("Genre")); 
        S.setAName((String) request.getSession(false).getAttribute("AName"));
        S.setUDate();

            try {
            	int check = S.uploadSong(inputStream);
				if(check == 1)
				{
					PrintWriter out = response.getWriter();
					out.println("SuccessFully Uploaded"); 
					out.close();
				}
				else if(check == -1)
				{
					PrintWriter out = response.getWriter();
					out.println("song name already exists"); 
				    out.close();
				}
				else if(check == -2)
				{
					PrintWriter out = response.getWriter();
					out.println("song name, artist name, or song file is empty"); 
				    out.close();
				}
				else
				{
					PrintWriter out = response.getWriter();
					out.println("ERROR"); 
				    out.close();
				}
				
			} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}

    
	}

}
