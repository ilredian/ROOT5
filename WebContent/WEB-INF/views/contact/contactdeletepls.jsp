<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AhnCheat</title>
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
	<form action="" method="post" name="writeForm" id="frm">
		<div class="container">
			<h2>피해사례 삭제요청</h2>
			<table class="table table-condensed">
				<tr>
					<th align="center" class="active">필독</th>
					<td>1. 삭제요청 게시물은 수정 및 삭제가 되지 않습니다. 신중히 작성해 주시기 바랍니다.<br>
						2. 피해사례 등록자에게 SMS와 이메일로 삭제요청 메세지가 전송되며, 삭제 요청자의 연락처가 안내됩니다.<br>
						3. 환불 증빙자료가 첨부되면, 즉시 피해사례가 삭제됩니다.<br> 4. 환불 증빙자료 미첨부시, 피해사례
						등록자가 직접 피해사례를 삭제할 때까지 기다려야 합니다.<br>
					</td>
				</tr>
				<tr>
					<th align="center" class="active">제목</th>
					<td><input type="text" name="제목"
						placeholder="사죄문과 함께 삭제요청 드립니다." style="width: 600px"></td>
				</tr>
				<tr>
					<th align="center" class="active">삭제 요청 연락처(필수)</th>
					<td><input type="text" name="연락처"
						placeholder="귀하가 사기행위에 사용한 휴대폰 번호를 모두 입력하세요.(복수입력 가능하며 , 로 구분하여 주세요)"
						style="width: 800px"></td>
				</tr>
				<tr>
					<th align="center" class="active"><label for="w_tit">연락처</label></th>
					<td><script>
						function srvTime() {
							var xmlHttp;
							if (window.XMLHttpRequest) {//분기하지 않으면 IE에서만 작동된다.
								xmlHttp = new XMLHttpRequest(); // IE 7.0 이상, 크롬, 파이어폭스 등
								xmlHttp.open('HEAD', window.location.href
										.toString(), false);
								xmlHttp.setRequestHeader("Content-Type",
										"text/html");
								xmlHttp.send('');
								return xmlHttp.getResponseHeader("Date");
							} else if (window.ActiveXObject) {
								xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
								xmlHttp.open('HEAD', window.location.href
										.toString(), false);
								xmlHttp.setRequestHeader("Content-Type",
										"text/html");
								xmlHttp.send('');
								return xmlHttp.getResponseHeader("Date");
							}
						}
						function v2014_handphone_check(form_name) {
							var key_handphone = $(form_name).val();
							var bbs_sms_type = $('#bbs_sms_type').val();
							if (!key_handphone) {
								alert('연락처를 입력해 주세요.');
								return false;
							}
							var st = srvTime();
							var dt = new Date(st);
							if (!dt)
								dt = new Date();
							var yyyy = dt.getFullYear().toString();
							var mm = (dt.getMonth() + 1).toString(); // getMonth() is zero-based
							var dd = dt.getDate().toString();
							var hh = dt.getHours().toString();
							var ii = dt.getMinutes().toString();
							var time = yyyy + (mm[1] ? mm : "0" + mm[0])
									+ (dd[1] ? dd : "0" + dd[0])
									+ (hh[1] ? hh : "0" + hh[0])
									+ (ii[1] ? ii : "0" + ii[0]);
							// https://developer.mozilla.org/en-US/docs/Web/API/WindowBase64/btoa
							window
									.open(
											'./?mod=sms_cheat&phone='
													+ window
															.btoa(unescape(encodeURIComponent(key_handphone)))
													+ '&bbs_sms_type='
													+ bbs_sms_type
													+ '&t='
													+ window
															.btoa(unescape(encodeURIComponent(time))),
											'cheat_demand');
							//window.open('./?mod=sms_cheat&phone='+key_handphone+'&bbs_sms_type='+bbs_sms_type,'cheat_demand');
						}
					</script> <select name="member_phone1"
						onchange="javascript:if(true){member_phone.value = member_phone1[member_phone1.selectedIndex].value + member_phone2.value + member_phone3.value }"
						style="width: 60px;">
							<option selected value=010>010</option>
							<option value="010">010
							<option value="011">011
							<option value="016">016
							<option value="017">017
							<option value="018">018
							<option value="019">019
					</select>&nbsp;-&nbsp;<input type="text" name="member_phone2" value=""
						maxlength="4" class="txt"
						onchange="javascript:if(true){member_phone.value = member_phone1[member_phone1.selectedIndex].value + member_phone2.value + member_phone3.value }"
						style="ime-mode: disabled;"
						onKeyPress="return numbersonly(event, false)" />&nbsp;-&nbsp;<input
						type="text" name="member_phone3" value="" maxlength="4"
						class="txt"
						onchange="javascript:if(true){member_phone.value = member_phone1[member_phone1.selectedIndex].value + member_phone2.value + member_phone3.value }"
						style="ime-mode: disabled;"
						onKeyPress="return numbersonly(event, false)" />&nbsp;<a
						onclick="v2014_handphone_check('#member_phone');return false;"
						target="target_iframe"></a></td>
				</tr>
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
				<tr>
					<th class="active">약관 동의</th>
					<td><input type="checkbox" id=chk1> <label for="chk1">서비스
							이용약관에 동의합니다.(필수)</label><br> <input type="checkbox" id=chk2>
						<label for="chk2">개인정보 수집 및 이용에 동의합니다.(필수)</label> <textarea
							cols=60 rows=10 style="width: 900px; height: 300px;">
1. 수집하는 개인정보 항목

더치트는 회원가입, 원활한 회원 상담, 각종 서비스 등 기본적인 서비스 제공을 위한 필수정보와 회원 맞춤 서비스 제공을 위한 선택정보로 구분하여 아래와 같은 개인정보를 수집하고 있습니다.

1) 수집항목

<일반 회원가입 시>

필수항목 : 성명, 아이디, 비밀번호, 휴대 전화번호, 이메일 주소, 아이핀 정보, CI/DI번호, 생년월일/성별, 만 14세 미만인 경우 법정 대리인 정보

선택사항 : 실명인증값, 아이핀 회원은 아이핀 번호, 사진

<기업 /단체 회원가입 시>

필수항목 : 아이디, 비밀번호, 법인명, 사업자등록번호, 대표자명, 업종, 이메일 주소, 사업장 소재지, 담당자 이름, 아이핀 정보, CI/DI번호, 생년월일/성별, 휴대 전화번호, 유선 전화번호

선택사항 : 설립일

<소셜 네트워크/모바일 서비스 이용 시>

선택사항 : 생년월일, 성별, 학교명(입학년도), 직업, 관심사, 혈액형, 사진, 폰번호, 폰주소록, 위치 정보(위치기반서비스 이용 시), 음성 정보(음성기반서비스 이용 시)

<유료 서비스 이용 시>

휴대폰 정보, 신용카드 정보, 은행계좌 정보, 결제기록 등의 정보가 수집될 수 있습니다.

선택정보를 입력하지 않은 경우에도 서비스 이용 제한은 없으며 이용자의 기본적 인권 침해의 우려가 있는 민감한 개인 정보(인종, 사상 및 신조, 정치적 성향 이나 범죄기록, 의료정보 등)는 수집하지 않습니다.

단, 서비스 이용과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.

IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록

2) 개인정보 수집방법

더치트는 다음과 같은 방법으로 개인정보를 수집할 수 있습니다.

- 홈페이지, 서면양식, 팩스, 전화, 상담 게시판, 이메일, 이벤트 응모, 배송요청

- 협력회사로부터의 제공

- 생성정보 수집 툴을 통한 수집




2. 개인정보의 수집 및 이용목적

더치트는 수집한 개인정보를 다음의 목적을 위해 활용합니다. 이용자가 제공한 모든 정보는 하기 목적에 필요한 용도 이외로는 사용되지 않으며 이용 목적이 변경될 시에는 사전 동의를 구할 것입니다.

1) 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산

콘텐츠 제공, 구매 및 요금 결제, 물품배송 또는 청구지 등 발송, 금융거래 본인 인증 및 금융 서비스, 요금추심 등

2) 회원 관리

회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가 사용 방지, 가입 의사 확인, 연령확인, 만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인, 불만처리 등 민원처리, 고지사항 전달

3) 신규 서비스 개발 및 마케팅·광고에의 활용

신규 서비스 개발과 이벤트 행사에 따른 정보 전달 및 맞춤 서비스 제공, 인구통계학적 특성에 따른 서비스 제공 및 광고 게재, 접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계




3. 개인정보의 보유 및 이용기간

더치트는 회원가입일로부터 서비스를 제공하는 기간 동안에 한하여 이용자의 개인정보를 보유 및 이용하게 됩니다.

회원 탈퇴를 요청하거나 개인정보의 수집 및 이용에 대한 동의를 철회하는 경우, 수집 및 이용목적이 달성되거나 보유 및 이용기간이 종료한 경우 해당 개인정보를 지체 없이 파기합니다.

단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.

- 부정이용기록

보존 이유 : 부정 이용 방지

보존 기간 : 1년

상법, 전자상거래 등에서의 소비자보호에 관한 법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 더치트는 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 이 경우 더치트는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.

- 계약 또는 청약철회 등에 관한 기록

보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률

보존 기간 : 5년

- 대금결제 및 재화 등의 공급에 관한 기록

보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률

보존 기간 : 5년

- 소비자의 불만 또는 분쟁처리에 관한 기록

보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률

보존 기간 : 3년

- 본인확인에 관한 기록

보존 이유 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률

보존 기간 : 6개월

- 방문에 관한 기록

보존 이유 : 통신비밀보호법

보존 기간 : 3개월
               </textarea></td>
				</tr>
			</table>
			<div align="center">
				<!-- 			<a href="#"><img src="homenavimages/p.PNG"></img></a> <a href="#"><img
                  src="homenavimages/o.PNG"></img></a> 버튼-->

				</table>
			</div>
			<a><input type="button" class="btn btn-info" value="등록"></a>
			<a href="contacted.go"><input type="button"
				class="btn btn-danger" value="취소"></a>
		</div>
	</form>
</body>
</html>