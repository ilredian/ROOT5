//Can also be used with $(document).ready()
$(window).load(function() {
	$('.flexslider').flexslider({
		animation : "slide"
	});
});

$(function() { /* 알람을 띄우기 */
	$('#alert').click(function() {
		$.ajax({
			url : "home.go",
			type : "GET",
			data : {
				"totalSearchAjax" : $("#tags").val()
			},
			success : function(responseData) {
			}
		});
	});
});

/* 테이블표 내용_ 새로고침 버튼 누르면 동기화  */

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
					$("#tags").autocomplete({
						source : temp
					});
				}
			});
		}
	});
});