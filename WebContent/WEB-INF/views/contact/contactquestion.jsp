<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>AhnCheat</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="" method="post" name="writeForm" id="frm">
		<div class="container">
			<div>
				<h3>
					<strong>문의하기 (의견수렴)</strong>
				</h3>
			</div>
			<Br>
			<div>
				<table class="table">
					<colgroup>
						<col width="150px;">
					</colgroup>
					<tr>
						<th class="active">필독</th>
						<td>1. 서비스 사용과 관련한 문의 또는 개선과 관련된 내용을 등록하실 수 있습니다.<br> 2.
							인터넷 사기와 관련한 상담은 "사기 Q&A"에 등록해 주세요.<br> 3. 피해사례 삭제요청은 "피해사례
							삭제요청"에 등록해 주세요.<br> 4. 문의 내용은 수정 및 삭제가 되지 않습니다.<br> 신중히
							작성하여 주시길 부탁드립니다.<br> ※ 다음과 같은 문의는 답변되지 않고 삭제되며, 벌점이 부과될 수
							있습니다.<br> - 맞춤법 및 육하원칙을 준수하지 않은 글, 욕설 또는 저속한 내용을 포함한 글
						</td>
					</tr>
					<tr>
						<th class="active">질문 요약</th>
						<td><input type="text" style="width: 600px"
							placeholder="한 줄의 완성된 질문으로 작성해 주세요. 상담자에 대한 최소한의 예의입니다. ^^"></td>
					</tr>
					<!-- 네이버 에디터 -->

					<!-- /네이버 에디터 -->
					<tr>
						<th class="active">고운 언어가 좋아요!</th>
						<td>"말이 입힌 상처는 칼이 입힌 상처보다 깊다." - 모로코 속담<br> 귀하의 글은 귀하의
							품격을 보여주는 귀하의 얼굴입니다. 글에 귀하의 성숙함을 담아주세요.
						</td>
					</tr>
					<tr>
						<th class="active">이메일(필수)</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th class="active">이름</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th class="active">비밀번호</th>
						<td><input type="password"></td>
					</tr>
					<tr>
						<th class="active">사건 발생 개요</th>
						<td>1.물품을 받은 경우에는 물품 사진을 첨부하세요. <br> 2.용의자의 주민등록번호와 사진은
							절대 등록하시면 안됩니다.(피해사례 등록자가 법적 처벌을 받을 수 있습니다.) <textarea id="editor"
								style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30"
								name="content"></textarea>
						</td>
					</tr>
				</table>
				<div align="center">
					<!--                <a href="#"><img src="homenavimages/p.PNG"></img></a> <a href="#"><img
                  src="homenavimages/o.PNG"></img></a> 버튼 -->
	</form>
	</div>
	<a><input type="button" class="btn btn-info" value="등록"></a>
	<a href="contacted.go"><input type="button" class="btn btn-danger"
		value="취소"></a>
	</div>
	</form>
</body>
</html>