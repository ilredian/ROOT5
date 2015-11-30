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
</head>
<body>
	<div class="container">
		<img alt="직거래 피해 상세 보기" src="img/a_Dtaile.PNG">
	</div>
	<div class="container">
		<div class="container">
			<table class="table table table-striped">
				<colgroup>
					<col width="162">
					<col width="">
				</colgroup>
				<tr>
					<td>사기당한 물건의 이름 & 올린 날자와 시간</td>
				</tr>
				<tr>
					<td>사진& 피해자 이름 (김 OO 식으로)</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 피해 발생 사이트 정보 -->
	<div class="container">
		<div class="container">
			<h3>
				<img src="img/a_Dtaile1.PNG" alt="피해 발생 사이트 정보">
			</h3>
			<table class="table table table-striped">
				<colgroup>
					<col width="162">
					<col width="">
				</colgroup>
				<tr>
					<th scope="row" colspan=3">사기 사건 발생일</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">사이트명(URL)</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기 & 게시물 링크 버튼 넣기</p>
						</div>
					</td>
					<th scope="row">용의자 아이디</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">거래 물품 종류</th>
					<td>
						<div id="vContent">
							<p>거래물품 종류의 사진을 보여준다</p>
						</div>
					</td>
					<th scope="row">피해 금액</th>
					<td>
						<div id="vContent">
							<p>입력한 값을 받는다</p>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 피해 상세보기 -->
	<div class="container">
		<div class="container">
			<h3>
				<img src="img/a_Dtaile2.PNG" alt="용의자 (사기자)정보">
			</h3>
			<table class="table table table-striped">
				<colgroup>
					<col width="162">
					<col width="">
				</colgroup>
				<tr>
					<th scope="row">명의자 성명</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기(김 OO) 이런식으로</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">계좌번호</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기(은행 이름과 계좌번호 12자리중뒷자리6자리는 *표시)</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">연락처</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기(입력된 연락처 받기)</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">트징</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기(입력된 특징 등록)</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">IP 주소</th>
					<td>

						<div id="vContent">
							<p>IP주소는 경찰이나 관리자만 볼수 있다</p>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 사건개요 -->
	<div class="container">
		<div class="container">
			<h3>
				<img src="img/a_Dtaile3.PNG" alt="사건 개요 (진술서)">
			</h3>
			<table class="table table table-striped">
				<colgroup>
					<col width="162">
					<col width="">
				</colgroup>
				<tr>
					<th scope="row">사건발생 개요</th>
					<td>
						<div id="vContent">
							<p>입력된글 받기</p>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="container">
		<div class="container">
			<table class="table table table-striped">
				<tr>
					<td>댓글 수</td>
				</tr>
				<tr>
					<td>댓글 쓴 사람의 프로필 사진과 내용 이름 댓글 올린 시간이 나온다</td>
				</tr>
			</table>
			<button type="submit"><a href="#">댓글 쓰기</a></button>
		</div>
	</div>
</body>
</html>