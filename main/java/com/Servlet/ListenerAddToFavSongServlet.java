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

import com.Music.Song;



@WebServlet("/ListenerAddToFav")
public class ListenerAddToFavSongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Song s1 = new Song();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String LName = (String) request.getSession(false).getAttribute("LName");
		String encodedAName = request.getParameter("AName");
		String decodedAName = URLDecoder.decode(encodedAName, "UTF-8");
		String encodedSName = request.getParameter("SName");
		String decodedSName = URLDecoder.decode(encodedSName, "UTF-8");
        RequestDispatcher dispatcher = null;

        try {
            if (s1.addSongToFav(LName, decodedSName, decodedAName)) {
            	PrintWriter out = response.getWriter();
                out.println("Song Added Successfully");
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("ERROR: SONG NOT ADDED TO FAV");
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

