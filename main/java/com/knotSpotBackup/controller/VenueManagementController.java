package com.knotSpotBackup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.knotSpotBackup.model.VenueModel;
import com.knotSpotBackup.service.CrudService;

/**
 * Servlet implementation class VenueManagementController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/management" })
public class VenueManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/Admin/venuemanagement.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateNewVenue(request, response);
		updateVenue(request, response);
		removeVenue(request,response);
	}
	
	private void CreateNewVenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String venueName = request.getParameter("venue_name");
		String venueAddress = request.getParameter("address");
		String venueCity = request.getParameter("city");
		String contactNumber = request.getParameter("contact");
		int venueCapacity = Integer.parseInt(request.getParameter("capacity"));
		String amenities = request.getParameter("amenities");
		String venueType = request.getParameter("venue_type");
		String status = request.getParameter("status");
		Part venueImage = request.getPart("venue_image");
		String imageFilePath = venueImage.getSubmittedFileName();
		System.out.println("Selected file name: "+imageFilePath);
		String uploadPath="C:/Users/eerii/eclipse-workspace/KnotSpotBackup/src/main/webapp/resources/"+imageFilePath;
		System.out.println("uploaded file name: "+uploadPath);
		System.out.println("Retrieved data");
		
		try {
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is= venueImage.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		VenueModel venue = new VenueModel(venueName, venueAddress, venueCity, contactNumber, venueCapacity, amenities, venueType, imageFilePath, status);
		CrudService crudService = new CrudService();
		VenueModel createdVenue = crudService.addVenue(venue);
		if (createdVenue != null) {
			response.sendRedirect(request.getContextPath() + "/management");
				
		}
		
	}
	
	private void updateVenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//fetch all data
		String venueName = request.getParameter("venue_name");
		String venueAddress = request.getParameter("address");
		String venueCity = request.getParameter("city");
		String contactNumber = request.getParameter("contact");
		int venueCapacity = Integer.parseInt(request.getParameter("capacity"));
		String amenities = request.getParameter("amenities");
		String venueType = request.getParameter("venue_type");
		String status = request.getParameter("status");
		Part venueImage = request.getPart("venue_image");
		String imageFilePath = venueImage.getSubmittedFileName();
		System.out.println("Selected file name: "+imageFilePath);
		String uploadPath="C:/Users/eerii/eclipse-workspace/KnotSpotBackup/src/main/webapp/resources/"+imageFilePath;
		System.out.println("uploaded file name: "+uploadPath);
		
		VenueModel updateVenue = new VenueModel(venueName, venueAddress, venueCity, contactNumber, venueCapacity, amenities, venueType, imageFilePath, status);
		
		if(imageFilePath!=null &&!imageFilePath.isEmpty()) {
			try {
				FileOutputStream fos = new FileOutputStream(uploadPath);
				InputStream is= venueImage.getInputStream();
				
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			updateVenue.setVenuePic(imageFilePath);
		}
		
		CrudService crudService = new CrudService();
		VenueModel updateVenueDetail = crudService.updateVenue(updateVenue);
		request.setAttribute("updatedVenue", updateVenueDetail);
		request.getRequestDispatcher("/WEB-INF/pages/Admin/venuemanagement.jsp").forward(request, response);
	}
	
	private void removeVenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String venueIdString = request.getParameter("venue_id");
		
		if(venueIdString !=null && !venueIdString.isEmpty()) {
			int venueId = Integer.parseInt(venueIdString);
			CrudService crudService = new CrudService();
			crudService.deleteVenue(venueId);
		}
		
	}

}
