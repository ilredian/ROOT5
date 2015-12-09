<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<head>
<style>
.modal-header, h4, .close {
	background-color: black;
	color: white !important;
	text-align: center;
	font-size: 30px;
}

.modal-footer {
	background-color: #f9f9f9;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
				<!-- Modal -->
					<div class="modal-dialog" id="dialog" role="dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<a href="index.go"><button type="button" class="close" data-dismiss="modal">&times;</button></a>
								<h4>
										<span class="glyphicon glyphicon-lock"></span> 로그인
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
 
       							<form action="${pageContext.request.contextPath}/login.go" method="post"> 
									<div class="form-group">
										<label for="usrname"><span
											class="glyphicon glyphicon-user"></span> 이메일</label> <input
											type="text" class="form-control" id="usrname" name="email"
											placeholder="Enter email">
									</div>
									<div class="form-group">
										<label for="psw"><span
											class="glyphicon glyphicon-eye-open"></span> 비밀번호</label> <input
											type="password" class="form-control" id="psw" name="password"
											placeholder="Enter password">
									</div>
									<!-- 이메일 저장- 쿠키에 기록_ -->
									<div class="checkbox">
									<input id="remember_me" 
										name="_spring_security_remember_me"
										type="checkbox" checked>
										<label for="remember_me" class="inline"> Remember
											me
										</label>
									</div>
									<button type="submit" class="btn btn-default btn-block">
										<span class="glyphicon glyphicon-off"></span> 로그인
									</button>
								</form>
							</div>
							<div class="modal-footer">
								<button type="submit"
									class="btn btn-default btn-default pull-left"
									data-dismiss="modal">
									<a href="index.go"><span class="glyphicon glyphicon-remove"></span> 닫기</a>
								</button>
								<p>
									<a href="signin.go">회원가입</a>
								</p>
								<p>
									<a href="pwSearch.go">비밀번호 찾기</a>
								</p>
							</div>
						</div>
					</div>
						<!-- modalend -->
</body>
</html>