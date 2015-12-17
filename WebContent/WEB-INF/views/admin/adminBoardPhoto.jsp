<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>사진게시판</h3>
<table class="table table-hover">

		<tr>	
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.boardno}</td>
				<td>${list.title}</td>
				<td>${list.name}</td>
				<td>
			<input type="button" class="btn btn-danger delete"
					id="deletePhotoBoard.go?bno=${list.boardno}" value="삭제">
			</td>
		</tr>
	</c:forEach>
</table>
<div style="text-align: center; clear:both;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">${pager}</ul>
</div>