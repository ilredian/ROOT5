$('#messageMain').click(function(){
	location.replace('messageWindow.go');
});

$('#name').keypress(function(){
	if ($('#name').val().length > 0) {
		$.ajax({
			url:"searchMemberNoAjax.go",
			type:"POST",
			data:{"searchMemberNoAjax": $('#name').val()},
			success:function(data){
				$('#name').autocomplete({
					source : data.result
				});
			},
		});
	}
});

$('#resultOk').click(function(){
	var $value = $('#resultOk')
	var $name = $('#name')
	if($value.attr("value") == "확정"){
		
		$.ajac({
			url:"resultOkAjax.go",
			type:"POST",
			data:{"resultOkAjax":$name.attr("value")},
			success:function(data){
				if(data.result == "seccess"){
					alert('존재하는 회원입니다.');
					$name.attr("readonly", "readonly");
					$value.attr("value", "확정취소");
				}else{
					alert('값을 입력하세요');
				}
			},
			error: function(result){
				alert('존재하지 않는 회원입니다');
			}
		});
	}else{
		$name.removeAttr("readonly");
		$value.attr("value", "확정");
	}
});

function submitOk(){
	if($value.attr("value") == "확정"){
		alert('쪽지를 발송하기 전 확정 버튼을 눌러주세요.');
		return false;
	}
}