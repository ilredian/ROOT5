<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form class="form-horizontal" name="writeForm" id="frm1" action=""
	method="post" onsubmit="return CheckForm();">
	<div class="container lawMain">
<script type="text/javascript">
$(function() {
	$(document).ready(function() {
	var dbTxt = $('#edu').html();
	dbTxt = dbTxt.replace(/<br>/g, '\n');
	});
	
});


function CheckForm() {
	//피해 발생 사이트 정보
	if (!$("#title").val()) {
		alert("제목을 입력해주세요.");
		return false;
	}
	if (!$("#career").val()) {
		alert("경력을 입력해주세요.");
		return false;
	}
	if (!$("#company").val()) {
		alert("소속을 입력해주세요.");
		return false;
	}
	if (!$("#place").val()) {
		alert("지역을 입력해주세요.");
		return false;
	}
	if (!$("#message").val()) {
		alert("한줄소개를 입력해주세요.");
		return false;
	}
	if (!$("#phone").val() || !$("#tel").val() ) {
		alert("전화번호를 입력해주세요.");
		return false;
	}
	if (!$("#edu").val()) {
		alert("학력사항을 입력해주세요.");
		return false;
	}
	if (!$("#fee").val()) {
		alert("요금란을 입력해주세요.");
		return false;
	}
}
</script>
		<h2>변호사게시판</h2>
		<table class="table">
			<tr>
				<th class="active">제목</th>
				<td><input type="text" id="title"  name="title" style="WIDTH: 100%;"></td>
			</tr>
			<tr>
				<th class="active">작성자</th>
				<td>
					<c:out value="${memberInfo.name}"/>
					<input type="hidden" name="name" value="${memberInfo.name}">
					<input type="hidden" name="memberno" value="${memberInfo.memberno}">
				</td>
			</tr>
			<tr>
				<th class="active">경력</th>
				<td>
						<input type="text" class="form-control" id="career" name="career" placeholder="경력을 써주세요. ex) 8년">	
				</td>
				</tr>
			<tr>
				<th class="active">소속</th>
				<td>
					 <input type="text" class="form-control" id="company" name="company" placeholder="ex) cheat법률사무소">	
			</td>
			</tr>
			
			<tr>
			<th class="active">지역</th> 
			<td>
				<input type="text" class="form-control" id="place" name="place" placeholder="ex) 서울 ㅇㅇ구">	</tr>
			<tr>
				<th class="active">한줄소개</th>
			<td>
					<input type="text" class="form-control"
					id="message" name="message" placeholder="ex) 부장검사 출신 부산 형사 성범죄 전문 변호사">	
			</td></tr>
			
			<tr>
				<th class="active">전화번호:</th>
			<td>
					휴대폰번호: <input type="text" class="form-control" id="phone" name="phone" style="width: 50%">	
					회사 전화번호: <input type="text" class="form-control" id="tel" name="tel" style="width: 50%" align="left">	
			</td>
			</tr>	
			<tr>
			<th class="active">학력 사항:</th>
			<td>
				<textarea id="edu" name="edu" 
				style="width: 100%; height: 100%" rows="15"></textarea>						
			</td></tr>
				
			<tr>
			<th class="active">요금 안내:</th>
			<td>
				 <textarea id="fee" name="fee"
								style="width: 100%; height: 100%" rows="15"></textarea>			
			</td></tr>
			
<!-- 			<tr>
				<td colspan="2">
					<textarea id="editor" style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30" name="content">
					</textarea>
				</td>
			</tr>
			<tr> -->
				<th class="active">고운 언어가 좋아요!</th>
				<td>
				"말이 입힌 상처는 칼이 입힌 상처보다 깊다." - 모로코 속담<br>
				<c:out value="${memberInfo.name}"/>님의 글은 <c:out value="${memberInfo.name}"/>님의 품격을 보여주는 <c:out value="${memberInfo.name}"/>님의 얼굴입니다.
				글에 <c:out value="${memberInfo.name}"/>님의 성숙함을 담아주세요.
				</td>
			</tr>
			<!-- place -->
			<tr>
				<td colspan="2">
					<div style="text-align: center;">
						<input class="btn btn-primary" id="savebutton1" type="button" value="저장"> 
						<input class="btn btn-default" onclick="history.go(-1)" type="button" value="취소">
					</div>
				</td>
			</tr>
		</table>
	</div>
</form>