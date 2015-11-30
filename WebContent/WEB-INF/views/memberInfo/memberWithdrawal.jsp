<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="memberWithdrawal" class="tab-pane fade">
	<h3>회원탈퇴</h3>
	<div>
		<div>
			<p style="color: blue">
				회원탈퇴를 원하시면 비밀번호를 입력하신 후 회원탈퇴 버튼을 클릭해 주세요.<br> 탈퇴하시면 회원정보가
				데이터베이스에서 완전히 삭제됩니다.
			</p>
			<p style="color: red">※ 탈퇴하신 후 재가입하시려면 다시 본인인증을 하셔야 합니다. ※ 신중히
				결정하신 후 탈퇴해 주세요.</p>
		</div>
		<hr>
		<div>
			<table>
				<thead
					style="float: left; margin-top: 5px; margin-bottom: 5px; margin-right: 30px">
					<tr style="margin: 10px;">
						<th>비밀번호</th>
					</tr>
					<tr style="margin: 10px;">
						<th>비밀번호 확인</th>
					</tr>
				</thead>

				<tbody style="float: left">
					<tr>
						<td><input type="password"></td>
					</tr>
					<tr>
						<td><input type="password"></td>
					</tr>
				</tbody>
			</table>
			<hr>
			<div style="margin-top: 20px">
				<a type="submit" href="#">로그아웃</a> <a type="submit" href="#">회원탈퇴</a>
			</div>
		</div>
	</div>
</div>