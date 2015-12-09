$('#chartSelect').click(function(){
	var start_year = $('#start_year').val();
	var start_month = $('#start_month').val();
	var end_year = $('#end_year').val();
	var end_month = $('#end_month').val();
	location.replace('chartMain.go?sy='+start_year+'&sm='+start_month+'&ey='+end_year+'&em='+end_month);
});