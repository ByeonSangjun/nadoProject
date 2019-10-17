<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee,employee.model.vo.Dept,java.util.ArrayList"%>

<%
	Employee loginEmp = (Employee) session.getAttribute("loginEmp"); //267, 321 사용자 프로필
	String manager = loginEmp.getIdLevel(); //340 줄

	// 521줄 560줄 까지 관리자 메뉴 
	
	//ArrayList<Employee> list = (ArrayList<Employee>)request.getAttribute("list");
	ArrayList<Dept> dlist = (ArrayList<Dept>)request.getAttribute("dlist");
%>
<%-- <%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int beginPage = ((Integer)request.getAttribute("beginPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
%>     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NADO GroupWare List</title>
</head>
<body>
<div align="center">
		<button onclick="location.href='/nado/views/groupware/employee/addDept.jsp';">새 부서 등록</button>
</div>
<table align="center" width="700" cellspacing="0" border="1" cellpadding="3">
<tr>
	<th>제목</th>
</tr>
<%  for(Dept d : dlist){ %>
<tr>
	<td>
	<% if(d.getDeptLevel() == 2){		//하위부서 일 때 %>
			&nbsp;	☞
			<%= d.getDeptName() %>
	<%-- <% }else if(d.getDeptLevel() != 1 && d.getDeptLevel() !=2){	//하위부서 속 사원 %>
			&nbsp; &nbsp;	☞☞
			<a href="/nado/emplist.ad?empid=<%= d.getDeptId() %>"><%= d.getFax() %><!-- </a> --> --%>
	<% } %>
			<%= d.getDeptName() %>
	</td>
	
</tr>
<% } %>
</table>
<br>
<div align="center">
		<a href="/first/index.jsp">홈으로 이동</a>
</div>
</body>
</html>