$('.viewBtn').click(function() {
	var location = $(this).attr("id");
	location.replace(location);
});

function radio_statement(data){
	$('#hidden_radio_value').val(data);
}

$('#regInterestStatement').click(function(){
	var content = "이 진술서를 관심 등록하여 피의자를 추적하시겠습니까?";
	var val = $('#hidden_radio_value').val();
	if(val != 0){
		if(confirm(content)){
			location.replace('regInterestStatement.go?stateno='+val);
		}
	}else{
		alert('먼저 진술서를 선택하세요.');
	}
});

$('#deleteInterestStatement').click(function(){
	var content = "이 진술서를 관심 등록 해제 하시겠습니까? 해제하시면 더이상 추적 내용을 확인할 수 없습니다.";
	if(confirm(content)){
		location.replace('deleteInterestStatement.go');
	}
});