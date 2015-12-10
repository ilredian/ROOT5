$('.update').click(function(data){

	$('.update').hide();
	$('.delete').hide();
	
	var path1 = data.target.origin;
	var path2 = data.target.pathname;
	var path3 = data.target.search;
	var cancel = path1 + path2 + path3;
	var id = data.target.id;
	var $hide = $(data.target.parentNode);
	var $tr = $(data.target.parentNode.parentNode.parentNode);
	var td = data.target.parentNode.parentNode.parentNode;
	var td1 = td.childNodes[1].innerHTML;
	var td2 = td.childNodes[3].innerHTML;
	$hide.hide();
	$tr.empty();
	$tr.append("<td colspan='3'><form action='updateDomainList.go' method='post' onsubmit='return updateOK()'><input type='text' name='do' value='"+td1+"' readonly><input type='text' name='dn' value='"+td2+"'><input type='hidden' name='origin' value='"+td1+"'><input class='btn btn-primary btn-sm' type='submit' value='수정 완료'><input type='button' class='btn btn-danger btn-sm' value='수정 취소' onclick='updateCancel()'></form></td>");

});

function updateCancel(){
	location.replace('adminDomainList.go');
}

function updateOK(){
	if(confirm('선택하신 항목을 수정하시겠습니까?')){
	}else{
		return false;
	}
}

$('.delete').click(function(){
	if(confirm('선택하신 항목을 삭제하시겠습니까?')){
		location.replace($(this).attr("id"));
	}
});