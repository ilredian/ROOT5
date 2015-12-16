$('.update').click(function(){
	if(confirm('선택하신 항목을 수정하시겠습니까?')){
		location.replace(""+$(this).attr("id")+"");
	}
});

$('.delete').click(function(){
	if(confirm('선택하신 항목을 삭제하시겠습니까?')){
		location.replace(""+$(this).attr("id")+"");
	}
});

$('.insert').click(function(){
	if(confirm('이 항목을 추가하시겠습니까?')){
		location.replace(""+$(this).attr("id")+"");
	}
});