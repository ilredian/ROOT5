$('.messageWrite').click(function(){
	var memberno = $('.messageWrite').attr("id");
	location.replace='messageWrite.go?mbno='+memberno;
});

$('.messageDelete').click(function(){
	var messageno = $(this).attr("id");
	location.replace='messageDelete.go?msno='+messageno;
});