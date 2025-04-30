package com.knotSpotBackup.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.knotSpotBackup.config.DdConfig;
import com.knotSpotBackup.model.UserModel;
import com.knotSpotBackup.util.PasswordUtil;

public class LoginService {
private Connection conn;	//Declare a variable to store the sql connection imported from config class
	
	
	/**
	 * A constructor to handle database connection 
	 * checks whether database is successfully connected 
	 */
	public LoginService() {
		try {
			conn = DdConfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException e){
			System.out.println("DB connection failed :" +e.getMessage());
			e.printStackTrace();
		}
	}
	
	public UserModel retreiveUsers(UserModel users) {
		if(conn == null) {
			System.out.println("DB connection failed");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT password, role_id FROM users WHERE username = ?");
			
			ps.setString(1, users.getUsername());

			
			ResultSet i = ps.executeQuery();
			
			if(i.next()) {
				String storedPassword = i.getString("password");
				String storedRole = i.getString("role_id");
				
				if(PasswordUtil.checkPassword(users.getPassword(), storedPassword)) {
					System.out.println("Logged in succesfully");
					users.setRole(storedRole);
					return users;
				}
				else {
					System.out.println("Incorrect password");
					return null;
				}
			}
			else {
				System.out.println("User not found");
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
		
	}
}
