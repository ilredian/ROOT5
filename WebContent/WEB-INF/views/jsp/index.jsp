<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Autocomplete - Default functionality</title>
<!-- <script>
  $(function() {
		var asdf = ${list};
    $( "#tags" ).autocomplete({
    	 source : asdf
        //조회를 위한 최소글자수
    });
})
  </script> -->
  
  <script type="text/javascript">
  $(function(){
	  $('#test').click(function(){
		  console.log("test");
	  });
	  
	  $("#joinOk").click(function(){
		  console.log("asdf");
	      $.ajax({
	          url : "ajax.go",
	          type: "POST",
	          data : { "joinOk" : $("#joinOk").val() },
	          success : function(responseData){
	        	  console.log(responseData);
	             /*  var data = responseData.person;
	              $("#ajax").remove();
	              if(!data){
	                  alert("존재하지 않는 ID입니다");
	                  return false;
	              }
	              var html = '';
	              html += '<form class="form-signin" action="" id="ajax">';
	              html += '이름<input type="text" class="form-control"  name="name" value="'+data.name+'">';
	              html += '아이디<input type="text" class="form-control" name=id" value="'+data.id+'">';
	              html += '이메일<input type="text" class="form-control"  name="email" value="'+data.email+'">';
	              html += '비밀번호<input type="text" class="form-control" name="password" value="'+data.password+'">';
	              html += '</form>';
	              $("#container").after(html); */
	          }
	      });

	  });
  });
  
  </script>
</head>
<body>
 <input type="hidden" value="${list}" id="asdf">
 <input type="text" value="${list}">
<div class="ui-widget">
  <label for="tags">Tags: </label>
  <input type="text" id="">
  <input type="button" id="joinOk" value="클릭">
</div>
 
<input type="button" id="test" value="test">
</body>
</html>