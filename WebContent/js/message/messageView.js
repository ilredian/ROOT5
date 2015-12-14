$('#messageMain').click(function(){
	location.replace('messageWindow.go');
});

$('#messageReply').click(function(){
	var msno = $('#messageNo').val();
	location.replace('messageReply.go?msno='+msno);
});