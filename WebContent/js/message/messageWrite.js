$('#messageMain').click(function(){
	location.replace('messageWindow.go');
});

$(function(){
	$('#name').keyup(function(){
		if ($('#name').val().length > 0) {
			$.ajax({
				url:"searchMemberNoAjax.go",
				type:"POST",
				data:{"searchMemberNoAjax": $('#name').val()},
				success:function(data){
					var Ca = /\+/g;
					var temp = decodeURIComponent(data.result).replace(Ca, " ").split(",");
						
					$('#name').autocomplete({
						source : temp
					});
				},
			});
		}
	});
});

$('#resultOk').click(function(){
	var $value = $('#resultOk')
	var $name = $('#name')
	if($value.attr("value") == "확정"){
		$.ajax({
			url:"resultOkAjax.go",
			type:"POST",
			data:{"resultOkAjax":$name.val()},
			success:function(data){
				if(data.result == "seccess"){
					alert('회원 검색에 성공했습니다.');
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
	var $value = $('#resultOk');
	if($value.val() == "확정"){
		alert('쪽지를 발송하기 전 확정 버튼을 눌러주세요.');
		return false;
	}
}