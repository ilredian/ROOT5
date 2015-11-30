<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title><!-- Title 영역  --> <tiles:getAsString name="title" />

</title>
<!-- StyleSheet  Link 영역 -->
<link href='<tiles:getAsString name="css"/>' type="text/css"
	rel="stylesheet" />
</head>
<body>
	<!-- Header 영역 -->
	<tiles:insertAttribute name="header" />
	<!-- Visual 영역 -->
	<div class="container">
		<tiles:insertAttribute name="visual" />
		<!-- Main   영역  -->
		<div id="main">
			<div class="top-wrapper clear">
				<!-- Content 영역 -->
				<div class="tab-content">
					<tiles:insertAttribute name="content" />
				</div>
				<!-- Aside(Navi) 영역 -->
				<tiles:insertAttribute name="aside" />
			</div>
		</div>

	</div>
	<!-- Footer 영역  -->
	<tiles:insertAttribute name="footer" />
</body>
</html>