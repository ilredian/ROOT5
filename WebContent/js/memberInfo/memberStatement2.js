$('.updateTrace').click(function(data){

	$('.updateTrace').hide();
	$('.updateComplete').hide();
	var $tr = $(data.target.parentNode.parentNode.parentNode);
	var id = $(data)[0].target.id;
	var content = "";
	var gno = 0;
	$.ajax({
		url:"policeUpdateTraceAjax.go",
		type:"POST",
		data:{"gno":id},
		success:function(data){
			if(data.result == "success"){
				content = data.String;
			}
			gno = data.gno;
			$tr.append("<tr><td colspan='7'><form action='policeUpdateTrace.go' method='POST' onsubmit='return updateOK()'><textarea id='content' cols='100' rows='5' name='content'>"+content+"</textarea><input class='btn btn-primary btn-sm' type='submit' value='등록 완료'><input type='button' class='btn btn-danger btn-sm' value='등록 취소' onclick='updateCancel()'><input type='hidden' name='gno' value='"+gno+"'></form></td></tr>");
		}
	})
});

$('.updateComplete').click(function(data){

	$('.updateTrace').hide();
	$('.updateComplete').hide();
	var $tr = $(data.target.parentNode.parentNode.parentNode);
	var id = $(data)[0].target.id;
	var content = "";
	var gno = 0;
	$.ajax({
		url:"policeUpdateCompleteAjax.go",
		type:"POST",
		data:{"gno":id},
		success:function(data){
			if(data.result == "success"){
				content = data.String;
			}
			gno = data.gno;
			$tr.append("<tr><td colspan='7'><form action='policeUpdateComplete.go' method='POST' onsubmit='return updateOK()'><textarea id='content' cols='100' rows='5' name='content'>"+content+"</textarea><input class='btn btn-primary btn-sm' type='submit' value='등록 완료'><input type='button' class='btn btn-danger btn-sm' value='등록 취소' onclick='updateCancel()'><input type='hidden' name='gno' value='"+gno+"'></form></td></tr>");
		}
	})
});

function updateCancel(){
	location.replace('memberStatement2.go?mno=3');
}

function updateOK(){
	if($('#content')[0].selectionEnd >= 10){
		if(confirm('선택하신 항목의 추적 현황을 등록하시겠습니까?')){
		}else{
			return false;
		}
	}else{
		alert('최소 10자 이상 입력하세요.');
		return false;
	}
}

$('.delete').click(function(){
	if(confirm('선택하신 항목을 삭제하시겠습니까?')){
		location.replace($(this).attr("id"));
	}
});