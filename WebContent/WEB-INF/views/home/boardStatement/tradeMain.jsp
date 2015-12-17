﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	직거래 피해 사례 수 : <c:out value="${boardCount}"></c:out>
	<table class="table table-hover table-responsive">
		<thead>
			<tr>
				<th>번호</th>
				<th>피해물품</th>
				<th style="width:10%;">용의자명</th>
				<th>용의자 계좌번호</th>
				<th>용의자 연락처</th>
				<th>등록일</th>
				<th style="width:10%;">내용</th>
				<c:if test="${memberInfo.typeno == 2}"><th style="width:10%;">사건 접수</th></c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.stateno}</td>
					<td>${list.goodsname}</td>
					<td>${list.cheatername}</td>
					<td>${list.account}</td>
					<td>${list.phone}</td>
					<td>${list.regdate}</td>
					<td><input type="button" class="btn btn-info viewBtn btn-sm" value="자세히보기"
						id="statementView.go?sno=${list.stateno}&cno=1"></td>
					<c:if test="${memberInfo.typeno == 2}">
						<c:choose>
							<c:when test="${list.complete != null}">
								<td>
									<input type="button" class="btn btn-danger btn-sm completeStatement" value="검거">
								</td>
							</c:when>
							<c:when test="${list.trace != null}">
								<td>
									<input type="button" class="btn btn-warning btn-sm traceStatement" value="추적중">
								</td>
							</c:when>
							<c:when test="${list.police != 0}">
								<td>
									<input type="button" class="btn btn-default btn-sm regOkStatement" value="접수완료">
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<input type="button" class="btn btn-success btn-sm regStatementPolice" id="${list.stateno}" value="사건접수">
								</td>
							</c:otherwise>
						</c:choose>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div>
	<a href="trade.go"><input type="button" class="btn btn-primary" id="a" style="float: right;" value="사기피해사례 등록"></a>
</div>
<div style="text-align: center; clear:both;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">
	${pager}
	</ul>
</div>
