package com.knotSpotBackup.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import com.knotSpotBackup.model.UserModel;
import com.knotSpotBackup.service.LoginService;
import com.knotSpotBackup.service.RegisterService;
import com.knotSpotBackup.util.CookieUtil;
import com.knotSpotBackup.util.ValidationUtil;


/**
 * Servlet implementation class AuthenticationController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//use of cache control to not store the data 
		response.setHeader("Cache-Control","no-store");
		
		request.getRequestDispatcher("/WEB-INF/pages/auth.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String registerFormAction = request.getParameter("register");
		String loginFormAction = request.getParameter("login");

		if("register".equals(registerFormAction)) {
			
			//calling the register logic method
			handleRegister(request, response);
			
		}
		else if("login".equals(loginFormAction)){
			System.out.println("calls handlelogin");
			//calling the login logic method
			handleLogin(request, response);
		}
		
	}

	private boolean isValidInputs(HttpServletRequest request, HttpServletResponse response, String firstName, String lastName, String dob, String gender, String contact, String email, String username, String password, String retypePassword)  throws ServletException, IOException {
		boolean isValid = true;
		
		isValid &= checkInputField(request, "firstname", firstName, "first name");
		isValid &= checkInputField(request, "lastname", lastName, "last name");
		isValid &= checkInputField(request, "dob", dob, "Age");
		isValid &= checkInputField(request, "gender", gender, "gender");
		isValid &= checkInputField(request, "contact", contact, "Phone number");
		isValid &= checkInputField(request, "email", email, "Email");
		isValid &= checkInputField(request, "username", username, "Username");
		isValid &= checkPasswordField(request, password, retypePassword, "Password", username);
		System.out.println("Validation is checked");
		
		return isValid;
	}
	
	private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String stringDob = request.getParameter("birthday");
			LocalDate dob = null;
			
			try {
				ValidationUtil.isValidDate(stringDob, "Calendar");
				dob = LocalDate.parse(stringDob);
			}
			catch(NullPointerException  | DateTimeParseException e) {
				System.out.println("Calendar must be in proper format");
			}
			
			
			int age = Period.between(dob, LocalDate.now()).getYears();
			String gender = request.getParameter("gender");
			String address = request.getParameter("city");
			String contact = request.getParameter("contact");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String retypePassword = request.getParameter("retypePassword");
			String role = request.getParameter("role");
			System.out.println("Retrieved data");
			boolean isValid = isValidInputs(request, response, firstName, lastName, stringDob, gender, contact, email, username, password, retypePassword);
				
			if(isValid) {
				System.out.println("All data is valid");
				//making an object of user to assign the values
				UserModel users = new UserModel(firstName,null,lastName, age, gender, address, contact, email, username, password, role);
				RegisterService registerService = new RegisterService();
				UserModel createdUser = registerService.addUsers(users);
				System.out.println("Reacher here 1");
				if (createdUser != null) {
					System.out.println("Reacher here 2");
					response.sendRedirect(request.getContextPath() + "/login");
						
				}
			}
			else {
				request.setAttribute("showRegister", true);
				request.setAttribute("firstname", firstName);
				request.setAttribute("lastname", lastName);
				request.setAttribute("email", email);
				request.setAttribute("contact", contact);
				request.setAttribute("gender", gender);
				request.setAttribute("address", request.getParameter("city"));
				request.setAttribute("birthday", stringDob);
				request.setAttribute("username", username);
				
				request.getRequestDispatcher("/WEB-INF/pages/auth.jsp").forward(request, response);
			}
			
			
	}
	
	//a try-catch for validationfield 
	public static boolean checkInputField(HttpServletRequest request, String field, String value, String attribute) {
		boolean isValid = true;
		try {
			getInputField(field, value, attribute);
		}
		catch(NullPointerException | IndexOutOfBoundsException | IllegalArgumentException e) {
			request.setAttribute(field+"Error", e.getMessage());
			System.out.println(e.getMessage());
			isValid = false;
		}
		return isValid;
	}
	
	//a try-catch for validationfield 
		public static boolean checkPasswordField(HttpServletRequest request, String password, String retypePassword, String attribute, String username) {
			boolean isValid = true;
			
			
			try {
				ValidationUtil.doPasswordsMatch(password, retypePassword, attribute, username);
			}
			catch(NullPointerException | IndexOutOfBoundsException | IllegalArgumentException e) {
				request.setAttribute("passwordError", e.getMessage());
				isValid = false;
			}
			return isValid;
		}
	
	
	//to differentiate methods for each fields
	public static void getInputField(String field, String value, String attribute) {
		if(field.equalsIgnoreCase("firstname") || field.equalsIgnoreCase("lastname")) {
			ValidationUtil.isValidName(value, attribute);
		}
		else if(field.equalsIgnoreCase("dob")) {
			ValidationUtil.isValidEntryAge(LocalDate.parse(value), attribute);
		}
		else if(field.equalsIgnoreCase("gender")) {
			ValidationUtil.isValidGender(value, attribute);
		}
		else if(field.equalsIgnoreCase("contact")) {
			ValidationUtil.isValidContact(value, attribute);		
		}
		else if(field.equalsIgnoreCase("email")) {
			ValidationUtil.isValidEmail(value, attribute);
		}
		else if(field.equalsIgnoreCase("username")) {
			ValidationUtil.isValidUserName(value, attribute);
		}
	}
	
	
	//login
	public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username !=null && password !=null) {
			System.out.println("user and password is not empty");
			UserModel users = new UserModel();
			users.setUsername(username);
			users.setPassword(password);
			
			LoginService loginService = new LoginService();
			UserModel retreivedUser = loginService.retreiveUsers(users);
			
			if(retreivedUser !=null) {
				System.out.println("The user is retreived");
				//creates a session for each new users if does not exists makes one using true
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				System.out.println("Session ID: "+session);
				CookieUtil.addCookie(response, "role", retreivedUser.getRole(), 15 * 60);
				System.out.println("Login successful. Role cookie set: " + retreivedUser.getRole());
				String role = retreivedUser.getRole();
			    if ("RL1".equalsIgnoreCase(role)) {
			        response.sendRedirect(request.getContextPath() + "/dashboard");
			    } else if ("RL2".equalsIgnoreCase(role)) {
			        response.sendRedirect(request.getContextPath() + "/home");
			    } else {
			        response.sendRedirect(request.getContextPath() + "/login");
			    }

			}
			else {
				request.setAttribute("username", username);
				request.setAttribute("errorMessage", "Invalid username or password");
				request.getRequestDispatcher("/WEB-INF/pages/auth.jsp").forward(request, response);
			}
		}
		
	}

}
