$('#noticeWrite').click(function(){
	location.replace("noticeWrite.go");
});

$('#select').change(function(){
	location.replace('noticeMain.go?pg=1&ps='+$('#select').val());
});