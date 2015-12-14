<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<h4>받은 쪽지함</h4>
	<table class="table table-hover">
		<tr>
			<th><p class="text-center">보낸이</p></th>
			<th><p class="text-center">제목</p></th>
			<th><p class="text-center">받은일시</p></th>
			<th><p class="text-center">삭제</p></th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list" varStatus="index">
					<tr style="text-align: center; <c:if test='${list.isopen == 0}'>font-weight:bold;</c:if>">
						<c:forEach items="${memberDTO}" var="memberDTO" begin="${index.index}" end="${index.index}">
							<td>
								<c:if test="${list.isopen == 0}">
									<span class="glyphicon glyphicon-envelope"></span>
								</c:if>
								<c:out value="${memberDTO.name}" /> &lt;<c:out value="${memberDTO.email}" />&gt;
							</td>
						</c:forEach>
						<td><a href="messageOpen.go?msno=${list.messageno}">
							<c:out value="${list.title}" />
						</a></td>
						<td>
							<c:out value="${list.regdate}" />
						</td>
						<td>
							<div>
								<input type="button" class="btn btn-danger btn-xs messageDelete" id="${list.messageno}"
									value="삭제">
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">
						<p class="text-center">
							<c:out value="받은 쪽지가 없습니다." />
						</p>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div style="float: right;">
		<input type="button" id="${memberno}"
			class="btn btn-primary btn-sm messageWrite" value="쪽지쓰기">
	</div>
	<div style="clear: both; text-align: center;">
		<c:set var="pager" value="${pager.toString()}" />
		<ul class="pagination">
			${pager}
		</ul>
	</div>
</div>