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

$('.regStatementPolice').click(function(){
	var stateno = $(this).attr("id");
	var content = '이 사건을 접수 하시겠습니까?';
	if(confirm(content)){
		location.replace("regStatementPolice.go?stateno="+stateno)
	}
});

$('.regOkStatement').click(function(){
	var content = '이미 접수 완료된 사건입니다.';
	alert(content);
});

$('.traceStatement').click(function(){
	var content = '현재 추적중인 사건입니다.';
	alert(content);
});

$('.completeStatement').click(function(){
	var content = '피의자가 검거된 사건입니다.';
	alert(content);
});