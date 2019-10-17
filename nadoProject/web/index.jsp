<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cloud.model.vo.Cloud" %>
<%
	Cloud loginUser = (Cloud)session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="The River template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/nado/resources/homepage/css/bootstrap-4.1.2/bootstrap.min.css">
<link href="/nado/resources/homepage/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/nado/resources/homepage/plugins/OwlCarousel2-2.3.4/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="/nado/resources/homepage/plugins/OwlCarousel2-2.3.4/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="/nado/resources/homepage/plugins/OwlCarousel2-2.3.4/animate.css">
<link href="/nado/resources/homepage/plugins/jquery-datepicker/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/nado/resources/homepage/plugins/colorbox/colorbox.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/nado/resources/homepage/css/main_styles.css">
<link rel="stylesheet" type="text/css" href="/nado/resources/homepage/css/responsive.css">
<title>NADO Groupware</title>
</head>
<body>

<div class="super_container">
	
	<!-- Header -->

	<header class="header">
		<div class="header_content d-flex flex-row align-items-center justify-content-start">
			<div class="logo"><a href="/nado/index.jsp"><b>NADO</b></a></div>
			<div class="ml-auto d-flex flex-row align-items-center justify-content-start">
				<nav class="main_nav">
					<ul class="d-flex flex-row align-items-start justify-content-start">
						<li class="active"><a href="/nado/index.jsp">홈</a></li>
						<li><a href="/nado/views/homepage/about.html">기능 소개</a></li>
						<li><a href="/nado/views/homepage/about.html">상품/가격 안내</a></li>
						<li><a href="#">고객지원</a></li>
						<% if(loginUser != null){ %>
							<% if(loginUser.getClId().equals("cloudadmin")){ %>
							<li><a href="/nado/views/groupware/main.jsp">관리 페이지로 이동</a></li>
							<%}else{ %>
							<li><a href="/nado/views/homepage/companyLogin.jsp">그룹웨어로 이동</a></li>
							<%} %>
							</ul>
				</nav>
				<div class="book_button"><a href="/nado/cllo">로그아웃</a></div>
							
						<%}else{ %>
					</ul>
				</nav>
				<div class="book_button"><a href="/nado/views/homepage/login.jsp">로그인</a></div>
				<div class="book_button"><a href="/nado/views/homepage/employeeEnroll.html">회원가입</a></div>
				<%}%>
				<!-- Hamburger Menu -->
				<div class="hamburger"><i class="fa fa-bars" aria-hidden="true"></i></div>
			</div>
		</div>
	</header>

	<!-- Menu -->

	<div class="menu trans_400 d-flex flex-column align-items-end justify-content-start">
		<div class="menu_close"><i class="fa fa-times" aria-hidden="true"></i></div>
		<div class="menu_content">
			<nav class="menu_nav text-right">
				<ul>
					<li><a href="/nado/index.jsp">홈</a></li>
					<li><a href="/nado/views/homepage/about.html">기능 소개</a></li>
					<li><a href="/nado/views/homepage/blog.html">상품/가격 안내</a></li>
					<li><a href="/nado/views/homepage/contact.html">고객지원</a></li>
					<li><a href="/nado/views/groupware/main.jsp">그룹웨어로 이동</a></li>
				</ul>
			</nav>
		</div>
		<div class="menu_extra">
			<div class="menu_extra"><a href="/nado/views/homepage/login.jsp">로그인</a></div>
			<div class="menu_extra"><a href="/nado/views/homepage/employeeEnroll.html">회원가입</a></div>
		</div>
	</div>

	<!-- Home -->

	<div class="home">
		<div class="home_slider_container">
			<div class="owl-carousel owl-theme home_slider">
				
				<!-- Slide -->
				<div class="slide">
					<div class="background_image" style="background-image:url(/nado/resources/homepage/images/Mobile-Computer.jpg)"></div>
					<div class="home_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="home_content text-center">
										<div class="home_title">NADO Groupware</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Slide -->
				<div class="slide">
					<div class="background_image" style="background-image:url(/nado/resources/homepage/images/qa-business-computer-support.jpg)"></div>
					<div class="home_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="home_content text-center">
										<div class="home_title">NADO Groupware</div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Slide -->
				<div class="slide">
					<div class="background_image" style="background-image:url(/nado/resources/homepage/images/1.jpg)"></div>
					<div class="home_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="home_content text-center">
										<div class="home_title">NADO Groupware</div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			
			<!-- Home Slider Dots -->
			<div class="home_slider_dots_container">
				<div class="home_slider_dots">
					<ul id="home_slider_custom_dots" class="home_slider_custom_dots d-flex flex-row align-items-start justify-content-start">
						<li class="home_slider_custom_dot active">●</li>
						<li class="home_slider_custom_dot">●</li>
						<li class="home_slider_custom_dot">●</li>
					</ul>
				</div>
			</div>
			
		</div>
	</div>
	

	<!-- About -->

	<div class="about">
		<div class="container">
			<div class="row">
				
				<!-- About Content -->
				<div class="col-lg-6">
					<div class="about_content">
						<div class="about_title"><h2></h2></div>
						<div class="about_text">
						<br><br><br><br><br><br><br>
							<div>
								<img id="i_1" width="600" height="300" src="/nado/resources/homepage/images/전자결재_상세보기.png">
							</div>
						</div>
					</div>
				</div>

				<!-- About Images -->
				<div class="col-lg-6">
				<div class="about_content">
				<br><br>
						<div class="about_title" id="title11"><h2>전자결재</h2></div>
						<div class="about_text" id="content11"><h4>실시간 결재로 빠르게 의사결정을 진행하세요.</h4></div>
						<br><br>
					<div class="about_images d-flex flex-row align-items-center justify-content-between flex-wrap">
			
							
							<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="1"><img src="/nado/resources/homepage/images/check.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>전자결재</h4></div>
					</div>
				</div>
				
							<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="2"><img src="/nado/resources/homepage/images/envelope.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>전자메일</h4></div>
					</div>
				</div>
				
				<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="3"><img src="/nado/resources/homepage/images/calendar.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>캘린더</h4></div>
					</div>
				</div>
				
				<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="4"><img src="/nado/resources/homepage/images/blackboard.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>게시판</h4></div>
					</div>
				</div>
				
				<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="5"><img src="/nado/resources/homepage/images/network.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>조직도</h4></div>
					</div>
				</div>
				
				<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="6"><img src="/nado/resources/homepage/images/feedback.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>채팅</h4></div>
					</div>
				</div>
				
				<!-- Icon Box -->
				<div class="col-lg-4 icon_box_col">
					<div class="icon_box d-flex flex-column align-items-center justify-content-start text-center">
						<div class="icon_box_icon"><a id="7"><img src="/nado/resources/homepage/images/agenda.svg" style="cursor:pointer" class="svg" alt="https://www.flaticon.com/authors/monkik"></a></div>
						<div class="icon_box_title"><h4>주소록</h4></div>
					</div>
				</div>
			
						<script type="text/javascript" src="/nado/resources/homepage/js/jquery-3.3.1.min.js"></script>
						<script type="text/javascript">
							
							$(function(){
								$('icon_box_icon').mouseover(function(){
									$('icon_box_icon').css('cursor', 'pointer');	
								});
								
								$('#1').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/전자결재_상세보기.png');
									$('#title11').html('<h2>전자결재</h2>');
									$('#content11').html('<h4>실시간 결재로 빠르게 의사결정을 진행하세요.</h4>');
								});
								
								$('#2').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/전자메일_메일쓰기.png');
									$('#title11').html('<h2>전자메일</h2>');
									$('#content11').html('<h4>회사 도메인을 사용하는 업무 전용 기업 메일</h4>');
								});
								
								$('#3').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/캘린더_월별 캘린더.png');
									$('#title11').html('<h2>캘린더</h2>');
									$('#content11').html('<h4>프로젝트 상황이 한눈에 들어오는 일정관리</h4>');
								});
								
								$('#4').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/게시판_공지사항.png');
									$('#title11').html('<h2>게시판</h2>');
									$('#content11').html('<h4>협업과 정보 공유를 위한 사내 게시판</h4>');
								});
								
								$('#5').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/조직도.png');
									$('#title11').html('<h2>조직도</h2>');
									$('#content11').html('<h4>조직과 사용자를 더욱 편리하게 생성, 관리하는 인사관리</h4>');
								});
								
								$('#6').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/사용자 메인페이지_채팅.png');
									$('#title11').html('<h2>채팅</h2>');
									$('#content11').html('<h4>사내 커뮤니케이션을 위한 업무 전용 메신저</h4>');
								});
								
								$('#7').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/주소록_주소록 추가.png');
									$('#title11').html('<h2>주소록</h2>');
									$('#content11').html('<h4>연락처 공유와 관리가 수월해지는 주소록</h4>');
								});
								
								$('#8').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/gallery_3.jpg');
								});
								
								$('#9').click(function(){
									$('#i_1').attr('src', '/nado/resources/homepage/images/gallery_1.jpg');
								});
							});
							
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Footer -->

	<footer class="footer">
		<div class="footer_content">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="footer_logo_container text-center">
							<div class="footer_logo">
								<a href="#"></a>
								<div>NADO</div>
								<div>since 2019</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row footer_row">
					
					<!-- Newsletter -->
						<div class="footer_title">NADO
						<div class="footer_list">
							<ul>
								<li>(주)나도 경기도 의정부시 대표이사 손혜진</li>
								<li>대표전화 1111-1111 사업자등록번호: 211-88-33333 통신판매업 신고번호: 제2019-경기의정부-1111호</li>
						
							</ul>
							</div>
						</div>


					<!-- Footer images -->
					<div class="col-lg-3">
						<div class="certificates d-flex flex-row align-items-start justify-content-lg-between justify-content-start flex-lg-nowrap flex-wrap">
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="copyright">
		
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->

Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#" target="_blank">NADO</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</div>
	</footer>
</div>

<script src="/nado/resources/homepage/js/jquery-3.3.1.min.js"></script>
<script src="/nado/resources/homepage/css/bootstrap-4.1.2/popper.js"></script>
<script src="/nado/resources/homepage/css/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="/nado/resources/homepage/plugins/greensock/TweenMax.min.js"></script>
<script src="/nado/resources/homepage/plugins/greensock/TimelineMax.min.js"></script>
<script src="/nado/resources/homepage/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/nado/resources/homepage/plugins/greensock/animation.gsap.min.js"></script>
<script src="/nado/resources/homepage/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/nado/resources/homepage/plugins/OwlCarousel2-2.3.4/owl.carousel.js"></script>
<script src="/nado/resources/homepage/plugins/easing/easing.js"></script>
<script src="/nado/resources/homepage/plugins/progressbar/progressbar.min.js"></script>
<script src="/nado/resources/homepage/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/nado/resources/homepage/plugins/jquery-datepicker/jquery-ui.js"></script>
<script src="/nado/resources/homepage/plugins/colorbox/jquery.colorbox-min.js"></script>
<script src="/nado/resources/homepage/js/custom.js"></script>
</body>
</html>