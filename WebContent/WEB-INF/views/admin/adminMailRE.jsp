<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
	<h3>받은 메일</h3>
</div>
<div style="float: right;">
	받은 메일 총 용량 : <c:out value="${sumMailSizeString}" />
</div>
<table class="table table-hover">
	<tr>
		<th>글번호</th>
		<th>보낸이</th>
		<th>제목</th>
		<th>날짜</th>
		<th>메일크기</th>
	</tr>

	<c:choose>
		<c:when test="${not empty list}">
			<c:forEach items="${list}" var="list" varStatus="index">
				<tr>
					<td>${index.index +1}</td>
					<td><c:out value="${(list.from).replace('[', '').replace(']', '')}" /></td>
					<td><a href="adminMailView.go?mno=${index.index + 1}">${list.title}</a></td>
					<td>${list.date}</td>
					<c:choose>
						<c:when test="${list.size > (1024*1024)}">
							<c:out value="${list.size/(1024*1024)} MB"/>
						</c:when>
						<c:when test="${list.size > 1024}">
							<c:out value="${list.size/(1024*1024)} KB"/>
						</c:when>
						<c:otherwise>
							<c:out value="${list.size} byte"/>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">받은 메일이 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>