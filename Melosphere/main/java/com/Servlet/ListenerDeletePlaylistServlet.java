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
import java.net.URLDecoder;
import java.sql.SQLException;

import com.melosphere.Listener;
import com.melosphere.User;


public class ListenerDeletePlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Listener L = new Listener();
		L.setName((String) request.getSession(false).getAttribute("LName"));
		String encodedPName = request.getParameter("PName");
		String decodedPName = URLDecoder.decode(encodedPName, "UTF-8");
		try 
		{
			if(L.deletePlaylist(decodedPName))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("ListenerGetPlaylistDummy.jsp");
			    request.setAttribute("status","success"); 
			    dispatcher.forward(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("Error Occured"); 
			    out.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
