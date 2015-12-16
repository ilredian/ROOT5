<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	$(document).ready(function() {
		$("#upload").bind("click", function() {
			$("form").ajaxSubmit({
//				dataType : "json",
				url : "fileUploadAjax.go",
				success : function(data) {
					//alert(data.result);
					$("#filename").val(data.file);
				},
				error : function(error) {
					alert("요청 처리 중 오류가 발생하였습니다.");
				}
			});
			return false;
		});
	});
</script>
<form action="" method="post" enctype="multipart/form-data">
	<div style="float: left;">
		<h3>메일 보내기</h3>
	</div>
	<div class="btn-group" style="float: right;">
		<input type="submit" class="btn btn-primary" value="보내기">
	</div>
		<table class="table">
			<tr>
				<th class="active">받는사람</th>
				<td><input type="text" name="to"
					placeholder="이메일을 입력해 주세요"><br></td>
			</tr>
			<tr>
				<th class="active">제목</th>
				<td><input type="text" name="title"><br></td>
			</tr>
			
			<tr>
				<th class="active">파일첨부</th>
				<td>
					<input type="file" name="file" id="uploadFile" value="" /><input id="upload" class="btn btn-default btn-sm" type="button" name="filebtn" value="첨부하기">
					<input type="hidden" id="filename" name="filename" value="">
				</td>
			</tr>
			<tr>
				<th class="active">내용</th>
				<td><textarea rows="10" cols="10" name="content"
						style="margin: 0px; width: 500px; height: 300px;"></textarea></td>
			</tr>
	
		</table>
</form>