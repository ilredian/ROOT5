/*자동 완성*/
$(function() {
	$('#tags').keyup(function() {
		
		if ($('#tags').val().length > 0) {
			$.ajax({
				url : "totalSearchAjax.go",
				type : "POST",
				data : {
					"totalSearchAjax" : $("#tags").val()
				},
				success : function(responseData) {
					var Ca = /\+/g;
					var temp = decodeURIComponent(responseData.auto).split(",");
					$('#tags').autocomplete({
						source : temp
					});
				}
			});
		}
	});
});