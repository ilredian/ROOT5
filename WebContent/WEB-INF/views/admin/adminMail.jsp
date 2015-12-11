<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>보낸 메일</h3>
<form action="email.go">
	<table class="table">
		<tr>
		<th class="active">받는사람</th>
		<td>
			<input type="text" name="e_mail"
				placeholder="이메일을 입력해 주세요"><br>
			</td>
		</tr>
		<tr>
		<th class="active">제목</th>
			<td><input type="text" name="id"><br>
			</td>
		</tr>
		<tr>
		<th class="active">내용</th>
			<td><textarea rows="10" cols="10"
					style="margin: 0px; width: 663px; height: 206px;"></textarea>
			</td>
		</tr>
		
	</table>
	<div class="btn-group" style="margin-left: 650px">
			<input type="button" class="btn btn-info" value="메일 보네기">
		</div>
</form>