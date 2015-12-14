<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
	
	<h2>공지사항 게시판</h2>
	<div style="float: left;">
		<p>
			Total :
			<c:out value="${boardCount}" />
		</p>
	</div>

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
					<td class="writer">관리자</td>
					<td class="subject" <c:if test="${list.critical == 1}">style="color:red;"</c:if>><c:choose>
							<c:when test="${param.q != null}">
							<c:if test="${list.critical == 1}"><span class="label label-danger">중요</span></c:if>
								<a
									href="noticeView.go?pg=${param.pg}&bno=${list.boardno}&f=${param.f}&q=${param.q}">${list.title}</a>
							</c:when>
							<c:otherwise>
								<a href="noticeView.go?pg=${param.pg}&bno=${list.boardno}">${list.title}</a>
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
		<se:authorize ifAllGranted="ROLE_USER,ROLE_ADMIN">
			<br>
			<input class="btn btn-primary" type="button" value="글쓰기"
				id="noticeWrite" style="float: right;">
		</se:authorize>

		<input class="btn btn-primary" type="button" value="글쓰기"
			id="noticeWrite" style="float: right;">
	</div>
	<div style="text-align: center; clear:both;">
		<c:set var="pager" value="${pager.toString()}" />
		<ul class="pagination">${pager}</ul>
	</div>
	<div style="text-align: center;">
		<form action="boardNoticeSearch.go">
			<select id="select" name="field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="name">작성자</option>
			</select> <input type="text" name="query"> <input
				class="btn btn-primary" type="submit" value="검색">
		</form>
	</div>
</div>
