<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>Bank</h3>
<table class="table table-hover">
	<tr>
		<th>은행명</th>
		<th>ad</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.bankname}</td>
			<td>
				<div class="container">
					<input type="button" class="btn btn-info update" id="updateBank.go?bank=${list.bankname}" value="수정"> <input
						type="button" class="btn btn-danger delete" id="deleteBank.go?bank=${list.bankname}" value="삭제"> <input
						type="button" class="btn btn-success insert" id="insertBank.go?bank=${list.bankname}" value="추가">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>