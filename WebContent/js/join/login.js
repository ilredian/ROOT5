$('#remember').click(function(){
	var $rm = $('#remember');
	
	if($rm.attr("value") == 0){
		if(confirm('로그인 상태를 유지하면 보안에 취약해질 수 있습니다. 정말로 유지하시겠습니까?')){
			$rm.attr("value", 1);
		}else{
			$rm.removeAttr("checked");
			$rm.attr("value", 0);
		}
	}else{
		$rm.removeAttr("checked");
		$rm.attr("value", 0);
	}
});

