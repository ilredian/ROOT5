<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form action="" method="post" name="writeForm">
	<div class="container">
		<h3>직거래 피해사례 등록</h3>
		<p>피해사례 등록이 완료되면 피해자를 위한 기능이 실시간 제공됩니다.</p>
		<br>
		
		<div style="float:left;"><h3>피해 발생 사이트 정보</h3></div>
		<div style="float:right;">
			<h6><code>*</code> 는 필수 기재 항목입니다</h6>
		</div>
		
		<div class="container" align="center">
			<table class="table">
				<tr>
					<th class="active" style="width: 150;"><label for="s_stie">사이트명(URL)</label>
						<code>*</code></th>
					<td><input type="hidden" name="category" value="신고"> <select
						name="cheat_site_temp"
						onchange="javascript:if(cheat_site_temp.value == '1'){ cheat_site.readOnly = false; cheat_site.focus(); } else{ cheat_site.value = cheat_site_temp.value; cheat_site.readOnly = true; cheat_site.focus(); }"
						id="cheat_site_temp" title="사이트 선택" name="">
							<option selected value="">피해가 발생한 사이트를 선택하세요.</option>
							<option value="">─────────</option>
							<c:forEach items="${domainlist}" var="list">
								<option value="${list.domain}">${list.domainname}</option>
							</c:forEach>
							<option value="">─────────</option>
							<option value="1">직접 입력하기(오른쪽에 URL을 입력하세요.)</option>
					</select> http://www.<input type="text" name="cheat_site" id="cheat_site"
						class="txt" value="" size="26" placeholder=" URL이 입력됩니다." readonly
						maxlength="30" /></td>
				</tr>


				<tr>
					<th class="active" style="width: 150;">거래 물품 종류 <code>*</code></th>
					<td>
						<table class="table table-bordered">
							<c:forEach items="${itemslist}" var="list" varStatus="a">
								<c:if test="${a.index % 6 == 0}">
									<tr>
								</c:if>
								<td align="center"><il class="oneLine"> <input
										type="radio" name="cheat_item_temp" class="rdo"
										id="${list.goodskind}" value="${list.goodsname}"
										onclick="label_rdo_click('${list.goodskind}');"> <label
										for="${list.goodskind}"><h1>
											<span class="${list.goodsspan}"
												onclick="label_rdo_click('${list.goodskind}');"></span>
										</h1>
										<div class="sub_msg">${list.goodsname}</div></label></il></td>
								<c:if test="${a.index % 6 == 5}">
									</tr>
								</c:if>
								<c:if test="${a.index == 25}">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>

				<tr>
					<th class="active" style="width: 150;"><label for="s_id">물품명</label><code>*</code></th>
					<td><input type="text" class="txt" name="subject" id="subject"
						value="" placeholder="거래한 물품의 물풍명 또는 모델명을 입력하세요."
						style="width: 580px;" maxlength="200"></td>
				</tr>

				<tr>
					<th class="active" style="width: 150;"><label for="s_id">용의자
							아이디</label></th>
					<td><input type="text" class="txt" name="cheat_id"
						id="cheat_id" value=""
						placeholder="사기범의 ID 또는 메신져주소, 이메일주소 등을 입력하세요."
						style="width: 580px;" maxlength="20"></td>
				</tr>

				<tr>
					<th class="active" style="width: 150;"><label for="s_link">사기
							게시물 링크</label></th>
					<td><input type="text" class="txt" name="link" id="link"
						value="" placeholder="판매 게시물의 URL을 입력하세요." style="width: 580px;"
						maxlength="400"></td>
				</tr>
			</table>
		</div>
		
		<div style="float:left;"><h3>용의자(사기범) 정보</h3></div>
		<div style="float:right;">
			<h6><code>*</code> 는 필수 기재 항목입니다</h6>
		</div>
		
		<div class="container" align="center">
			<table class="table">

				<tr>
					<th class="active" style="width: 150;">계좌정보 <code>*</code></th>
					<td><input type="checkbox" name="cheat_account_none"
						class="chk" id="su_bankNumchk"
						onclick="javascript:if($('#show_bank_info_1').css('display')!='none'){document.writeForm.cheat_bank.value='직거래';cheat_account.value='모름';cheat_suspect.value='모름';cheat_price.value='0'; $('#show_bank_info_1').css({display: 'none'}); $('#show_bank_info_2').css({display: 'none'}); $('#show_bank_info_3').css({display: 'none'}); $('#show_bank_info_4').css({display: 'none'}); $('#show_bank_info_5').css({display: 'none'}); }else {$('#show_bank_info_1').css({display: ''}); $('#show_bank_info_2').css({display: ''}); $('#show_bank_info_3').css({display: ''}); $('#show_bank_info_4').css({display: ''}); $('#show_bank_info_5').css({display: ''});}"><label
						for="su_bankNumchk">계좌를 모르는 경우에만 체크하세요.</label></td>
				</tr>

				<tr id="show_bank_info_1">
					<th class="active" style="width: 150;"><label for="su_bank">은행명</label>
						<code>*</code></th>
					<td><select name="cheat_bank" id="su_bank" title="은행선택"
						style="width: 230px;">
							<option selected value="0">은행선택(가나다 순)</option>
							<option value="">-----------------------
								<c:forEach items="${banklist1}" var="list">
									<option value="${list.bankname}">${list.bankname}
								</c:forEach>
							<option value="">-----------------------
								<c:forEach items="${banklist2}" var="list">
									<option value="${list.bankname}">${list.bankname}
								</c:forEach>
							<option value="">-----------------------
								<c:forEach items="${banklist3}" var="list">
									<option value="${list.bankname}">${list.bankname}
								</c:forEach>
							<option value="">-----------------------
							<option value="직거래">직거래피해
							<option value="아이템양도">아이템양도
					</select></td>
				</tr>

				<tr id="show_bank_info_2">
					<th class="active" style="width: 150;"><label for="su_name">명의자
							성명</label> <code>*</code></th>
					<td><input type="text" class="txt" name="cheat_suspect"
						id="su_name" style="width: 230px;" maxlength="16" value=""
						placeholder="계좌 명의자명을 입력하세요."></td>
				</tr>

				<tr id="show_bank_info_3">
					<th class="active" style="width: 150;"><label for="su_bankNum">계좌
							번호</label> <code>*</code></th>
					<td><input type="text" class="txt" name="cheat_account"
						id="su_bankNum" value="" style="width: 230px;"
						style="ime-mode:disabled;"
						onKeyPress="return numbersonly(event, false)" maxlength="30"
						placeholder="계좌번호를 입력하세요."></td>
				</tr>

				<tr id="show_bank_info_4">
					<th class="active" style="width: 150;"><label for="su_sum">입금
							금액</label> <code>*</code></th>
					<td><input type="text" class="txt" name="cheat_price" value=""
						placeholder="피해금액을 원 단위로 입력하세요." id="su_sum"
						style="width: 230px; ime-mode: disabled;"
						onKeyPress="return numbersonly(event, false)" maxlength="8">
						원</td>
				</tr>

				<tr id="show_bank_info_5">
					<th class="active" style="width: 150;"><label for="su_dateY">입금일</label>
						<code>*</code></th>
					<td><input type="hidden" name="cheat_date" value=""
						class="thecheat_input" /> <select name="cheat_date_temp_1"
						id="su_dateY" title="연도선택" style="width: 80px;"
						onchange="javascript:if(1){cheat_date.value = cheat_date_temp_1[cheat_date_temp_1.selectedIndex].value + cheat_date_temp_2.value + cheat_date_temp_3.value }">
							<option value="">년도 선택</option>
							<c:forEach begin="${year-10}" end="${year}" var="years">
								<c:choose>
									<c:when test="${years == year}">
										<option selected value="${years}">${years}</option>
									</c:when>
									<c:otherwise>
										<option value="${years}">${years}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <select name="cheat_date_temp_2" id="su_dateM" title="월 선택"
						style="width: 80px;"
						onchange="javascript:if(1){cheat_date.value = cheat_date_temp_1.value + cheat_date_temp_2[cheat_date_temp_2.selectedIndex].value + cheat_date_temp_3.value }">
							<option selected value="">월 선택</option>
							<c:forEach begin="1" end="12" var="months">
								<c:choose>
									<c:when test="${months == month}">
										<option selected value="${months}">${months}</option>
									</c:when>
									<c:otherwise>
										<option value="${months}">${months}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <select name="cheat_date_temp_3" id="su_dateD" title="일 선택"
						style="width: 80px;"
						onchange="javascript:if(1){cheat_date.value = cheat_date_temp_1.value + cheat_date_temp_2.value + cheat_date_temp_3[cheat_date_temp_3.selectedIndex].value }">
							<option selected value="0">일 선택</option>
							<c:forEach begin="01" end="31" var="days">
								<c:choose>
									<c:when test="${days == date}">
										<option selected value="${days}">${days}</option>
									</c:when>
									<c:otherwise>
										<option value="${days}">${days}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<th class="active" style="width: 150;">연락처 정보 <code>*</code></th>
					<td><input type="checkbox" name="cheat_phone_none" class="chk"
						id="su_telchk"
						onclick="javascript:if($('#show_tel_info').css('display')!='none'){document.writeForm.cheat_phone1.value='010';cheat_phone2.value='0000';cheat_phone3.value='0000';cheat_phone.value='01000000000';$('#show_tel_info').css({display: 'none'}); }else { $('#show_tel_info').css({display: ''}); }" /><label
						for="su_telchk">연락처를 모르는 경우에만 체크하세요.</label></td>
				</tr>

				<tr id="show_tel_info">
					<th class="active" style="width: 150;"><label for="su_tel">연락처</label>
						<code>*</code></th>
					<td><select name="cheat_phone1" id="su_tel" title="국번선택"
						style="width: 80px;"
						onchange="javascript:if(1){cheat_phone.value = cheat_phone1[cheat_phone1.selectedIndex].value + cheat_phone2.value + cheat_phone3.value }">
							<option selected value=0>선택</option>
							<option value="010">010
							<option value="011">011
							<option value="016">016
							<option value="017">017
							<option value="018">018
							<option value="019">019
							<option value="02">02
							<option value="031">031
							<option value="032">032
							<option value="033">033
							<option value="041">041
							<option value="042">042
							<option value="043">043
							<option value="051">051
							<option value="052">052
							<option value="053">053
							<option value="054">054
							<option value="055">055
							<option value="061">061
							<option value="062">062
							<option value="063">063
							<option value="064">064
							<option value="013">013
							<option value="070">070
								<!--<option value="050">050-->
								<!--<option value="168">168-->
					</select>-<input type="text" name="cheat_phone2" value="" class="txt"
						maxlength="4"
						onchange="javascript:if(1){cheat_phone.value = cheat_phone1[cheat_phone1.selectedIndex].value + cheat_phone2.value + cheat_phone3.value }"
						style="ime-mode: disabled;"
						onKeyPress="return numbersonly(event, false)" style="width:70px;"
						title="가운데 자리 입력" />-<input type="text" name="cheat_phone3"
						value="" class="txt" maxlength="4"
						onchange="javascript:if(1){cheat_phone.value = cheat_phone1[cheat_phone1.selectedIndex].value + cheat_phone2.value + cheat_phone3.value }"
						style="ime-mode: disabled;"
						onKeyPress="return numbersonly(event, false)" style="width:70px;"
						title="끝 자리 입력" />&nbsp;<input type="hidden" name="cheat_phone"
						value="" readonly /> ※ 050 임시번호 등록을 금합니다.</td>
				</tr>

				<tr>
					<th class="active" style="width: 150;">성별</th>
					<td><span class="chkArea"><input type="radio"
							name="cheat_sex" value="남자" checked id="su_sexM" class="rdo"><label
							for="su_sexM">남자</label></span> <span class="chkArea"><input
							type="radio" name="cheat_sex" value="여자" id="su_sexW" class="rdo"><label
							for="su_sexW">여자</label></span></td>
				</tr>

				<tr>
					<th class="active" style="width: 150;"><label for="su_keynote">용의자
							특징</label></th>
					<td><input type="text" class="txt" name="cheat_character"
						value="" placeholder="예) 경상도 사투리 사용" id="su_keynote"
						style="width: 580px;" maxlength="60"></td>
				</tr>



			</table>
		</div>
		
		<div style="float:left;"><h3>사건 개요 (진술서)</h3></div>
		<div style="float:right;">
			<h6><code>*</code> 는 필수 기재 항목입니다</h6>
		</div>
		
		
		<div class="container" align="center">
			<table class="table">
				<tr>
					<th class="active" style="width: 13%;">사건 발생 개요 <code>*</code></th>
					<td>
					1.물품을 받은 경우에는 물품 사진을 첨부하세요.
					<br>
					2.용의자의 주민등록번호와 사진은 절대 등록하시면 안됩니다.(피해사례 등록자가 법적 처벌을 받을 수 있습니다.) 
						<textarea id="editor" style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30" name="content">
						</textarea>
					</td>
				</tr>
			</table>
		</div>
		<br>

<h3 align="center">피해사례 등록 약관</h3>
		
		<div class="agreeArea" align="center">
			<textarea rows="10px" cols="100px" readonly="readonly" style=""> 
피해사례 등록 약관
            
1. 본인이 등록한 피해사례 정보는 용의자 검거 및 피해 방지를 위한 목적으로 공인된 수사기관에 제공됨을 동의합니다.
2. 본인이 등록한 피해사례 정보는 추가 피해를 방지하기 위한 목적으로 개인, 기업 등 제3자에게 제공됨을 동의합니다.
3. 본인이 등록한 피해사례 정보는 경찰서에 직접 방문하여 신고 접수 예정이거나 신고 접수가 완료된 사례입니다.
4. 허위사실 유포, 명예훼손 등 법적분쟁 발생 시 모든 책임은 피해사례 등록자인 본인에게 있습니다.
5. 피해사례 등록 여부 및 검거소식 등의 추가 정보가 용의자와 피해자에게 통지됨을 동의합니다.
6. 물품 배송 또는 환불이 완료된 경우, 즉시 등록한 피해사례를 삭제할 것입니다.
            
※ 약관을 위배하는 경우, 다음과 같은 처벌을 받을 수 있습니다.
① 사람을 비방할 목적으로 정보통신망을 통하여 공연히 사실을 적시하여 타인의 명예를 훼손하는 경우 3년 이하의 징역이나 금고 또는 2천만원 이하의 벌금에 처하고
사람을 비방할 목적으로 정보통신망을 통하여 공연히 허위의 사실을 적시하여 타인의 명예를 훼손하는 경우에는 7년 이하의 징역, 10년 이하의 자격정지 또는 5천만원 이하의 벌금에 처할 수 있습니다.
② 개인적인 앙심 또는 괘씸함 등 사기범죄 외의 사유로 등록하시는 경우, 명예훼손에 해당될 수 있으며 이는 민·형사상의 불이익을 받을 수 있습니다.
③ 제품에 대한 상태불만, 반품거부, 택배비 착불등의 사례 등록은 영업방해 및 명예훼손에 해당될 수 있습니다.
</textarea>
			<div class="alert_cheat_write alert_cheat_write-info">
				<b><input type="checkbox" name="cheat_rule_c" value="1"
					id="cheat_rule_c" valign="bottom"
					onclick="return cheat_rule_check(cheat_rule.value)" /> <label
					for="cheat_rule_c">피해사례 등록 약관에 동의합니다.(필수)</label></b> <input
					type="hidden" readonly name="cheat_rule" value="" valign="bottom"
					style="width: 0px; height: 0px;">
			</div>

			<div class="alert_cheat_write alert_cheat_write-info">
				<b><input type="checkbox" name="agreement_1" value="1"
					id="chk_agreement_1"
					onclick="return value_change('agreement_1h',this.value)" /> <label
					for="chk_agreement_1">서비스 이용약관에 동의합니다.(필수)</label></b>
			</div>
			<div class="alert_cheat_write alert_cheat_write-info">
				<div style="margin-top: 6px;">
					<textarea rows="10" cols="100" readonly="readonly">
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
         
</textarea>
				</div>
				<b><input type="checkbox" name="agreement_2" value="1"
					id="chk_agreement_2"
					onclick="return value_change('agreement_2h',this.value)" /> <label
					for="chk_agreement_2">개인정보 수집 및 이용에 동의합니다.(필수)</label></b>
			</div>


		</div>
		<input type="hidden" id="agreement_1h" value="0" name="agreement_1h"
			style="width: 0px; height: 0px;"> <input type="hidden"
			id="agreement_2h" value="0" name="agreement_2h"
			style="width: 0px; height: 0px;">

		<div>
			<br>
		</div>
		<div class="container">
			<input type="submit" class="btn btn-info btn-block"
				value="피해사례 등록 약관에 동의하며, 피해사례를 등록합니다.">
		</div>
		<!-- <input type="submit" value="" name="" onclick="return confirm('확인 버튼을 누른 후 피해사례가 등록될 때 까지 기다려 주세요.');"/> -->
		
	</div>
</form>