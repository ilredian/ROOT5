<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
	<table class="table table-hover">
		<thead>
			<tr >
				<th class="sub">제목</th><th class="subwrite"><input type="text" style="float: left; margin-bottom: 1px;" value
							placeholder="이메일 주소 입력" size="15px"></input></th>
			</tr>
			<tr>
				<th class="writer">작성자:</th> <th class="Writer" name="Writher">
					<input type="text" value="${Email}" readonly> <!-- <c:set value=/> -->
			</tr>
	</table>
	<br>
		<div><br>
			<table style="float: right; margin-right: 30px;">
			<tr style="margin-right: 30px;">
			<th>
			<input type="submit" value="취소"
							style="float: right; width: 65px; height: 40px; margin-right: 20px; margin-top: -40px;">
			</th>
			<th >
			<input type="submit" value="확인"
							style="float: right; width: 65px; height: 40px; margin-right: 20px; margin-top: -40px;">
							</th>
		</table>
		</div>
</div>
