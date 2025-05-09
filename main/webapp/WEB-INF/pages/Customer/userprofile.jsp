<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Knot Spot</title>
<!-- This connects to profile.css file -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userprofile.css">

<!-- This gives access to google font "inter" -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
<!-- This gives access to font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<main class="container">
		<div class="arrow"><a href="${pageContext.request.contextPath}/home"><i class="fa-solid fa-arrow-left"></i>My Profile</a></div>
		<div class="user-con">
			<div class="fst-sub-con">
				<img src="${pageContext.request.contextPath}/resources/userpfp.jpg" alt="user-img" class="user-img">
				<div class="buttons">
					<button class="change-btn">Change picture</button>
					<button class="del-btn">Delete picture</button>
				</div>
				<p>At least 800 x 800 px recommended.<br>JPG or PNG is allowed</p>
			</div>
			<form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
				<div class="sec-sub-con">
					<div class="detail-row light">
					  <div class="field">Name</div><div class="colon">:</div><div class="input-name"><input type="text" name="first_name" /><input type="text" name="last_name"/></div>
					</div>
					<div class="detail-row white">
					  <div class="field">Role</div><div class="colon">:</div><div class="inputs"><input type="text" name="role_id" /></div>
					</div>
					<div class="detail-row light">
					  <div class="field">Email</div><div class="colon">:</div><div class="inputs"><input type="email" name="email" /></div>
					</div>
					<div class="detail-row white">
					  <div class="field">Contact</div><div class="colon">:</div><div class="inputs"><input type="tel" name="contact_no" /></div>
					</div>
					<div class="detail-row light">
					  <div class="field">Gender</div><div class="colon">:</div><div class="inputs"><input type="text" name="gender" /></div>
					</div>
					<div class="detail-row white">
					  <div class="field">Username</div><div class="colon">:</div><div class="inputs"><input type="text" name="username" /></div>
					</div>
				</div>
			</form>
			<div class="thd-sub-con">
				<button class="cancel-btn">Cancel</button>
      			<button class="save-btn">Save changes</button>
			</div>
		</div>
	</main>
</body>
</html>