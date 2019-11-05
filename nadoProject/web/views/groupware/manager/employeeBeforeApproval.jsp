<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="employee.model.vo.Employee, java.util.ArrayList" %>
<%
	Employee emp = (Employee)request.getAttribute("empInfo");
	ArrayList<Employee> deptList = (ArrayList<Employee>)request.getAttribute("deptList");
	ArrayList<Employee> jobList = (ArrayList<Employee>)request.getAttribute("jobList");
	ArrayList<Employee> paystep = (ArrayList<Employee>)request.getAttribute("paystep");
	
	String empNo = emp.getEmpNo();
	String birthday = empNo.substring(2, empNo.indexOf("-"));
	String bMonth = birthday.substring(0, 2);
	String bDay = birthday.substring(2, 4);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="/nado/resources/css/bootstrap.css">
<script src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<style type="text/css">
* {
   margin: 0;
   padding: 3px;
}

.table th{
   background: #f3f1fa;
   text-align: center;
   vertical-align: middle;
   width: 15%;
}

.table td{
   vertical-align: middle;
   width: auto;
}
button#modifyBtn {
   border: 1px solid #c7bee8;
   background: #c7bee8;
   width: 75px;
   margin-right: 10px;
}

button#modifyBtn:hover, button#modifyBtn:focus {
   border: 1px solid #b9abe8;
   background: #b9abe8;
   width: 75px;
   margin-right: 10px;
}

button#closeBtn {
   border: 1px solid #c7bee8;
   background: #fff;
   width: 75px;
}

button#closeBtn:hover, button#closeBtn:focus {
   border: 1px solid #b9abe8;
   background: #b9abe8;
   width: 75px;
}

</style>
</head>
<body>
	<div class="container">
		<form class="form-group" action="empapp.ad" method="post">
			<div class="row" style="text-align:center;">
				<table class="table table-bordered">
					<div class="form-inline form-group">
						<tr>
							<th><label class="control-label" for="emp_name">이름 </label></th>
							<td>
								<input type="text" class="form-control" id="emp_name" name="emp_name" value="<%=emp.getEmpName()%>" readonly>
							</td>
							<th><label class="control-label" for="emp_id">사번 </label></th>
							<td>
								<input type="text" class="form-control" id="emp_id" name="emp_id">	
							</td>
						</tr>
					</div>
					<div class="form-inline form-group">
						<tr>
							<th><label class="control-label" for="user_id">아이디 </label></th>
							<td>
								<input type="text" class="form-control" id="user_id" name="user_id" value="<%= emp.getUserId() %>" readonly>
							</td>
							<th><label class="control-label" for="id_level">계정구분 </label></th>
							<td>
								<input type="radio" id="id_level" name="id_level" value="M" checked>일반사원
								<input type="radio" id="id_level" name="id_level" value="G">관리자
							</td>
						</tr>
					</div>
					<div class="form-inline form-group">
						<tr>
							<th><label class="control-label">생일 </label></th>
							<td>
							<div class="form-inline form-group">											
							<input style="width:50px;"type="text" class="form-control" id="bMonth" name="bMonth" value="<%= bMonth %>" readonly>
							&nbsp;
							<label for="bMonth" class="control-label">월</label>
							<input style="width:50px;"type="text" class="form-control" id="bDay" name="bDay" value="<%= bDay %>" readonly>
							<label for="bDay" class="control-label">일</label>
							</div>
							</td>	
							<th><label class="control-label" for="dept_id">부서 </label></th>
							<td>
							<select class="form-control" id="dept_id" name="dept_id">	
							<% for(Employee dList : deptList){ %>
								<option value="<%= dList.getDeptId() %>"><%= dList.getDeptName() %></option>
							<%} %>
							</td>	
						</tr>
					</div>
					<div class="form-inline form-group">
						<tr>
							<th><label class="control-label" for="email">이메일 </label></th>
							<td>
								<input type="text" class="form-control" id="email" name="email" value="<%= emp.getEmail() %>" readonly>
							</td>
							<th><label class="control-label" for="job_id">직책 </label></th>
							<td>
								<select class="form-control" id="job_id" name="job_id">	
									<% for(Employee jList : jobList){ %>
										<option value="<%= jList.getJobId() %>"><%= jList.getJobName() %></option>
									<%} %>
							</td>	
						</tr>
					</div>
					<div class="form-inline form-group">
						<tr>
							<th><label class="control-label" for="phone">휴대전화 </label></th>
							<td>
								<input type="text" class="form-control" id="phone" name="phone" value="<%= emp.getPhone() %>" readonly>
							</td>
							<th><label class="control-label" for="paystep">호봉 </label></th>
							<td>
								<select class="form-control" id="paystep" name="paystep">	
									<% for(Employee pList : paystep){ %>
										<option value="<%= pList.getPaystep() %>"><%= pList.getPaystep() %></option>
									<%} %>
							</td>
						</tr>
					</div>
					<div class="form-inline form-group">
						<tr>
							<th><label class="control-label" for="address">주소 </label></th>
							<td>
							<input type="text" class="form-control" id="address" name="address" value="<%= emp.getAddress() %>" readonly>
							</td>
								
						</tr>
					</div>
					<div class="form-inline form-group">
						<tr>
	                    <td colspan="4" style="text-align: center"><button id="modifyBtn" type="submit" style="cursor: pointer">승인</button>
	                        <button id="closeBtn" onclick="window.close();" style="cursor: pointer">닫기</button></td>
	                 	</tr>
                 	</div>			
				</table>
			</div>
		</form>
	</div>
	<script src="/nado/resources/js/bootstrap.js"></script>
</body>
</html>