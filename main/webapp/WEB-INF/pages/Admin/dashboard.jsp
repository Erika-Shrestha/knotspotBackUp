<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- This connects to dashboard.css file -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard.css">

<!-- This connects to dashboard.js file -->
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/dashboard.js"></script>

<!-- This gives access to google font "inter" -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

<!-- This gives access to font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>

<!-- Body stores the content for admin page -->
<body>

<!-- This is the main wrapper -->
<div class="main-container">

<!-- This is a container that directs admin to various pages -->
<div class="navigation"></div>

<!-- This div connects head and data content -->
<div class="joint-container">
<!-- This is the container for search bar, user profile -->
<div class="head-content">
<nav>

<!-- This is the image for company logo -->
<img src="${pageContext.request.contextPath}/resources/companylogo.png" alt="company-logo" class="company-logo">


<!-- This is for notification bell -->
<a href="#" class="notify-link"><i class="fa-solid fa-bell"></i></a>

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

</nav>
</div>

<!-- This contains each pages front content/data -->
<div class="data-content">
<h1>data content</h1>
</div>

</div>

</div>

</body>
</html>