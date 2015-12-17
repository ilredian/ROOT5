$('#freeWrite').click(function(){
	location.replace("freeWrite.go");
});

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});

$('#select').change(function(){
	location.replace('freeMain.go?pg=1&ps='+$('#select').val());
});