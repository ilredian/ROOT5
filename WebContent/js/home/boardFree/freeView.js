$('#edit').click(function() {
	var pg = $('#pageNo').val();
	var bno = $('#boardNo').val();
	location.replace("freeEdit.go?pg=" + pg + "&bno=" + bno);
});

$('#delete').click(function() {
	var bno = $('#boardNo').val();
	location.replace("freeDelete.go?bno=" + bno);
});

$('#list').click(function() {
	var pg = $('#pageNo').val();
	location.replace("freeMain.go?pg=" + pg);
});

$('#freeWrite').click(function(){
	location.replace("freeWrite.go");
});