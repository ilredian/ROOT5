<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>내 페이지</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#home">쪽지</a></li>
			<li><a data-toggle="tab" href="#menu1">게시물</a></li>
			<li><a data-toggle="tab" href="#menu2">댓글</a></li>
			<li><a data-toggle="tab" href="#menu3">답글</a></li>
			<li><a data-toggle="tab" href="#menu4">접속기록</a></li>
			<li><a data-toggle="tab" href="#menu5">정보수정</a></li>
			<li><a data-toggle="tab" href="#menu6">비번변경</a></li>
			<li><a data-toggle="tab" href="#menu7">회원탈퇴</a></li>
		</ul>
		<div class="tab-content">

			<div id="home" class="tab-pane fade in active">
				<div class="container">
					<h3>쪽지</h3>
					<table class="table table-hover table-responsive"
						style="margin-top: 10px">
						<tr>
							<th>글 번호</th>
							<th>보낸이</th>
							<th>내용</th>
							<th>날짜</th>
						</tr>
						<tr>
							<td>1</td>
							<td>시스템</td>
							<td>족지없습니다</td>
							<td>2015.11.28 15:26</td>
						</tr>
					</table>
					<!-- 페이저 -->
					<div class="container">
						<a type="submit" href="#">삭제</a>
						<a type="submit" href="#">쪽지쓰기</a>
					</div>
				</div>
			</div>
			<div id="menu1" class="tab-pane fade">
				<div class="container">
					<h3>게시물</h3>
					<table class="table table-hover table-responsive"
						style="margin-top: 10px">
						<tr>
							<th>글 번호</th>
							<th>내용</th>
							<th>조회</th>
							<th>날짜</th>
						</tr>
						<tr>
							<td>1</td>
							<td>게시물이 없습니다</td>
							<td>-</td>
							<td>2015.11.28 15:26</td>
						</tr>
					</table>
					<!-- 페이저 -->
					<div class="container" align="center">
						<select>
							<option>제목+태그</option>
							<option>본문</option>
						</select> <input type="text">
						<a type="submit" href="#">검색</a>
					</div>
				</div>
			</div>

			<div id="menu2" class="tab-pane fade">
				<div class="container">
					<h3>댓글</h3>
					<table class="table table-hover table-responsive"
						style="margin-top: 10px">
						<tr>
							<th>글 번호</th>
							<th>내용</th>
							<th>조회</th>
							<th>날짜</th>
						</tr>
						<tr>
							<td>1</td>
							<td>게시물이 없습니다</td>
							<td>-</td>
							<td>2015.11.28 15:26</td>
						</tr>
					</table>
					<!-- 페이저 -->
					<div class="container" align="center">
						<select>
							<option>내용</option>
						</select> <input type="text">
						<a type="submit" href="#">검색</a>
					</div>
				</div>
			</div>
			<div id="menu3" class="tab-pane fade">
				<div class="container">
					<h3>답글</h3>
					<table class="table table-hover table-responsive"
						style="margin-top: 10px">
						<tr>
							<th>글 번호</th>
							<th>한줄의견</th>

							<th>날짜</th>
						</tr>
						<tr>
							<td>1</td>
							<td>게시물이 없습니다</td>

							<td>2015.11.28 15:26</td>
						</tr>
					</table>
					<!-- 페이저 -->
					<div class="container" align="center">
						<select>
							<option>내용</option>
						</select> <input type="text">
						<a type="submit" href="#">검색</a>
					</div>
				</div>
			</div>
			<div id="menu4" class="tab-pane fade">
				<div class="container">
					<h3>검색기록</h3>
					<table class="table table-hover table-responsive"
						style="margin-top: 10px">
						<tr>
							<th>글 번호</th>
							<th>검색기록</th>
							<th>결과</th>
							<th>날짜</th>
						</tr>
						<tr>
							<td>1</td>
							<td>검색한거 이름</td>
							<td>몇건</td>
							<td>2015.11.28 15:26</td>
						</tr>
					</table>
					<!-- 페이저 -->
					<div class="container" align="center">
						<select>
							<option>내용</option>
						</select> <input type="text">
						<a type="submit" href="#">검색</a>
					</div>
				</div>
			</div>
			<div id="menu5" class="tab-pane fade">
				<div class="container">
					<h3>정보수정</h3>

					(*) 표시가 있는 항목은 반드시 입력해야 합니다.<br> 허위로 작성된 정보일 경우 승인이 보류되거나 임의로
					삭제처리될 수 있으니 주의해 주세요.
					<hr>
					<table>

						<tr>
							<td class="key">이메일<span>*</span><br>(로그인 시 사용)
							</td>
							<td style="padding-left: 20px"><input type="text"
								name="email" value="jkikss@naver.com" size="35" class="input"
								onblur="sameCheck(this,'hLayeremail');" /> <span class="hmsg"
								id="hLayeremail"></span>
								<div>주로 사용하는 이메일 주소를 입력해 주세요. 비밀번호 잊어버렸을 때 확인 받을 수 있습니다.</div>
								<div class="remail">
									<input type="checkbox" name="remail" value="1"
										checked="checked" /> 정보/광고 수신 동의(검거소식, 피해자 알림 등의 수신을 위해 동의해
									주십시오.)
								</div>
								<div class="remail">
									<input type="checkbox" name="smail" value="1" /> 댓글 알림 수신
									거부(댓글 입력한 글에 다른 사람이 댓글을 남겨도 알림을 받지 않습니다.)
								</div></td>
						</tr>
					</table>

					<!-- 페이저 -->
					<div class="container" align="">
						<a type="submit" href="#">정보수정</a>
						<a type="submit" href="#">컨택센터</a>
						<a type="submit" href="#">로그아웃</a> 
					</div>
				</div>
			</div>
			<div id="menu6" class="tab-pane fade">
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
			<div id="menu7" class="tab-pane fade">
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
							<a type="submit" href="#">로그아웃</a>
							<a type="submit" href="#">회원탈퇴</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>