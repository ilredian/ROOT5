<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
	<h3>회원관리</h3>
</div>
<br>
<div style="float: right;">총 회원 수 :${memberCount}</div>

<form action="adminMember.go" style="clear:both;">

	<div style="float: right;">
		<select id="select" name="ps">
			<c:forEach begin="5" end="30" step="5" var="index">
				<c:choose>
					<c:when test="${empty param.ps && index == 10}">
						<option value="${index}" selected>${index}개씩 보기</option>
					</c:when>
					<c:when test="${param.ps == index}">
						<option value="${index}" selected>${index}개씩 보기</option>
					</c:when>
					<c:otherwise>
						<option value="${index}">${index}개씩 보기</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>

	<table class="table table-hover">
		<tr>
			<th>회원번호</th>
			<th>이메일</th>
			<th>이름</th>
			<th>타입번호</th>
			<th>활동여부</th>
			<th>기능</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.memberno}</td>
				<td>${list.email}</td>
				<td>${list.name}</td>
				<td>${list.typeno}</td>
				<c:choose>
					<c:when test="${list.active == 0}">
						<td>정상 회원</td>
					</c:when>
					<c:when test="${list.active == 1}">
						<td style="color:red;">탈퇴 회원</td>
					</c:when>
				</c:choose>
				<td>
					<div class="btn-group">
						<input type="button" class="btn btn-info update" value="수정">
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align: center; clear: both;">
		<c:set var="pager" value="${pager.toString()}" />
		<ul class="pagination">${pager}</ul>
	</div>
	<br>
	<div style="text-align: center;">

		<select id="select" name="field">
			<option value="email">회원 이메일</option>
			<option value="name">회원 이름</option>
			<option value="typeno">회원 타입 번호</option>
		</select> <input type="text" name="query"> <input
			class="btn btn-primary" type="submit" value="검색">

	</div>
</form>