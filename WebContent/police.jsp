<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">


</script>
</head>
<body>

DB 자료 열람 <br>
ver_ 오늘 날짜 기준_ <br>
<a href="/excel2">차트 다운</a> <br>
<a href="/excel">테이블 다운</a> <br>

///////////

이용자에게 들어온 피드백  <br>
버튼을 누르면 로그인한 경찰이 담당사건을 맡음

<div class="container">
	<h3>진술서</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px" border="solid">
		<tr>
			<th></th>
			<th>피해유형</th>
			<th>피해물품</th>
			<th>용의자명</th>
			<th>용의자 계좌</th>
			<th>용의자 연락처</th>
			<th>등록일 | 피해 발생일</th>
			<th>내용</th>
			<th>수사 확인</th>
		</tr>
		<c:forEach var="l" items="${list}">
			<tr>
				<td><input type="radio" name="chk_statement"></td>
				<td>${l.cheatname}</td>
				<td><b>${l.goodsname}</b><br>${l.deposit} 원 | ${l.domain}</td>
				<td>${l.cheatername}</td>
				<td>${l.bankname} | ${l.account}</td>
				<td>${l.phone}</td>
				<td>${l.regdate} | ${l.depositdate}</td>
				<td><button href="/statementView.go?sno=${l.stateno}&cno=1">자세히보기</button></td>
				<td><button onclick="">승낙</button></td>
			</tr>
		</c:forEach>
	</table>
</div>	

</body>
</html>