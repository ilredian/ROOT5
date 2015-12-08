<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form action="" method="post">
	<div class="form-group">
		<label for="frommemberno"> 받는이 회원번호 : </label> <input name="frommemberno"
			type="text" class="form-control" id="frommemberno">
	</div>
	<div class="form-group">
		<label for="name"> 받는이 : </label> <input name="name"
			type="text" class="form-control" id="frommemberno">
	</div>
	<div class="form-group">
		<label for="title"> 제목 : </label> <input name="title" type="text"
			class="form-control" id="title">
	</div>
	<div class="form-group">
		<label for="comment"> 내용 : </label>
		<textarea name="content" class="form-control" rows="5" id="comment">
		</textarea>
	</div>
	<input type="hidden" name="memberno" value="${param.mbno}">
	<input type="submit" class="btn btn-primary" value="쪽지 보내기">
</form>