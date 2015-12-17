<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>

h3 {
	font-family: "나눔고딕"
}
</style>
</html>
<div class="display" style="background-image: url('include/nav.jpg');">
	<div class="container" style="margin: auto; padding: 20px; vertical-align: left; height: 100%; width: 100%;">
		<h3 align="middle">이메일찾기</h3><br>
		<div style="text-align: center; color: white;">
		</div>
		<div class="well well-lg" align="center" style="margin: auto; height: 85%; width: 50%;">
		<div style="width: 300px;">
		<form class="form-horizontal" name="joinform" action="" method="post" onsubmit="return CheckForm();">
				
			<div class="form-group">
				<label for="name" class="col-sm-4 control-label">이름</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="name" id="name" placeholder="Name">
				</div>
			</div>
		
			<div class="form-group">
				<label for="password" class="col-sm-4 control-label">휴대폰번호</label>
				<div class="col-sm-8">
					<input type="tel" class="form-control" name="tel" id="tel" placeholder="'-'없이 ex)01012345678">
				</div>
			</div>
			
			<hr>
				<input type="button" id="emailSerach" value="메일 찾기" class="btn btn-success">
			<input type="button" onclick="location.href='index.go'" value="CANCEL" class="btn btn-danger">
		</form>
		</div>
		</div>
	</div>
		
	<div class="container" style="margin: auto; padding: 20px; vertical-align: left; height: 100%; width: 100%;">
		<h3 align="center">비밀번호찾기</h3><br>
		<div style="text-align: center; color: white;">
		</div>
		<div class="well well-lg" align="center" style="margin: auto; height: 85%; width: 50%;">
		<div style="width: 300px;">
		<form class="form-horizontal" name="joinform" action="" method="post" onsubmit="return CheckForm();">
				
			<div class="form-group">
				<label for="name" class="col-sm-4 control-label">이름</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="name" id="name" placeholder="Name">
				</div>
			</div>
		
			<div class="form-group">
				<label for="password" class="col-sm-4 control-label">이메일주소</label>
				<div class="col-sm-8">
					<input type="email" class="form-control" name="email" id="email" placeholder="이메일을 입력해주세요">
				</div>
			</div>
			
			<hr>
				<input type="button" id="pwdSearch" value="비밀번호 찾기" class="btn btn-info">
			<input type="button" onclick="location.href='index.go'" value="CANCEL" class="btn btn-danger">
		</form>
		</div>
		</div>
		
		<script type="text/javascript">
		var re_id = /^(\w+)(((\.?)(\w+))*)[@](((\w+)[.])+)(\w{2,3})$/; // 아이디 검사식
		var uid = document.getElementById("email");
		
		var re_tel = /^[0-9]{8,11}$/; // 전화번호 검사식
		var tel = document.getElementById("phone");
		
		function CheckForm() {
			var result= true;
			if (re_id.test(uid.value) != true) { // 아이디 검사
				alert('[아이디(이메일) 입력 오류] 유효한 이메일을 입력해 주세요.');
				return false;
			}
			
			if(!$('#name').val()){
				alert('유효한 이름을 입력해 주세요.');
				return false;
			}
			
			if(re_tel.test(tel.value) != true){
				alert('유효한 전화번호를 입력해 주세요.');
				return false;
			}
		}
		
		</script>
	</div>
	
</div>	
