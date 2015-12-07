$('.delete').click(function() {
	if (confirm('선택하신 항목을 삭제하시겠습니까?')) {
		location.replace("'" + $(this).attr("id") + "'");
	}
});

function setPreviewBox(e) {
	var e = e || window.event;
	document.getElementById('preview').style.left = e.clientX
			+ document.body.scrollLeft + 20 + 'px'; // 마우스 
	document.getElementById('preview').style.top = e.clientY
			+ document.body.scrollTop + 'px'; // 포인트에 위치 
}

function showPreview(content) {
	var text;
	text = '<table cellpadding="5" bgcolor="#ffffff" style="font-size:9pt;color:#005F8B;filter:alpha(opacity=90); border-width:1; border-color:#3291BD; border-style:solid;">';
	text += '<tr><td>' + content + '</td></tr></table>';
	document.getElementById('preview').innerHTML = text;
	document.getElementById('preview').style.visibility = 'visible';
}

function hidePreview() {
	document.getElementById('preview').innerHTML = '';
	document.getElementById('preview').style.visibility = 'hidden';
}