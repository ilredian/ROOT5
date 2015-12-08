<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<h4>받은 쪽지 읽기</h4>
	<form action="" method="post">
		<div class="form-group">
			<label for="frommemberno"> 보낸이 회원번호 : </label> <input
				name="frommemberno" type="text" class="form-control"
				id="frommemberno" value="${messageDTO.frommemberno}"
				readonly="readonly">
		</div>
		<div class="form-group">
			<label for="name"> 보낸이 : </label> <input name="name" type="text"
				class="form-control" id="frommemberno" value="${memberDTO.name}"
				readonly="readonly">
		</div>
		<div class="form-group">
			<label for="title"> 제목 : </label> <input name="title" type="text"
				class="form-control" id="title" value="${messageDTO.title}"
				readonly="readonly">
		</div>
		<div class="form-group">
			<label for="comment"> 내용 : </label>
			<textarea name="content" class="form-control" rows="5" id="comment"
				readonly="readonly">${messageDTO.content}</textarea>
		</div>
		<input type="button" value="목록" class="btn btn-default" id="messageMain" style="float: left;">
	</form>
</div>