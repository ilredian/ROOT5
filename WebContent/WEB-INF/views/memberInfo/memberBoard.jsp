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
tr, th, td {text-align: center;}
</style>
<div class="container">

<c:if test="${typeno==3}">
	<h3>변호사게시물</h3>
	게시물 수 : <c:out value="${boardCountLaw}"/>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th colspan="1">글번호</th>
			<th colspan="4">내용</th>
			<th colspan="1">조회</th>
			<th colspan="1">날짜</th>
		</tr>
		<c:forEach var="l" items="${listLaw}">
		<tr align="center">
			<td colspan="1">${l.boardno}</td>
			<td colspan="4"> <a href="lawView.go?pg=${pgFree}&bno=${l.boardno}">${l.title}</a></td>
			<td colspan="1">${l.countno}</td>
			<td colspan="1">${l.regdate}</td>
		</tr>
		</c:forEach>
	</table>
</c:if>
	<h3>자유게시물</h3>
	게시물 수 : <c:out value="${boardCountFree}"/>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th colspan="1">글 번호</th>
			<th colspan="4">내용</th>
			<th colspan="1">조회</th>
			<th colspan="1">날짜</th>
		</tr>
		<c:forEach var="f" items="${listFree}">
		<tr align="center">
			<td colspan="1">${f.boardno}</td>
			<td colspan="4" ><a href="freeView.go?pg=${pgLaw}&bno=${f.boardno}">${f.title}</a></td>
			<td colspan="1">${f.countno}</td>
			<td colspan="1">${f.regdate}</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 페이저 -->
<div style="text-align: center;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">${pager}</ul>
</div>
</div>
