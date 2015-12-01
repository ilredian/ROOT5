<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container noticeView">
	<h2>공지사항 게시판</h2>
	 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">

$(function() {
    $('#edit').click(function(){
    	console.log("수정페이지로이동")
    	 location.replace("/noticeEdit.go");
    });
    
    $('#delete').click(function(){
    	console.log("삭제")
    	location.replace("/noticeDelete.go");
    });
    
    $('#list').click(function(){
    	console.log("전페이지로 이동")
    	history.go(-1);
    });
    
});
</script>	
	
	
	<table class="table table-striped">
		<tbody>
			<tr>
				<th colspan="3"> 제목</th>
			</tr>
			<tr>
				<td style="width: 10%;">
					<img alt="no_pic" src="img/user_no_pic.gif">
				</td>
				<td style="width: 70%;">
					<table>
						<tr>
							<th>운영자</th>
						</tr>
						<tr>
							<td>입력된 인사말이 없습니다.</td>
						</tr>
					</table>
				</td>
				<td style="width: 20%">조회</td>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<tr>
			<td>공지사항 내용입니다.<br> 이러이러한 일을 공지합니다.<br> 뷰 수정해야함
			</td>
		</tr>
	</table>
	<table class="table">
		<thead>
			<tr>
				<th>게시물 주소 :
					<div style="float: right;">
						<input type="button" id="edit" value="수정"> <!-- noticeEdit.go -->
						<input type="button" id="delete" value="삭제"> <!-- noticeDelete.gos\  -->
						<input type="button" id="list" value="목록"> <!--  -->
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>댓글 1개</th>
			</tr>
		</tbody>
	</table>
	<table class="table">
		<tbody>
			<tr>
				<td style="width: 10%;">
					<img alt="no_pic" src="img/user_no_pic.gif">
				</td>
				<td style="width: 90%;">
					<table>
						<tr>
							<td>aqua 님 | 2015.11.28 09:55:21</td>
						</tr>
						<tr>
							<td>댓글 내용을 입력해 봅시다.</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<input type="text" id="" name="" style="width: 90%">
					<input type="button" id="replybtn" value="댓글 등록">
				</td>
			</tr>
		</tbody>
	</table>
</div>