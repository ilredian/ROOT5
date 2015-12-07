<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>사이트명</h3>
<table class="table table-hover">
	<tr>
		<th>주소</th>
		<th>이름</th>
		<th>aasdf</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.domain}</td>
			<td>${list.domainname}</td>
			<td>
				<div class="container">
					<input type="button" class="btn btn-info update"
						id="updateStatementCategory.go?do=${list.domain}&dn=${list.domainname}"
						value="수정"> <input type="button"
						class="btn btn-danger delete"
						id="deleteStatementCategory.go?do=${list.domain}&dn=${list.domainname}"
						value="삭제"> <input type="button"
						class="btn btn-success insert"
						id="insertStatementCategory.go?do=${list.domain}&dn=${list.domainname}"
						value="추가">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>