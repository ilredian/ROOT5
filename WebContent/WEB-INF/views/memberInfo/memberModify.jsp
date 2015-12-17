<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function submitOk(){
	var r = confirm("정말로 바꾸시겠습니까?");
	if (r == true) {
		
	} else {
		return false;
	}
}

$(document).ready(function() {
	$("#upload").bind("click", function() {
		$("form").ajaxSubmit({
//			dataType : "json",
			url : "fileUploadAjax.go",
			success : function(data) {
				$("#filename").val(data.file);
				alert('파일이 첨부되었습니다.');
			},
			error : function(error) {
				alert("요청 처리 중 오류가 발생하였습니다.");
			}
		});
		return false;
	});
});

</script>
<div class="container">
	<form id="frm2" method="post" action="" onsubmit="return submitOk();">
		<h3>정보수정</h3>
		등록된 사진과 인사말은 더치트 활동 시 회원님을 대표하며, 타인에게 보여지므로 신중히 설정해 주세요.
		<hr>
		<table>
			<tr>
				<td><img src="${photo}" width="108px" height="128px"
					align="left"></td>
				<th class="active">사진첨부</th>
				<td><input type="file" name="file" id="uploadFile" value="" /><input
					id="upload" class="btn btn-default btn-sm" type="button"
					name="filebtn" value="첨부하기"> <input type="hidden"
					id="filename" name="photo" value="${photo}"></td>
				
				<td class="key">메세지<span>*</span><br>
				</td>
				<c:set var="n" value="${message}" />
				<td style="padding-left: 20px">인사말 : ${n} <br> <input
					type="text" name="message" size="25" placeholder="바꿀 메세지를 쓰세요">

					<span class="hmsg" id="hLayeremail"></span>
					<div>메세지는 상태글과 같은 역할을 합니다.</div>
					<br>
				<br>
					<div class="remail">
						<input type="checkbox" name="remail" value="1" checked="checked" />
						정보/광고 수신 동의(검거소식, 피해자 알림 등의 수신을 위해 동의해 주십시오.)
					</div>
					<div class="remail">
						<input type="checkbox" name="smail" value="1" /> 댓글 알림 수신 거부(댓글
						입력한 글에 다른 사람이 댓글을 남겨도 알림을 받지 않습니다.)
					</div>
					<br>
				<br></td>
			</tr>
		</table>

		<div class="container" align="">
			<input class="btn btn-primary" id="savebutton2" type="submit"
				value="정보수정">
		</div>
	</form>
</div>

