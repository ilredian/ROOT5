<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div style="float: left">
	<div style="width: 800px; height: 500px; float: top;">
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
					<td onMouseMove="setPreviewBox(event);"
						onMouseOver="showPreview('${list.content}'); return true;"
						onMouseOut="hidePreview(); return true;">${list.title}</td>
					<td>${list.regdate}</td>
					<td>${list.countno}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="width: 500px; height: 300px; float: top; margin-top: 10px;">
		<table class="table table-hover">
			<thead>
				<th>신고된 사진게시판</th>
				<td>추후에 등록</td>
			</thead>
		</table>
	</div>
	<div style="width: 500px; height: 300px; float: top; margin-top: 10px;">
		<table class="table table-hover">
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>asdf</th>
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
<div style="float: left">
	<div
		style="width: 300px; height: 450px; float: top; margin-left: 10px;">
		<table>
			<thead>
				<tr>
					<th>등록된 진술서수 :</th>
					<td><c:out value="${boardCount}"></c:out></td>
				</tr>
			</thead>
			<thead>
				<th>추가 예정</th>
			</thead>
		</table>
	</div>
	<div
		style="width: 300px; height: 450px; float: top; margin-top: 15px; margin-left: 10px;">
		<table class="table table-hover">
			<thead>
				<th>받은 메일 목록</th>
			</thead>
		</table>
	</div>
</div>