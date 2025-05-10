package com.knotSpotBackup.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.knotSpotBackup.config.DdConfig;
import com.knotSpotBackup.model.UserModel;

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

	    public UserModel updateUserDetails(UserModel user) {
	        String updateSql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, contact_no = ?, gender = ?, profile_image = ? WHERE user_id = ?";
	        String selectSql = "SELECT u.first_name, u.last_name, u.username, u.email, u.contact_no, u.gender, u.role_id, u.profile_image, r.user_role FROM users u JOIN userrole r ON u.role_id = r.role_id WHERE u.user_id = ?";
	        
	        System.out.println("Updating user with ID: " + user.getUserId());
	        System.out.println("Update SQL values:");
	        System.out.println(user.getFirstName() + ", " + user.getLastName() + ", " + user.getUsername() + ", " + user.getEmail() + ", " + user.getContactNumber() + ", " + user.getGender());
	        try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
	        	ps.setString(1, user.getFirstName()); 
	            ps.setString(2, user.getLastName());
	            ps.setString(3, user.getUsername()); 
	            ps.setString(4, user.getEmail());
	            ps.setString(5, user.getContactNumber());
	            ps.setString(6, user.getGender());
	            ps.setString(7, user.getProfilePic());
	            ps.setInt(8, user.getUserId());

	            int updateDetail = ps.executeUpdate();
	            System.out.println("Update result (affected rows): " + updateDetail);

	            if(updateDetail >0) {
	            	
	            	try (PreparedStatement selectPs = conn.prepareStatement(selectSql)){
	            		
	            		selectPs.setInt(1, user.getUserId());
	            		ResultSet i = selectPs.executeQuery();
	            		
	            		if(i.next()) {
	            			user.setFirstName(i.getString("u.first_name"));
	                        user.setLastName(i.getString("u.last_name"));
	                        user.setUsername(i.getString("u.username"));
	                        user.setEmail(i.getString("u.email"));
	                        user.setContactNumber(i.getString("u.contact_no"));
	                        user.setGender(i.getString("u.gender"));
	                        user.setRole(i.getString("u.role_id"));
	                        user.setProfilePic(i.getString("u.profile_image"));
	                        user.setRoleName(i.getString("r.user_role"));
	            		}
	            	}
	            	return user;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();

	        }
			return user;

	    }
}
