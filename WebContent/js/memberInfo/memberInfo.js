$('.viewBtn').click(function() {
	var loc = this.getAttribute("id");
	location.href = loc;
});

$(function() {
	var cno = $('#mno').attr("value");
	if(cno == 1){
		$('#memberMessage').attr("class", "active");
		$('#statementText').text("쪽지");
	}else if(cno == 2){
		$('#memberBoard').attr("class", "active");
		$('#statementText').text("게시물");
	}else if(cno == 3){
		$('#memberComment').attr("class", "active");
		$('#statementText').text("댓글");
	}else if(cno == 4){
		$('#memberConnect').attr("class", "active");
		$('#statementText').text("접속기록");
	}else if(cno == 5){
		$('#memberModify').attr("class", "active");
		$('#statementText').text("정보수정");
	}else if(cno == 6){
		$('#memberPwdChange').attr("class", "active");
		$('#statementText').text("비번변경");
	}else if(cno == 7){
		$('#memberWithdrawal').attr("class", "active");
		$('#statementText').text("회원탈퇴");
	}else{
		$('#memberStatement').attr("class", "active");
		$('#statementText').text("진술서");
	}
});

/*<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h2>내 페이지</h2>
<ul class="nav nav-tabs">
	<li role="presentation"><a id="memberMessage" href="memberMessage.go?mno=1">쪽지</a></li>
	<li role="presentation"><a id="memberBoard" href="memberBoard.go?mno=2">게시물</a></li>
	<li role="presentation"><a id="memberComment" href="memberComment.go?mno=3">댓글</a></li>
	<li role="presentation"><a id="memberReply" href="memberReply.go?mno=4">답글</a></li>
	<li role="presentation"><a id="memberConnect" href="memberConnect.go?mno=5">접속기록</a></li>
	<li role="presentation"><a id="memberModify" href="memberModify.go?mno=6">정보수정</a></li>
	<li role="presentation"><a id="memberPwdChange" href="memberPwdChange.go?mno=7">비번변경</a></li>
	<li role="presentation"><a id="memberWithdrawal" href="memberWithdrawal.go?mno=8">회원탈퇴</a></li>
</ul>*/