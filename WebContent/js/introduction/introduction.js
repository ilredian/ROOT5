$('.viewBtn').click(function() {
	var loc = this.getAttribute("id");
	location.href = loc;
});

$(function() {
	var cno = $('#ino').attr("value");
	if(cno == 1){
		$('#introduction').attr("class", "active");
		$('#statementText').text("소개");
	}else if(cno == 2){
		$('#introductionhistory').attr("class", "active");
		$('#statementText').text("연혁");
	}else if(cno == 3){
		$('#introductiondb').attr("class", "active");
		$('#statementText').text("보유중인 데이터베이스");
	}else{
		$('#introductionservice').attr("class", "active");
		$('#statementText').text("주 서비스");
	}
});