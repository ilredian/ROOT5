<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>회원가입</h3>
<table class="table table-hover">
	<tr>
		<th>타입번호</th>
		<th>타입이름</th>
		<th>ㅁㅇㄴㄹ</th>
	</tr>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.typeno}</td>
			<td>${list.typename}</td>
			<td>
				<div class="btn-group">
					<input type="button" class="btn btn-info update" id="updateMemberType.go?tn=${list.typename}&tno=${list.typeno}" value="수정"> <input
						type="button" class="btn btn-danger delete" id="deleteMemberType.go?tn=${list.typename}&tno=${list.typeno}" value="삭제"> <input
						type="button" class="btn btn-success insert" id="insertMemberType.go?tn=${list.typename}&tno=${list.typeno}" value="추가">
				</div>
			</td>
		</tr>
	</c:forEach>
</table>