<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<h2>비매너 피해사례 상세보기</h2>
	<div class="container">
		<div class="container">
			<table class="table table-striped">
				<tbody>
					<tr>
						<th colspan="3">${cheaterDTO.goodsname}</th>
					</tr>
					<tr>
						<td style="width: 10%;"><img alt="no_pic" src="<c:out value='${memberDTO.photo}'></c:out>"></td>
						<td style="width: 70%;">
							<table>
								<tr>
									<th>피해자 : ${memberDTO.name} 님 (피해회원)</th>
								</tr>
								<tr>
									<td>${memberDTO.message}</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			<hr>
		</div>

		<!-- 피해 발생 사이트 정보 -->

		<div class="container">
			<h4>피해 발생 사이트 정보</h4>
			<table class="table">
				<tr>
					<th class="active" style="width: 15%">사기 사건 발생일</th>
					<td colspan="3">${cheaterDTO.depositdate}</td>
				</tr>
				<tr>
					<th class="active">사이트명(URL)</th>
					<td>${cheaterDTO.domain }
					<c:if test="${not empty cheaterDTO.link}">
					<button class="btn btn-default btn-xs"><a href="${cheaterDTO.link}">게시물 링크</a></button>
					</c:if>
					</td>
					<th class="active" style="width: 15%">용의자 아이디</th>
					<td>${cheaterDTO.cheaterid}</td>
				</tr>
				<tr>
					<th class="active">거래 물품 종류</th>
					<td>${cheatItemsDTO.goodsname} <span class="${cheatItemsDTO.goodsspan}"></span></td>
					<th class="active">피해 금액</th>
					<td>${deposit} 원</td>
				</tr>
			</table>
		</div>

		<!-- 피해 상세보기 -->
		<div class="container">
			<h4>용의자(사기범) 정보</h4>
			<table class="table">
				<tr>
					<th class="active" style="width: 15%">명의자 성명</th>
					<td>${cheaterDTO.cheatername}</td>
				</tr>
				<tr>
					<th class="active" >계좌번호</th>
					<td>${cheaterDTO.account}</td>
				</tr>
				<tr>
					<th class="active" >연락처</th>
					<td>${cheaterDTO.phone}</td>
				</tr>
				<tr>
					<th class="active" >특징</th>
					<td>${cheaterDTO.feature}</td>
				</tr>
			</table>
		</div>

		<!-- 사건개요 -->
		<div class="container">
			<h4>사건 개요 (진술서)</h4>
			<table class="table">
				<tr>
					<th class="active" style="width: 15%">사건발생 개요</th>
					<td>${cheaterDTO.content}</td>
				</tr>
			</table>
		</div>
	</div>
</div>