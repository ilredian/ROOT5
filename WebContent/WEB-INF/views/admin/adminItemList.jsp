<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
	<h3>거래물품종류</h3>
</div>
<br>
<div style="float: right;">
	<form action="insertItem.go" method="post">
		물품명(영어) :<input type="text" name="gk"> 물품명(한글) :<input
			type="text" name="gn"> 물품태그 :<input type="text" name="gs">
		<input type="submit" class="btn btn-primary btn-sm" value="추가">
	</form>
</div>
<table class="table table-hover">
	<tr>
		<th>물품명(영어)</th>
		<th>물품명(한글)</th>
		<th>물품태그</th>
		<th>태그이미지</th>
		<th>기능</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr style="text-align: center;">
			<td>${list.goodskind}</td>
			<td>${list.goodsname}</td>
			<td>${list.goodsspan}</td>
			<td><h1>
					<span class="${list.goodsspan}"></span>
				</h1></td>
			<td>
				<div class="btn-group">
					<input type="button" class="btn btn-info update" value="수정">
					<input type="button" class="btn btn-danger delete"
						id="deleteItem.go?gn=${list.goodsname}&gk=${list.goodskind}&gs=${list.goodsspan}"
						value="삭제">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>