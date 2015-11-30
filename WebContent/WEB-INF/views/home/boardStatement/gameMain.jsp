<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<table class="table table-hover table-responsive">
		<thead>
			<tr>
				<th>번호</th>
				<th>피해물품</th>
				<th>용의자명</th>
				<th>용의자 계좌번호</th>
				<th>용의자 연락처</th>
				<th>등록일</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>핸드폰</td>
				<td>이 oo</td>
				<td>111-1111-1111-1110</td>
				<td>010-1234-5678</td>
				<td>2015.10.10</td>
				<td><input type="button" class="viewBtn"
					id="statementView.go?sno=1" value="자세히보기"></td>
			</tr>
		</tbody>
	</table>
</div>
<div style="float: left;">
	<button id="list">
		<a href="#">목록</a>
	</button>
	<button id="next">
		<a href="#">다음</a>
	</button>
	"src/controllers/BoardStatementController.java"
</div>
<div class="container">
	<table class="table table-hover table-responsive">
		<thead>
			<tr>
				<th>번호</th>
				<th>피해물품</th>
				<th>용의자명</th>
				<th>용의자 계좌번호</th>
				<th>용의자 연락처</th>
				<th>등록일</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>핸드폰</td>
				<td>이 oo</td>
				<td>111-1111-1111-1110</td>
				<td>010-1234-5678</td>
				<td>2015.10.10</td>
				<td><button type="submit">
						<a href="game.go">자세히 보기</a>
					</button></td>
			</tr>
		</tbody>
	</table>
</div>
<div style="float: left;">
	<button id="list">
		<a href="#">목록</a>
	</button>
	<button id="next">
		<a href="#">다음</a>
	</button>

</div>
<div>
	<button id="a" style="float: right;">
		<a href="#">사기피해사례 등록</a>
	</button>
</div>
<div>
	<button id="a" style="float: right;">
		<a href="#">사기피해사례 등록</a>
	</button>
</div>
<c:out value="${pager.toString}" />