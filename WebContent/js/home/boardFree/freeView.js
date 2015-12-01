$('#edit').click(function() {
	console.log("수정페이지로이동")
	location.replace("/freeEdit.go");
});

$('#delete').click(function() {
	console.log("삭제")
	location.replace("/freeDelete.go");
});

$('#list').click(function() {
	console.log("전페이지로 이동")
	history.go(-1);
});

$('#freeWrite').click(function(){
	location.replace("/freeWrite.go");
});