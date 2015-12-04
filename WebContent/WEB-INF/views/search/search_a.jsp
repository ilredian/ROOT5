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
		count : <c:out value="${tradeboardCount}"></c:out>
		<div class="container" align="center">

			<br> <br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
					<th>내용</th>
				</tr>
				<c:forEach items="${tradelist}" var="list">
					<tr>
						<td>${list.stateno}</td>
						<td>${list.goodsname}</td>
						<td>${list.cheatername}</td>
						<td>${list.account}</td>
						<td>${list.phone}</td>
						<td>${list.regdate}</td>
						<td>${list.content}</td>
					</tr>
				</c:forEach>
			</table>
			<c:set var="page" value="${tradepager.toString()}"/>
				${page}
			<h3>d에 대한 등록된 피해사례가 없습니다.</h3>
		</div>
		
		<br> <br>
		<h3>게임 피해 목록</h3><br>
		count : <c:out value="${gameboardCount}"></c:out>
		<div class="container" align="center">

			<br> <br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
					<th>내용</th>
				</tr>
			<c:forEach items="${gamelist}" var="list">
					<tr>
						<td>${list.stateno}</td>
						<td>${list.goodsname}</td>
						<td>${list.cheatername}</td>
						<td>${list.account}</td>
						<td>${list.phone}</td>
						<td>${list.regdate}</td>
						<td>${list.content}</td>
					</tr>
				</c:forEach>
			</table>
			<c:set var="page" value="${gamepager.toString()}"/>
				${page}
			<h3>d에 대한 등록된 피해사례가 없습니다.</h3>
		</div>
		
		<br> <br>
		<h3>비매너 피해 목록</h3><br>
		count : <c:out value="${mannerboardCount}"></c:out>
		<div class="container" align="center">

			<br> <br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
					<th>내용</th>
				</tr>
			<c:forEach items="${mannerlist}" var="list">
					<tr>
						<td>${list.stateno}</td>
						<td>${list.goodsname}</td>
						<td>${list.cheatername}</td>
						<td>${list.account}</td>
						<td>${list.phone}</td>
						<td>${list.regdate}</td>
						<td>${list.content}</td>
					</tr>
				</c:forEach>
			</table>
			<c:set var="page" value="${mannerpager.toString()}"/>
				${page}
			<h3>d에 대한 등록된 피해사례가 없습니다.</h3>
		</div>
		
		<br> <br>
		<h3>?? 피해 목록</h3>
		<div class="container" align="center">

			<br> <br>
			<table class="table table-condensed">
				<tr class="active">
					<th>번호</th>
					<th>피해 물품</th>
					<th>용의자명</th>
					<th>용의자 계좌번호</th>
					<th>용의자 연락처</th>
					<th>등록일</th>
					<th>내용</th>
				</tr>
			</table>
			<h3>d에 대한 등록된 피해사례가 없습니다.</h3>
		</div>
		<div class="container" align="right">
			<input type="submit" alt="직거래 피해사례 등록" value="직거래 피해사례 등록"
				align="right">
		</div>
	</div>
</body>
</html>