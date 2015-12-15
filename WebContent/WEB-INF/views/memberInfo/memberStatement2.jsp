<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
tr, th, td {
	text-align: center;
}
</style>
<div class="container">
	<h3>접수 처리한 진술서(경찰 회원 전용)</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th>용의자명</th>
			<th>용의자 계좌</th>
			<th>용의자 연락처</th>
			<th>용의자 아이디</th>
			<th>내용</th>
			<th>추적 현황</th>
			<th>검거 현황</th>
		</tr>
		<c:forEach var="l" items="${list}">
			<tr>
				<td>${l.cheatername}</td>
				<td>${l.account}</td>
				<td>${l.phone}</td>
				<td>${l.cheaterid}</td>
				<td><input type="button" class="btn btn-info btn-sm viewBtn"
					value="자세히보기" id="statementView.go?sno=${l.stateno}&cno=${l.cheatno}"></td>
				<td><input type="button" class="btn btn-primary btn-sm updateTrace" id="policeUpdateTrace?gno=${l.groupno}" value="등록하기"></td>
				<td><input type="button" class="btn btn-primary btn-sm updateComplete" value="등록하기"></td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이저 -->
	<div style="text-align: center; clear:both;">
		<c:set var="pager" value="${pager.toString()}" />
		${pager}
	</div>
</div>