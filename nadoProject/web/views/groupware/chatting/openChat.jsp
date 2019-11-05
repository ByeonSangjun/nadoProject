<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee, java.util.ArrayList, chatting.model.vo.Chatting, employee.model.vo.Company" %>
<%
	Company company = (Company)session.getAttribute("comp");
	String comp = company.getDriver() +"ł"+ company.getUrl() +"ł"+ company.getId() +"ł"+ company.getPassword();
	Employee loginEmp = (Employee)session.getAttribute("loginEmp");
	
	//해당 방 전체 채팅 글
	ArrayList<Chatting> contentList = (ArrayList<Chatting>)request.getAttribute("contentList");
	
	//참여인원
	String roomUsers = (String)request.getAttribute("roomUsers");
	String[] users = roomUsers.split("ł");
	
	//친구 리스트
	ArrayList<Employee> empList = (ArrayList<Employee>)request.getAttribute("empList");
	
	//방번호
	String chatRoomNo = (String)request.getAttribute("chatRoomNo");
	
	//방 이름
	String chatTitle = (String)request.getAttribute("chatTitle");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<style type="text/css">
.jun-top {
	background: #E9E6F5;
}

</style>
<script type="text/javascript" src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/nado/resources/css/bootstrap.css">
<link rel="stylesheet" href="/nado/resources/css/jun.css">
<link rel="stylesheet" href="/nado/resources/groupware/bower_components/font-awesome/css/font-awesome.min.css">
</head>
<body>
<!-- 채팅방 화면 구현하기 -->
<div id="container">
<!-- 	<div class="row"> -->
<!-- 		<div class="col-lg-12 jun-top" style="height:50px;"> -->
<!-- 			<table> -->
<!-- 					<tr> -->
<%-- 						<th><%=chatTitle%><th> --%>
<!-- 					</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="page-header jun-top">
		<table style="border:solid">
			<tr>
				<th rowspan="2">이미지</th>
				<td id="title"><%=chatTitle%></td>
				<th rowspan><a data-toggle="modal" href="#junModal">초대</a></th>
				<th rowspan><a data-toggle="modal" href="#junModal2">나가기</a></th>
			</tr>
			<tr>
				<td>검색</td>
			</tr>
		</table>
	</div>
	<div id="messageWindow">	
		<div class="row">
		<% if(contentList.size()>0){
				for(Chatting list : contentList){
					if(loginEmp.getEmpId().equals(list.getEmpId())){					
		%>
				<div class="col-lg-12">
					<div class="talk-bubble tri-right round right-in">
						<div class="talktext">
							<p><%= list.getChatContent() %></p>
						</div>
					</div>
				</div>
				<%}else{ %>
				<div class="col-lg-12">
					<div class="talk-bubble-left tri-left round left-in other-side ">
						<div class="talktext">
							<p><%= list.getChatContent() %></p>
						</div>
					</div>
				</div>
		<%}}}else{ %>
		<%} %>
			</div>
		</div>
		<div class="input-group"><input class="form-control" type="text" name="content" id="inputMessage" onkeyup="enterKey();">
		<span class="input-group-addon"><button id="bn" class="btn" onclick="send();"><i class="fa fa-edit fa-2x"></i></button></span></div>
</div>

<div class="modal fade" id="junModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">초대</h4>
					<button type="button" class="close" data-dismiss="modal">×</button>	
			</div>
				<div class="container">
					<div class="modal-body">
						<div class="row">
							<div style="float:left">
								<div style="overflow:auto; width:200px; height:200px; border:1px solid silver">
									<%jun: for(Employee eList : empList){
											   	for(String userList : users){
													if(eList.getEmpId().equals(userList))
														continue jun;
											   	}
									%>
									<div style="text-align:center;">
										<label for="<%= eList.getEmpId() %>"><img style="width:30px; height:30px;" src="/nado/resources/groupware/employee_profile/<% if(eList.getMyProfile() != null){ %><%= eList.getMyProfile() %><%}else{%>default-profile.png<%}%>">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= eList.getEmpName() %></label>						
										<input type="checkbox" id="<%= eList.getEmpId() %>" name="invitedUsers" value="<%= eList.getEmpId() %>">
									</div>
									<%} %>
								</div>
							</div>
							<div>
								
							</div>
						</div>
					</div>
				</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="inviteUser2();">초대</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" >취소</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="junModal2" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">방 나가기</h5>
					<button type="button" class="close" data-dismiss="modal">×</button>	
			</div>		
			<div class="modal-body">
				<p>정말로 나가시겠습니까?</p>
			</div>		
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="exitTheRoom();">나가기</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" >취소</button>
			</div>
		</div>
	</div>
</div>
<script src="/nado/resources/js/bootstrap.js"></script>
<script type="text/javascript">

var $textarea = $("#messageWindow");
var $inputMessage = $("#inputMessage");
var webSocket;

//자기사번
var empId = "<%= loginEmp.getEmpId() %>";

//방 참여인원들
var roomUsers ="<%= roomUsers %>";

//회사정보 저장
var comp= "<%= comp %>";

//방번호
var rNo = "<%= chatRoomNo %>";

//자기이름
var empName = "<%= loginEmp.getEmpName() %>";


connection();

function connection(){
	
	webSocket = new WebSocket("ws://127.0.0.1:8800/nado/chatserver");

	webSocket.onopen = function(event){
		//방 참여자들 사번 보냄
	    webSocket.send("roomsł" + roomUsers);
	  
		//회사정보
	    webSocket.send("compł" + comp);
	};

	webSocket.onmessage = function(event){
    	onMessage(event);
	};

	webSocket.onerror = function(event){
	 	onError(event);
	};

	webSocket.onclose = function(event){
 		onClose(event);
	};
}
function send(){
	//메세지를 입력하지 않고 버튼 누른 경우
	if($inputMessage.val() == "") {
		alert("전송할 메세지를 입력하세요.");
	}else{  //메세지가 입력된 경우
		$textarea.html($textarea.html() + "<div class='talk-bubble tri-right round right-in'><div class='talktext'><p>"
			+ $inputMessage.val() + "</p></div></div><br>");
		
		// 방번호, 사번, 이름, 메시지 
		webSocket.send("rChatł"+rNo+"ł"+empId+"ł"+empName+"ł"+$inputMessage.val()); 
		
		$inputMessage.val("");  //기록된 메세지 삭제함
		$textarea.scrollTop($textarea[0].scrollHeight);
	}
	
};

function enterKey(){
	if(window.event.keyCode == 13){
		send();
	}
};

	function onMessage(event){
		//받은내용(사번+"ł"+이름+"ł"+내용))
		var fullMessage = event.data;
		var message = fullMessage.split("ł");
		var mHead = message[0];
		console.log(event.data);
		if(mHead == "inviteUser"){
			var mBody = fullMessage.substring(fullMessage.indexOf("ł")+1, fullMessage.length);
			var fullTitle = mBody.split(":");
			roomUsers = fullTitle[0];   //원래 저장되있던 방참여유져들을 초대된 유져들로 바꿔줌
			var rtitle = fullTitle[1];
			$("#title").html(rtitle);
			console.log("참여인원+타이틀 : "+mBody);
			console.log("방참여유져들 : "+roomUsers);
			console.log("타이틀 : "+rtitle);		
		}else{		
			var receiveName = message[1];
			var receiveContent = message[2];
			
			$textarea.html($textarea.html()
					+ "<div class='talk-bubble-left tri-left round left-in other-side'><div class='talktext'><p>"
					+ receiveName + " : "+ receiveContent 
					+ "</p></div></div><br>");
// 			화면이 위로 스크롤되게 처리함
			$textarea.scrollTop($textarea[0].scrollHeight);
		}
	}
$(function(){
	$textarea.scrollTop($textarea[0].scrollHeight);
});
	

	//친구초대
	function inviteUser2() {
		var plus = "inviteChatł" + roomUsers +":";
		if($("input:checkbox[name='invitedUsers']:checked").length ==0){
			alert("한명 이상을 선택해주세요.");
		}else{
			$("input:checkbox[name='invitedUsers']").each(function() {
				if ($(this).is(":checked") == true) {
					plus += "ł" + $(this).val();
				}
			});
			webSocket.send(plus);
			$("input:checkbox[name='invitedUsers']").each(function() {
				if ($(this).is(":checked") == true) {
					$(this).prop("checked", false);
				}
			});	
		}
	}
	
	function exitTheRoom(){
		webSocket.send("etrł"+ roomUsers + ":" + empId);
	}
</script>
</body>
</html>