
$('#edit').click(function() {
	var pg = $('#pageNo').val();
	var bno = $('#boardNo').val();
	location.replace("lawEdit.go?pg=" + pg + "&bno=" + bno);
});

$('#delete').click(function() {
	var bno = $('#boardNo').val();
	alert('삭제되었습니다.');
	location.replace("lawDelete.go?bno=" + bno);
});

$('#list').click(function() {
	var pg = $('#pageNo').val();
	location.replace("lawMain.go?pg=" + pg);
});

$('#lawWrite').click(function(){
	location.replace("lawWrite.go");
});
