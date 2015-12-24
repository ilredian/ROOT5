<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="display" style="background-image: url('include/nav.jpg');">
	<div class="container" style="margin: auto; padding: 20px; vertical-align: middle; height: 100%; width: 100%;">
		<div style="text-align: center; color: white;">
			<h2>회원가입</h2>
		</div>
		<div class="well well-lg" align="center" style="margin: auto; height: 85%; width: 50%;">
		
		<div style="width: 400px;">
		<form class="form-horizontal" name="joinform" action="" method="post" onsubmit="return CheckForm();">
			<div class="form-group">
				<label for="email" class="col-sm-4 control-label">이메일</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="email" id="email"  placeholder="이메일을 입력하세요">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-4 control-label">비밀번호</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" name="password" id="password" placeholder="6자리 이상 입력하세요">
				</div>
			</div>
			<div class="form-group">
				<label for="passwordchk" class="col-sm-4 control-label">비밀번호확인</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" name="passwordchk" id="passwordchk" placeholder="비밀번호를 다시 입력하세요">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-4 control-label">이름</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="name" id="name" placeholder="Name">
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-4 control-label">전화번호</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="phone" id="phone" placeholder="(예시) 01012345678">
				</div>
			</div>
			<hr>
			<input type="hidden" value="images/common/user_no_pic.gif" name="photo">
			<input type="submit" value="JOIN" class="btn btn-success">
			<input type="button" onclick="location.href='javascript:joinform.reset()'" value="RESET" class="btn btn-info">
			<input type="button" onclick="location.href='index.go'" value="CANCEL" class="btn btn-danger">
		</form>
		</div>
		</div>
	</div>

<script>
	var re_id = /^(\w+)(((\.?)(\w+))*)[@](((\w+)[.])+)(\w{2,3})$/; // 아이디 검사식
	var re_pw = /^[a-z0-9_-]{6,18}$/; // 비밀번호 검사식
	var re_id = /^(\w+)(((\.?)(\w+))*)[@](((\w+)[.])+)(\w{2,3})$/;  // 이메일 검사식 */
	var re_url = /^(\d+)+[0|1](\d)+([0|1|2|3]\d)$/;
	var re_tel = /^[0-9]{8,11}$/; // 전화번호 검사식
	   
	var uid = document.getElementById("email");
	var upw = document.getElementById("password");
	var upw2 = document.getElementById("passwordchk");
	var name2 = document.getElementById("name");
	var tel = document.getElementById("phone");

	function CheckForm() {
		var result= true;
		if (re_id.test(uid.value) != true) { // 아이디 검사
			alert('[아이디(이메일) 입력 오류] 유효한 ID를 입력해 주세요.');
			uid.focus();
			return false;
		}
		if(re_pw.test(upw.value) != true) { // 비밀번호 검사
			alert('[비밀번호 입력 오류] 유효한 비밀번호 를 입력해 주세요.');
			upw.focus();
			return false;
		}
		if(re_pw.test(upw2.value) != true) { // 비밀번호 검사
			alert('[비밀번호 확인 입력 오류] 유효한 비밀번호 를 입력해 주세요.');
			upw.focus();
			return false;
		}
		if(upw.value != upw2.value) { // 비밀번호 일치 확인
			alert('[비밀번호 확인 입력 오류] 비밀번호를  제대로 입력해주세요.');
			upw2.focus();
			return false;
		}
		if(name2.value == "") { // 이름 검사
			alert('[이름 입력 오류] 이름 을 입력해 주세요.');
			name2.focus();
			return false;
		}
		if(re_tel.test(tel.value) != true) { // 전화번호 검사
			alert('[Tel 입력 오류] 유효한 전화번호를 입력해 주세요.');
			tel.focus();
			return false;
		}
	}
	$('#m_id, #m_pwd1').after('<strong></strong>');
</script>

</div>