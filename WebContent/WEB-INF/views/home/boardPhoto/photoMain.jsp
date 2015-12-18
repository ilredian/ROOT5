<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<div class="container PhotoMain">
		<h2>사진 게시판</h2>
		<p>사건사진 자료 게시판 입니다</p>
		<div>
			<p>
				▶Total :
				<c:out value="${boardCount}" /><br>
			</p>
		</div>
		<div class="row">
			<c:forEach var="list" items="${list}">
				<div class="col-md-3">
					<a href="PhotoView.go?pg=${param.pg}&bno=${list.boardno}" class="thumbnail">
						<img src="${list.uploadfile}" alt="" style="width: 200px; height: 200px">
						<p class="subject">제목 : ${list.title}</p> 
					</a>
					
				</div>
			</c:forEach>
		</div>
		<div class="container" align="right">
			<a href="PhotoWrite.go"><input type="button" id="photobtn"
				class="btn btn-info" value="사진 등록"></a>
		</div>
		<div style="text-align: center; clear: both;">
			<c:set var="pager" value="${pager.toString()}" />
			<ul class="pagination">${pager}</ul>
		</div>
	</div>