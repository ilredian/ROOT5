<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form id="frm1" method="post" action="">
<div class="container">
	<h3>정보수정</h3>

	<!-- (*) 표시가 있는 항목은 반드시 입력해야 합니다.<br> 허위로 작성된 정보일 경우 승인이 보류되거나 임의로
	삭제처리될 수 있으니 주의해 주세요. -->
	<hr>
	<table>

		<tr>

			<td class="key">메세지<span>*</span><br>
			</td>
			<td style="padding-left: 20px"><input type="text" name="email"
				value="${memberDTO.message}" size="35" class="input"
				onblur="sameCheck(this,'hLayeremail');" /> <span class="hmsg"
				id="hLayeremail"></span>
				<div>메세지는 상태글과 같은 역할을 합니다.</div>
				<div class="remail">
					<input type="checkbox" name="remail" value="1" checked="checked" />
					정보/광고 수신 동의(검거소식, 피해자 알림 등의 수신을 위해 동의해 주십시오.)
				</div>
				<div class="remail">
					<input type="checkbox" name="smail" value="1" /> 댓글 알림 수신 거부(댓글
					입력한 글에 다른 사람이 댓글을 남겨도 알림을 받지 않습니다.)
				</div></td>
		</tr>
	</table>

	<!-- 페이저 -->
	<div class="container" align="">
			<input class="btn btn-primary" id="savebutton1" type="button" value="정보수정"> 
	</div>
</div>

</form>