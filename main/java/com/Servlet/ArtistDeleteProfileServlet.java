package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.melosphere.Artist;
import com.melosphere.Listener;
import com.melosphere.User;

@WebServlet("/ArtistDeleteProfileDummy")
public class ArtistDeleteProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User A = new Artist();
		A.setName((String) request.getSession(false).getAttribute("AName"));
		try 
		{
			int check = A.deleteUser();
			if(check == 1)
			{
				HttpSession session = request.getSession();
				session.removeAttribute("AName");
				session.invalidate();
				   
				RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomePage.html");
			    request.setAttribute("status","success"); 
			    dispatcher.forward(request, response);
			}
			else if(check == 0)
			{
				PrintWriter out = response.getWriter();
				out.println("Listener Not Found"); 
			    out.close();
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("Error Occured"); 
			    out.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
