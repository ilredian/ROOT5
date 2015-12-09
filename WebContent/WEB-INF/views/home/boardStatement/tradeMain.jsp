<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	직거래 피해 사례 수 : <c:out value="${boardCount}"></c:out>
	<table class="table table-hover table-responsive">
		<thead>
			<tr>
				<th>번호</th>
				<th>피해물품</th>
				<th>용의자명</th>
				<th>용의자 계좌번호</th>
				<th>용의자 연락처</th>
				<th>등록일</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.stateno}</td>
					<td>${list.goodsname}</td>
					<td>${list.cheatername}</td>
					<td>${list.account}</td>
					<td>${list.phone}</td>
					<td>${list.regdate}</td>
					<td><input type="button" class="viewBtn" value="자세히보기"
						id="statementView.go?sno=${list.stateno}&cno=1"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div style="text-align: center;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">
	${pager}
	</ul>
</div>
<div style="float: left;">
</div>
<div>
	<button id="a" style="float: right;">
		<a href="trade.go">사기피해사례 등록</a>
	</button>
</div>