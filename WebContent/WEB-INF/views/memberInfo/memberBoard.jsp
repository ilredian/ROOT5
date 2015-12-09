<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function() {
	$("#savebutton2").click(function() {
		$("#frm2").submit();
	});
});
</script>
<style>
tr {text-align: center;}
</style>
<div class="container">
	<h3>변호사게시물</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th>글 번호</th>
			<th>내용</th>
			<th>조회</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="l" items="${listLaw}">
		<tr>
			<td>${l.boardno}</td>
			<td><a href="lawView.go?pg=${param.pg}&bno=${n.boardno}"></a></td>
			<td>${l.countno}</td>
			<td>${l.regdate}</td>
		</tr>
		</c:forEach>
	</table>
	<h3>자유게시물</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th>글 번호</th>
			<th>내용</th>
			<th>조회</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="f" items="${listFree}">
		<tr align="center">
			<td>${f.boardno}</td>
			<td><a href="freeView.go?pg=${param.pg}&bno=${f.boardno}&f=${param.f}&q=${param.q}">${f.title}</a></td>
			<td>${f.countno}</td>
			<td>${f.regdate}</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 페이저 -->
<div style="text-align: center;">
	<c:set var="pager" value="${pager.toString()}" />
	${pager}
</div>
	<div class="container" align="center">
		<select>
			<option>제목+태그</option>
			<option>본문</option>
		</select> 
		<input type="text"><button type="submit" href="#">검색</button>
	</div>	
</div>
