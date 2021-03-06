﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">

	<h2>--º&nbsp; 자유게시판&nbsp; º-- </h2>  *AhnCheat회원들이 자유롭게 소통할 수 있는 공간<br><br><br>
	<div style="float: left;">
	<p>
		▶Total :
		<c:out value="${boardCount}" />
	</p>
	</div>
	<br>
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
		<thead>
			<tr>
				<th class="number">번호</th>
				<th class="writer">작성자</th>
				<th class="subject">제목</th>
				<th class="date">등록일</th>
				<th class="count">조회</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
					<td class="number">${list.boardno}</td>
					<td class="writer">${list.name}</td>
					<td class="subject"><c:choose>
							<c:when test="${param.q != null}">
								<a href="freeView.go?pg=${param.pg}&bno=${list.boardno}&f=${param.f}&q=${param.q}"
									data-toggle="tooltip" title="${list.title}"
									data-placement="right"> <c:if test="${list.depth > 0}">
										<c:forEach begin="0" end="${list.depth}">
									&nbsp;&nbsp;
									</c:forEach>
									->
									</c:if> ${list.title}
								</a>
							</c:when>
							<c:otherwise>
								<a href="freeView.go?pg=${param.pg}&bno=${list.boardno}"
									data-toggle="tooltip" title="${list.title}"
									data-placement="right"> <c:if test="${list.depth > 0}">
										<c:forEach begin="0" end="${list.depth}">
									&nbsp;&nbsp;
									</c:forEach>
									->
									</c:if> ${list.title}
								</a>
							</c:otherwise>
						</c:choose> <c:if test="${list.boardReplyCount > 0}">
							<span style="color: red;">${list.boardReplyCount}</span>
						</c:if></td>
					<td class="date">${list.regdate}</td>
					<td class="count">${list.countno}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div>
		<se:authorize ifAnyGranted="ROLE_USER">
			<!-- Any 아무거나 만족해도  -->
			<%--    <se:authorize ifAnyGranted="hasRole('ROLE_USER')">  --%>
			<br>
			<input class="btn btn-primary" type="button" value="글쓰기"
				id="freeWrite" style="float: right;">
		</se:authorize>

		<input class="btn btn-primary" type="button" value="글쓰기"
			id="freeWrite" style="float: right; text-align: center;">
	</div>
	<div style="text-align: center; clear:both;">
		<c:set var="pager" value="${pager.toString()}" />
		<ul class="pagination">${pager}</ul>
	</div>
	<br>
	<div style="text-align: center;">
		<form action="boardFreeSearch.go">
			<select id="select" name="field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="name">작성자</option>
			</select> <input type="text" name="query"> <input
				class="btn btn-primary" type="submit" value="검색">
		</form>
	</div>
</div>