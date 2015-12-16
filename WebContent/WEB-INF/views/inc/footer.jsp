<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<div class="footer2">
		<hr>
		<div>
			<div>
				<img src="images/footerimages/logo.PNG" style="float: left;" width="167px" height="67px">
				<p style="float: left; color: silver;" align="left">피해사례 게시물 내용에 대해 안치트는 보증하지 않으며, 게시물의 법적 책임은 피해사례 등록자에게 있습니다.<br>

					Copyrights ⓒ AhnCheat All Rights Reserved.</p>
				<p>관리자 이메일 : ilredian@naver.com
					관리자 전화번호 : 010-0000-0000</p>

						<table style="margin-top:20px; background:; text-align: right" >
					<tr><th style="color:red /* #D83939; */">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					오늘 방문자수:&nbsp;&nbsp;&nbsp;&nbsp;
					
			</th><td style="color:orange" id="todayCount"></td></tr>
					<tr><th style="color: #D83939;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					누적 방문자수:&nbsp;&nbsp;&nbsp;&nbsp;</th><td  style="color:orange" id="totalCount"></td></tr>
					
				</table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="pagePath" value="${pageContext.request.requestURI}">
<c:if test="${not empty memberInfo}">
	<input type="hidden" id="memberInfo" value="${memberInfo}">
</c:if>
<script type="text/javascript">

	//로그인 안하면 메인으로 보내기
	$(function(){
		var path = $('#pagePath').val();
		var login = $('#memberInfo').val();
		var go = "notIndex";
		if(path == "/ROOT/WEB-INF/views/inc/layout.jsp"){
			go = "Index"
		}else if(path == "/ROOT/WEB-INF/views/join/inc/layout.jsp"){
			go = "Index"
		}else if(login != null){
			go = "Index"
		}
		$.ajax({
			url:"sessionCheckAjax.go",
			type:"POST",
			data : {
				"location" : go
			},
			success:function(data){
				if(data.result == "success"){
					alert('회원만 이용할 수 있습니다.');
					location.replace('login.go');
				}
			}
		})
	});
	
	//방문자수 확인
	$.ajax({
		url : "visitCountAjax.go",
		type : "POST",
		success:function(data){
			$('#todayCount').html(data.todayCount);
			$('#totalCount').html(data.totalCount);
		}
	});
</script>