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
		<h2>
			피해사례 통계
		</h2>
		<div class="well well-sm">
			<FORM METHOD="POST" name="search_submit" ACTION="./?mod=_statistics">
				<div style="height: 20px;"></div>
				<div class="boxTypeC di_searchArea al">
					<strong>기간설정</strong> <select
						name="start_year" id="start_year" title="시작 년 선택">
						<option value="2006" selected>2006</option>
						<option value="2007">2007</option>
						<option value="2008">2008</option>
						<option value="2009">2009</option>
						<option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
						<option value="2015">2015</option>
					</select>년 <select name="start_month" id="start_month" title="시작 월 선택">
						<option value="01" selected>01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>월 - <select name="end_year" id="end_year" title="끝 년 선택">
						<option value="2006">2006</option>
						<option value="2007">2007</option>
						<option value="2008">2008</option>
						<option value="2009">2009</option>
						<option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
						<option value="2015" selected>2015</option>
					</select>년 <select name="end_month" id="end_month" title="끝 월 선택">
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10" selected>10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>월 <a href="./?mod=_home"
						onclick="return confirm('로그인 후 사용 가능한 기능입니다.\n확인을 누르면 로그인 화면으로 이동합니다.');"><img
						src="images/N_1.PNG" alt="조회하기"></a> &nbsp;※ 2015년 10월까지를 선택하면
					2015년 10월 말일까지의 통계가 조회됩니다.
				</div>
			</FORM>
		</div>
	</div>
	<div class="container">
		<table class="table table table-striped">
			<colgroup>
				<col width="162">
				<col width="">
			</colgroup>
			<tr>
				<th>피해사례 수 |</th>
				<td>피해수</td>
				<th>전화번호 수 |</th>
				<td>피해수</td>
				<th>계좌번호 수 |</th>
				<td>피해수</td>
				<th>피해금액 |</th>
				<td>피해수</td>
			</tr>
			<tr>
				<th colspan="8">조회 기간이 1년 이내인 경우에만 피해자의 연령 통계가 제공됩니다.</th>
			</tr>
		</table>
	</div>
	<div class="container">
		<table class="table table table-striped">
			<tr>
				<th>용의자(명수)</th>
				<th>사이트(사이트수)</th>
				<th>은행(은행수)</th>
			</tr>
			<tr>
				<td>용의자 이름 과 수</td>
				<td>사이트이름 과 수</td>
				<td>은행이름과 수</td>
			</tr>
			<tr>
				<td>유호진	502건	</td>
				<td>cafe.naver.com	131,564건	</td>
				<td>농협	48,525건</td>
			</tr>
		</table>
	</div>
</body>
</html>