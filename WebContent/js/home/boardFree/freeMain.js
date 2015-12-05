$('#freeWrite').click(function(){
	location.replace("freeWrite.go");
});

$('#select').change(function(){
	console.log($('#select').val());
});

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});