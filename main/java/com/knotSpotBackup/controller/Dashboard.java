package com.knotSpotBackup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard" })
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//to make sure the session id exists otherwise returns null
		HttpSession session = request.getSession(false);
		//converting back to string from an object
		String userSession = (String) session.getAttribute("username");
		if(userSession !=null ) {
			System.out.println("Session is created");
			request.getRequestDispatcher("/WEB-INF/pages/Admin/dashboard.jsp").forward(request,response);
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/authentication");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
