<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NADO Groupware</title>
<link rel="stylesheet" href="/nado/resources/css/bootstrap.css">
<script src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<script src="/nado/resources/js/jquery.validate.js"></script>
<script src="/nado/resources/js/additional-methods.js"></script>
<script src="/nado/resources/js/messages_ko.js"></script>
<script src="/nado/resources/js/bootstrap.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style type="text/css">
.error {
	color: darkred;
	font-size: 13px;
}

#btn1 {
	width: 120px;
	height: 35px;
	margin-top: 1px;
	border: 1px solid #b9abe8;
	background: #fff;
}

.abb {
	background: #c7bee8;
}

#logo {
	border: solid 2px rgb(77, 36, 151);
	margin-left: auto;
	margin-right: auto;
	text-align: center;
	width: 300px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="container" style="margin: 30px 0 30px 0">
					<div id="logo">
						<h1 class="text-info text-center">
							<a href="/nado/index.jsp"
								style="font-size: 70px; font-weight: bold; text-decoration: none; color: #4d2497;">NADO</a>
						</h1>
					</div>
				</div>
				<div class="card">
					<div class="card-body" style="padding: 50px">
						<div class="card-title" style="margin-bottom: 50px">
							<h4 class="text">아이디 등록신청</h4>
						</div>
						<div class="card-text">
							<form id="enrollForm">
								<div class="form-group" style="padding: 10px 0 10px 0;">
									<label for="emp_name">이름</label> <input type="text"
										name="emp_name" id="emp_name" class="form-control"
										placeholder="이름입력">
								</div>
								<div class="form-group" style="padding: 10px 0 10px 0;">
									<label for="user_id" id="user_id_lable">아이디</label> <input
										type="text" name="user_id" id="user_id" class="form-control"
										placeholder="아이디(최소5글자)">
									<div id="idAlertBox" style="border-top: 0;"></div>
								</div>
								<div class="form-group" style="padding: 10px 0 0 0;">
									<label for="user_pwd">비밀번호</label> <input type="password"
										name="user_pwd" id="user_pwd" class="form-control"
										placeholder="비밀번호">
								</div>
								<div id="pwdAlertBox"></div>
								<div class="form-group">
									<input type="password" id="user_pwd2" class="form-control"
										placeholder="비밀번호확인">
									<div id="pwdComfirm" style="border-top: 0;"></div>
								</div>

								<div class="form-group" style="padding: 10px 0 10px 0;">
									<label for="phone">전화번호</label> <input type="tel" name="phone"
										id="phone" class="form-control" placeholder="-없이 숫자만 입력하세요.">
								</div>
								<div id="phoneAlertBox"></div>
								<div class="form-group" style="padding: 10px 0 10px 0;">
									<label for="email">이메일</label> <input type="email" name="email"
										id="email" class="form-control" placeholder="이메일주소">
										<div id="emailAlertBox" style="border-top: 0;"></div>
								</div>				
								<div style="display: inline">
									<div class="form-group">
										<label for="postcode" style="display: block;">주소</label> <input
											type="text" name="postcode" id="postcode" placeholder="우편번호">
										<input type="text" name="sample6_extraAddress"
											id="sample6_extraAddress" placeholder="참고항목"><span> <input
											type="button"
											onclick="sample6_execDaumPostcode()" value="우편번호 찾기" id="btn1"></span>
									</div>
								</div>					
								<div class="form-group">
									<input type="text" class="form-control" name="address" id="address" placeholder="주소">
								</div>
								<div class="form-group">
									<input type="text" class="form-control" name="detailAddress" id="detailAddress" placeholder="상세주소">			
								</div>	
								<div class="form-group">
									<input type="submit" class="form-control btn-info abb"
										value="가입신청">
								</div>
							</form>
						</div>
						<!-- card-text 끝 -->
					</div>
					<!-- card-body 끝 -->
				</div>
				<!-- card 끝 -->
			</div>
			<!-- col-lg-6 끝 -->
			<div class="col-lg-2"></div>
		</div>
		<!-- 로우 끝 -->
	</div>
	<!-- 컨테이너 끝 -->
	<script type="text/javascript" src="/nado/resources/js/junEnroll.js"></script>
</body>
</html>