<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" id="pageNo" value="${param.pg}">
<input type="hidden" id="boardNo" value="${param.bno}">
<div class="container freeView">
	<h2>자유게시판</h2>
	<table class="table table-striped">
		<tbody>
			<tr>
				<th colspan="3">${boardFreeDTO.title}</th>
			</tr>
			<tr>
				<td style="width: 10%;"><img alt="no_pic"
					src="boardFreeimages/user_no_pic.gif"></td>
				<td style="width: 70%;">
					<table>
						<tr>
							<th>(피해회원)</th>
						</tr>
						<tr>
							<td>${memberInfo.message}</td>
						</tr>
					</table>
				</td>
				<td style="width: 20%">조회 ${boardFreeDTO.countno}</td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<tr>
			<td>
				${boardFreeDTO.content}
			</td>
		</tr>
	</table>
	<table class="table">
		<thead>
			<tr>
				<td><b>게시물 주소 :</b>
				<div id="asdf"><script>$('#asdf').text(window.location.href)</script>
					<div style="float: right;">
						<input class="btn btn-default" type="button" id="edit" value="수정">  <!-- freeEdit.go  -->
						<input class="btn btn-default" type="button" id="delete" value="삭제">  <!-- freeDelete.go -->
						<input class="btn btn-default" type="button" id="list" value="목록">  <!-- freeMain.go -->
					</div>
				</div>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><b>댓글 </b><c:out value="${replycount}" /><b>개</b></td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<tbody>
			<c:forEach var="replyDTO" items="${replyDTO}">
			<tr>
				<td style="width: 10%;">
					<img alt="no_pic" src="boardFreeimages/user_no_pic.gif">
				</td>
				<td style="width: 90%;">
					<table>
						<tr>
							<td>${replyDTO.name} 님 | ${replyDTO.regdate}</td>
						</tr>
						<tr>
							<td>${replyDTO.content}</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td style="text-align: center;" colspan="2">
					<input type="text" id="" name="" style="width: 90%">
					<input class="btn btn-default" type="button" id="replybtn" value="댓글 등록">
				</td>
			</tr>
		</tbody>
	</table>
</div>