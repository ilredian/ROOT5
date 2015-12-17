<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>자유게시판</h3>
<p>
	Total :
	<c:out value="${boardCount}" />
</p>
<div id='preview' STYLE="BORDER-RIGHT: 1px; BORDER-TOP: 1px; Z-INDEX: 1; VISIBILITY: hidden; BORDER-LEFT: 1px; BORDER-BOTTOM: 1px; POSITION: absolute;"></div>
<table class="table table-hover">

	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>asdf</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.boardno}</td>
			<td>${list.name}</td>
			<td onMouseMove="setPreviewBox(event);" onMouseOver="showPreview('${list.content}'); return true;" onMouseOut="hidePreview(); return true;">
				${list.title}
			</td>
			<td>${list.regdate}</td>
			<td>${list.countno}</td>
			<td>
					<input type="button" class="btn btn-danger delete"
						id="deleteReportBoardFree.go?bno=${list.boardno}"
						value="삭제">
			</td>
		</tr>
	</c:forEach>
</table>
<div style="text-align: center; clear:both;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">${pager}</ul>
</div>
