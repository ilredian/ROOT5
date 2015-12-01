<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<a href="freeMain.go">자유게시판</a>
<a href="statementMain.go?cno=1">진술서게시판</a>
<a href="lawMain.go">변호사게시판</a>
<div class="container-fluid" align="center">
	<div class="row1">
		<div class="col-sm-6"
			style="background-color: lavender; margin: 10px; height: 165px; width: 535px;">
			<div style="margin-top: 30px">
				<img alt="목록" src="homeimages/z.PNG"> <select
					style="width: 317px">
					<option>전체 피해 사례</option>
					<option>직거래</option>
					<option>전체 사례</option>
				</select>
			</div>
			<div>
				<img alt="검색" src="homeimages/x.PNG"> <input type="text"
					placeholder="로그인후 피해사례를 검색 할 수 있습니다(무료)" size="40">
			</div>
			<div>
				<input type="image" src="homeimages/button.PNG"
					onclick="form_submit('search','./?mod=_search_result','web');"
					style="position: absolute; left: 455px; top: 20px;">
			</div>
			<hr>
			<p style="text-align: inherit;">
				<서버코드> 건의 사기 피해사례가 등록되어 있습니다 | Since 2015-11-27 
			</p>
		</div>
		<div class="col-sm-3"
			style="margin: 10px; height: 165px; width: 214px; background-color: lavender; padding: 20px;">
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
		<div class="col-sm-3"
			style="background-color: lavender; margin: 10px; height: 165px; width: 275px;">
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
				<input type="image" src="homeimages/regist.PNG">
			</div>
		</div>
	</div>
	<div class="row2">
		<div class="col-sm-6"
			style="background-color: lavender; margin: 10px; width: 565px; height: 165px; float: left;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><a href="noticeMain.go"><p style="text-align: left;">공지사항</p></a></th>
						<th></th>
						<th><a href="noticeMain.go"><p style="text-align: right;">더보기</p></a></th>
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
			style="background-color: lavender; margin: 10px; width: 481px; height: 165px; float: left;">
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
			<div
				style="background-color: lavender; margin-left: 203px; width: 219px; height: 165px; position: relative;">
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
</div>