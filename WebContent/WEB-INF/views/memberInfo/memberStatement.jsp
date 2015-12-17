<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
tr, th, td {
	text-align: center;
}
</style>
<!-- 기록 추적용 진술서 -->
<div class="container">
	<h3>타임라인 설정</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th></th>
			<th>피해유형</th>
			<th>피해물품</th>
			<th>용의자명</th>
			<th>용의자 계좌</th>
			<th>용의자 연락처</th>
			<th>등록일 | 피해 발생일</th>
			<th>내용</th>
		</tr>
		<c:if test="${not empty isDTO}">
			<tr>
				<td><input type="radio" name="chk_statement"></td>
				<td>${isDTO.cheatname}</td>
				<td><b>${isDTO.goodsname}</b><br>${isDTO.deposit} 원 | ${isDTO.domain}</td>
				<td>${isDTO.cheatername}</td>
				<td>${isDTO.bankname}| ${isDTO.account}</td>
				<td>${isDTO.phone}</td>
				<td>${isDTO.regdate}| ${isDTO.depositdate}</td>
				<td><input type="button" class="btn btn-info btn-sm viewBtn"
					value="자세히보기" id="statementView.go?sno=${isDTO.stateno}&cno=${isDTO.cheatno}"></td>
			</tr>
		</c:if>
		<c:if test="${empty isDTO}">
			<tr>
				<td colspan="8">관심 등록한 진술서가 없습니다.<br>
				진술서를 관심등록하시면 피의자를 추적할 수 있습니다.</td>
			</tr>
		</c:if>
	</table>
	<div style="float:right;">
		<input type="button" id="deleteInterestStatement" class="btn btn-primary btn-sm" value="관심 등록 해제">
	</div>
</div>

<!-- 자신이 쓴 진술서 -->
<div class="container">
	<h3>진술서</h3>
	<table class="table table-hover table-responsive"
		style="margin-top: 10px">
		<tr>
			<th></th>
			<th>피해유형</th>
			<th>피해물품</th>
			<th>용의자명</th>
			<th>용의자 계좌</th>
			<th>용의자 연락처</th>
			<th>등록일 | 피해 발생일</th>
			<th>내용</th>
		</tr>
		<c:forEach var="l" items="${list}">
			<tr>
				<td>
					<input type="radio" class="radio_statement" name="chk_statement" onclick="radio_statement(${l.stateno})">
					<input type="hidden" id="hidden_radio_value" value="0">
				</td>
				<td>${l.cheatname}</td>
				<td><b>${l.goodsname}</b><br>${l.deposit} 원 | ${l.domain}</td>
				<td>${l.cheatername}</td>
				<td>${l.bankname}| ${l.account}</td>
				<td>${l.phone}</td>
				<td>${l.regdate}| ${l.depositdate}</td>
				<td><input type="button" class="btn btn-info btn-sm viewBtn"
					value="자세히보기" id="statementView.go?sno=${l.stateno}&cno=${l.cheatno}"></td>
			</tr>
		</c:forEach>
	</table>
	<div style="float:right;">
		<input type="button" id="regInterestStatement" class="btn btn-primary btn-sm" value="관심 등록">
	</div>
	<!-- 페이저 -->
	<div style="text-align: center; clear:both;">
		<c:set var="pager" value="${pager.toString()}" />
		${pager}
	</div>
</div>