package com.knotSpotBackup.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.knotSpotBackup.config.DdConfig;

public class ProfileService {


	    private Connection conn;

	    public ProfileService() {
	        try {
	            conn = DdConfig.getDbConnection();
	        } catch (SQLException | ClassNotFoundException e) {
	            System.out.println("DB connection failed :" + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    public boolean updateUserDetails(String userId, String newFirstName, String newLastName, String newUsername, String newEmail, String newPhoneNumber) {
	        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, contact_no = ? WHERE user_id = ?";
	        
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        	ps.setString(1, newFirstName); 
	            ps.setString(2, newLastName);
	            ps.setString(3, newUsername); 
	            ps.setString(4, newEmail);
	            ps.setString(5, newPhoneNumber);
	            ps.setString(6, userId);   

	            int updateDetail = ps.executeUpdate();
	            
	            return updateDetail> 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }

	    }
}
