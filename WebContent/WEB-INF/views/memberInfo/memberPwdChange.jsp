<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="memberPwdChange" class="tab-pane fade">
	<h3>비밀번호 변경</h3>
	<div>
		<div>
			<p style="color: blue">
				현재 비밀번호는
				<%-- <p style="color: red"><% %></p> --%>
				에 변경(등록)되었으며
				<%-- <p style="color: red"><% %></p> --%>
				일이 경과되었습니다.<br> 비밀번호는 가급적 주기적으로 변경해 주세요.
			</p>
		</div>
		<hr>
		<div>
			<strong style="margin-left: 30px">현재 비밀번호</strong> <input
				type="password" style="margin-left: 30px">
		</div>
		<hr>
		<div>
			<strong style="margin-left: 30px">변경 비밀번호</strong> <input
				type="password" style="margin-left: 30px"><br>
			<p style="color: silver; margin-top: 10px; margin-left: 150px;">4~12자의
				영문과 숫자만 사용할 수 있습니다.</p>
		</div>
		<hr>
		<div>
			<strong style="margin-left: 30px">비밀번호 확인</strong> <input
				type="password" style="margin-left: 30px">
			<p style="color: silver; margin-top: 10px; margin-left: 150px;">비밀번호를
				한번 더 입력하세요. 비밀번호는 잊지 않도록 주의하시기 바랍니다.</p>
		</div>
		<hr>
		<div align="center">
			<a type="submit" href="#">비밀번호 변경</a>
		</div>
	</div>
</div>