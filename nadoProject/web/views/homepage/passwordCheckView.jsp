<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="cloud.model.vo.Cloud"%>
<%
   Cloud loginUser = (Cloud)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>NADO Cloud</title>
<meta charset="UTF-8">
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
<script src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<style type="text/css">
.main_nav li:not(:last-of-type){
   margin-right: 49px;
}
</style>
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
                     <li><a href="/nado/views/homepage/priceGuidanceView.jsp">상품/가격 안내</a></li>
                     <li><a href="/nado/views/homepage/subscriptView.jsp">그룹웨어 신청</a></li>
                     <li><a href="/nado/views/homepage/customerSupportView.jsp">고객지원</a></li>
                     <% if(loginUser != null){ %>
                     <li><a href="/nado/views/homepage/passwordCheckView.jsp">내 정보 수정</a></li>
                     <% if(loginUser.getClId().equals("nado")){ %>
                     <li><a href="/nado/views/homepage/cloudAdminMain.jsp">관리 페이지로 이동</a></li>
                     <%}else{ %>
                     <li><a href="/nado/views/homepage/companyLogin.jsp">그룹웨어로 이동</a></li>
                     <%} %>
                  </ul>
               </nav>
               <div class="book_button">
                  <a href="/nado/cllo">로그아웃</a>
               </div>

               <%}else{ %>
               </ul>
               </nav>
               <div class="book_button">
                  <a href="/nado/views/homepage/login.jsp">로그인</a>
               </div>
               <div class="book_button">
                  <a href="/nado/views/homepage/cloudEnroll.jsp">회원가입</a>
               </div>
               <%}%>
               <!-- Hamburger Menu -->
               <div class="hamburger">
                  <i class="fa fa-bars" aria-hidden="true"></i>
         </div>
      </div>
   </header>

      <!-- Menu -->

      <div
         class="menu trans_400 d-flex flex-column align-items-end justify-content-start">
         <div class="menu_close">
            <i class="fa fa-times" aria-hidden="true"></i>
         </div>
         <div class="menu_content">
            <nav class="menu_nav text-right">
               <ul>
                  <li><a href="/nado/index.jsp">홈</a></li>
                  <li><a href="/nado/views/homepage/priceGuidanceView.jsp">상품/가격 안내</a></li>
                  <li><a href="/nado/views/homepage/subscriptView.jsp">그룹웨어 신청</a></li>
                  <li><a href="/nado/views/homepage/customerSupportView.jsp">고객지원</a></li>
                  <% if(loginUser != null){ %>
                  <li><a href="/nado/views/homepage/passwordCheckView.jsp">내 정보 수정</a></li>
                  <% if(loginUser.getClId().equals("nado")){ %>
                  <li><a href="/nado/views/homepage/cloudAdminMain.jsp">관리페이지로 이동</a></li>
                  <% } else { %>
                  <li><a href="/nado/views/homepage/companyLogin.jsp">그룹웨어로 이동</a></li>
                  <% } %>
                  <br>
                  <li><a href="/nado/cllo">로그아웃</a></li>
                  <% } else { %>
                  <br>
                  <li><a href="/nado/views/homepage/login.jsp">로그인</a></li>
                  <li><a href="/nado/views/homepage/cloudEnroll.jsp">회원가입</a>
                     <% } %>
               </ul>
            </nav>
         </div>
      </div>

      <!-- Home -->

      <div class="home">
      	<div class="container">
      		<div class="row" style="margin-top:180px; text-align:center;">
      			<div class="col-lg-4">
      			</div>
      			<div class="col-lg-4">
      				<div style="padding:30px; font-size:30px; color:black;">
      						회원정보수정
      				</div>
      				<div class="card">
      					<div class="card-body">
		      				<form id="check_pwd">
		      					<div class="form-group">
		      						<label for="user_pwd" style="float:left; color:black;">비밀번호확인</label>
		      						<input class="form-control" type="password" name="user_pwd" id="user_pwd" placeholder="비밀번호를 입력하세요.">	
		      						<input type="hidden" name="user_id" id="user_id" value="<%= loginUser.getClId() %>">			
		      					</div>
		      					<div class="form-group" style="margin:20px 0 0 0;">
		      						<input type="submit" style="background:#c7bee8;color:black;"class="form-control" value="확인">					
		      					</div>
		      				</form>
      					</div>
      				</div>
      			</div>
      			<div class="col-lg-4">
      			</div>
      		</div>
      	</div>
      </div>


      <!-- About -->

      <div class="about">
         
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
                     <div class="footer_title">
                        NADO
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

               Copyright &copy;
               <script>document.write(new Date().getFullYear());</script>
               All rights reserved | This template is made with 
               <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#"target="_blank">NADO</a>
               <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </div>
         </footer>
      </div>
	  <script src="/nado/resources/js/jquery.validate.js"></script>
	  <script src="/nado/resources/js/additional-methods.js"></script>
	  <script src="/nado/resources/js/messages_ko.js"></script>
      <script src="/nado/resources/js/bootstrap.js"></script>
	  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      <script type="text/javascript" src="/nado/resources/js/junEnroll.js"></script>
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