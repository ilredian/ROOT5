$(function() {
	$('#tags').keypress(function() {
		
		if ($('#tags').val().length > 0) {
			$.ajax({
				url : "totalSearchAjax.go",
				type : "POST",
				data : {
					"totalSearchAjax" : $("#tags").val()
				},
				success : function(responseData) {
					$('#asdf').val(responseData.auto)
					$("#tags").autocomplete({
						source : responseData.auto
					});
				}
			});
		}
	});
});