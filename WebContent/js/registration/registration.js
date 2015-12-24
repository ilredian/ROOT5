$('#trade').click(function(){
	location.replace("trade.go");
});
$('#tradeMain').click(function(){
	location.replace("trade.go");
});
$('#game').click(function(){
	location.replace("game.go");
});
$('#manner').click(function(){
	location.replace("manner.go");
});

$(function(){
	if(${empty memberInfo}){
		location.replace("index.go");
	}
});