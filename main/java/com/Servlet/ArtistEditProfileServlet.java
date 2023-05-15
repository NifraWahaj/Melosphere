package com.Servlet;

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

@WebServlet("/ArtistEditProfile")
public class ArtistEditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User A = new Artist();

	    String newLPassword = request.getParameter("newAPassword");
	    A.setName((String) request.getSession(false).getAttribute("AName"));
	    try {
			if (A.editProfile(newLPassword)) {
				PrintWriter out = response.getWriter();
			    out.println("Password Updated");
			    out.close();
			} else {
				PrintWriter out = response.getWriter();
			    out.println("ERROR");
			    out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
