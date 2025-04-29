package com.knotSpotBackup.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.knotSpotBackup.config.DdConfig;
import com.knotSpotBackup.model.UserModel;

public class RegisterService {
private Connection conn;
	
	public RegisterService() {
		try {
			conn = DdConfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException e){
			System.out.println("DB connection failed :" +e.getMessage());
			e.printStackTrace();
		}
	}
	
	public UserModel addUsers(UserModel users) {
		if(conn == null) {
			System.out.println("DB connection failed");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users(first_name, middle_name, last_name, age, gender, address, contact_no, email, username, password, registered_date, role_id, task_id) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, users.getFirstName());
			ps.setString(2, users.getMiddleName());
			ps.setString(3, users.getLastName());
			ps.setInt(4, users.getAge());
			ps.setString(5, users.getGender());
			ps.setString(6, users.getAddress());
			ps.setString(7, users.getContactNumber());
			ps.setString(8, users.getEmail());
			ps.setString(9, users.getUsername());
			ps.setString(10,users.getPassword());
			ps.setDate(11, java.sql.Date.valueOf(LocalDate.now()));
			ps.setString(12, users.getRole());
			ps.setString(13, "T1");
			
			int i = ps.executeUpdate();
			
			if(i>0) {
				System.out.println("Success");
				
			}
			else {
				System.out.println("not inserted");
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
