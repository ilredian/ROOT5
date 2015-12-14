<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function() {
		$("#savebutton2").click(function() {
			$("#frm2").submit();
		});
	});
	
	
	$('#tradeMain').click(function(){
		location.replace("trade.go");
	});
	$('.btn btn-info view').click(function(){
		location.replace("statementView.go?sno=${l.stateno}&cno=1");
	});
	
	$('.viewBtn').click(function() {
		var loc = this.getAttribute("id");
		location.href = loc;
	});
	
	
</script>
<style>
tr, th, td {
	text-align: center;
}
</style>

<div class="container">
<form>
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
				<td><input type="radio" name="chk_statement"></td>
				<td>${l.cheatno}</td>
				<td><b>${l.goodsname}</b><br>${l.deposit} 원 | ${l.domain}</td>
				<td>${l.cheatername}</td>
				<td>${l.bankname} | ${l.account}</td>
				<td>${l.phone}</td>
				<td>${l.regdate} | ${l.depositdate}</td>
				<td><input type="button" class="btn btn-info viewBtn" value="자세히보기"
						id="statementView.go?sno=${l.stateno}&cno=1"></td>
			</tr>
		</c:forEach>

	</table>
	<!-- 페이저 -->
	<div style="text-align: center;">
		<c:set var="pager" value="${pager.toString()}" />
		${pager}
	</div>
	<div class="container" align="center">
		<select>
			<option>제목+태그</option>
			<option>본문</option>
		</select> <input type="text">
		<button type="submit" href="#">검색</button>
	</div>

</form>
</div>
