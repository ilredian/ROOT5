<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form class="form-horizontal" name="writeForm" id="frm" action=""
	method="post" onsubmit="return CheckForm();">
	<div class="container">
		<h3>게임, 비실물 피해사례 등록</h3>
		<p>피해사례 등록이 완료되면 피해자를 위한 기능이 실시간 제공됩니다.</p>
		<br>

		<div style="float: left;">
			<h3>피해 발생 사이트 정보</h3>
		</div>
		<div style="float: right;">
			<h6>
				<code>*</code>
				는 필수 기재 항목입니다
			</h6>
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
								<td align="center" style="width: 15%;"><il class="oneLine">
									<input type="radio" name="cheat_item_temp" class="rdo"
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
								<c:if test="${a.index == 2}">
									<td style="width: 15%;"></td>
									<td style="width: 15%;"></td>
									<td style="width: 15%;"></td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>

				<tr>
					<th class="active" style="width: 150;"><label for="s_id">물품명</label>
						<code>*</code></th>
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

		<div style="float: left;">
			<h3>용의자(사기범) 정보</h3>
		</div>
		<div style="float: right;">
			<h6>
				<code>*</code>
				는 필수 기재 항목입니다
			</h6>
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
						style="ime-mode:disabled;" onKeyPress="InpuOnlyNumber(this)"
						maxlength="30" placeholder="계좌번호를 입력하세요."></td>
				</tr>

				<tr id="show_bank_info_4">
					<th class="active" style="width: 150;"><label for="su_sum">입금
							금액</label> <code>*</code></th>
					<td><input type="text" class="txt" name="cheat_price" value=""
						placeholder="피해금액을 원 단위로 입력하세요." id="su_sum"
						style="width: 230px; ime-mode: disabled;"
						onKeyPress="InpuOnlyNumber(this)" maxlength="8"> 원</td>
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
						style="ime-mode: disabled;" onKeyPress="InpuOnlyNumber(this)"
						style="width:70px;" title="가운데 자리 입력" />-<input type="text"
						name="cheat_phone3" value="" class="txt" maxlength="4"
						onchange="javascript:if(1){cheat_phone.value = cheat_phone1[cheat_phone1.selectedIndex].value + cheat_phone2.value + cheat_phone3.value }"
						style="ime-mode: disabled;" onKeyPress="InpuOnlyNumber(this)"
						style="width:70px;" title="끝 자리 입력" />&nbsp;<input type="hidden"
						name="cheat_phone" value="" readonly /> ※ 050 임시번호 등록을 금합니다.</td>
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

		<div style="float: left;">
			<h3>사건 개요 (진술서)</h3>
		</div>
		<div style="float: right;">
			<h6>
				<code>*</code>
				는 필수 기재 항목입니다
			</h6>
		</div>


		<div class="container" align="center">
			<table class="table">
				<tr>
					<th class="active" style="width: 13%;">사건 발생 개요 <code>*</code></th>
					<td>1.물품을 받은 경우에는 물품 사진을 첨부하세요. <br> 2.용의자의 주민등록번호와 사진은
						절대 등록하시면 안됩니다.(피해사례 등록자가 법적 처벌을 받을 수 있습니다.) <textarea id="editor"
							style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30"
							name="content"></textarea>
					</td>
				</tr>

				<br>
				<tr>
					<th class="active">약관 동의</th>
					<td>
						<div>
							<textarea cols=60 rows=10 style="width: 900px; height: 300px;">
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
						</div>
						<div class="alert_cheat_write alert_cheat_write-info">
							<b><input type="checkbox" name="cheat_rule_c" value="1"
								id="cheat_rule_c" valign="bottom"
								onclick="return cheat_rule_check(cheat_rule.value)" /> <label
								for="cheat_rule_c">피해사례 등록 약관에 동의합니다.(필수)</label></b> <input
								type="hidden" readonly name="cheat_rule" value=""
								valign="bottom" style="width: 0px; height: 0px;">
						</div>

						<div class="alert_cheat_write alert_cheat_write-info">
							<b><input type="checkbox" name="agreement_1" value="1"
								id="chk_agreement_1"
								onclick="return value_change('agreement_1h',this.value)" /> <label
								for="chk_agreement_1">서비스 이용약관에 동의합니다.(필수)</label></b>
						</div>
						<div class="alert_cheat_write alert_cheat_write-info">
							<b><input type="checkbox" name="agreement_2" value="1"
								id="chk_agreement_2"
								onclick="return value_change('agreement_2h',this.value)" /> <label
								for="chk_agreement_2">개인정보 수집 및 이용에 동의합니다.(필수)</label></b>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" id="agreement_1h" value="0" name="agreement_1h"
				style="width: 0px; height: 0px;"> <input type="hidden"
				id="agreement_2h" value="0" name="agreement_2h"
				style="width: 0px; height: 0px;">
			<div>
				<br>
			</div>
			<div class="container">
				<input type="button" class="btn btn-info btn-block"
					id="savebutton" value="피해사례 등록 약관에 동의하며, 피해사례를 등록합니다.">
			</div>

		</div>


		<script type="text/javascript">
			function CheckForm() {
				//피해 발생 사이트 정보
				if (!$("#cheat_site").val()) {
					alert("피해가 발생한 사이트를 선택해주세요.");
					return false;
				}
				if (!$('input:radio[name=cheat_item_temp]:checked').val()) { // 물품종류 입력 검사
					alert('물품종류 선택해주세요');
					return false;

				}
				if (!$("#subject").val()) { // 물품명 입력 검사
					alert('물품명을 입력해주세요');
					return false;
					///계좌정보 부분	
				}

				if ($("#su_bankNumchk")[0].checked == false) { // 계좌정보 검사 (체크박스)

					////은행체크_### -- 셀렉트 문에서 제외한 나머지를 선택할때만
					if (!$("select[name=cheat_bank]").val()) { //계좌은행명 검사
						alert('은행명을 입력해주세요');
						return false;
					}
					if (!$("#su_name").val()) { //명의자명 입력
						alert('명의자명을 입력해주세요');
						return false;
					}
					if (!$("#su_bankNum").val()) { //계좌 번호 입력
						alert('계좌번호를 입력해주세요');
						return false;
					}
					if (!$("#su_sum").val()) { //금액 입력
						alert('피해금액을 입력해주세요');
						return false;
					}
				}

				if ($("#su_telchk")[0].checked == false) { // 연락처정보
					if (!$("#phone2").val() || !$("#phone3").val()) {
						alert('연락처를 기입하세요');
					}
					return false;
				}

				//	if(!$("#se2_iframe").val()){  // 진술서 작성 확인
				//	if(!$(".se2_input_wysiwyg").val()){  // 진술서 작성 확인
				// .se2_input_area husky_seditor_editing_area_container	

				//var area = document.getElementById("se2_iframe").innerHTML
				//var area = document.getElementsByClassName("se2_input_area husky_seditor_editing_area_container").innerHTML
				//var area = document.getElementsByClassName("se2_inputarea").value;
				var area = document.getElementById("ir1");
				//스마트에디터 --- textarea를 선택하지 못하고 있음...왜지...?!
				if ($(area).val() == "") { // 진술서 작성 확인
					alert('진술서를 작성해주세요.');
					return false;
				}
				//약관 동의 체크박스__
				if ($("#cheat_rule_c")[0].checked == false
						|| $("#chk_agreement_1")[0].checked == false
						|| $("#chk_agreement_2")[0].checked == false) {
					alert('약관에 동의하십시오.');
					return false;
				}
			}

			function InpuOnlyNumber(obj) {
				if (event.keyCode >= 48 && event.keyCode <= 57) { //숫자키만 입력
					return true;
				} else {
					event.returnValue = false;
				}
			}
		</script>

	</div>
</form>