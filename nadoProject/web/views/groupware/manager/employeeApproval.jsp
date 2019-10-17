<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee, java.util.ArrayList"%>
<%
	Employee loginEmp = (Employee) session.getAttribute("loginEmp"); //267, 321 사용자 프로필
	ArrayList<Employee> list = (ArrayList<Employee>)request.getAttribute("list");  //411
	int listSize = list.size();
	
	//217 채팅 모달
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>NADO</title>
<style>

.inbox{
border: none; background: transparent;

}
	.popup{
	position:absolute; left:50%; top:50%; transform:translate(-50%, -50%);
	width:1000px; height:650px; box-shadow: 0 0 10px rgba(0,0,0,0.5);
	border-radius: 5px; text-align: center; padding:20px; background:#fff; opacity:0;}
	
	.pop{
		display:inline-block;
		width: 100px;
		height: 30px;
		border :1px solid black;
		text-decoration:none;
	}
	
	.popup:target{
	opacity :10;
	z-index:5;
	}
	
	#pop_left{
	width:50%;
	height:550px;
	float: left;
	}
	
	#pop_right{
	width:50%;
	height:550px;
	float: right;
	}
</style>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome 사이드 메뉴 앞에 아이콘 생성됨-->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style 전체적인 테마스타일 바뀜 왼쪽 사이드 메뉴도 바뀜-->
<link rel="stylesheet"
	href="/nado/resources/groupware/dist/css/AdminLTE.min.css">

<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. 색상들 변경됨-->
<link rel="stylesheet"
	href="/nado/resources/groupware/dist/css/skins/_all-skins.min.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/morris.js/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/jvectormap/jquery-jvectormap.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="/nado/resources/groupware/bower_components/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="/nado/resources/groupware/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
	<div class="wrapper">

		<header class="main-header">
			<!-- 로고 Logo -->
			<a href="/nado/views/groupware/main.jsp" class="logo"> <!-- 미니 사이드바 50x50px 미니 로고 mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>NADO</b></span> <!-- 모바일 디바이스 사이즈 로고 logo for regular state and mobile devices -->
				<span class="logo-lg"><b>NADO</b>Groupware</span>
			</a>
			
		<!-- 관리자 페이지 헤더 네비바 Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- 관리자 페이지 토글 버튼 Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> 
				<span class="sr-only">Toggle navigation</span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- 관리자 페이지 쪽지 토글 버튼 Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-envelope"></i> <span class="label label-success">3</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">3개의 새로운 쪽지가 있습니다.</li>
								<li>
									<!-- 관리자 페이지 쪽지 토글 눌렀을 때 드롭다운 메뉴 inner menu: contains the actual data -->
									<ul class="menu">
										<!-- 관리자 페이지 쪽지 내용 부분 시작 start message --> 
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="/nado/resources/groupware/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>보낸사람 이름<small><i class="fa fa-clock-o"></i> 시간</small></h4>
												<p>쪽지 내용</p>
											</a>
										</li>
										<!-- 관리자 페이지 쪽지 내용 부분 끝 end message -->
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="/nado/resources/groupware/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>AdminLTE Design Team <small><i class="fa fa-clock-o"></i> 2시간</small></h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="/nado/resources/groupware/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
												</div>
												<h4>Developers <small><i class="fa fa-clock-o"></i>오늘</small></h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img src="/nado/resources/groupware/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image"></div>
												<h4>Sales Department <small><i class="fa fa-clock-o"></i>어제</small></h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
										<li>
											<a href="#">
												<div class="pull-left">
													<img
														src="/nado/resources/groupware/dist/img/user4-128x128.jpg"
														class="img-circle" alt="User Image">
												</div>
												<h4>Reviewers <small><i class="fa fa-clock-o"></i> 2일</small></h4>
												<p>Why not buy a new awesome theme?</p>
											</a>
										</li>
									</ul>
								</li>
								<li class="footer"><a href="#">모든 쪽지 보기</a></li>
							</ul>
						</li><!-- 관리자 페이지 쪽지 드롭다운 메뉴 끝 -->
						
						<!-- 관리자 페이지 알림 토글 버튼 Notifications: style can be found in dropdown.less -->
						<li class="dropdown notifications-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="fa fa-bell"></i> <span class="label label-warning">14</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">14개의 알림이 있습니다.</li>
								<li>
									<!-- 관리자 페이지 알림 토글 버튼 드롭다운 메뉴 inner menu: contains the actual data -->
									<ul class="menu">
										<li>
											<a href="#"> <i class="fa fa-clipboard text-blue"></i>
												전자결재가 승인되었습니다.
										</a></li>
										<li><a href="#"> <i class="fa fa-calendar text-yellow"></i>
												오늘의 일정이 있습니다.
										</a></li>
										<li><a href="#"> <i class="fa fa-envelope text-purple"></i>
												새로운 메일이 도착했습니다.
										</a></li>
										<li><a href="#"> <i class="fa fa-table text-green"></i>
												새로운 댓글이 달렸습니다.
										</a></li>
										<li><a href="#"> <i class="fa fa-calendar-check-o text-red"></i>
												예약신청이 반려되었습니다.
										</a></li>
									</ul>
								</li>
								<li class="footer"><a href="#">전체 보기</a></li>
							</ul></li><!-- 관리자 페이지 알림 토글 메뉴 끝 -->
							
<!-- 						관리자 페이지 채팅 토글 버튼 Control Sidebar Toggle Button -->
<!-- 						<li><a href="#" data-toggle="control-sidebar"><i -->
<!-- 								class="fa fa-wechat"></i></a></li>관리자 페이지 채팅 토글 끝 -->

	<!-- 				관리자 페이지 채팅 토글 버튼 Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"><i class="fa fa-wechat"></i></a></li>

						
						<!-- 관리자 페이지 상단 프로필 영역 User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> 
							<!-- 관리자 페이지 오른쪽 상단 프로필 사진 -->
								<img src="/nado/resources/groupware/dist/img/혜진.png"
								class="user-image" alt="User Image"> <span
								class="hidden-xs">손혜진</span>
						</a>
							<!-- 관리자 페이지 프로필 부분 클릭했을 때 드롭다운 메뉴 -->
							<ul class="dropdown-menu">
								<!-- 프로필 사진 User image -->
								<li class="user-header"><img
									src="/nado/resources/groupware/dist/img/혜진.png"
									class="img-circle" alt="User Image">
									<p>인사부 팀장</p>
									<p>손혜진</p></li>
								<!-- 관리자 페이지 프로필 수정, 로그아웃 버튼 Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">프로필</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">로그아웃</a>
									</div>
								</li>
							</ul></li><!-- 관리자 페이지 프로필 영역 끝 -->
					</ul>
				</div>
			</nav>
		</header>

		<!-- 관리자 페이지 왼쪽 사이드바 시작 Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- 관리자 페이지 왼쪽 사이드바 사용자 정보 부분 Sidebar user panel -->
				<div class="user-panel">
					<div class="user-panel-image">
						<img src="/nado/resources/groupware/dist/img/혜진.png"
							class="img-circle" alt="User Image">
					</div>
					<br>
					<div class="user-panel-info">
						<p>인사부 팀장 손혜진</p>
						<button id="logout">로그아웃</button>
					</div>
				</div>

				<!-- 관리자 페이지 왼쪽 사이드바 검색 기능 부분 search form -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control" placeholder="검색">
						<span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- 관리자 페이지 왼쪽 사이드바 검색 기능 끝 /.search form -->
				
		<!-- 관리자 메뉴 -->
		<ul class="sidebar-menu" data-widget="tree">
			<li><a href="/nado/apuser.ad" style="background:#f3f1fa;"> <i
					class="fa fa-drivers-license"></i> <span>사용자승인관리</span>
			</a></li>
			<li><a href="#"> <i class="fa fa-group"></i> <span>조직도</span>
			</a></li>
			<li class="treeview"><a href="#"><i class="fa fa-list"></i><span>게시판
						관리</span> <span class="pull-right-container"><i
						class="fa fa-angle-left pull-right"></i> </span> </a>
				<ul class="treeview-menu">
					<li><a href="#"><i class="fa fa-angle-right"></i>공지사항</a></li>
					<li><a href="#"><i class="fa fa-angle-right"></i>설문조사</a></li>
					<li><a href="#"><i class="fa fa-angle-right"></i>자유게시판</a></li>
				</ul></li>
			<li><a href="calendar/calendarMonthView.jsp"><i
					class="fa fa-clipboard"></i> <span>전자결재양식</span></a></li>
		</ul>

		</section>
		<!-- 관리자 메뉴 끝 /.sidebar -->
		</aside>
		
		<!-- Content Wrapper. Contains page content  가운데 작업 영역-->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					사용자 승인 관리 <small>사용자</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-home"></i> 홈</a></li>
					<li class="active">사용자 승인 관리</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
			<% if(listSize >0) {
			for(Employee emp : list){
			%>
			<form action="/nado/empapp.ad" method="post">
			<div class="popup" id="<%=emp.getEmpId()%>">
			<!-- 왼쪽 사용자 입력 사항 -->
			<div id="pop_left">
				<table>
			<tr>
				<th><label for="username">이름</label></th>
				</tr><tr>
					<td>
					<input type="text" class="inbox" name="username" id="username" value="<%= emp.getEmpName() %>" readonly>
					</td>
					</tr>
					<tr>
					<th><label for="userid">아이디</label></th>
					</tr>
					<tr>
					<td>
					<input type="text" class="inbox"name="userid" id="userid" value="<%= emp.getUserId() %>" readonly>
					</td>
					</tr>
					<tr>
					<th><label for="empno">생년월일</label></th>
					</tr><tr>
					<td>
					<input type="text" class="inbox"name="empno" id="empno" value="<%= emp.getEmpNo() %>" readonly>
					</td>
					</tr>
					<tr>
					<th><label for="email">이메일</label></th>
					</tr><tr>
					<td>
					<input type="text" class="inbox"name="email" id="email" value="<%= emp.getEmail() %>" readonly>
					</td>
					</tr>
					<tr>
					<th><label for="phone">휴대전화</label></th>
					</tr><tr>
					<td>
					<input type="text" class="inbox"name="phone" id="phone" value="<%= emp.getPhone() %>" readonly>
					</td>
					</tr>
					<tr>
					<th><label for="address">주소</label></th>
					</tr><tr>
					<td>
					<input type="text" class="inbox"name="address" id="address" value="<%= emp.getAddress() %>" readonly>
					</td>
					</tr>
				</table>
			</div>
			<!--  오른쪽 관리자 입력 사항 -->
			<div id="pop_right">
			<table>
			<tr>
				<th><label for="empid">사번</label></th>
				</tr><tr>
					<td>
					<input type="text" class="inbox" name="empid" id="empid" >
					</td>
					</tr>
					<tr>
					<th><label for="idlevel">계정구분</label></th>
					</tr>
					<tr>
					<td>
					<input type="radio" name="idlevel" value="G" >일반사원
					<input type="radio" name="idlevel" value="M">관리자
					</td>
					</tr>
					<tr>
					<th><label for="deptid">부서</label></th>
					</tr><tr>
					<td>
					<select name="deptid" id="deptid">		
					<option value="A1">관리팀</option>
					<option value="A2">인사팀</option>
					<option value="B1">개발1팀</option>
					<option value="B2">개발2팀</option>
					<option value="C1">디자인팀</option>
					<option value="D1">영업팀</option>
					
					</td>
					</tr>
					<tr>
					<th><label for="jobid">직책</label></th>
					</tr><tr>
					<td>
					<select name="jobid" id="jobid" >
					<option value="J1">대표이사</option>
					<option value="J2">부사장</option>
					<option value="J3">부장</option>
					<option value="J4">차장</option>
					<option value="J5">과장</option>
					<option value="J6">대리</option>
					<option value="J7">사원</option>
					
					</select>
					<select name="paystep" id="paystep">
					<option value="1호봉">1호봉</option>
					<option value="2호봉">2호봉</option>
					<option value="3호봉">3호봉</option>
					</select>
					</td>
					</tr>
					<tr>
					<th><label for="state">상태</label></th>
					</tr><tr>
					<td>
					<input type="radio" name="state"  value="재직">재직
					<input type="radio" name="state"  value="휴직">휴직
					</td>
					</tr>
				</table>
			</div>
			<span class="pop"><button type="submit" style="background:none;">승인</button></span>&nbsp;
			<span class="pop"><button>거절</button></span></div>
			</form>
			<%}} %>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">승인 대기 신청자</h3>
				 <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>이름</th>
                  <th>아이디</th>
                  <th>신청일</th>
                  <th>승인</th>
                </tr>
                </thead>
                <tbody>
                <% if(listSize>0){ 
                for(Employee emp : list){
                %>
                <tr> 
                  <td><%= emp.getEmpName() %></td>
                  <td><%= emp.getUserId() %></td>
                  <td><%= emp.getHireDate() %></td>                  
                  <td><a href="#<%=emp.getEmpId() %>"> 확인 후 승인</a></td>
                  <td><a data-target="#<%=emp.getEmpId() %>" data-toggle="modal"> 확인 후 승인</a></td>
                  
                </tr>     
                <%}} %>
                </tbody>
              </table>
             
            </div>
            
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
				</div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
				<div class="row">
					<div class="modal" id="" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button class="close" data-dismiss="modal">&times;</button>
								</div>
							</div>
						</div>
					</div>
				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2019 <a href="/nado/index.jsp">NADO
					Groupware</a>.
			</strong> All rights reserved.
		</footer>

		<!-- 오른쪽 채팅 사이드바 Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark"
			style="display: none;"></aside>
		<!-- 오른쪽 채팅 사이드바 끝 /.control-sidebar -->
		
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<script
		src="/nado/resources/groupware/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script
		src="/nado/resources/groupware/bower_components/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="/nado/resources/groupware/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Morris.js charts -->
	<script
		src="/nado/resources/groupware/bower_components/raphael/raphael.min.js"></script>
	<script
		src="/nado/resources/groupware/bower_components/morris.js/morris.min.js"></script>
	<!-- Sparkline -->
	<script
		src="/nado/resources/groupware/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- jvectormap -->
	<script
		src="/nado/resources/groupware/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="/nado/resources/groupware/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- jQuery Knob Chart -->
	<script
		src="/nado/resources/groupware/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script
		src="/nado/resources/groupware/bower_components/moment/min/moment.min.js"></script>
	<script
		src="/nado/resources/groupware/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script
		src="/nado/resources/groupware/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script
		src="/nado/resources/groupware/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script
		src="/nado/resources/groupware/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="/nado/resources/groupware/bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="/nado/resources/groupware/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="/nado/resources/groupware/dist/js/pages/dashboard.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="/nado/resources/groupware/dist/js/demo.js"></script>
</body>
</html>