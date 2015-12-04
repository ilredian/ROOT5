<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
	// html dom 이 다 로딩된 후 실행된다.
	$(document).ready(function() {
		// memu 클래스 바로 하위에 있는 a 태그를 클릭했을때
		$(".menu>a").click(function() {
			// 현재 클릭한 태그가 a 이기 때문에
			// a 옆의 태그중 ul 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
			$(this).next("ul").toggleClass("hide");
		});
	});
</script>
</head>
<body>

	<div class="container">
		<h1>관리자 페이지</h1>
		<p>이페이지는 고나리자 전용 페이지 입니다</p>
		<div class="container" style="margin-top: 20px">
			<div class="row">

				<div class="col-sm-2">
					<ul>
						<li class="menu"><a><img src="" alt="신고관리" /></a>
							<ul class="hide">
								<li>자유게시판</li>
								<li>사진게시판</li>
								<li>댓글</li>

							</ul></li>

						<li class="menu"><a><img src="" alt="메뉴관리" /></a>
							<ul class="hide">
								<li>사이트명</li>
								<li>거래물품종류</li>
								<li>은행종류</li>
								<li>회원가입</li>
								<li>사기종류</li>
								<li>회원관리</li>
							</ul></li>
						<li class="menu"><a><img src="" alt="메일함" /></a>
							<ul class="hide">
								<li>보낸함</li>
								<li>받은함</li>
							</ul></li>
					</ul>
				</div>

				<div class="col-sm-10">
					<div style="float: left">
						<div style="width: 500px; height: 300px; float: top;">
							<table class="table table-hover">
								<thead>
									<th>신고된 자유게시판</th>
								</thead>
							</table>
						</div>
						<div
							style="width: 500px; height: 300px; float: top; margin-top: 10px;">
							<table class="table table-hover">
								<thead>
									<th>신고된 사진게시판</th>
								</thead>
							</table>
						</div>
						<div
							style="width: 500px; height: 300px; float: top; margin-top: 10px;">
							<table class="table table-hover">
								<thead>
									<th>신고된 댓글</th>
								</thead>
							</table>
						</div>
					</div>
					<div style="float: left">
						<div style="width: 300px; height: 450px; float: top; margin-left: 10px;">
							<table>
								<thead>
								<tr>
									<th>방문자수&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; </th>
									<td> 2명 </td></tr>
								</thead>
								<thead>
								<tr>
									<th>등록된 진술서수 : </th>
									<td> 4개 </td></tr>
								</thead>
								<thead>
									<th>추가 예정</th>
								</thead>
							</table>
						</div>
						<div style=" width: 300px; height: 450px; float: top; margin-top: 15px; margin-left: 10px;">
							<table class="table table-hover">
								<thead>
									<th>받은 메일 목록</th>
								</thead>
							</table>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>