package com.Servlet;
import com.melosphere.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
@WebServlet("/ListenerLogIn")
public class ListenerLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User L = new Listener(); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		L.setName(request.getParameter("LName"));
		L.setPassword(request.getParameter("LPassword")); 
		L.setEmail(request.getParameter("LEmail"));
		RequestDispatcher dispatcher = null;
		try {
			if(L.logIn())
			{     
				HttpSession session = request.getSession();
				session.setAttribute("LName",L.getName());
				
				
				dispatcher = request.getRequestDispatcher("ListenerDisplaySongs.jsp");
				request.setAttribute("status","success"); 
				dispatcher.forward(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("ERROR: No Such User exists : Username or Password or Email might be incorrect"); 
			    out.close();
			}
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	

}
