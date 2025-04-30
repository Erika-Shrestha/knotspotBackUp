<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- This connects to dashboard.css file -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">

<!-- This connects to dashboard.js file -->
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/header.js"></script>

<!-- This gives access to google font "inter" -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

<!-- This gives access to font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

</head>
<body>
		<div class="header">
			<div class="company-con">
			
				<div class="smalltab">
	                <input type="checkbox" id="check">
	                <label for="check">
	                    <i class="fa fa-bars" id="btn"></i>
	                    <i class="fas fa-times" id="cancel"></i>
	                </label>
	                <div class="div-sidebar">
	                    <ul class="sidebar-lists">
	                        <li style="padding-top:40px; font-size: 20px; font-weight: 700;">BRANDS</li>
	                        <li><a href="">Nike</a></li>
	                        <li><a href="">Adidas</a></li>
	                        <li><a href="">Puma</a></li>
	                        <li><a href="">Uggs</a></li>
	                        <li><a href="product-newbalance.html">New Balance</a></li>
	                        <li style="padding-top:50px; font-size: 20px; font-weight: 700;">NEW COLLECTION</li>
	                        <li><a href="product-spring.html">Spring Collection</a></li>
	                        <li><a href="product-autumn.html">Autumn Collection</a></li>
	                        <li class="html-pages-lists" style="padding-top: 60px;"><a href="blog.html">Blog</a></li>
	                        <li class="html-pages-lists"><a href="journey.html">Our Journey</a></li>
	                        <li class="html-pages-lists"><a href="contact-us.html">Contact Us</a></li>
	                        <li class="html-pages-lists"><a href="FAQs.html">FAQs</a></li>
	            
	                    </ul>
	                </div>
	            </div>
				<div class="company-name">
					<img src="${pageContext.request.contextPath}/resources/companyicon.png" alt="company-logo" class="company-logo">
				</div>
			</div>
			<div class="navigation">
				<ul>
                  <li><a href="">Home</a></li>
                  <li><a href="">Product</a></li>
                  <li><a href="contact.html">Blog</a></li>
                  <li><a href="${pageContext.request.contextPath}/aboutUs">About us</a></li>
                  <li><a href="${pageContext.request.contextPath}/contactUs">Contact us</a></li>
                  <li><a href="${pageContext.request.contextPath}/faq">FAQ</a></li>
                </ul>
			</div>
			<nav>
			<div class="user-con">
				<a href="" class="favorite-icon"><i class="fa-regular fa-heart"></i></a>
				<!-- The image is to display the pop up message -->
				<img src="${pageContext.request.contextPath}/resources/userpfp.jpg" alt="user-profile" class="user-pic" onclick="toggleMenu()">
				
				<!-- This is pop-up wrapper to display user-profile -->
				<div class="menu-container" id="menu">
				<div class="menu">
				<div class="user-info">
				<img src="${pageContext.request.contextPath}/resources/userpfp.jpg" alt="user-profile">
				<h2>James Seldon</h2>
				</div>
				<hr>
				
				<a href="#" class="menu-link">
				<i class="fa-solid fa-user"></i>
				<p>Edit Profile</p>
				<span>></span>
				</a>
				
				<a href="#" class="menu-link">
				<i class="fa-solid fa-gears"></i>
				<p>Settings & Privacy</p>
				<span>></span>
				</a>
				
				<a href="#" class="menu-link">
				<i class="fa-solid fa-circle-question"></i>
				<p>Help & Support</p>
				<span>></span>
				</a>
				
				<a href="#" class="menu-link">
				<i class="fa-solid fa-right-from-bracket"></i>
				<p>Logout</p>
				<span>></span>
				</a>		
				
				</div>
				</div>
			</div>
			</nav>
		</div>
</body>
</html>