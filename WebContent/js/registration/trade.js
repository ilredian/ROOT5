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

$("#cheat_site_temp").change(function() {
	if ($(this).val() == 1) {
		$("#cheat_site").removeClass('input_hidden');
		$("#cheat_site").addClass('input_bold');
	} else {
		$("#cheat_site").removeClass('input_bold');
		$("#cheat_site").addClass('input_hidden');
	}
});
$("#cheat_item_temp").change(function() {
	if ($(this).val() == 1) {
		$("#cheat_item").removeClass('input_hidden');
		$("#cheat_item").addClass('input_bold');
	} else {
		$("#cheat_item").removeClass('input_bold');
		$("#cheat_item").addClass('input_hidden');
	}
});
function label_rdo_click(id_name) {
	$('#' + id_name).prop('checked', true);
	var temp_item_name = $('#' + id_name).val();
	$('#cheat_item').val(temp_item_name);
}
function srvTime() { // 서버의 시간을 가져온다.
	var xmlHttp;
	if (window.XMLHttpRequest) {//분기하지 않으면 IE에서만 작동된다.
		xmlHttp = new XMLHttpRequest(); // IE 7.0 이상, 크롬, 파이어폭스 등
		xmlHttp.open('HEAD', window.location.href.toString(), false);
		xmlHttp.setRequestHeader("Content-Type", "text/html");
		xmlHttp.send('');
		return xmlHttp.getResponseHeader("Date");
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
		xmlHttp.open('HEAD', window.location.href.toString(), false);
		xmlHttp.setRequestHeader("Content-Type", "text/html");
		xmlHttp.send('');
		return xmlHttp.getResponseHeader("Date");
	}
}
function v2014_handphone_check(form_name) {
	var key_handphone = $(form_name).val();
	if (!key_handphone) {
		alert('연락처를 입력해 주세요.');
		return false;
	}
	var st = srvTime(); // 서버읜 현재 시간 가져오기
	var dt = new Date(st);
	if (!dt)
		dt = new Date();
	var yyyy = dt.getFullYear().toString();
	var mm = (dt.getMonth() + 1).toString(); // getMonth() is zero-based
	var dd = dt.getDate().toString();
	var hh = dt.getHours().toString();
	var ii = dt.getMinutes().toString();
	var time = yyyy + (mm[1] ? mm : "0" + mm[0]) + (dd[1] ? dd : "0" + dd[0])
			+ (hh[1] ? hh : "0" + hh[0]) + (ii[1] ? ii : "0" + ii[0]);
	// https://developer.mozilla.org/en-US/docs/Web/API/WindowBase64/btoa
	window.open('./?mod=sms_cheat&phone='
			+ window.btoa(unescape(encodeURIComponent(key_handphone))) + '&t='
			+ window.btoa(unescape(encodeURIComponent(time))), 'temp');
}
function value_change(id_name, value) {
	var input = document.getElementById(id_name);
	input.value = value;
	//input.onchange();
}