<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<h4>받은 쪽지함</h4>
	<table class="table table-striped">
		<tr>
			<th></th>
			<th><p class="text-center">회원번호</p></th>
			<th><p class="text-center">보낸이</p></th>
			<th><p class="text-center">제목</p></th>
			<th><p class="text-center">받은일시</p></th>
			<th></th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list">
					<tr>
						<td>${list.frommemberno}</td>
						<td>${memberDTO.name}</td>
						<td><a href="messageOpen.go?msno=${list.messageno}">${list.title}</a></td>
						<td>${list.regdate}</td>
						<td>
							<div>
								<input type="button" class="btn btn-danger messageDelete" id="${list.messageno}"
									value="삭제">
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p class="text-center">
					<c:out value="받은 쪽지가 없습니다." />
				</p>
			</c:otherwise>
		</c:choose>
	</table>
	<div style="float: right;">
		<input type="button" id="${memberno}"
			class="btn btn-primary messageWrite" value="쪽지쓰기">
	</div>
	<div style="clear: both; text-align: center;">
		<c:set var="pager" value="${pager.toString()}" />
		${pager}
	</div>
</div>