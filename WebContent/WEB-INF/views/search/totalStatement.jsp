<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap Example</title>
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
		<h3>검색된 피해 사례 목록</h3>
		<br> <br>
		<h3>직거래 피해 목록</h3><br>
		<div style="float:left;">count : <c:out value="${tradeboardCount}" /></div>
		<div style="float:right;"><a href="statementMain.go?f=${param.f}&q=${param.q}&cno=1">더보기</a></div>
		<div class="container" align="center" style="clear: both;">
			<br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
				</tr>
				<c:forEach items="${tradelist}" var="list">
					<tr>
						<td>${list.stateno}</td>
						<td>${list.goodsname}</td>
						<td>${list.cheatername}</td>
						<td>${list.account}</td>
						<td>${list.phone}</td>
						<td>${list.regdate}</td>
					</tr>
				</c:forEach>
			</table>
			<h3><c:if test="${empty list}">에 대한 등록된 피해사례가 없습니다.</c:if></h3>
		</div>
		
		<br> <br>
		<h3>게임 피해 목록</h3><br>
		<div style="float:left;">count : <c:out value="${gameboardCount}" /></div>
		<div style="float:right;"><a href="statementMain.go?f=${param.f}&q=${param.q}&cno=2">더보기</a></div>
		<div class="container" align="center">

			<br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
				</tr>
			<c:forEach items="${gamelist}" var="list">
					<tr>
						<td>${list.stateno}</td>
						<td>${list.goodsname}</td>
						<td>${list.cheatername}</td>
						<td>${list.account}</td>
						<td>${list.phone}</td>
						<td>${list.regdate}</td>
					</tr>
				</c:forEach>
			</table>
			<h3><c:if test="${empty list}">에 대한 등록된 피해사례가 없습니다.</c:if></h3>
		</div>
		
		<br> <br>
		<h3>비매너 피해 목록</h3><br>
		<div style="float:left;">count : <c:out value="${mannerboardCount}" /></div>
		<div style="float:right;"><a href="statementMain.go?f=${param.f}&q=${param.q}&cno=3">더보기</a></div>
		<div class="container" align="center">

			<br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
				</tr>
			<c:forEach items="${mannerlist}" var="list">
					<tr>
						<td>${list.stateno}</td>
						<td>${list.goodsname}</td>
						<td>${list.cheatername}</td>
						<td>${list.account}</td>
						<td>${list.phone}</td>
						<td>${list.regdate}</td>
					</tr>
				</c:forEach>
			</table>
			<h3><c:if test="${empty list}">에 대한 등록된 피해사례가 없습니다.</c:if></h3>
		</div>
		
		<div class="container" align="right">
			<input type="submit" alt="직거래 피해사례 등록" value="직거래 피해사례 등록"
				align="right">
		</div>
	</div>
</body>
</html>