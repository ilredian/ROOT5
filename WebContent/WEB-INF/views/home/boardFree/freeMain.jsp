<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
	<h2>자유게시판</h2>
	<p>
		Total :
		<c:out value="${boardCount}" />
	</p>

	<table class="table table-hover">
		<thead>
			<tr>
				<th class="number">번호</th>
				<th class="writer">작성자</th>
				<th class="subject">제목</th>
				<th class="date">등록일</th>
				<th class="count">조회</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="n" items="${list}">
				<tr>
					<td class="number">${n.boardno}</td>
					<td class="writer">작성자</td>
					<td class="subject"><a
						href="freeView.go?page=${pager}&contentNo=${n.boardno}">${n.title}</a></td>
					<td class="date">${n.regdate}</td>
					<td class="count">${n.countno}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<div>
		<br>
		<!-- Spring Taglib 사용 -->
		<se:authorize ifAllGranted="ROLE_USER , ROLE_ADMIN">
<!-- 	예제<a class="btn-write button" href="noticeReg.htm">글쓰기</a>
 -->
			 <input type="button" value="글쓰기" id="freeWrite"
			style="float: right; width: 60px; height: 40px; margin-right: 40px; margin-top: -40px;">
		</se:authorize>
		
	
	</div>
</div>
<div style="text-align: center;">
	<c:set var="pager" value="${pager.toString()}" />
	${pager}
</div>