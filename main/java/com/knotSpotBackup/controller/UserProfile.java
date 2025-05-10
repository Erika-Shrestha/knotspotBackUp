package com.knotSpotBackup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.knotSpotBackup.model.UserModel;
import com.knotSpotBackup.service.ProfileService;

/**
 * Servlet implementation class UserProfile
 */

@MultipartConfig
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/Customer/userprofile.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//fetch all data 
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact_no");
		String gender = request.getParameter("gender");
		String username = request.getParameter("username");
		Part image = request.getPart("profile_image");
		String imageFilePath = image.getSubmittedFileName();
		String uploadPath="C:/Users/eerii/eclipse-workspace/KnotSpotBackup/src/main/webapp/resources/"+imageFilePath;
		System.out.println("uploaded file name: "+uploadPath);
		
		
		//get user from the session
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userModel") == null) {
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}
		UserModel user = (UserModel) session.getAttribute("userModel");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setContactNumber(contact);
		user.setGender(gender);
		user.setUsername(username);
		
		if(imageFilePath!=null &&!imageFilePath.isEmpty()) {
			try {
				FileOutputStream fos = new FileOutputStream(uploadPath);
				InputStream is= image.getInputStream();
				
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			user.setProfilePic(imageFilePath);
		}
		
		//update in database
		ProfileService profile = new ProfileService();
		UserModel updatedUser = profile.updateUserDetails(user);
		session.setAttribute("userModel", updatedUser);
		request.setAttribute("userModel", updatedUser);
	    request.getRequestDispatcher("/WEB-INF/pages/Customer/userprofile.jsp").forward(request, response);
		
		
		
	}

}
