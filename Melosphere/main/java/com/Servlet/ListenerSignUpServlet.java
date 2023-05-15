package com.Servlet;
import com.melosphere.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@SuppressWarnings("unused")
@WebServlet("/ListenerSignUp")
public class ListenerSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User L = new Listener(); 
	
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		L.setName(request.getParameter("LName"));
		L.setPassword(request.getParameter("LPassword")); 
		L.setEmail(request.getParameter("LEmail"));
		RequestDispatcher dispatcher = null;
		
		try {
			if(L.signUp())
			{
				HttpSession session = request.getSession();
				session.setAttribute("LName",L.getName());
				
				// create a new request object to send to the other servlet
				HttpServletRequest newRequest = new HttpServletRequestWrapper(request) {
				@Override
				public String getMethod() {
				return "GET";
				}
				};
				dispatcher = request.getRequestDispatcher("ListenerDisplaySongs.jsp");
				request.setAttribute("status","success"); 
				dispatcher.forward(newRequest, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("ERROR: Username Not Unique or ERROR: Can not insert in database"); 
			    out.close();
			}
		} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}