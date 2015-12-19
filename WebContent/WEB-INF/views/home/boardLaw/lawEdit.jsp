<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C/ 
+./DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form id="frm1" method="post" action="">
	<input type="hidden" name="boardno" value="${boardLawDTO.boardno}" >
	<div class="container lawMain">
		<h2>변호사게시판</h2>
		<table class="table">
			<tr>
				<th class="active">제목</th>
				<td><input id="css1" type="text" name="title" value="${boardLawDTO.title}"></td>
			</tr>
			<tr>
				<th class="active">작성자</th>
				<td>
					<c:out value="${boardLawDTO.name}"/>
					<input type="hidden" name="name" value="${boardLawDTO.name}">
					<input type="hidden" name="memberno" value="${boarreplynodLawDTO.memberno}">
				</td>
			</tr>
			<tr>
				<th class="active">경력</th>
				<td>
					<input type="text" class="form-control" id="career" name="career" value="${boardLawDTO.career}">	
				</td>
			</tr>
			<tr>
				<th class="active">소속</th>
				<td>
					 <input type="text" class="form-control" id="company" name="company" value="${boardLawDTO.company}">	
			</td>
			</tr>
			
			<tr>
			<th class="active">지역</th> 
			<td>
				<input type="text" class="form-control" id="place" name="place"  value="${boardLawDTO.place}">	</tr>
			<tr>
				<th class="active">한줄소개</th>
			<td>
					<input type="text" class="form-control" id="message" name="message" value="${boardLawDTO.message}">	
			</td></tr>
			
			<tr>
				<th class="active">전화번호:</th>
			<td>
					휴대폰번호: <input type="text" class="form-control" id="phone" name="phone" value="${boardLawDTO.phone}">	
					회사 전화번호: <input type="text" class="form-control" id="tel" name="tel" value="${boardLawDTO.tel}">	
			</td>
			</tr>	
			<tr>
			<th class="active">학력 사항:</th>
			<td>
				<textarea id="edu" name="edu" rows="15">${boardLawDTO.edu}</textarea>						
			</td></tr>
				
			<tr>
			<th class="active">요금 안내:</th>
			<td>
					 <textarea id="fee" name="fee" rows="15">${boardLawDTO.fee}</textarea>			
			</td></tr>
			<tr>
				<th class="active">고운 언어가 좋아요!</th>
				<td>
				"말이 입힌 상처는 칼이 입힌 상처보다 깊다." - 모로코 속담<br>
				<c:out value="${boardLawDTO.name}"/>님의 글은 <c:out value="${boardLawDTO.name}"/>님의 품격을 보여주는 <c:out value="${boardLawDTO.name}"/>님의 얼굴입니다.
				글에 <c:out value="${boardLawDTO.name}"/>님의 성숙함을 담아주세요.
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="text-align: center;">
						<input class="btn btn-primary" id="edit" type="submit" value="저장"> 
						<input class="btn btn-default" onclick="history.go(-1);" type="button" value="취소">
					</div>
				</td>
			</tr>
		</table>
	</div>
</form>