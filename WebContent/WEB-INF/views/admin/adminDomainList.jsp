<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="float: left;">
	<h3>사이트명</h3>
</div>
<br>
<div style="float: right;">
<form action="insertDomainList.go" method="post">
	주소 :<input type="text" name="do"> 이름 :<input type="text" name="dn">
	<input type="submit" class="btn btn-primary insert" value="추가">
</form>
</div>
<table class="table table-hover" style="clear: both;">
	<tr>
		<th>주소</th>
		<th>이름</th>
		<th>기능</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.domain}</td>
			<td>${list.domainname}</td>
			<td>
				<div class="btn-group">
					<input type="button" class="btn btn-info update" value="수정">
					<input type="button" class="btn btn-danger delete"
						id="deleteDomainList.go?do=${list.domain}&dn=${list.domainname}"
						value="삭제">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>