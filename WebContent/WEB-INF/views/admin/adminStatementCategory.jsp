<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>사기종류</h3>
<table class="table table-hover">

	<tr>
		<th>사기번호</th>
		<th>사기이름</th>
		<th>df</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.cheatno}</td>
			<td>${list.cheatname}</td>
			<td>
				<div class="container">
					<input type="button" class="btn btn-info update"
						id="updateStatementCategory.go?cn=${list.cheatname}&cno=${list.cheatno}"
						value="수정"> <input type="button"
						class="btn btn-danger delete"
						id="deleteStatementCategory.go?cn=${list.cheatname}&cno=${list.cheatno}"
						value="삭제"> <input type="button"
						class="btn btn-success insert"
						id="insertStatementCategory.go?cn=${list.cheatname}&cno=${list.cheatno}"
						value="추가">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>