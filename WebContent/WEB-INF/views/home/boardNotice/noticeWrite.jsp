<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form class="form-horizontal" name="writeForm" id="frm" action=""
	method="post" onsubmit="return CheckForm();">
	<div class="container noticeWrite">
<script type="text/javascript">
function CheckForm() {
	if (!$("#title").val()) {
		alert("제목을 입력해주세요.");
		return false;
	}
 	if ($()=="") {
		alert("본문을 입력해주세요.");
		return false;
	} 
}
</script>	
		<h2>공지게시판</h2>
		<table class="table">
			<tr>
				<th class="active">제목</th>
				<td><input type="text" id="title" name="title" style="WIDTH: 100%;"></td>
			</tr>
			<tr>
				<th class="active"></th>
				<td>강조 : <input type="checkbox" id="check" onclick="chk()"><input type="hidden" id="critical" name="critical" value="0"></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="editor" style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30" name="content">
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="text-align: center;">
						<input class="btn btn-primary" id="savebutton" type="button" value="저장"> 
						<input class="btn btn-default" onclick="history.go(-1);" type="button" value="취소">
					</div>
				</td>
			</tr>
		</table>
	</div>
</form>