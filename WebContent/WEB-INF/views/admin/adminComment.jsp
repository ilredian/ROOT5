<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>Reply</h3>
<p>
	Total :
	<c:out value="${boardCount}" />
</p>
<table class="table table-hover">
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>내용</th>
		<th>asdf</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.replyno}</td>
			<td>${list.name}</td>
			<td>${list.content}</td>
			<td>
				<input type="button" class="btn btn-danger delete"
					id="deleteReportBoardFree.go?bno=${list.replyno}" value="삭제">
			</td>
		</tr>
	</c:forEach>
</table>
<div style="text-align: center; clear:both;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">${pager}</ul>
</div>iv>