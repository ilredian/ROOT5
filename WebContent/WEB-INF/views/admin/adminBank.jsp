<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
<h3>Bank</h3>
</div>
<br>
<div style="float: right;">
<form action="insertBank.go" method="post">
	은행명 :<input type="text" name="bank">
	<input type="submit" class="btn btn-primary insert" value="추가">
</form>
</div>
<table class="table table-hover">
	<tr>
		<th>은행명</th>
		<th>기능</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.bankname}</td>
			<td>
				<div class="btn-group">
					<input type="button" class="btn btn-info update" value="수정">
					<input type="button" class="btn btn-danger delete" id="deleteBank.go?bank=${list.bankname}" value="삭제">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>