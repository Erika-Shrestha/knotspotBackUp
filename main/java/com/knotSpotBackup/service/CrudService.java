package com.knotSpotBackup.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.knotSpotBackup.config.DdConfig;
import com.knotSpotBackup.model.VenueModel;

public class CrudService {

	private Connection conn;
	
	public CrudService() {
		try {
			conn = DdConfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException e){
			System.out.println("DB connection failed :" +e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
	    return conn;
	}
	
	//add query for venue table
	public VenueModel addVenue(VenueModel venues) {
		if(conn == null) {
			System.out.println("DB connection failed");
		}
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO venues(name, address, city, contact_no, capacity, amenities, type, registered_date, venue_picture, status) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, venues.getName());
			ps.setString(2, venues.getAddress());
			ps.setString(3, venues.getCity());
			ps.setString(4, venues.getContactNumber());
			ps.setInt(5, venues.getCapacity());
			ps.setString(6, venues.getAmenities());
			ps.setString(7, venues.getType());
			ps.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
			ps.setString(9, venues.getVenuePic());
			ps.setString(10, venues.getStatus());
			
			int i = ps.executeUpdate();
						
				if(i>0) {
					System.out.println("Success");
					return venues;
					
				}
				else {
					System.out.println("not inserted");
					return null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//update query for venue table
	public boolean deleteVenue(int venueId) {
		if(conn == null) {
			System.out.println("DB connection failed");
		}
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM venues WHERE venue_id = ?");
			ps.setInt(1, venueId);
			
			int i = ps.executeUpdate();
			
			if(i>0) {
				System.out.println("Success");
				return true;
				
			}
			else {
				System.out.println("not inserted");
				return false;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//query to update venue table
	public VenueModel updateVenue(VenueModel venues) {
		if(conn == null) {
			System.out.println("DB connection failed");
		}
		
		String updateSql = "UPDATE venues SET name = ?, address = ?, city = ?, contact_no = ?, capacity = ?, amenities = ?, type = ?, venue_picture = ?, status = ? WHERE venue_id = ?";
		String selectSql = "SELECT name, address, city, contact_no, capacity, amenities, type, venue_picture, status FROM venues WHERE venue_id = ?";
		
		try (PreparedStatement updatePs = conn.prepareStatement(updateSql)){
			
			updatePs.setString(1, venues.getName());
			updatePs.setString(2, venues.getAddress());
			updatePs.setString(3, venues.getCity());
			updatePs.setString(4, venues.getContactNumber());
			updatePs.setInt(5, venues.getCapacity());
			updatePs.setString(6, venues.getAmenities());
			updatePs.setString(7, venues.getType());
			updatePs.setString(8, venues.getVenuePic());
			updatePs.setString(9, venues.getStatus());
			updatePs.setInt(10, venues.getVenueId());
			
			int updateDetail = updatePs.executeUpdate();
			
			if(updateDetail > 0) {
				
				try (PreparedStatement selectPs = conn.prepareStatement(selectSql)){
					
					selectPs.setInt(1, venues.getVenueId());
	        		ResultSet i = selectPs.executeQuery();
	        		
	        		if(i.next()) {
	        			venues.setName(i.getString("name"));
	    				venues.setAddress(i.getString("address"));
	    				venues.setCity(i.getString("city"));
	    				venues.setContactNumber(i.getString("contact_no"));
	    				venues.setCapacity(i.getInt("capacity"));
	    				venues.setAmenities(i.getString("amenities"));
	    				venues.setType(i.getString("type"));
	    				venues.setVenuePic(i.getString("venue_picture"));
	    				venues.setStatus(i.getString("status"));
	        		}
				}
				return venues;
                
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return venues;
	
	}
	
	
}
