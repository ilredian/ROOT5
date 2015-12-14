<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
	<h3>받은 메일</h3>
</div>
<div style="float: right;">
	받은 메일 총 용량 : <c:out value="${sumMailSizeString}" />
</div>
<table class="table table-hover">
	<tr>
		<th style="width:10%;">글번호</th>
		<th style="width:20%;">보낸이</th>
		<th style="width:32%;">제목</th>
		<th style="width:28%;">날짜</th>
		<th style="width:10%;">메일크기</th>
	</tr>

	<c:choose>
		<c:when test="${not empty list}">
		<fmt:parseNumber var="size" value="${list.size()}" integerOnly="true" />
			<c:forEach items="${list}" var="list" varStatus="index">
				<tr>
					<td>${size - index.index}</td>
					<td><c:out value="${(list.from).replace('[', '').replace(']', '')}" /></td>
					<td><a href="adminMailView.go?mno=${index.index + 1}">${list.title}</a></td>
					<td>${list.date}</td>
					<td>
					<c:choose>
						<c:when test="${list.getMailSize() > (1024*1024)}">
						<fmt:parseNumber var="mailSize" value="${list.getMailSize() /(1024*1024)}" integerOnly="true" />
							<c:out value="${mailSize} MB"/>
						</c:when>
						<c:when test="${list.getMailSize() > 1024}">
						<fmt:parseNumber var="mailSize" value="${list.getMailSize() / 1024}" integerOnly="true" />
							<c:out value="${mailSize} KB"/>
						</c:when>
						<c:otherwise>
						<fmt:parseNumber var="mailSize" value="${list.getMailSize()}" integerOnly="true" />
							<c:out value="${mailSize} byte"/>
						</c:otherwise>
					</c:choose>
					</td>
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