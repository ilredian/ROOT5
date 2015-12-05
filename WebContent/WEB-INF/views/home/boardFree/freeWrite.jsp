<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="<%=ctx %>/navereditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>

<script type="text/javascript">
var editor_object = [];

$(function() {
	//전역변수선언
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : editor_object,
		elPlaceHolder : "editor",
		sSkinURI : "/ROOT/navereditor/SmartEditor2Skin.html",
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,
			fOnBeforeUnload : function() {
			}
		},
		fCreator : "createSEditor2"
	});

	//전송버튼 클릭이벤트
	$("#savebutton").click(function() {
		//id가 smarteditor인 textarea에 에디터에서 대입
		editor_object.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
		// 이부분에 에디터 validation 검증
		//폼 submit
		$("#frm").submit();
	});
});

</script>

<form id="frm" method="post" action="freeMain.go">
	<div class="container freeMain">
		<h2>자유게시판</h2>
		<table class="table">
			<tr>
				<th class="active">제목</th>
				<td><input type="text" name="title" style="WIDTH: 100%;"></td>
			</tr>
			<tr>
				<th class="active">작성자</th>
				<td>
					<c:out value="${memberInfo.name}"/>
					<input type="hidden" name="name" value="${memberInfo.name}">
					<input type="hidden" name="memberno" value="${memberInfo.memberno}">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="editor" style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30" name="content">
					</textarea>
				</td>
			</tr>
			<tr>
				<th class="active">고운 언어가 좋아요!</th>
				<td>
				"말이 입힌 상처는 칼이 입힌 상처보다 깊다." - 모로코 속담<br>
				<c:out value="${memberInfo.name}"/>님의 글은 <c:out value="${memberInfo.name}"/>님의 품격을 보여주는 <c:out value="${memberInfo.name}"/>님의 얼굴입니다.
				글에 <c:out value="${memberInfo.name}"/>님의 성숙함을 담아주세요.
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