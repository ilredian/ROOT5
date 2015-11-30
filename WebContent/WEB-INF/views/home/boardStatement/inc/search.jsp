<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
	<ul class="nav nav-tabs">
		<li role="presentation"><a href="statementMain.go?cno=1">직거래</a></li>
		<li role="presentation" class="active"><a href="statementMain.go?cno=2">게임.비실물</a></li>
		<li role="presentation"><a href="statementMain.go?cno=3">스팸</a></li>
		<li role="presentation"><a href="statementMain.go?cno=4">비매너</a></li>
	</ul>
</div>
<div class="container">
	<div class="well well-sm" align="center">
		<form>
			<div class="form-group">
				<h2>직거래 피해 사례</h2> <input type="text"
					name="keyword" class="txt" title="검색어 입력"
					placeholder="로그인 후 상세 검색을 사용하세요." size="30" onclick="#">
				<!-- onclick 에 로그인 창띄우는 거 -->
				<button type="button" class="btn btn-default">
					<a href="#">검색</a>
				</button>
			</div>
		</form>
		<div>
			<select>
				<option>최근 3개월간</option>
				<option>최근 6개월간</option>
				<option>최근 9개월간</option>
			</select> <span class="chkArea"> <input type="radio" name="where"
				value="cheat_site|cheat_item|cheat_suspect|cheat_id|cheat_phone|cheat_account"
				class="rdo" id="sRdo_all" checked><label for="sRdo_all">전체(개요
					제외)</label></span> <span class="chkArea"><input type="radio" name="where"
				value="cheat_phone" class="rdo" id="sRdo_1"><label
				for="sRdo_1">연락처</label></span> <span class="chkArea"><input
				type="radio" name="where" value="cheat_account" class="rdo"
				id="sRdo_1"><label for="sRdo_2">계좌번호</label></span> <span
				class="chkArea"><input type="radio" name="where"
				value="cheat_suspect" class="rdo" id="sRdo_1"><label
				for="sRdo_3">판매자명</label></span> <span class="chkArea"><input
				type="radio" name="where" value="cheat_id" class="rdo" id="sRdo_1"><label
				for="sRdo_3">아이디</label></span> <span class="chkArea"><input
				type="radio" name="where" value="content" class="rdo" id="sRdo_1"><label
				for="sRdo_4">사건개요</label></span>
		</div>
	</div>
	<div class="well well-sm">
		<div class="boxTypeC"
			style="color: #808080; font-weight: bold; text-align: center; margin: 0 0 10px 0; padding: 14px;">
			이히히는 피해사례의 사실 확인을 위해 전화통화, 계좌번호 검증을 진행하고 있습니다.</div>

	</div>
</div>