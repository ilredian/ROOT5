<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div style="float: left">
	<div style="width: 800px; height: 400px; float: top;">
		<table class="table table-hover">
			<thead>
				<th>신고된 자유게시판</th>
			</thead>
		</table>
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
	<div style="width: 800px; height: 400px; float: top; margin-top: 10px;">
		<table class="table table-hover">
			<thead>
				<th>신고된 사진게시판</th>
				<td>추후에 등록</td>
			</thead>
		</table>
	</div>
	<div style="width: 800px; height: 400px; float: top; margin-top: 10px;">
		<table class="table table-hover">
			<thead>
				<th>신고된 댓글</th>
			</thead>
		</table>
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
<div style="float: left">
	<div style="width: 300px; height: 50px; float: top; margin-left: 10px;">
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
		style="width: 800px; height: 900px; float: top; margin-top: 15px; margin-left: 10px; margin-bottom: 100px">

		<table class="table table-hover">
			<thead>
				<th>받은 메일</th>
			</thead>
		</table>
		<table class="table table-hover">
			<tr>
				<th style="width: 10%;">글번호</th>
				<th style="width: 20%;">보낸이</th>
				<th style="width: 32%;">제목</th>
				<th style="width: 28%;">날짜</th>
				<th style="width: 10%;">메일크기</th>
			</tr>

			<c:choose>
				<c:when test="${not empty maillist}">
					<fmt:parseNumber var="size" value="${maillist.size()}"
						integerOnly="true" />
					<c:forEach items="${maillist}" var="maillist" varStatus="index">
						<tr>
							<td>${size - index.index}</td>
							<td><c:out
									value="${(maillist.from).replace('[', '').replace(']', '')}" /></td>
							<td><a href="adminMailView.go?mno=${index.index + 1}">${maillist.title}</a></td>
							<td>${maillist.date}</td>
							<td><c:choose>
									<c:when test="${maillist.getMailSize() > (1024*1024)}">
										<fmt:parseNumber var="mailSize"
											value="${maillist.getMailSize() /(1024*1024)}"
											integerOnly="true" />
										<c:out value="${mailSize} MB" />
									</c:when>
									<c:when test="${maillist.getMailSize() > 1024}">
										<fmt:parseNumber var="mailSize"
											value="${maillist.getMailSize() / 1024}" integerOnly="true" />
										<c:out value="${mailSize} KB" />
									</c:when>
									<c:otherwise>
										<fmt:parseNumber var="mailSize"
											value="${maillist.getMailSize()}" integerOnly="true" />
										<c:out value="${mailSize} byte" />
									</c:otherwise>
								</c:choose></td>
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
	</div>
</div>