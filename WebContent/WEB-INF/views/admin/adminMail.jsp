<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	$(document).ready(function() {
		$("#upload").bind("click", function() {
			$("form").ajaxSubmit({
//				dataType : "json",
				url : "fileUploadAjax.go",
				success : function(data) {
					$("#filename").val(data.fullfile);
					alert('������ ÷�εǾ����ϴ�.');
				},
				error : function(error) {
					alert("��û ó�� �� ������ �߻��Ͽ����ϴ�.");
				}
			});
			return false;
		});
	});
</script>
<form action="" method="post" enctype="multipart/form-data">
	<div style="float: left;">
		<h3>���� ������</h3>
	</div>
	<div class="btn-group" style="float: right;">
		<input type="submit" class="btn btn-primary" value="������">
	</div>
		<table class="table">
			<tr>
				<th class="active">�޴»��</th>
				<td><input type="text" name="to"
					placeholder="�̸����� �Է��� �ּ���"><br></td>
			</tr>
			<tr>
				<th class="active">����</th>
				<td><input type="text" name="title"><br></td>
			</tr>
			
			<tr>
				<th class="active">����÷��</th>
				<td>
					<input type="file" name="file" id="uploadFile" value="" />
					<input id="upload" class="btn btn-default btn-sm" type="button" name="filebtn" value="÷���ϱ�">
					<input type="hidden" id="filename" name="filename" value="">
				</td>
			</tr>
			<tr>
				<th class="active">����</th>
				<td><textarea rows="10" cols="10" name="content"
						style="margin: 0px; width: 500px; height: 300px;"></textarea></td>
			</tr>
	
		</table>
</form>