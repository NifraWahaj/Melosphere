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
import java.sql.SQLException;

import com.melosphere.Listener;

@WebServlet("/ListenerRemoveFromFav")
public class ListenerRemoveFromFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Listener L = new Listener();
		L.setName((String) request.getSession(false).getAttribute("LName"));
		String encodedAName = request.getParameter("AName");
		String decodedAName = URLDecoder.decode(encodedAName, "UTF-8");
		String encodedSName = request.getParameter("SName");
		String decodedSName = URLDecoder.decode(encodedSName, "UTF-8");
		
		try {
			if(L.DeleteSongFromFav(decodedAName,decodedSName)) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("ListenerFavDummy.jsp");
			    request.setAttribute("status","success"); 
			    dispatcher.forward(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("Error Occured"); 
			    out.close();
			}
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
