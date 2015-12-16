<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div style="float: left">
	<div style="width: 800px; height:200px">
	<h4>신고된 자유게시판</h4>
		<table class="table table-hover">
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.boardno}</td>
					<td>${list.name}</td>
					<td onMouseMove="setPreviewBox(event);"
						onMouseOver="showPreview('${list.title}'); return true;"
						onMouseOut="hidePreview(); return true;">${list.title}</td>
					<td>${list.regdate}</td>
					<td>${list.countno}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="width: 800px; height:200px">
		<h4>신고된 사진게시판</h4>
		<table class="table table-hover">
		<tr>	
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach items="${phlist}" var="phlist">
			<tr>
				<td>${phlist.boardno}</td>
				<td>${phlist.title}</td>
				<td>${phlist.name}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	<div style="width: 800px; height:200px">
		<h4>신고된 댓글</h4>
		<table class="table table-hover">
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>내용</th>
			</tr>
			<c:forEach items="${relist}" var="relist">
				<tr>
					<td>${relist.replyno}</td>
					<td>${relist.name}</td>
					<td>${relist.content}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>