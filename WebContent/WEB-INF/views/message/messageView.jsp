<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<div style="float:left;">
		<h4>받은 쪽지 읽기</h4>
	</div>
	<br>
	<div style="float:right;">
		보낸 일시 : ${messageDTO.regdate}
	</div>
	<div class="form-group" style="clear:both;">
		<label for="name"> 보낸이 : </label> <input name="name" type="text"
			class="form-control" id="frommemberno" value="${memberDTO.name} &lt;${memberDTO.email}&gt;"
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
	<input type="hidden" id="messageNo" value="${param.msno}">
	<input type="button" value="목록" class="btn btn-default"
		id="messageMain" style="float: left;"> <input type="button"
		value="답장" class="btn btn-primary" id="messageReply"
		style="float: right;">
</div>