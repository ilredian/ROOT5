<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form class="form-horizontal" name="writeForm" id="frm" action="" onsubmit="return CheckForm();"
	method="post" enctype="multipart/form-data">
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
					<td><input type="text" name="title" id="title"
						placeholder="사죄문과 함께 삭제요청 드립니다." style="width: 600px"></td>
				</tr>
				<tr>
					<th class="active">고운 언어가 좋아요!</th>
					<td>"말이 입힌 상처는 칼이 입힌 상처보다 깊다." - 모로코 속담<br> 귀하의 글은 귀하의
						품격을 보여주는 귀하의 얼굴입니다. 글에 귀하의 성숙함을 담아주세요.
					</td>
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
							cols=60 rows=10 style="width: 900px; height: 300px;" readonly="readonly">
1. 수집하는 개인정보 항목

안치트는 회원가입, 원활한 회원 문의, 각종 서비스 등 기본적인 서비스 제공을 위한 필수정보와 회원 맞춤 서비스 제공을 위한 선택정보로 구분하여 아래와 같은 개인정보를 수집하고 있습니다.

1) 수집항목

<일반 회원가입 시>

필수항목 : 성명, 비밀번호, 휴대 전화번호, 이메일 주소

선택사항 : 실명인증값

<유료 서비스 이용 시>

휴대폰 정보, 은행계좌 정보 등의 정보가 수집될 수 있습니다.

선택정보를 입력하지 않은 경우에도 서비스 이용 제한은 없으며 이용자의 기본적 인권 침해의 우려가 있는 민감한 개인 정보(인종, 사상 및 신조, 정치적 성향 이나 범죄기록, 의료정보 등)는 수집하지 않습니다.

단, 서비스 이용과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.

IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록

2) 개인정보 수집방법

안치트는 다음과 같은 방법으로 개인정보를 수집할 수 있습니다.

- 홈페이지, 서면양식, 전화, 이메일

- 생성정보 수집 툴을 통한 수집



2. 개인정보의 수집 및 이용목적

안치트는 수집한 개인정보를 다음의 목적을 위해 활용합니다. 이용자가 제공한 모든 정보는 하기 목적에 필요한 용도 이외로는 사용되지 않으며 이용 목적이 변경될 시에는 사전 동의를 구할 것입니다.

1) 회원 관리

회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가 사용 방지, 가입 의사 확인, 불만처리 등 민원처리



3. 개인정보의 보유 및 이용기간

안치트는 회원가입일로부터 서비스를 제공하는 기간 동안에 한하여 이용자의 개인정보를 보유 및 이용하게 됩니다.

회원 탈퇴를 요청하거나 개인정보의 수집 및 이용에 대한 동의를 철회하는 경우, 수집 및 이용목적이 달성되거나 보유 및 이용기간이 종료한 경우 해당 개인정보를 지체 없이 파기합니다.

단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.

- 부정이용기록

보존 이유 : 부정 이용 방지

보존 기간 : 1년

상법, 전자상거래 등에서의 소비자보호에 관한 법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 안치트는 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 이 경우 안치트는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.

- 계약 또는 청약철회 등에 관한 기록

보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률

보존 기간 : 3년

- 대금결제 및 재화 등의 공급에 관한 기록

보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률

보존 기간 : 3년

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
			<a><input type="submit" class="btn btn-info" value="등록"></a>
			<a href="contacted.go"><input type="button"
				class="btn btn-danger" value="취소"></a>
		</div>
	</form>
	
<script type="text/javascript">

	function CheckForm() {
		
			//피해 발생 사이트 정보
				//약관 동의 체크박스__
			if ($("#chk1")[0].checked == false
					|| $("#chk2")[0].checked == false) {
				alert('약관에 동의하십시오.');
				return false;
			} 
			
			if (!$("#title").val()) {
				alert("제목을 입력해주세요");
				return false;
			}
	}
	</script>		