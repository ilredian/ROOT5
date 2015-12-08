<!-- 게임뭐시기 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="well" align="center">
	<div class="container">
		<h3 align="center">용의자(사기범) 정보</h3>
		<p class="infoTxtTop" align="center">
			<span class="bltS_A">*</span> 는 필수 기재 항목입니다
		</p>
		<table class="formTypeA">
			<caption>피해 발생 사이트에 대한 사이트명, 거래물품종류, 용의자 아이디, 사기 게시물 링크 입력폼</caption>
			<colgroup>
				<col width="150">
				<col width="*">
			</colgroup>
			<tr>
				<th><label for="s_stie">사이트명(URL)</label> <span class="bltS_A">*</span></th>
				<td><input type="hidden" name="category" value="신고"> <select
					name="cheat_site_temp"
					onchange="javascript:if(cheat_site_temp.value == '1'){ cheat_site.readOnly = false; cheat_site.focus(); } else{ cheat_site.value = cheat_site_temp.value; cheat_site.readOnly = true; cheat_site.focus(); }"
					id="cheat_site_temp" title="사이트 선택">
						<option selected value="">피해가 발생한 사이트를 선택하세요.</option>
						<option value="">─────────</option>
						<option value="cafe.naver.com">네이버 카페</option>
						<option value="blog.naver.com">네이버 블로그</option>
						<option value="cafe.daum.net">다음 카페</option>
						<option value="blog.daum.net">다음 블로그</option>
						<option value="kakao.com">카카오톡/스토리</option>
						<option value="ruliweb.daum.net">루리웹</option>
						<option value="nateonweb.nate.com">네이트온</option>
						<option value="itembay.com">아이템베이</option>
						<option value="itemmania.com">아이템매니아</option>
						<option value="">─────────</option>
						<option value="1">직접 입력하기(오른쪽에 URL을 입력하세요.)</option>
				</select> http://www.<input type="text" name="cheat_site" id="cheat_site"
					class="txt" value="" size="26" placeholder=" URL이 입력됩니다." readonly
					maxlength="30" /></td>
			</tr>
			<tr>
				<th>거래 물품 종류 <span class="bltS_A">*</span></th>
				<td>
					<ul class="damageGoodsDealList">
						<il class="first oneLine" style="float: left;"> <input
							type="radio" name="cheat_item_temp" class="rdo" id="s_goods17"
							value="포인트/마일리지" onclick="label_rdo_click('s_goods17');">
						<label for="s_goods17" style="margin-right: 10px">
							<h1>
								<span class="glyphicon glyphicon-usd"></span>
							</h1>
							<div class="sub_msg">포인트/마일리지</div>
						</label></il>


						<il class="oneLine" style="float: left;"> <input type="radio"
							name="cheat_item_temp" class="rdo" id="s_goods18" value="게임 아이템"
							onclick="label_rdo_click('s_goods18');"> <label
							for="s_goods18" style="margin-top: 8px; margin-right: 10px">
							<h2>
								<span class="glyphicon glyphicon-briefcase"></span>
							</h2>
							<div class="sub_msg">게임 아이템</div>
						</label></il>


						<il class="oneLine" style="float: left;"> <input type="radio"
							name="cheat_item_temp" class="rdo" id="s_goods20" value="아이디/계정"
							onclick="label_rdo_click('s_goods20');"> <label
							for="s_goods20" style="margin-top: 8px">
							<h2>
								<span class="glyphicon glyphicon-user"></span>
							</h2>
							<div class="sub_msg">아이디/계정</div>
						</label></il>
						<input type="hidden" name="cheat_item" id="cheat_item" value=""
							maxlength="20" class="txt" readonly />

					</ul>
				</td>
			</tr>

			<tr>
				<th><label for="s_id">물품명 <span class="bltS_A">*</span></th>
				<td><input type="text" class="txt" name="subject" id="s_id"
					value="" placeholder="거래한 물품의 물풍명 또는 모델명을 입력하세요."
					style="width: 580px;" maxlength="200"></td>
			</tr>

			<tr>
				<th><label for="s_id">용의자 아이디</th>
				<td><input type="text" class="txt" name="cheat_id" id="s_id"
					value="" placeholder="사기범의 ID 또는 메신져주소, 이메일주소 등을 입력하세요."
					style="width: 580px;" maxlength="20"></td>
			</tr>

			<tr>
				<th><label for="s_link">사기 게시물 링크</label></th>
				<td><input type="text" class="txt" name="adddata" id="s_link"
					value="" placeholder="판매 게시물의 URL을 입력하세요." style="width: 580px;"
					maxlength="400"></td>
			</tr>
		</table>
	</div>


	<div class="formSection">
		<div class="well" align="center">
			<h3 align="center">용의자(사기범) 정보</h3>
			<p class="infoTxtTop" align="center">
				<span class="bltS_A">*</span> 는 필수 기재 항목입니다
			</p>

			<table class="formTypeA">
				<caption>용의자 정보에 대한 계좌정보, 은행명, 명의자 성명, 계좌번호, 입금 금액, 입금일,
					연락처 정보, 연락처, 성별, 용의자 특징 입력 폼</caption>
				<colgroup>
					<col width="150">
					<col width="*">
				</colgroup>
				<tr>
					<th>계좌정보 <span class="bltS_A">*</span></th>
					<td><input type="checkbox" name="cheat_account_none"
						class="chk" id="su_bankNumchk"
						onclick="javascript:if($('#show_bank_info_1').css('display')!='none'){document.writeForm.cheat_bank.value='직거래';cheat_account.value='모름';cheat_suspect.value='모름';cheat_price.value='0'; $('#show_bank_info_1').css({display: 'none'}); $('#show_bank_info_2').css({display: 'none'}); $('#show_bank_info_3').css({display: 'none'}); $('#show_bank_info_4').css({display: 'none'}); $('#show_bank_info_5').css({display: 'none'}); }else {$('#show_bank_info_1').css({display: ''}); $('#show_bank_info_2').css({display: ''}); $('#show_bank_info_3').css({display: ''}); $('#show_bank_info_4').css({display: ''}); $('#show_bank_info_5').css({display: ''});}"><label
						for="su_bankNumchk">계좌를 모르는 경우에만 체크하세요.</label></td>
				</tr>

				<tr id="show_bank_info_1">
					<th><label for="su_bank">은행명</label> <span class="bltS_A">*</span></th>
					<td><select name="cheat_bank" id="su_bank" title="은행선택"
						style="width: 230px;">
							<option selected value=0>은행선택(가나다 순)</option>
							<option value="">-----------------------
							<option value="경남은행">경남은행
							<option value="광주은행">광주은행
							<option value="국민은행">국민은행
							<option value="기업은행">기업은행
							<option value="농협">농협
							<option value="대구은행">대구은행
							<option value="도이치은행">도이치은행
							<option value="부산은행">부산은행
							<option value="산림조합은행">산림조합은행
							<option value="산업은행">산업은행
							<option value="상호저축은행">상호저축은행
							<option value="새마을금고">새마을금고
							<option value="수협">수협
							<option value="SC은행">스탠다드차타드(제일)은행
							<option value="신용협동조합">신협
							<option value="신한은행">신한은행
							<option value="씨티은행">씨티은행
							<option value="외환은행">외환은행
							<option value="우리은행">우리은행
							<option value="우체국예금">우체국예금
							<option value="전북은행">전북은행
							<option value="제주은행">제주은행
							<option value="하나은행">하나은행
								<!--<option value="조흥은행">조흥은행-->
								<!--<option value="한미은행">한미은행-->
							<option value="">-----------------------
							<option value="기술보금">기술보금
							<option value="서울보증">서울보증
							<option value="수출입은행">수출입은행
							<option value="신용보금">신용보금
							<option value="BNPP">BNPP
							<option value="BOA">BOA
							<option value="HSBC">HSBC(홍콩샹하이)
							<option value="JP모간">JP모간
							<option value="MIZHO">MIZHO
							<option value="RBS">RBS(알비에스)
							<option value="UFJ">UFJ(상와은행)
								<!--<option value="조흥은행">조흥은행-->
								<!--<option value="한미은행">한미은행-->
							<option value="">-----------------------
							<option value="HMC투자증권">HMC투자증권
							<option value="LIG투자증권">LIG투자증권
							<option value="NH투자증권">NH투자증권
							<option value="SK증권">SK증권
							<option value="교보증권">교보증권
							<option value="대신증권">대신증권
							<option value="대우증권">대우증권
							<option value="동부증권">동부증권
							<option value="메리츠증권">메리츠증권
							<option value="미래에셋">미래에셋증권
							<option value="부국증권">부국증권
							<option value="삼성증권">삼성증권
							<option value="신영증권">신영증권
							<option value="신한투자증권">신한투자증권
							<option value="아이엠투자증권">아이엠투자증권
							<option value="우리투자증권">우리투자증권
							<option value="유안타증권">유안타(동양)증권
							<option value="유진투자증권">유진투자증권
							<option value="이베스트투자증권">이베스트투자증권
							<option value="키움증권">키움증권
							<option value="하나금융투자">하나금융투자
							<option value="하이투자증권">하이투자증권
							<option value="한국투자증권">한국투자증권
							<option value="한화증권">한화증권
							<option value="현대증권">현대증권
							<option value="">-----------------------
							<option value="직거래">직거래피해
							<option value="아이템양도">아이템양도
					</select></td>
				</tr>

				<tr id="show_bank_info_2">
					<th><label for="su_name">명의자 성명</label> <span class="bltS_A">*</span></th>
					<td><input type="text" class="txt" name="cheat_suspect"
						id="su_name" style="width: 230px;" maxlength="16" value=""
						placeholder="계좌 명의자명을 입력하세요."></td>
				</tr>

				<tr id="show_bank_info_3">
					<th><label for="su_bankNum">계좌 번호</label> <span class="bltS_A">*</span></th>
					<td><input type="text" class="txt" name="cheat_account"
						id="su_bankNum" value="" style="width: 230px;"
						style="ime-mode:disabled;"
						onKeyPress="return numbersonly(event, false)" maxlength="30"
						placeholder="계좌번호를 입력하세요."></td>
				</tr>

				<tr id="show_bank_info_4">
					<th><label for="su_sum">입금 금액</label> <span class="bltS_A">*</span></th>
					<td><input type="text" class="txt" name="cheat_price" value=""
						placeholder="피해금액을 원 단위로 입력하세요." id="su_sum"
						style="width: 230px; ime-mode: disabled;"
						onKeyPress="return numbersonly(event, false)" maxlength="8">
						원</td>
				</tr>

				<tr id="show_bank_info_5">
					<th><label for="su_dateY">입금일</label> <span class="bltS_A">*</span></th>
					<td><input type="hidden" name="cheat_date" value=""
						class="thecheat_input" /> <select name="cheat_date_temp_1"
						id="su_dateY" title="연도선택" style="width: 80px;"
						onchange="javascript:if(1){cheat_date.value = cheat_date_temp_1[cheat_date_temp_1.selectedIndex].value + cheat_date_temp_2.value + cheat_date_temp_3.value }">
							<option selected value=0>년도 선택</option>
							<option value="2015" selected>2015
							<option value="2014">2014
					</select> <select name="cheat_date_temp_2" id="su_dateM" title="월 선택"
						style="width: 80px;"
						onchange="javascript:if(1){cheat_date.value = cheat_date_temp_1.value + cheat_date_temp_2[cheat_date_temp_2.selectedIndex].value + cheat_date_temp_3.value }">
							<option selected value=0>월 선택</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11" selected>11</option>
							<option value="12">12</option>
					</select> <select name="cheat_date_temp_3" id="su_dateD" title="일 선택"
						style="width: 80px;"
						onchange="javascript:if(1){cheat_date.value = cheat_date_temp_1.value + cheat_date_temp_2.value + cheat_date_temp_3[cheat_date_temp_3.selectedIndex].value }">
							<option selected value=0>일 선택</option>
							<option value="01">01</option>
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
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26" selected>26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
					</select></td>
				</tr>

				<tr>
					<th>연락처 정보 <span class="bltS_A">*</span></th>
					<td><input type="checkbox" name="cheat_phone_none" class="chk"
						id="su_telchk"
						onclick="javascript:if($('#show_tel_info').css('display')!='none'){document.writeForm.cheat_phone1.value='010';cheat_phone2.value='0000';cheat_phone3.value='0000';cheat_phone.value='01000000000';$('#show_tel_info').css({display: 'none'}); }else { $('#show_tel_info').css({display: ''}); }" /><label
						for="su_telchk">연락처를 모르는 경우에만 체크하세요.</label></td>
				</tr>

				<tr id="show_tel_info">
					<th><label for="su_tel">연락처</label> <span class="bltS_A">*</span></th>
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
					<th>성별</th>
					<td><span class="chkArea"><input type="radio"
							name="cheat_sex" value="1" checked id="su_sexM" class="rdo"><label
							for="su_sexM">남자</label></span> <span class="chkArea"><input
							type="radio" name="cheat_sex" value="2" id="su_sexW" class="rdo"><label
							for="su_sexW">여자</label></span></td>
				</tr>

				<tr>
					<th><label for="su_keynote">용의자 특징</label></th>
					<td><input type="text" class="txt" name="cheat_character"
						value="" placeholder="예) 경상도 사투리 사용" id="su_keynote"
						style="width: 580px;" maxlength="60"></td>
				</tr>

			</table>
		</div>


		<div class="formSection">
			<div class="well" align="center">
				<h3 align="center">용의자(사기범) 정보</h3>
				<p class="infoTxtTop" align="center">
					<span class="bltS_A">*</span> 는 필수 기재 항목입니다

				</p>
				<table class="formTypeA" border="1px">
					<colgroup>
						<col width="150">
						<col width="*">
					</colgroup>


					<tr>
						<th><label for="vi_pw">피해사례 비밀번호</label> <span class="bltS_A">*</span></th>
						<td><input type="password" class="txt" name="pw" id="vi_pw"
							value="" style="width: 230px;" maxlength="20"
							placeholder="비밀번호를 입력하세요."></td>
					</tr>

					<tr>
						<th><label for="vi_name">성명</label> <span class="bltS_A">*</span></th>
						<td><input type="text" class="txt" name="name" value=""
							id="vi_name" style="width: 230px;" placeholder="귀하의 성명을 입력하세요."></td>
					</tr>

					<tr>
						<th><label for="vi_tel">연락처</label> <span class="bltS_A">*</span></th>
						<td><select name="member_phone1" id="vi_tel" title="국번선택"
							style="width: 80px;"
							onchange="javascript:if(1){member_phone.value = member_phone1[member_phone1.selectedIndex].value + member_phone2.value + member_phone3.value }">
								<option selected value=0>선택</option>
								<option value="010">010
								<option value="011">011
								<option value="016">016
								<option value="017">017
								<option value="018">018
								<option value="019">019
								<option value="070">070
						</select> - <input type="text" name="member_phone2" class="txt" value=""
							maxlength="4" title="가운데 자리 입력"
							onchange="javascript:if(1){member_phone.value = member_phone1[member_phone1.selectedIndex].value + member_phone2.value + member_phone3.value }"
							style="ime-mode: disabled; width: 70px;"
							onKeyPress="return numbersonly(event, false)" /> - <input
							type="text" name="member_phone3" class="txt" value=""
							maxlength="4" class="input80"
							onchange="javascript:if(1){member_phone.value = member_phone1[member_phone1.selectedIndex].value + member_phone2.value + member_phone3.value }"
							style="ime-mode: disabled; width: 70px;" title="끝 자리 입력"
							onKeyPress="return numbersonly(event, false)" /> <input
							type="hidden" name="member_phone" id="member_phone" value=""
							readonly /> <!--<iframe src="#" name="target_iframe" width="0" height="0" frameborder="0" marginwidth="0", marginheight="0" scrolling="no"></iframe>-->
						</td>
					</tr>


					<tr>
						<th><label for="vi_email">이메일</label> <span class="bltS_A">*</span></th>
						<td><input type="text" class="txt" name="member_email"
							id="vi_email" value="" style="width: 230px;"
							placeholder="귀하의 이메일 주소를 입력하세요."></td>
					</tr>
				</table>

			</div>
			<h3>해사례 등록 약관</h3>
			<textarea rows="" cols="" readonly="readonly"
				style="margin: 0px; width: 900px; height: 300px;">
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
			<div class="container">
				<input type="submit" class="btn btn-info btn-block"
					value="피해사례 등록 약관에 동의하며, 피해사례를 등록합니다.">
			</div>
			<!--<input type="submit" value="" name="" onclick="return confirm('확인 버튼을 누른 후 피해사례가 등록될 때 까지 기다려 주세요.');"/>-->
		</div>
	</div>
</div>
</div>
