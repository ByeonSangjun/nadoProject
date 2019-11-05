<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NADO</title>
<link rel="stylesheet" href="/nado/resources/css/bootstrap.css">
<style type="text/css">
.error{
	color: darkred;
}
</style>
</head>
<body>
	<div class="container" style="margin-top: 60px;">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-4" style="margin-top:30px;">
				<div class="card">					
					<div class="card-body">
						<form method="post" id="cloudLoginForm">
							<div id="messageBox2" style="height: 60px;"></div>
							<div class="form-group">
								<input type="text" class="form-control" name="cloud_user_id" id="cloud_user_id" placeholder="아이디">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name="cloud_user_pwd" id="cloud_user_pwd" placeholder="비밀번호">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary form-control">로그인</button>
							</div>
						</form>
					</div>		
				</div>
				<!-- card-body 끝 -->
			</div>
			<!-- col-lg-4 끝 -->
			<div class="col-lg-2"></div>
			<!-- 회사 로그인 -->
			<div class="col-lg-4">
				<div class="card" style="margin-top:30px;">
					<div class="card-body">
						<form action="/nado/compli" method="post">
							<div class="form-group">
								<label for="company_id">회사 오피스 바로 입장</label>
								<input type="text" class="form-control" id="company_id" name="company_id" placeholder="회사명을 입력하세요.">
							</div>
								<div class="form-group">
								<input type="submit" class="btn btn-primary form-control" value="다음">
							</div>	
						</form>		
					</div>
				</div>
			</div>
			<!-- 회사 로그인 끝 -->
			<div class="col-lg-1"></div>
		</div>
		<!-- row 끝 -->
	</div>
	<!-- 컨테이너 끝 -->
<script src="/nado/resources/js/jquery-3.4.1.min.js"></script>
<script src="/nado/resources/js/jquery.validate.js"></script>
<script src="/nado/resources/js/additional-methods.js"></script>
<script src="/nado/resources/js/messages_ko.js"></script>
<script src="/nado/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="/nado/resources/js/jun.js"></script>
</body>
</html>