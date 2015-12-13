$('#noticeWrite').click(function(){
	location.replace("noticeWrite.go");
});

$('#select').change(function(){
	location.replace('noticeMain.go?ps='+$('#select').val());
});