package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.melosphere.*;

@WebServlet("/UnFollowArtist")
public class UnfollowArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Artist A = new Artist();
	private Listener L = new Listener();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		L.setName((String) request.getSession(false).getAttribute("LName"));
		A.setName(request.getParameter("AName"));

		try {
			if(L.unfollowArtist(A.getName()))
			{
				//RequestDispatcher dispatcher = null;
				//dispatcher = request.getRequestDispatcher("CheckFollowArtist.jsp?AName=" + A.getName());
				//request.setAttribute("status","success"); 
				//dispatcher.forward(request, response);
				PrintWriter out = response.getWriter();
				out.println("Successfully UnFollowed"); 
			    out.close();
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("ERROR: Already Not Following"); 
			    out.close();
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}