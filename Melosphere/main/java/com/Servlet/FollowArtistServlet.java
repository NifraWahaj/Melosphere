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

@WebServlet("/FollowArtist")
public class FollowArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Artist A = new Artist();
	private Listener L = new Listener();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		L.setName((String) request.getSession(false).getAttribute("LName"));
		A.setName(request.getParameter("AName"));

		try {
			if(L.followArtist(A.getName()))
			{
				//RequestDispatcher dispatcher = null;
				//dispatcher = request.getRequestDispatcher("CheckFollowArtist.jsp?AName=" + A.getName());
				//dispatcher = request.getRequestDispatcher("");
				//request.setAttribute("status","success"); 
				//dispatcher.forward(request, response);
				PrintWriter out = response.getWriter();
				out.println("Successfully Followed!"); 
			    out.close();
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("ERROR: Already Following"); 
			    out.close();
			}
		} catch (ClassNotFoundException | SQLException  | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}