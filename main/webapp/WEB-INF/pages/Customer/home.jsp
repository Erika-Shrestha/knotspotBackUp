<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Knot spot</title>
<!-- This connects to dashboard.css file -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">


<!-- This gives access to google font "inter" -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

<!-- This gives access to font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

</head>
<body>
	<header class="header-con sticky">
		<%@ include file="header.jsp" %>
	</header>
	<div class="scroll-down">
		<p>Scroll down</p>
		<i class="fa-solid fa-computer-mouse"></i>
	</div>
	<main class="container">
		<section class="video-scroll-section">
			<div class="scroll-container">
				<video preload="auto" id="scrollVideo" playsinline>
					<source src="${pageContext.request.contextPath}/resources/home.mp4" type="video/mp4">
				</video>
			</div>
			<div class="blur-con">
				<h1 class="blurShow one">Where</h1>
				<h1 class="blurShow two">Every</h1>
				<h1 class="blurShow three">Knot</h1>
				<h1 class="blurShow four">Ties A</h1>
				<h1 class="blurShow five">Beautiful Memories</h1>
			</div>
			<div class="intro-con">
				<h1 class="autoShow"><span>This is not just an ordinary event planners</span>
				<span class="paragraph-span">Since 2020,  Knot spot has blended tradition and innovations to serve our customers with unique experience. 
				Guided by “Wed me good” we offer luxurious service, and Nepal's signature warmth for a truly memorable wedding experience.</span></h1>
			</div>
			<div class="img-box">
				<img src="${pageContext.request.contextPath}/resources/companybatch.png" class="autoRotate">
			</div>
		</section>
		<div class="space"></div>
		<div class="card">
				<div class="image-wrapper">
					<img src="${pageContext.request.contextPath}/resources/missionimg.jpg" alt="test_image">
				</div>
			</div>
		<div class="space"></div>
	</main>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/home.js"></script>
</body>
</html>