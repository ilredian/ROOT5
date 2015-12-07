<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
	<h3>댓글</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th>댓글번호</th>
			<th>내용</th>
			<th>게시판 종류</th>
			<th>게시판번호</th>
			<th>작성일</th>
		</tr>
		
		<tr>
			<td>${replyDTO.replyno}</td>
			<td>${replyDTO.content}</td>
			<td>${replyDTO.categoryno}</td>
			<td>${replyDTO.boardno}</td>
			<td>${replyDTO.regdate}</td>
		</tr>
	</table>
	<!-- 페이저 -->
	<div class="container" align="center">
		<select>
			<option>내용</option>
		</select> <input type="text"> <a type="submit" href="#">검색</a>
	</div>
</div>
