$('.viewBtn').click(function() {
	var loc = this.getAttribute("id");
	location.href = loc;
});

$(function() {
	var cno = $('#cno').attr("value");
	
	if(cno == 1){
		$('#tradeMain').attr("class", "active");
		$('#statementText').text("직거래 피해 사례");
	}else if(cno == 2){
		$('#gameMain').attr("class", "active");
		$('#statementText').text("게임, 비실물 피해 사례");
	}else{
		$('#mannerMain').attr("class", "active");
		$('#statementText').text("비매너 피해 사례");
	}
});