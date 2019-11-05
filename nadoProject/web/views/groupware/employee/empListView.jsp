<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee,employee.model.vo.Dept, java.util.ArrayList"%>
<%
   Employee loginEmp = (Employee) session.getAttribute("loginEmp"); //267, 321 사용자 프로필
   String manager = loginEmp.getIdLevel(); //340 줄

   // 521줄 560줄 까지 관리자 메뉴 
   
   ArrayList<Employee> list = (ArrayList<Employee>)request.getAttribute("list");
   ArrayList<Dept> dlist = (ArrayList<Dept>)request.getAttribute("dlist");
   Employee employee = (Employee)request.getAttribute("employee");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>NADO</title>


<script type="text/javascript" src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/nado/resources/groupware/tree_fontello/css/fontello.css">
<style type="text/css">
.emptree


.emptree, .emptree ul {
	list-style: none;
	padding-left: 25px;
}

.emptree * :before {
	width: 22px;
	height: 20px;
	display: inline-block;
}

.emptree label:before {
	content: '\e801';
	font-family: fontello;
}
/* .emptree a{
	text-decoration: none;
} */
.emptree a:before {
	content: '\f2c0';
	font-family: fontello;
}

.emptree input[type="checkbox"] {
	display: none;
}

.emptree input[type="checkbox"]:checked ~ul {
	display: none;
}

.emptree input[type="checkbox"]:checked+label:before {
	content: '\e800';
	font-family: fontello;
}

</style>




<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="/nado/resources/groupware/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome 사이드 메뉴 앞에 아이콘 생성됨-->
<link rel="stylesheet" href="/nado/resources/groupware/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="/nado/resources/groupware/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style 전체적인 테마스타일 바뀜 왼쪽 사이드 메뉴도 바뀜-->
<link rel="stylesheet" href="/nado/resources/groupware/dist/css/AdminLTE.min.css">

<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. 색상들 변경됨-->
<link rel="stylesheet" href="/nado/resources/groupware/dist/css/skins/_all-skins.min.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet" href="/nado/resources/groupware/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>


<body class="hold-transition skin-purple-light sidebar-mini">

   <!-- 공통 -->
   <div class="wrapper">
      <header class="main-header">
      
         <!-- 로고 Logo -->
         <a href="/nado/views/groupware/main.jsp" class="logo"> <!-- 미니 사이드바 50x50px 미니 로고 mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>NADO</b></span> <!-- 모바일 디바이스 사이즈 로고 logo for regular state and mobile devices -->
            <span class="logo-lg"><b>NADO</b>Groupware</span>
         </a>
         
         <!-- 일반 사원 페이지 -->
         <% if (manager.equals("G") || manager.equals("M")) { %>
         <!-- 헤더 네비바 Header Navbar: style can be found in header.less -->
         <nav class="navbar navbar-static-top">
            <!-- 토글 버튼 Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> 
               <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
               <ul class="nav navbar-nav">
                  <!-- 쪽지 토글 버튼 Messages: style can be found in dropdown.less-->
                  <li class="dropdown messages-menu">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                        <i class="fa fa-envelope"></i> 
                           <span class="label label-success">4</span>
                     </a>
                     <ul class="dropdown-menu">
                        <li class="header">4개의 새로운 쪽지가 있습니다.</li>
                        <li>
                           <!-- 쪽지 토글 눌렀을 때 드롭다운 메뉴 inner menu: contains the actual data -->
                           <ul class="menu">
                              <!-- 쪽지 내용 부분 시작 start message --> 
                              <li>
                                 <a href="#">
                                    <div class="pull-left">
                                       <img src="/nado/resources/groupware/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                    </div>
                                    <h4>보낸사람 이름<small><i class="fa fa-clock-o"></i> 시간</small></h4>
                                    <p>쪽지 내용</p>
                                 </a>
                              </li>
                              <!-- 쪽지 내용 부분 끝 end message -->
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
                  </li><!-- 쪽지 드롭다운 메뉴 끝 -->
                  
                  <!-- 알림 토글 버튼 Notifications: style can be found in dropdown.less -->
                  <li class="dropdown notifications-menu">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                         <i class="fa fa-bell"></i> <span class="label label-warning">10</span>
                     </a>
                     <ul class="dropdown-menu">
                        <li class="header">10개의 알림이 있습니다.</li>
                        <li>
                           <!-- 알림 토글 버튼 드롭다운 메뉴 inner menu: contains the actual data -->
                           <ul class="menu">
                              <li>
                                 <a href="#"> <i class="fa fa-clipboard text-blue"></i>
                                    전자결재가 승인되었습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-calendar text-yellow"></i>
                                    오늘의 일정이 있습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-envelope text-purple"></i>
                                    새로운 메일이 도착했습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-table text-green"></i>
                                    새로운 댓글이 달렸습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-calendar-check-o text-red"></i>
                                    예약신청이 반려되었습니다.
                                 </a>
                              </li>
                           </ul>
                        </li>
                        <li class="footer"><a href="#">전체 보기</a></li>
                     </ul>
                  </li>
                  <!-- 알림 토글 메뉴 끝 -->
                     
                  <!-- 채팅 토글 버튼 Control Sidebar Toggle Button -->
                  <li>
                     <a href="#" data-toggle="control-sidebar">
                        <i class="fa fa-wechat"></i>
                     </a>
                  </li>
                  <!-- 채팅 토글 끝 -->
                  
                  <!-- 상단 프로필 영역 User Account: style can be found in dropdown.less -->
                  <li class="dropdown user user-menu">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                        <!-- 오른쪽 상단 프로필 사진 -->
                        <% if(loginEmp.getMyProfile() != null) { %>
                        <img src="/nado/resources/groupware/employee_profile/<%= loginEmp.getMyProfile() %>" class="user-image" alt="User Image"> 
                        <% } else { %>
                        <img src="/nado/resources/groupware/employee_profile/default-profile.png" class="user-image" alt="User Image"> 
                        <% } %>
                        <span class="hidden-xs"><%= loginEmp.getEmpName() %></span>
                     </a>
                     <!-- 프로필 부분 클릭했을 때 드롭다운 메뉴 -->
                     <ul class="dropdown-menu">
                        <!-- 프로필 사진 User image -->
                        <li class="user-header">
                        <% if(loginEmp.getMyProfile() != null) { %>
                        <img   src="/nado/resources/groupware/employee_profile/<%= loginEmp.getMyProfile() %>" class="img-circle" alt="User Image">
                        <% } else { %>
                        <img src="/nado/resources/groupware/employee_profile/default-profile.png" class="img-circle" alt="User Image">
                        <% } %>
                           <p><%= loginEmp.getDeptName() %> <%= loginEmp.getJobName() %></p>
                           <p><%= loginEmp.getEmpName() %></p>
                        </li>
                        <!-- 프로필 수정, 로그아웃 버튼 Menu Footer-->
                        <li class="user-footer">
                           <div class="pull-left">
                              <a href="#" class="btn btn-default btn-flat">프로필</a>
                           </div>
                           <div class="pull-right">
                              <a href="/nado/emplo" class="btn btn-default btn-flat">로그아웃</a>
                           </div>
                        </li>
                     </ul>
                  </li>
                  <!-- 프로필 영역 끝 -->
               </ul>
            </div>
         </nav>
      </header>

      <!-- 왼쪽 사이드바 시작 Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
         <!-- sidebar: style can be found in sidebar.less -->
         <section class="sidebar">
            <!-- 왼쪽 사이드바 사용자 정보 부분 Sidebar user panel -->
            <div class="user-panel">
               <div class="user-panel-image">
                  <% if(loginEmp.getMyProfile() != null) { %>
                  <img src="/nado/resources/groupware/employee_profile/<%= loginEmp.getMyProfile() %>" class="img-circle" alt="User Image">
                  <% } else { %>
                  <img src="/nado/resources/groupware/employee_profile/default-profile.png" class="img-circle" alt="User Image">
                  <% } %>
               </div>
               <br>
               <div class="user-panel-info">
                  <p><%= loginEmp.getDeptName() %> <%= loginEmp.getJobName() %> <%= loginEmp.getEmpName() %></p>
                  <input type="button" id="logout" onclick="location='/nado/emplo'" value="로그아웃">
               </div>
            </div>

            <!-- 왼쪽 사이드바 검색 기능 부분 search form -->
            <form action="#" method="get" class="sidebar-form">
               <div class="input-group">
                  <input type="text" name="q" class="form-control" placeholder="검색">
                  <span class="input-group-btn">
                     <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                     </button>
                  </span>
               </div>
            </form>
            <!-- 왼쪽 사이드바 검색 부분 끝 /.search form -->

            <!-- 왼쪽 사이드바 메뉴 시작 sidebar menu: : style can be found in sidebar.less  -->
            <ul class="sidebar-menu" data-widget="tree">

               <!-- 전자결재 메뉴 -->
               <li class="treeview">
                  <a href="#"> <i class="fa fa-clipboard"></i> <span>전자결재</span>
                     <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="/nado/views/groupware/approval/approvalSendForm.jsp">
                           <i class="fa fa-angle-right"></i> 기안하기
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/approval/approvalListView.jsp">
                           <i class="fa fa-angle-right"></i> 진행중인 문서
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/approval/approvalDocumentView.jsp">
                           <i class="fa fa-angle-right"></i> 문서함
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/approval/approvalSettingView.jsp">
                           <i class="fa fa-angle-right"></i> 설정
                        </a>
                     </li>
                  </ul>
               </li>

               <!-- 조직도 메뉴 -->
               <li class="notreeview">
                  <a href="/nado/emplist"> 
                     <i class="fa fa-group"></i> <span>조직도</span>
                  </a>
               </li>

               <!-- 전자메일 메뉴 -->
               <li class="treeview">
                  <a href="#"> <i class="fa fa-envelope"></i> <span>전자메일</span> 
                     <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="/nado/views/groupware/email/emailSendForm.jsp">
                           <i class="fa fa-angle-right"></i> 메일쓰기
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailSelfForm.jsp">
                           <i class="fa fa-angle-right"></i> 내게 쓰기
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailListView.jsp">
                           <i class="fa fa-angle-right"></i> 전체 메일함
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailSendView.jsp">
                           <i class="fa fa-angle-right"></i> 보낸 메일함
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailReceiveView.jsp">
                           <i class="fa fa-angle-right"></i> 받은 메일함
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailSaveView.jsp">
                           <i class="fa fa-angle-right"></i> 임시 메일함
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailSelfView.jsp">
                           <i class="fa fa-angle-right"></i> 내게 쓴 메일함
                        </a>
                     </li>
                     <li>
                        <a href="/nado/views/groupware/email/emailDeleteView.jsp">
                           <i class="fa fa-angle-right"></i>휴지통
                        </a>
                     </li>
                  </ul>
               </li>

               <!-- 캘린더 메뉴 -->
               <li class="notreeview">
                  <a href="/nado/views/groupware/calendar/calendarMainView.jsp"> 
                     <i class="fa fa-calendar-o"></i> <span>캘린더</span>
                  </a>
               </li>

               <!-- 게시판 메뉴 -->
               <li class="treeview">
                  <a href="#"> <i class="fa fa-table"></i> <span>게시판</span> 
                     <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="/nado/nlist.ad">
                           <i class="fa fa-angle-right"></i>공지사항
                        </a>
                     </li>
                     <li>
                        <a href="/nado/surlist">
                           <i class="fa fa-angle-right"></i>설문조사
                        </a>
                     </li>
                     <li>
                        <a href="/nado/blist">
                           <i class="fa fa-angle-right"></i>자유게시판
                        </a>
                     </li>
                  </ul>
               </li>

               <!-- 주소록 메뉴 -->
               <li class="treeview">
                  <a href="#"> <i class="fa fa-address-book"></i> <span>주소록</span> 
                     <span class="pull-right-container"> <i] class="fa fa-angle-left pull-right"></i></span>
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="pages/tables/simple.html">
                           <i class="fa fa-angle-right"></i> 주소록 추가
                        </a>
                     </li>
                     <li>
                        <a href="pages/tables/data.html">
                           <i class="fa fa-angle-right"></i> 개인 주소록
                        </a>
                     </li>
                     <li>
                        <a href="pages/tables/data.html">
                           <i class="fa fa-angle-right"></i> 공유 주소록
                        </a>
                     </li>
                  </ul>
               </li>

               <!-- 예약 메뉴 -->
               <li class="treeview">
                  <a href="#"> <i class="fa fa-calendar-check-o"></i> <span>예약</span>
                     <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="pages/forms/general.html">
                           <i class="fa fa-angle-right"></i> 예약하기
                        </a>
                     </li>
                     <li>
                        <a href="pages/forms/advanced.html">
                           <i class="fa fa-angle-right"></i> 예약 조회
                        </a>
                     </li>
                     <li>
                        <a href="pages/forms/editors.html">
                           <i class="fa fa-angle-right"></i> 나의 예약 조회
                        </a>
                     </li>
                     <li>
                        <a href="pages/forms/editors.html">
                           <i class="fa fa-angle-right"></i> 예약 관리
                        </a>
                     </li>
                  </ul>
               </li>
               
               <!-- 마이페이지 메뉴 -->
               <li class="treeview">
                  <a href="#"> <i class="fa fa-user-circle"></i> <span>마이페이지</span> 
                     <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="#">
                           <i class="fa fa-angle-right"></i> 근태 관리
                        </a>
                     </li>
                     <li>
                        <a href="#">
                           <i class="fa fa-angle-right"></i> 쪽지함
                        </a>
                     </li>
                     <li>
                        <a href="#">
                           <i class="fa fa-angle-right"></i> 내 정보 관리
                        </a>
                     </li>
                  </ul>
               </li>
            </ul>
         </section>
         <!-- 왼쪽 사이드바 메뉴 끝 /.sidebar -->
      </aside>

      <!-- 가운데 작업 영역 Content Wrapper. Contains page content -->
      <div class="content-wrapper">
         <!-- 가운데 작업 영역 헤더 부분 Content Header (Page header) -->
         <section class="content-header">
            <h1>조직도 <small>EmployeeList</small></h1>
            <ol class="breadcrumb">
               <li><a href="/nado/views/groupware/main.jsp"><i class="fa fa-home"></i> 홈</a></li>
               <li class="active">홈</li>
            </ol>
         </section>

<!-- 가운데 메인 작업 영역 Main content -->
         <section class="content">








<%-- <script type="text/javascript">
function deletereply(e){
	$(".replydel").each(function(index, item){
		if(e == item){
			$.ajax({
				url : "/nado/breplydel",
				type : "post",
				dataType : "json",
				data : {cmno : $("input[name=cmno]").eq(index).val(), boardno : "<%= board.getBoardNo() %>" },
				success : function(data){
					//전송 온 object 를 string 으로 바꿈
					var jsonStr = JSON.stringify(data);
					//string 을 json 객체로 바꿈
					var json = JSON.parse(jsonStr);
					//json 안에 list 가 들어있음.
					var s = null;
					for(var i in json.list){
				
						s = "<input type= 'hidden' name='cmno' class='cmno' value='" + json.list[i].cmno + "'>" +
						decodeURIComponent(json.list[i].userid).replace(/\+/gi, " ") + "&nbsp;&nbsp;&nbsp;" + json.list[i].cmdate + 
						"<br><h4 style='display:inline;'>" + decodeURIComponent(json.list[i].cmcontent).replace(/\+/gi, " ") + "</h4>";		 
					}
					<% for(Comments c : list){ %>
					<%if(!(loginEmp.getUserId().equals(c.getUserId()))){%> 
					s+="<a style='float:right; margin-right:100px; display:inline;'>답글달기</a>";
					<% }else{ %>
					s+="<a href='javascript:void(0)' class='replydel' style='float:right; margin-right:50px;display:inline;' onclick='deletereply(this);'>삭제하기</a>";
					+ "<a href='javascript:void(0)' class='replyupdate' style='float:right; margin-right:50px; display:inline;' onclick='updatereply(); return false;'>수정하기</a>";
					<% }}%>
					s+="<hr style='align:left; margin-right:20px; color:gray;'>";	 
							 
					$(".replybox").eq(i).html(s);
							
					 location.reload(true); 
					
					
				},
				error : function(jqXHR, textStatus, errorThrown){
					console.log("error : " + textStatus);
				}
			});
		}
	}); //each
}
</script> --%>





	
	
	


<script type="text/javascript">
	$(function(){
		$(".empdetail").click(function(){
			var emp = $(this).val();
			//console.log(emp);
			
			$.ajax({
				url : "/nado/edetail",
	            type : "post",
	            dataType : "json",
	            data : { empid : emp },
	            success : function(data){	//email myProfile userId empName phone deptName jobName empPhone fax
	            	//console.log("data : " + data);
	            var email = decodeURIComponent(data.email).replace(/\+/gi, " ");	
	            var myProfile = decodeURIComponent(data.myProfile).replace(/\+/gi, " ");
	            var userId = decodeURIComponent(data.userId).replace(/\+/gi, " ");
	            var empName = decodeURIComponent(data.empName).replace(/\+/gi, " ");
	            var phone = data.phone;
	            var deptName = decodeURIComponent(data.deptName).replace(/\+/gi, " ");
	            var jobName = decodeURIComponent(data.jobName).replace(/\+/gi, " ");
	            var empPhone = data.empPhone;
	            var fax = data.fax;
	            
	            $("#elistcontent").html(
	            		"<div><div style='width: 70%; float: left;'>" +
	    				"<h4 style='display:inline;'>이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + empName + "</h4><br><br>" +
	    				"<h4 style='display:inline;'>아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + userId + "</h4>" +
	    				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
	    				"<form action='/nado/empdetailchatting' method='post' style='display:inline;'>" +
	    				"<input type='button' value='메신저연결'><input type='hidden' value='" + userId + "'></form><br><br>" +																		
	    				"<h4 style='display:inline;'>핸드폰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + phone + "</h4><br><br>" +
	    				"<h4 style='display:inline;'>이메일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + email + "</h4>" +
	    				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
	    				"<form action='/nado/empdetailemail' method='post' style='display:inline;'>" +
	    				"<input type='button' value='메일쓰기'><input type='hidden' value='" + userId + "'></form><br><br>" +																
	    				"<h4 style='display:inline;'>부서&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + deptName + "</h4><br><br>" +
	    				"<h4 style='display:inline;'>직급&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + jobName + "</h4><br><br>" +
	    				"<h4 style='display:inline;'>내선번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + empPhone + "</h4><br><br>" +
	    				"<h4 style='display:inline;'>팩스번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + fax + "</h4></div>" +
	    				"<div style='width: 30%; float: right; text-align:center;'>" +
	    				"<img class='img-circle' src='/nado/resources/groupware/employee_profile/" + myProfile + "'style='width:200px; height:220px;'></div></div>");
				},
				error : function(jqXHR, textStatus, errorThrown){
					console.log("error : " + jqXHR);
	            }
	          });	 	//end ajax
	});
	
});
</script>









<br>

<!-- 전체 div -->
<div id="elist" name="elist" style="width:90%;">

		<!-- 왼쪽 위아래 구분 div -->
        <div style="border: 2px solid #4d2498; background-color: #f3f1fa; width: 30%; height:550px; float: left; margin-right:0px; ">
        
        <!-- 1번 왼쪽 위 div 시작-->
		<div id="elistLeftTop" name="elistLeftTop" style="overflow: auto; border: 1px solid #4d2498; background-color: #f3f1fa; width: 100%; height:400px; margin-right:0px; ">
			<ul class="emptree">
				<li><input type="checkbox" id="root"> <label for="root"><strong>NADO</strong></label>
					<ul>
		<!-- --------------------------------------------------------------------------------------------------------------------------- -->		
							<% for(Dept d : dlist){ 
									int count = 0;
									for(Employee e : list){
										if(d.getDeptId().equals(e.getDeptId())) {
											count++;
										}
									}%>	
						<li>
							<input type="checkbox" id="<%= d.getDeptId() %>"><label for="<%= d.getDeptId() %>"><%= d.getDeptName() %>  (<%= count %>)</label>
							<% for(Employee e : list){ 
								if(d.getDeptId().equals(e.getDeptId())) {
							%>
							<ul>
								<li><a><button type="button" style="background:none; border:none;"class="empdetail" value="<%= e.getEmpId() %>" ><%= e.getEmpName() %></button></a></li>
							</ul>
								<% } %>
							<% } %>
						</li>
						<% } %>
		<!-- --------------------------------------------------------------------------------------------------------------------------- -->					
					</ul>
				</li>
			</ul>
				</div><!-- 1번 왼쪽 위 div 끝 -->
				
				<!-- 2번 왼쪽 밑 div 시작-->
				<div id="elistLeftBottom" name="elistLeftBottom" style="border: 1px solid #4d2498; background-color: #f3f1fa; width:100%; height:147px; ">
				검색조회 부분
				</div><!-- 2번 왼쪽 밑 div 끝-->
</div>
				<!-- 3번 오른쪽 div -->
				<div id="elistcontent" style="border: 2px solid #4d2498; background-color: #f3f1fa; width:70%; height:550px; float: left; padding:120px;">
				
				<!-- <div>
				<div style="width: 70%; float: left;">
				<h4 style="display:inline;">이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;홍길동</h4><br><br>
				<h4 style="display:inline;">아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;laopos</h4>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<form action="/nado/empdetailchatting" method="post" style="display:inline;">
				<input type="button" value="메신저연결"><input type="hidden" value="userId"></form><br><br>																		
				<h4 style="display:inline;">핸드폰&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;010-1234-5678</h4><br><br>
				<h4 style="display:inline;">이메일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;abcde@naver.com</h4>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<form action="/nado/empdetailemail" method="post" style="display:inline;">
				<input type="button" value="메일쓰기"><input type="hidden" value="userId"></form><br><br>																
				<h4 style="display:inline;">부서&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;개발부</h4><br><br>
				<h4 style="display:inline;">직급&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;대표이사</h4><br><br>
				<h4 style="display:inline;">내선번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;02-123-4567</h4><br><br>
				<h4 style="display:inline;">팩스번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;02-345-6789</h4>
				</div>
				<div style="width: 30%; float: right; text-align:center;">
				<img src="/nado/resources/groupware/employee_profile/호성.png" style="width:200px; height:220px;">
				</div>
				</div> -->
				
				</div><!-- 3번 오른쪽 div 끝-->
</div>












         </section>
         <!-- 가운데 메인 작업 영역 끝 /.content -->
      </div>
      <!-- 가운데 작업 영역 끝 /.content-wrapper -->
      
         
         <!-- 관리자 페이지 -->
         <% } else if(manager.equals("S")) { %>
         <!-- 관리자 페이지 헤더 네비바 Header Navbar: style can be found in header.less -->
         <nav class="navbar navbar-static-top">
            <!-- 관리자 페이지 토글 버튼 Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> 
               <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
               <ul class="nav navbar-nav">
                  <!-- 관리자 페이지 쪽지 토글 버튼 Messages: style can be found in dropdown.less-->
                  <li class="dropdown messages-menu">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                        <i class="fa fa-envelope"></i> <span class="label label-success">3</span>
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
                  <li class="dropdown notifications-menu">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
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
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-calendar text-yellow"></i>
                                    오늘의 일정이 있습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-envelope text-purple"></i>
                                    새로운 메일이 도착했습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-table text-green"></i>
                                    새로운 댓글이 달렸습니다.
                                 </a>
                              </li>
                              <li>
                                 <a href="#"> <i class="fa fa-calendar-check-o text-red"></i>
                                    예약신청이 반려되었습니다.
                                 </a>
                              </li>
                           </ul>
                        </li>
                        <li class="footer"><a href="#">전체 보기</a></li>
                     </ul>
                  </li>
                  <!-- 관리자 페이지 알림 토글 메뉴 끝 -->
                     
                  <!-- 관리자 페이지 채팅 토글 버튼 Control Sidebar Toggle Button -->
                  <li>
                     <a href="#" data-toggle="control-sidebar">
                        <i class="fa fa-wechat"></i>
                     </a>
                  </li>
                  <!-- 관리자 페이지 채팅 토글 끝 -->
                  
                  <!-- 관리자 페이지 상단 프로필 영역 User Account: style can be found in dropdown.less -->
                  <li class="dropdown user user-menu">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                        <!-- 관리자 페이지 오른쪽 상단 프로필 사진 -->
                        <% if(loginEmp.getMyProfile() != null) { %>
                        <img src="/nado/resources/groupware/employee_profile/<%= loginEmp.getMyProfile() %>" class="user-image" alt="User Image"> 
                        <% } else { %>
                        <img src="/nado/resources/groupware/employee_profile/default-profile.png" class="user-image" alt="User Image">
                        <% } %>
                        <span class="hidden-xs"><%= loginEmp.getEmpName() %></span>
                     </a>
                     <!-- 관리자 페이지 프로필 부분 클릭했을 때 드롭다운 메뉴 -->
                     <ul class="dropdown-menu">
                        <!-- 프로필 사진 User image -->
                        <li class="user-header">
                        <% if(loginEmp.getMyProfile() != null) { %>
                        <img src="/nado/resources/groupware/employee_profile/<%= loginEmp.getMyProfile() %>" class="img-circle" alt="User Image">
                        <% } else { %>
                        <img src="/nado/resources/groupware/employee_profile/default-profile.png" class="img-circle" alt="User Image">
                        <% } %>
                           <p>관리자</p>
                           <p><%= loginEmp.getEmpName() %></p>
                        </li>
                        <!-- 관리자 페이지 프로필 수정, 로그아웃 버튼 Menu Footer-->
                        <li class="user-footer">
                           <div class="pull-left">
                              <a href="#" class="btn btn-default btn-flat">프로필</a>
                           </div>
                           <div class="pull-right">
                              <a href="/nado/emplo" class="btn btn-default btn-flat">로그아웃</a>
                           </div>
                        </li>
                     </ul>
                  </li>
                  <!-- 관리자 페이지 프로필 영역 끝 -->
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
                  <% if(loginEmp.getMyProfile() != null) { %>
                  <img src="/nado/resources/groupware/employee_profile/<%= loginEmp.getMyProfile() %>" class="img-circle" alt="User Image">
                  <% } else { %>
                  <img src="/nado/resources/groupware/employee_profile/default-profile.png" class="img-circle" alt="User Image">
                  <% } %>
               </div>
               <br>
               <div class="user-panel-info">
                  <p>관리자 <%= loginEmp.getEmpName() %></p>
                  <input type="button" id="logout" onclick="location='/nado/emplo'" value="로그아웃">
               </div>
            </div>

            <!-- 관리자 페이지 왼쪽 사이드바 검색 기능 부분 search form -->
            <form action="#" method="get" class="sidebar-form">
               <div class="input-group">
                  <input type="text" name="q" class="form-control" placeholder="검색">
                  <span class="input-group-btn">
                     <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                     </button>
                  </span>
               </div>
            </form>
            <!-- 관리자 페이지 왼쪽 사이드바 검색 기능 끝 /.search form -->
            
            <!-- 관리자 메뉴 -->
            <ul class="sidebar-menu" data-widget="tree">
               <li>
                  <a href="/nado/empic.ad"> 
                     <i class="fa fa-drivers-license"></i> <span>사용자승인관리</span>
                  </a>
               </li>
               <li>
                  <a href="#"> 
                     <i class="fa fa-group"></i> <span>조직도</span>
                  </a>
               </li>
               <li class="treeview">
                  <a href="#">
                     <i class="fa fa-list"></i><span>게시판 관리</span> 
                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i> </span> 
                  </a>
                  <ul class="treeview-menu">
                     <li>
                        <a href="/nado/nlist.ad">
                           <i class="fa fa-angle-right"></i>공지사항
                        </a>
                     </li>
                     <li>
                        <a href="#">
                           <i class="fa fa-angle-right"></i>설문조사
                        </a>
                     </li>
                     <li>
                        <a href="/nado/blist">
                           <i class="fa fa-angle-right"></i>자유게시판
                        </a>
                     </li>
                  </ul>
               </li>
               <li>
                  <a href="calendar/calendarMonthView.jsp">
                     <i class="fa fa-clipboard"></i> <span>전자결재양식</span>
                  </a>
               </li>
            </ul>
         </section>
         <!-- 관리자 메뉴 끝 /.sidebar -->
         
         </aside>

         <!-- 관리자 페이지 가운데 작업 영역 Content Wrapper. Contains page content -->
         <div class="content-wrapper">
            <!-- 관리자 페이지 가운데 작업 영역 헤더 부분 Content Header (Page header) -->
            <section class="content-header">
               <h1>조직도 <small>EmployeeList</small></h1>
               <ol class="breadcrumb">
                  <li><a href="/nado/views/groupware/main.jsp"><i class="fa fa-home"></i> 홈</a></li>
                  <li class="active">홈</li>
               </ol>
            </section>

            <!-- 관리자 페이지 가운데 메인 작업 영역 Main content -->
            <section class="content">
               
               
               
               
               
               
               
               
               
 
               
               
 
 








               




            </section>
            <!-- 관리자 페이지 가운데 메인 작업 영역 끝 /.content -->
         </div>
         <!-- 관리자 페이지 가운데 작업 영역 끝 /.content-wrapper -->
         <% } %>

         <footer class="main-footer">
            <div class="pull-right hidden-xs">
               <b>Version</b> 1.0.0
            </div>
            <strong>Copyright &copy; 2019 
               <a href="/nado/index.jsp">NADO Groupware</a>.</strong> All rights reserved.
         </footer>

         <!-- 공통 -->
         <!-- 오른쪽 채팅 사이드바 Control Sidebar -->
         <aside class="control-sidebar control-sidebar-dark" style="display: none;"></aside>
         <!-- 오른쪽 채팅 사이드바 끝 /.control-sidebar -->
        
         <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
         <div class="control-sidebar-bg"></div>
      </div>
      <!-- ./wrapper -->

   <!-- jQuery 3 -->
   <script src="/nado/resources/groupware/bower_components/jquery/dist/jquery.min.js"></script>
   <!-- jQuery UI 1.11.4 -->
   <script src="/nado/resources/groupware/bower_components/jquery-ui/jquery-ui.min.js"></script>
   <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
   <script>
     $.widget.bridge('uibutton', $.ui.button);
   </script>
   <!-- Bootstrap 3.3.7 -->
   <script src="/nado/resources/groupware/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
   <!-- Sparkline -->
   <script src="/nado/resources/groupware/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
   <!-- jQuery Knob Chart -->
   <script src="/nado/resources/groupware/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
   <!-- Bootstrap WYSIHTML5 -->
   <script src="/nado/resources/groupware/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
   <!-- Slimscroll -->
   <script src="/nado/resources/groupware/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
   <!-- FastClick -->
   <script src="/nado/resources/groupware/bower_components/fastclick/lib/fastclick.js"></script>
   <!-- AdminLTE App -->
   <script src="/nado/resources/groupware/dist/js/adminlte.min.js"></script>
   <!-- AdminLTE for demo purposes -->
   <script src="/nado/resources/groupware/dist/js/demo.js"></script>
</body>
</html>