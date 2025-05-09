package com.knotSpotBackup.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import jakarta.servlet.http.Part;

public class ValidationUtil {
	/*
	 * Regular expression (Regex) for email, phone number, password and username
	 */
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$");
	private static final Pattern CONTACT_PATTERN = Pattern.compile("^98\\d{8}$");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
	private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z][a-zA-Z0-9_-]{3,19}$");
	private static final Pattern ALPHABETIC_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");
	
	//to check if the field is null or empty
	public static void isNullorEmpty(String value, String attribute) {
		if(value == null || value.trim().isEmpty()) {
			throw new NullPointerException(attribute + " cannot be empty.");
		}
	}
	
	//to check if the field has alphabetic input
	public static void isAlphabet(String value, String attribute) {
		if(!ALPHABETIC_PATTERN.matcher(value).matches()) {
			throw new NumberFormatException(attribute+" must only have alphabetic character.");
		}
	}
	
	//to check duplicate values
	public static void isDuplicate(String compareValue1, String compareValue2) {
		if(compareValue1.equalsIgnoreCase(compareValue2)) {
			throw new IllegalArgumentException("Duplicate value found: " + compareValue2);
		}
	}
	
	
	//to check if the name field inputs are valid
	public static void isValidName(String name, String attribute) {
		isNullorEmpty(name, attribute);
		isAlphabet(name, attribute);
		if(name.length()<=4){
            throw new IllegalArgumentException(attribute +" is too short");
        }
		else if(name.length()>=100) {
			throw new IllegalArgumentException(attribute +" is too long");
		}
	}
	
	//to check if the email field input is valid
	public static void isValidEmail(String email, String attribute) {
		isNullorEmpty(email, attribute);
		if(!EMAIL_PATTERN.matcher(email).matches()) {
			throw new IllegalArgumentException("Invalid email format for: "+ attribute);
		}
	}
	
	//to check if the contact number field input is valid
	public static void isValidContact(String number, String attribute) {
		isNullorEmpty(number, attribute);
		if(!CONTACT_PATTERN.matcher(number).matches()) {
			throw new NumberFormatException(attribute +" must be of 10 digits and start with 98");
		}
	}
	
	//to check if the gender radio button is checked
	public static void isValidGender(String gender, String attribute) {
		isNullorEmpty(gender, attribute);
		if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
			throw new IllegalArgumentException(attribute +" must be either male or female"); 
		}
	}
	
	//to check whether the user age is above 18
	public static void isValidEntryAge(LocalDate dob, String attribute) {
		LocalDate current = LocalDate.now();
		if(Period.between(dob, current).getYears() < 18) {
			throw new IllegalArgumentException(attribute +" must be atleast 18 years old."); 
		}
	}
	
	//to check if the user name field input is valid
	public static void isValidUserName(String username, String attribute) {
		isNullorEmpty(username, attribute);
		if(!USERNAME_PATTERN.matcher(username).matches()) {
			throw new IllegalArgumentException(attribute +" must be in a proper format");  
		}
	}
	
	//to check whether the date is null
	public static void isValidDate(String dob, String attribute) {
		isNullorEmpty(dob, attribute);
	}
	
	//to check if two-step confirmation password matches
	public static void doPasswordsMatch(String password, String retypePassword, String attribute, String username) {
		isNullorEmpty(password, attribute);
		isNullorEmpty(retypePassword, attribute);
		if(!PASSWORD_PATTERN.matcher(password).matches()) {
			throw new IllegalArgumentException(attribute +" must be in a proper format.");
		}
		else if(!password.equals(retypePassword)) {
			throw new IllegalArgumentException("password and retype password do not match.");  
		}
		else if(password.equalsIgnoreCase(username)) {
			throw new IllegalArgumentException("Password cannot be similar to username");  
		}
		
	}
	
	//to check if the image file size has proper format and size
	public static void isValidImageExtension(Part imagePart) {
        if (imagePart == null || imagePart.getSubmittedFileName() == null) {
        	throw new IllegalArgumentException("No file uploaded.");
        }
        String fileName = imagePart.getSubmittedFileName().toLowerCase();
        if(!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")) {
        	throw new IllegalArgumentException("Invalid image format. Only jpg, jpeg, png, are allowed.");
        }
        
    }
	

}
