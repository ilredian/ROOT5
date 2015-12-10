<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <!-- Le styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">
    <link href="assets/css/font-style.css" rel="stylesheet">
    <link href="assets/css/flexslider.css" rel="stylesheet">
	    
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

	</head>
<style type="text/css">
 body {
   padding-top: 60px;
 }
</style>
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

  	<!-- Google Fonts call. Font Used Open Sans & Raleway -->
	<link href="http://fonts.googleapis.com/css?family=Raleway:400,300" rel="stylesheet" type="text/css">
  	<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
</html>

<table class="table">
</table>
<div class="container" align="center">
	<div class="row1">
		<div class="col-sm-6"
			style="background-color: white; margin: 10px; height: 165px; width: 570px; border: 1px solid lightgray">
			
			<div style="margin-top:40px; margin-left:10px">


				<h4 style="float:left; vertical-align: left;"><strong>거래 구분</strong></h4>

				<select style="margin-top:6px; margin-left:35px; float:left; vertical-align: middle;">
					<option>전체 피해 사례</option>
					<option>직거래</option>
					<option>게임</option>
					<option>비매너</option>
			 </select>
			 
			</div>
			<div style="margin-top:0px;">
				<br><br><h4 style="float:left;margin-right:5px;vertical-align: middle;"><strong>&nbsp;&nbsp;피해사례검색</strong></h4>
				<input style="width: 60%; margin-top:6px; float:left; vertical-align: middle;" type="text" placeholder="로그인후 피해사례를 검색 가능"  size="30">
			</div>
		</div>
		<div class="col-sm-3"
			style="margin: 10px; height: 165px; width: 515px; background-color: white; border: 1px solid lightgray; padding: 20px;">
			<table>
				<thead>
					<tr>
						<th>사건 사진</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div>
								<input type="image" src="homeimages/1.PNG" width="86px"
									height="51px">
							</div>
						</td>
						<td>
							<div style="margin-left: 5px;">
								<input type="image" src="homeimages/aa.PNG" width="86px"
									height="51px">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<input type="image" src="homeimages/catch.PNG" width="86px"
									height="51px">
							</div>
						</td>
						<td>
							<div style="margin-left: 5px;">
								<input type="image" src="homeimages/notice.PNG" width="86px"
									height="51px">
							</div>
						</td>
					</tr>
				</tbody>

			</table>
		</div>
<!-- 		<div class="col-sm-3"
			style="background-color: white; border: 1px solid lightgray; margin: 10px; height: 165px; width: 275px;">
			<form action="" style="margin-top: 20px; float: left;">
				<div>
					<input type="text" style="float: left; margin-bottom: 1px;" value
						placeholder="이메일 주소 입력" size="15px"> <input
						type="password" style="float: left; margin-top: 1px;" value
						placeholder="비밀번호" size="15px">
				</div>
				<div>
					<input type="submit" value="로그인"
						style="float: right; width: 70px; height: 60px; margin-left: 10px; margin-top: -30px;">
				</div>
			</form>
			<br> <br>
			<div>
				<input type="checkbox" style="float: left;">
				<p style="float: left">로그인 유지</p>
				<a href="#" style="float: right;">비밀번호 찾기</a>
			</div>
			<div>
				<a href="signin.go"><input type="button" class="btn btn-info btn-lg" value="회원가입"></a>
			</div>
		</div> -->
	</div>
	<div class="row2">
		<div class="col-sm-6"
			style="background-color: white; border: 1px solid lightgray; margin: 10px; width: 570px; height: 165px; float: left;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><a href="noticeMain.go?pg=1"><p style="text-align: left;">자유게시판</p></a></th>
						<th><a href="noticeMain.go?pg=1"><p style="text-align: right;">더보기</p></a></th>
					</tr>
				</thead>
				<tbody>

					<tr>

						<td><a href="CC.jsp">피해사례 검증 안내</a></td>

					</tr>


					<tr>

						<td><a href="#">KBS임재성기자입니다. 추석 선물/상품권 관련 사기 피해자를 찾습니다.</a></td>

					</tr>


					<tr>

						<td><a href="#">더치트 및 클린콜 서비스 점검 안내(2015.08.11)</a></td>

					</tr>

				</tbody>
			</table>
		</div>
		<div class="col-sm-6"
			style="background-color: white; border: 1px solid lightgray; margin: 10px; width: 516px; height: 165px; float: left;">
			<div
				style="margin-left: -16px; width: 214px; height: 165px; float: left;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><a href="chartMain.go">월간 피해 통계</a></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>용의자</td>
							<td><서버코드></td>
						</tr>
						<tr>
							<td>은행</td>
							<td><서버코드></td>
						</tr>
						<tr>
							<td>통신사</td>
							<td><서버코드></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="background-color: white;margin-top:-1px;border-bottom:1px solid lightgray; border-top:1px solid lightgray; margin-left: 203px; width: 219px; height: 165px; position: relative;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th><a href="chartMain.go" style="float: right;">더보기</a></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>사이트</td>
							<td><서버코드></td>
						</tr>
						<tr>
							<td>누적 피해금액</td>
							<td><서버코드></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row3">
		<div class="col-sm-6"
			style="background-color: white;border: 1px solid lightgray; margin: 10px; width: 570px; height: 165px; float: left;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><a href="noticeMain.go?pg=1"><p style="text-align: left;">공지사항</p></a></th>
						<th><a href="noticeMain.go?pg=1"><p style="text-align: right;">더보기</p></a></th>
					</tr>
				</thead>
				<tbody>

					<tr>

						<td><a href="CC.jsp">피해사례 검증 안내</a></td>

					</tr>


					<tr>

						<td><a href="#">KBS임재성기자입니다. 추석 선물/상품권 관련 사기 피해자를 찾습니다.</a></td>

					</tr>


					<tr>

						<td><a href="#">더치트 및 클린콜 서비스 점검 안내(2015.08.11)</a></td>

					</tr>

				</tbody>
			</table>
		</div>
			<div class="col-sm-6"
			style="background-color: white; border: 1px solid lightgray;margin-top:10px; margin-right: 23px; width: 516px; height: 349px; float: right;">
			<div
				style="margin-left: -16px; width: 214px; height: 165px; float: left;">
				
			</div>
		</div>
		<div class="col-sm-6"
			style="background-color: white; border: 1px solid lightgray; margin: 10px; width: 570px; height: 165px; float: left;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><a href="noticeMain.go?pg=1"><p style="text-align: left;">변호사게시판</p></a></th>
						<th><a href="noticeMain.go?pg=1"><p style="text-align: right;">더보기</p></a></th>
					</tr>
				</thead>
				<tbody>

					<tr>

						<td><a href="CC.jsp">피해사례 검증 안내</a></td>

					</tr>


					<tr>

						<td><a href="#">KBS임재성기자입니다. 추석 선물/상품권 관련 사기 피해자를 찾습니다.</a></td>

					</tr>


					<tr>

						<td><a href="#">더치트 및 클린콜 서비스 점검 안내(2015.08.11)</a></td>

					</tr>

				</tbody>
			</table>
		</div>
	</div>
</div>