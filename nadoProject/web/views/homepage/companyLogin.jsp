<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="/nado/resources/css/bootstrap.css">
<style type="text/css">
.error {
	color: darkred;
}
</style>
</head>
<body>
<div class="container" style="margin-top: 60px;">
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4" style="margin-top: 30px;">
			<h3 class="text-info text-center" style="padding:30px;">NADO</h3>
				<div class="card" style="margin-top:30px;">
					<div class="card-body">
						<form action="/nado/compli" method="post">
							<div class="form-group">
								<label for="company_id">회사 오피스 아이디</label>
								<input type="text" class="form-control" id="company_id" name="company_id" placeholder="회사명을 입력하세요.">
							</div>
								<div class="form-group">
								<input type="submit" class="btn btn-primary form-control" value="다음">
							</div>	
						</form>		
					</div>
				</div>
					<div class="col-lg-4"></div>
				<!-- card 끝 -->

				<!-- card-body 끝 -->			
			</div>
			<!-- col-lg-4 끝 -->
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