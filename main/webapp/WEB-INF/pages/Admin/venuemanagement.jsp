<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<button class="add-btn" id="openCreate"><i class="fa-solid fa-plus"></i></button>
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
		    <c:forEach var="venue" items="${listVenue}">
		      <tr>
		        <td><input type="checkbox"></td>
		        <td><c:out value='${venue.venueId}'/></td>
		        <td><c:out value='${venue.name}'/></td>
		        <td><c:out value='${venue.address}'/></td>
		        <td><c:out value='${venue.contactNumber}'/></td>
		        <td><c:out value='${venue.capacity}'/></td>
		        <td><c:out value='${venue.amenities}'/></td>
		        <td><c:out value='${venue.registeredDate}'/></td>
		        <td><c:out value='${venue.type}'/></td>
		        <td><span class="badge high"><c:out value='${venue.status}'/></span></td>
		        <td class="actions">
    				<form action="${pageContext.request.contextPath}/management" method="get">
				        <input type="hidden" name="action" value="edit">
				        <input type="hidden" name="venue_id" value="${venue.venueId}">
				        <button type="submit" class="edit-btn"><i class="fas fa-pen"></i></button>
				    </form>
  					<button><i class="fas fa-eye" onclick="openPanel()"></i></button>
  					<form action="${pageContext.request.contextPath}/management" method="post">
  						<input type="hidden" name="action" value="delete">
  						<input type="hidden" name="venue_id" value="<c:out value='${venue.venueId}'/>">
    					<button type="submit"><i class="fas fa-trash"></i></button>
		        	</form>
		        </td>
		      </tr>
		    </c:forEach>
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
	
	<div class="modal-container" id="modal-container-add">
		<div class="modal">
		<h1>Venue Detail</h1>
		<form action="${pageContext.request.contextPath}/management" method="post" class="form-grid" id="venue-form" enctype="multipart/form-data">
			<input type="hidden" name="action" value="create">
			<input type="text" name="venue_name" placeholder="venue name">
			<input type="text" name="address" placeholder ="address">
			<input type="text" name="city" placeholder="city">
			<input type="text" name="contact" placeholder="phone number">
			<input type="text" name="capacity" placeholder="capacity">
			<input type="text" name="amenities" placeholder="Amenities">
			<div class="input-box">
				<select class="type" name="venue_type" required>
					<option value="" disabled>Type</option>
					<option value="Ballroom" ${type == 'Ballroom' ? 'selected' : ''}>Ballroom</option>
					<option value="Banquet Hall" ${type == 'Banquet Hall' ? 'selected' : ''}>Banquet Hall</option>
				</select>
			</div>
			
			<div class="input-box">
				<select class="status" name="status" required>
					<option value="" disabled>Status</option>
					<option value="Available" ${status == 'Available' ? 'selected' : ''}>Available</option>
					<option value="Non-Available" ${status == 'Non-Available' ? 'selected' : ''}>Non-Available</option>
				</select>
			</div>
			<div class="file-con">
				  <label class="file-label" for="file-upload">Upload Image</label>
				  <input type="file" id="file-upload" class="file-box" name="venue_image"/>
			</div>
			<button type="submit">Submit</button>
		</form>
		<button id="closeCreate">Close</button>
		</div>
	</div>
	
	<div class="modal-container" id="modal-container-edit">
		<div class="modal">
		<h1>Venue Detail</h1>
			<form action="${pageContext.request.contextPath}/management" method="post" enctype="multipart/form-data">
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="venue_id" value="${selectedVenue.venueId}">
				<input type="text" name="venue_name" placeholder ="name" value="${selectedVenue.name}">
				<input type="text" name="address" placeholder ="address" value="${selectedVenue.address}">
				<input type="text" name="city" placeholder="city" value="${selectedVenue.city}">
				<input type="text" name="contact" placeholder="phone number" value="${selectedVenue.contactNumber}">
				<input type="text" name="capacity" placeholder="capacity" value="${selectedVenue.capacity}">
				<input type="text" name="amenities" placeholder="Amenities" value="${selectedVenue.amenities}">
				<div class="input-box">
	              	<select class="type" name="venue_type" required>
	                	<option value="" disabled>Type</option>
	                	<option value="Ballroom" ${selectedVenue.type == 'Ballroom' ? 'selected' : ''}>Ballroom</option>
	                	<option value="Banquet Hall" ${selectedVenue.type == 'Banquet Hall' ? 'selected' : ''}>Banquet Hall</option>
	              	</select>
            	</div>
            	<div class="input-box">
                	<select class="status" name="status" required>
                    	<option value="" disabled>Status</option>
                    	<option value="Available" ${selectedVenue.status == 'Available' ? 'selected' : ''}>Available</option>
                    	<option value="Non-Available" ${selectedVenue.status == 'Non-Available' ? 'selected' : ''}>Non-Available</option>
                	</select>
            	</div>
				<div class="file-con">
				  <label class="file-label" for="file-upload">Change Image</label>
				  <input type="file" id="file-upload" class="file-box" name="venue_image"/>
				</div>
				<button type="submit">Save changes</button>
			</form>
			<form action="${pageContext.request.contextPath}/management" method="get">
            	<button type="submit" id="closeEdit">Close</button>
        	</form>
		</div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/venuemanagement.js"></script>
	<script>
        <c:if test="${not empty selectedVenue}">
            document.getElementById('modal-container-edit').classList.add('show');
        </c:if>
    </script>
</body>
</html>