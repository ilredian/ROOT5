<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>비밀번호 변경</h3>
<script>
function aaa(){
	if($('#pwd2').val()!=$('#pwd3').val()){
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
}
$('#click').onclick(){
	if($('#pwd1').val()!=$('#pwd2').val()){
		alert("변경할 비밀번호가 현재비밀번호와 일치합니다_");
		return false;
	}
}
</script>
<div>
<%-- <input type="hidden" name="" value="${param. }">
<input type="hidden" name="" value="${param. }"> --%>
	<div>
		<p style="color: blue">
			현재 비밀번호는
			<b>${regpwd}</b>
			<%-- <p style="color: red"><% %></p> --%>
			에 변경(등록)되었으며
			<b>${diffDay}</b>
			일이 경과되었습니다.<br> 비밀번호는 가급적 주기적으로 변경해 주세요.
		</p>
	</div>
	<hr>
<form action="memberPwdChange.go" method="post" onsubmit="return aaa()">
	<div>
	<c:set var="n" value="${memberInfo}"/>
		<strong style="margin-left: 30px">현재 비밀번호</strong> 
		<input type="password" id="pwd1" name="password" style="margin-left: 30px">
	</div>
	<hr>
	<div>
		<strong style="margin-left: 30px">변경 비밀번호</strong> 
		<input type="password" id="pwd2" name="npassword" style="margin-left: 30px"><br>
		<p style="color: silver; margin-top: 10px; margin-left: 150px;">4~12자의
			영문과 숫자만 사용할 수 있습니다.</p>
	</div>
	<hr>
	<div>
		<strong style="margin-left: 30px">비밀번호 확인</strong> 
		<input type="password" id="pwd3" style="margin-left: 30px">
		<p style="color: silver; margin-top: 10px; margin-left: 150px;">비밀번호를
			한번 더 입력하세요. 비밀번호는 잊지 않도록 주의하시기 바랍니다.</p>
	</div>
	<hr>
	<div align="center">
		<input type="submit" id="click" value="비밀번호변경"></a>
	</div>
	</form>
</div>
