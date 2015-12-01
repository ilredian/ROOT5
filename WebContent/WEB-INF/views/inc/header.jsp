<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- nav -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<style>
.modal-header, h4, .close {
	background-color: #5cb85c;
	color: white !important;
	text-align: center;
	font-size: 30px;
}

.modal-footer {
	background-color: #f9f9f9;
}
</style>
<script type="text/javascript">
	/* 로그인 버튼  */
	$(document).ready(function() {
		$("#myBtn").click(function() {
			$("#myModal").modal();
		});
	});
	/* 로그아웃 버튼 */
	$(document).ready(function() {
		$("#myBtn2").click(function() {
			location.replace("/logout.go");
		});
	});
	
</script>

<nav class="navbar navbar-default" role="navigation" style="
    margin-bottom: 0px;
">
<div class="container-fluid" style="
    margin-top: 0px;
    margin-bottom: 0px;
">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.go">AhnCheat</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
			<li><a href="index.go">검색</a></li>
			<li><a href="registration.go">피해등록 </a></li>
			<li><a href="home.go">홈</a></li>
		</ul>
<!-- ////////////////////////////////////////////////////////// -->
<!--  2단계 Spring taglib 사용방법(인증과 권한) 코드에 대한 해석 -->
		<se:authorize ifNotGranted="ROLE_USER">
				<li><a
					href="${pageContext.request.contextPath}/joinus/login.htm">로그인</a></li>
			</se:authorize>
			<se:authentication property="name" var="LoingUser" />
			<se:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
				<li><a href="${pageContext.request.contextPath}/logout">
						(${LoingUser})로그아웃</a></li>
			</se:authorize>		
			
		<div class="links">
			<div class="container" align="right">
				<!-- 로그인 버튼 _ 로그인 전과 후의 변화 -->
						<c:when test="${empty session}">  <!-- 세션이 null 이 아니면 -->
							<button type="button" class="btn btn-default" id="myBtn"
								style="margin-right: 50px; margin-top: 10px">
								<span class="glyphicon glyphicon-log-in"></span>로그인
							</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-default" id="myBtn2"
								style="margin-right: 50px; margin-top: 10px">
								<span class="glyphicon glyphicon-log-in"></span>로그아웃
							</button>
						</c:otherwise>

				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">
<!--//////////////////////////////////////////////////////////////////////////  -->
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 35px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
										<span class="glyphicon glyphicon-lock"></span> 로그인
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
	 <c:if test="${param.error != null}">
   		<div>
     		로그인실패<br>
     		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
   			이유 : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
     	</c:if>
   		</div>
  	</c:if>  
								<!-- 로그인 폼 -->
								<!-- <form action="login.go" method="post"> 
								${pageContext.request.contextPath}/login -->
       							<form action="login.go" method="post"> 
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
										<label> <input type="checkbox" value="" checked>Remember
											me
										</label>
									</div>
									<input type="submit" class="btn btn-success btn-block">
									<span class="glyphicon glyphicon-off"></span> 로그인
								</form>
							</div>
							<div class="modal-footer">
								<button type="submit"
									class="btn btn-danger btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> 닫기
								</button>
								<p>
									<a href="signin.go">회원가입</a>
								</p>
								<p>
									<a href="pwSearch.go">비밀번호 찾기</a>
								</p>
							</div>
						</div>
						<!-- modalend -->
					</div>
				</div>
				<!--  -->
				<button type="submit" class="btn btn-default"
					style="margin-right: 50px; margin-top: 10px">
					<a href="memberMessage.go"> <span
						class="glyphicon glyphicon-log-in"></span> 내 정보
					</a>
				</button>
				<button type="submit" class="btn btn-default"
					style="margin-right: 50px; margin-top: 10px">
					<a href="signin.go"> <span class="glyphicon glyphicon-log-in"></span>
						회원가입
					</a>
				</button>
				<button type="submit" class="btn btn-default"
					style="margin-right: 50px; margin-top: 10px">
					<a href="pwSearch.go"> <span class="glyphicon glyphicon-log-in"></span>
						비밀번호 찾기
					</a>
				</button>
			</div>
		</div>
	</div>
</div>
</nav>