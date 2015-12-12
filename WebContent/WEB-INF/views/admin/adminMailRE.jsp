<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>받은 메일</h3>
<table class="table table-hover">

	<tr>
		<th>글번호</th>
		<th>보낸이</th>
		<th>제목</th>
		<th>내용</th>
		<th>날짜</th>
		<th>첨부파일</th>
		<th>메일크기</th>
	</tr>

	<c:choose>
		<c:when test="${not empty list}">
			<c:forEach items="${list}" var="list" varStatus="index">
				<tr>
					<td>${index.index +1}</td>
					<td>${list.from}</td>
					<td>${list.title}</td>
					<td>${list.content}</td>
					<td>${list.date}</td>
					<td>${list.file}</td>
					<td>${list.size}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="4">받은 메일이 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>

</table>
<div class="btn-group">
	<input type="button" class="btn btn-danger" value="삭제">
</div>