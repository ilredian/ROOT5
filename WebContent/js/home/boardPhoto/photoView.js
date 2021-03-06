$('#edit').click(function() {
	var pg = $('#pageNo').val();
	var bno = $('#boardNo').val();
	location.replace("photoEdit.go?pg=" + pg + "&bno=" + bno);
});

$('#delete').click(function() {
	var bno = $('#boardNo').val();
	var content = '정말로 삭제하시겠습니까?';
	if(confirm(content)){
		location.replace("photoDelete.go?bno=" + bno);
	}
});

$('#list').click(function() {
	var pg = $('#pageNo').val();
	location.replace("PhotoMain.go?pg=" + pg);
});

$('.updateReply').click(function(data){
	$('.updateReply').hide();
	$('.updateReplyActive').hide();
	
	console.log(data);
	var path1 = data.target.origin;
	var path2 = data.target.pathname;
	var path3 = data.target.search;
	var cancel = path1 + path2 + path3;
	var id = data.target.id;
	var hide = data.target.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[3].childNodes[1];
	var $div = $(data.target.parentNode);

	$div.empty();
	$div.append("<a style='color:red;' href='"+cancel+"'>수정 취소</a>");

	var $hide = $(hide);

	var text = $hide.html();
	$hide.empty();
	$hide.append("<form action='"+id+"' method='post'><textarea rows='5' cols='1' style='width:100%;' type='text' name='content'>"+text+"</textarea><div style='float:right; margin:5px'><input type='submit' class='btn btn-primary' value='댓글 수정'></div></form>");
});

$('#reportboard').click(function(){
	var pg = $('#pageNo').val();
	var bno = $('#boardNo').val();
	if(confirm('정말 신고하시겠습니까?')){
		location.replace("reportboardfree.go?pg="+pg+"&bno="+bno+"&cno=3");
	}
	return false;
});

$('.reportreply').click(function(){
	var id = $('.reportreply').attr('id');
	if(confirm('정말 신고하시겠습니까?')){
		location.replace(id);
	}
	return false;
});