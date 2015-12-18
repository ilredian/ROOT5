<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<h3>���� ����</h3>

<div style="float: left;">
	<p>
		��Total :
		<c:out value="${mailCount}" />
	</p>
</div>
<div style="float: right;">
	���� ���� �� �뷮 : <c:out value="${sumMailSizeString}" />
</div>
<table class="table table-hover">
	<tr>
		<th style="width:10%;">�۹�ȣ</th>
		<th style="width:20%;">������</th>
		<th style="width:32%;">����</th>
		<th style="width:28%;">��¥</th>
		<th style="width:10%;">����ũ��</th>
	</tr>

	<c:choose>
		<c:when test="${not empty list}">
			<c:forEach items="${list}" var="list" varStatus="index">
				<tr>
					<td>${mailCount - ((param.pg-1)*10+index.index)}</td>
					<td><c:out value="${(list.from).replace('[', '').replace(']', '')}" /></td>
					<td><a href="adminMailView.go?mno=${index.index + 1}">${list.title}</a></td>
					<td>${list.date}</td>
					<td>
					<c:choose>
						<c:when test="${list.getMailSize() > (1024*1024)}">
						<fmt:parseNumber var="mailSize" value="${list.getMailSize() /(1024*1024)}" integerOnly="true" />
							<c:out value="${mailSize} MB"/>
						</c:when>
						<c:when test="${list.getMailSize() > 1024}">
						<fmt:parseNumber var="mailSize" value="${list.getMailSize() / 1024}" integerOnly="true" />
							<c:out value="${mailSize} KB"/>
						</c:when>
						<c:otherwise>
						<fmt:parseNumber var="mailSize" value="${list.getMailSize()}" integerOnly="true" />
							<c:out value="${mailSize} byte"/>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">���� ������ �����ϴ�.</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>
<div style="text-align: center; clear:both;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">${pager}</ul>
</div>