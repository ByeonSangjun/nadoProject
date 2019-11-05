<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee, chatting.model.vo.Chatting, java.util.ArrayList, employee.model.vo.Company" %>
<%
	Company company = (Company)session.getAttribute("comp"); 
	String comp = company.getDriver() +"ł"+ company.getUrl() +"ł"+ company.getId() +"ł"+ company.getPassword();
	Employee loginEmp = (Employee)session.getAttribute("loginEmp");
	
	//참여중인방 번호, 사번, 타이틀
	ArrayList<Chatting> chatList = (ArrayList<Chatting>)request.getAttribute("chatList");
	
	//친구 리스트
	ArrayList<Employee> empList = (ArrayList<Employee>)request.getAttribute("empList"); 
	
	//채팅방 마지막글들
	ArrayList<Chatting> lastContents = (ArrayList<Chatting>)request.getAttribute("lastContents"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>채팅</title>
<style type="text/css">
h6{
	margin:0;
	padding:0;
}
.junstyle{
	color : #c7bee8;
	font-size: 20px;
	margin : 10px;
	display : inline;
	float : right;
}
.jun-box-size{
	width: 120px;
	text-align:center;
	background:#f3f1fa;
}

.jun-a{
	color: black;
	text-decoration:none;
}
.custom-menu {
    z-index:1000;
    position: absolute;
    background-color: #fff;
    border: 1px solid silver;
    padding: 2px;
    border-radius: 5px 5px 5px 5px;
}
</style>
<script src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/nado/resources/css/bootstrap.css">
<link rel="stylesheet" href="/nado/resources/groupware/bower_components/font-awesome/css/font-awesome.min.css">
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div style="display:inline;">
				채팅
				</div>
				<div class="junstyle">
					<a class="jun-a" data-toggle="modal" href="#junModal"><i class="fa fa-comments junstyle"></i>방만들기</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<ul class="nav nav-tabs">
					<li class="nav-item jun-box-size"><a class="nav-link active jun-a"
						data-toggle="tab" href="#friends">사원목록</a></li>
					<li class="nav-item jun-box-size"><a class="nav-link jun-a" data-toggle="tab"
						href="#chatList">채팅리스트</a></li>
					<li class="nav-item jun-box-size"><a class="nav-link jun-a" data-toggle="tab"
						href="#open">오픈채팅방</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade show active" id="friends">
						<!-- 친구리스트 -->
						<table class="table table-hover">
							<tr>
								<td><img style="width:30px; height:30px;" src="/nado/resources/groupware/employee_profile/
									<% if(loginEmp.getMyProfile() != null){ %><%= loginEmp.getMyProfile() %><%}else{%>default-profile.png<%}%>">
									<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= loginEmp.getEmpName() %></a></td>
							</tr>	
						<% for(Employee list : empList){
								if(!list.getEmpId().equals(loginEmp.getEmpId())){
						%>
								<tr>
									<td ondblclick="clroom(<%= loginEmp.getEmpId() %>,<%= list.getEmpId() %>);">
											<img style="width:30px; height:30px;" src="/nado/resources/groupware/employee_profile/<% if(list.getMyProfile() != null){ %><%= list.getMyProfile() %><%}else{%>default-profile.png<%}%>">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= list.getEmpName() %>					
									</td>
								</tr>							
						<%}} %>
						</table>
					</div>
					<!-- 채팅방 리스트 -->
					<div class="tab-pane fade" id="chatList">				
					<% for(Chatting clist: chatList){%>
						<%for(Chatting last : lastContents){%>
							<% if(clist.getChatRoomNo() == last.getChatRoomNo()){ %>
							<div ondblclick="rlist(<%= clist.getChatRoomNo() %>);" id="c<%= clist.getChatRoomNo() %>">	
									<h6 id="<%= clist.getChatTitle() %>" >
										<strong>
										<%= clist.getChatTitle() %>
										</strong>
									</h6>
										<p id="<%= clist.getChatRoomNo() %>"><%= last.getChatContent() %></p>
							</div>
							<%}}} %>
					</div>
					<div class="tab-pane fade" id="open">
						<h3>여긴 오픈채팅방</h3>
						<div id="nad"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="junModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<h4 class="modal-title">방만들기</h4>
					<button type="button" class="close" data-dismiss="modal">×</button>	
				</div>
					<div class="container">
						<div class="modal-body">
							<div class="input-group">
							<label for="room_title">방 이름<input class="input-control" type="text" id="room_title"></label>
							</div>
							<div class="row">
								<div style="float:left">
									<div style="overflow:auto; width:200px; height:200px; border:1px solid silver">
										<% for(Employee eList : empList){ if(!eList.getEmpId().equals(loginEmp.getEmpId())){ %>
										<div style="text-align:center;">
										<label for="<%= eList.getEmpId() %>"><img style="width:30px; height:30px;" src="/nado/resources/groupware/employee_profile/<% if(eList.getMyProfile() != null){ %><%= eList.getMyProfile() %><%}else{%>default-profile.png<%}%>">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= eList.getEmpName() %></label>						
										<input type="checkbox" id="<%= eList.getEmpId() %>" name="invitedUsers" value="<%= eList.getEmpId() %>">
										</div>
										<%}} %>
									</div>
								</div>
								<div>
								
								</div>
							</div>
						</div>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal" onclick="inviteUser();">초대</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" >취소</button>
				</div>
			</div>

		</div>
	</div>

	<script src="/nado/resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
//오픈시 커넥션 연결
connection();

//웹소켓
var ws;

//내사번
var empId = "<%=loginEmp.getEmpId()%>";

//회사정보 저장
var comp= "<%= comp %>";

//자식창열기
var openCh;

function connection(){
	
	ws = new WebSocket("ws://127.0.0.1:8800/nado/chatserver");

	ws.onopen = function(event){
		//첫 접속시 사원 사번 꺼내서 보내기
	  	ws.send("empIdł" + empId);
		//첫 접속시 회사 보내기
	  	ws.send("compł" + comp);
	};

	ws.onmessage = function(event){
		onMessage(event);
	};

	ws.onerror = function(event){
 		onError(event);
	};

	ws.onclose = function(event){
   		onClose(event);
	};
}

function clroom(myid, userid){
	ws.send("searchRoomł"+ myid + "ł" + userid);
}

function rlist(roomNo){
	ws.send("searchListRoomł"+ roomNo);
}

	function onMessage(event){
		var message = event.data.split("ł");
		var mHead = message[0];	
		
		if(mHead=="openRoom"){
			var mBody = message[1];
			openCh = window.open("/nado/chto?myid="+empId+"&roomNo="+mBody, mBody, "width=420px, height=660px, menubar=no, status=no, toolbar=no");	
		}else if(mHead =="sendToMain"){
			//받은내용(방번호+"ł"+사번+"ł"+이름+"ł"+내용+"ł"+타이틀)  //사번 필요없을지도 모름 나중에 바꿈
			var roomNo = message[1];
			var eId = message[2];
			var name = message[3];
			var content = message[4];
			var title = message[5];
			
			if($("#c"+roomNo).length > 0){
				$("#c"+roomNo).remove();
	 		}
	 			$("#chatList").prepend("<div ondblclick='rlist("+roomNo+");' id='c"+roomNo+"'><strong>"+title+"</strong><p id='"+roomNo+"'>"+content+"</p></div>"); 		
		}
	}

var $room_title = $("#room_title");
		function inviteUser(){
			if($room_title.val() == ""){
				alert("방 이름을 입력해주세요.");
			}else if($("input:checkbox[name='invitedUsers']:checked").length ==0){
				alert("한명 이상을 선택해주세요.");
				$room_title.val("");
			}else{
				var plus = "createChatł" + empId + "ł" + $room_title.val();
				$("input:checkbox[name='invitedUsers']").each(function() {
					if ($(this).is(":checked") == true) {
						plus += "ł" + $(this).val();
					}
				});
				ws.send(plus);
				$room_title.val("");
				$("input:checkbox[name='invitedUsers']").each(function() {
					if ($(this).is(":checked") == true) {
						$(this).prop("checked", false);
					}
				});
			}
		}
	
// 	$(document).bind("contextmenu", function(event) {
// 		event.preventDefault();
// 		$("<div class='custom-menu'>제목 바꾸기</div><div class='custom-menu'>방 나가기</div>").appendTo("body").css({
// 			top : event.pageY + "px",
// 			left : event.pageX + "px"
// 		});
// 	}).bind("click", function(event) {
// 		$("div.custom-menu").hide();
// 	});
// 	document.addEventListener('contextmenu', function() {
// 		event.preventDefault();
// 	});
// 	document.addEventListener('mousedown', function() {
// 		if ((event.button == 2) || (event.which == 3)) {
// 			console.log("마우스 오른쪽 작동");
// 		}
// 	});
</script>
<script src="/nado/resources/js/bootstrap.js"></script>
</body>
</html>