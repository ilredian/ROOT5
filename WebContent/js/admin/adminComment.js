$('.delete').click(function() {
	if (confirm('선택하신 항목을 삭제하시겠습니까?')) {
		location.replace("'" + $(this).attr("id") + "'");
	}
});