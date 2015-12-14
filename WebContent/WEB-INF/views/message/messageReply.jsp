<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<h4>쪽지 보내기</h4>
	<form action="" method="post">
		<input type="hidden" name="frommemberno" value="${messageDTO.frommemberno}">
		<div class="form-group">
			<label for="name"> 받는이 : </label> <input readonly name="name" type="text"
				class="form-control" id="frommemberno" value="${memberDTO.name} &lt;${memberDTO.email}&gt;">
		</div>
		<div class="form-group">
			<label for="title"> 제목 : </label> <input name="title" type="text"
				class="form-control" id="title" value="RE : ${messageDTO.title}">
		</div>
		<div class="form-group">
			<label for="comment"> 내용 : </label>
			<textarea name="content" class="form-control" rows="5" id="comment">
			
			-----원 본 글 -----
			${messageDTO.content}
			</textarea>
		</div>
		<input type="button" value="목록" class="btn btn-default" id="messageMain" style="float: left;"> <input
			type="submit" style="float: right;" class="btn btn-primary btn-sm"
			value="쪽지 보내기">
	</form>
</div>