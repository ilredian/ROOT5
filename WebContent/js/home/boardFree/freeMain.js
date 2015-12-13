$('#freeWrite').click(function(){
	location.replace("freeWrite.go");
});

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});

$('#select').change(function(){
	location.replace('freeMain.go?ps='+$('#select').val());
});