<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form action="" method="post">
<div style="float: left;">
	<h3>메일 상세 화면</h3>
</div>
<div class="btn-group" style="float: right;">
	<input type="submit" class="btn btn-primary" value="보내기">
</div>
	<table class="table">
		<tr>
			<th class="active">보낸사람</th>
			<td><c:out value="${(receiveMailDTO.from).replace('[', '').replace(']', '')}" /></td>
		</tr>
		<tr>
			<th class="active">받는사람</th>
			<td><c:out value="${(receiveMailDTO.to).replace('[', '').replace(']', '')}" /></td>
		</tr>
		<tr>
			<th class="active">보낸날짜</th>
			<td>${receiveMailDTO.date}</td>
		</tr>
		<tr>
			<th class="active">제목</th>
			<td>${receiveMailDTO.title}</td>
		</tr>
		<c:if test="${not empty receiveMailDTO.fileLocation}">
			<tr>
				<th class="active">파일첨부</th>
				<td><a href="/download.go?path=${receiveMailDTO.fileLocation}&fileName=${(receiveMailDTO.fileName).replace('[', '').replace(']', '')}"><c:out value="${(receiveMailDTO.fileName).replace('[', '').replace(']', '')}" /></a></td>
			</tr>
			<tr>
				<th class="active">첨부파일크기</th>
				<td>${receiveMailDTO.size} byte</td>
			</tr>
		</c:if>
		<tr>
			<th class="active">내용</th>
			<td><c:choose>
						<c:when test="${not empty receiveMailDTO.content}">
						<c:out value="${(receiveMailDTO.content).replace('[', '').replace(']', '')}" />
						<c:out value="${receiveMailDTO.html}" />
						<%-- <c:import url="${receiveMailDTO.html}"/> --%>
						</c:when>
						<c:otherwise><c:out value="${receiveMailDTO.html}" /><c:import url="${receiveMailDTO.html}"/></c:otherwise></c:choose></td>
		</tr>
	</table>
</form>