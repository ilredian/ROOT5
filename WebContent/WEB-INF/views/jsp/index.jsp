<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Autocomplete - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
		var asdf = ${list};
    $( "#tags" ).autocomplete({
    	 source : asdf
        //조회를 위한 최소글자수
    });
})
  </script>
</head>
<body>
 <input type="hidden" value="${list}" id="asdf">
 <input type="text" value="${list}">
<div class="ui-widget">
  <label for="tags">Tags: </label>
  <input type="text" id="tags">
</div>
 
 
</body>
</html>