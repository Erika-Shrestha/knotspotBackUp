<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Knot Spot</title>

<!-- This connects to venuemanagement.css file -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/venuemanagement.css">


<!-- This gives access to google font "inter" -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

<!-- This gives access to font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	<section class="home">
		<div class="search-con">
			<input type="text" placeholder="search">
			<i class="fa-solid fa-magnifying-glass"></i>
			<!-- This is for notification bell -->
			<a href="#" class="notify-link"><i class="fa-solid fa-bell icon"></i></a>
		</div>
		<div class="venue-title">
			<label>Venue Details</label>
		</div>
		<div class="filters">
			<div class="search-con search">
				<input type="text" placeholder="search venues...">
				<i class="fa-solid fa-magnifying-glass"></i>
			</div>
			<div class="sort">
				<i class="fa fa-filter" aria-hidden="true"></i>
				<label>Filters</label>
			</div>
			<i class="fa-solid fa-plus"></i>
		</div>
		<div class="table-container">
		  <table class="venue-table">
		    <thead>
		      <tr>
		        <th><input type="checkbox"></th>
		        <th>ID</th>
		        <th>Venue</th>
		        <th>Location</th>
		        <th>Contact Info</th>
		        <th>Capacity</th>
		        <th>Amenities</th>
		        <th>Date</th>
		        <th>Type</th>
		        <th>Status</th>
		        <th>Actions</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td><input type="checkbox"></td>
		        <td>FIG-123</td>
		        <td>Venue 1</td>
		        <td>Lalitpur</td>
		        <td>9832465421</td>
		        <td>300</td>
		        <td>High furnished bed</td>
		        <td>9th June 2020</td>
		        <td>Ballroom</td>
		        <td><span class="badge high">Available</span></td>
		        <td class="actions">
		          	<button><i class="fas fa-pen"></i></button>
  					<button><i class="fas fa-eye" onclick="openPanel()"></i></button>
  					<button><i class="fas fa-trash"></i></button>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		</div>
		<div id="viewPanel" class="view-panel">
		  <div class="panel-content">
		    <span class="close-btn" onclick="closePanel()">&times;</span>
		    <h2>Edit Venue</h2>
		    <p>Editing content goes here...</p>
		  </div>
		</div>
	</section>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/venuemanagement.js"></script>
</body>
</html>