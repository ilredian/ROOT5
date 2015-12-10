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
	var td3 = td.childNodes[5].innerHTML;
	var td4 = td.childNodes[7].innerHTML;
	var td5 = td.childNodes[9].innerHTML;
	if(td5 == '정상 회원'){
		td5 = 0;
	}else{
		td5 = 1;
	}
	$hide.hide();
	$tr.empty();
	$tr.append("<td colspan='6'><form action='updateMember.go' method='post' onsubmit='return updateOK()'><input type='hidden' name='mno' value='"+td1+"'><span class='label label-info'>"+td1+"</span><span class='label label-info'>"+td2+"</span><span class='label label-info'>"+td3+"</span> 타입 번호 :<input type='text' name='tn' value='"+td4+"'> 활동 여부(0:활동/1:탈퇴) :<input type='text' name='active' value='"+td5+"'><input class='btn btn-primary btn-sm' type='submit' value='수정 완료'><input type='button' class='btn btn-danger btn-sm' value='수정 취소' onclick='updateCancel()'></form></td>");

});

$('#select').change(function(){
	location.replace('adminMember.go?ps='+$('#select').val());
})

function updateCancel(){
	location.replace('adminMember.go');
}

function updateOK(){
	if(confirm('선택하신 항목을 수정하시겠습니까?')){
	}else{
		return false;
	}
}