<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="slide/flexslider.css" type="text/css">

<!-- Timeline CSS -->
<link href="sb/dist/css/timeline.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="sb/dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="sb/bower_components/morrisjs/morris.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="sb/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<title>
		<tiles:getAsString name="title"/>
</title>
<!-- StyleSheet  Link 영역 -->
<link href='<tiles:getAsString name="css"/>' type="text/css"
	rel="stylesheet" />
</head>
<body>
	<!-- Header 영역 -->
	<tiles:insertAttribute name="header" />
	<!-- Visual 영역 -->
	<tiles:insertAttribute name="visual" />
	<!-- Main   영역  -->
	<div id="main">
		<div class="top-wrapper clear">
			<!-- Content 영역 -->
			<tiles:insertAttribute name="content" />
			<!-- Aside(Navi) 영역 -->
			<tiles:insertAttribute name="aside" />
		</div>
	</div>


	<!-- Footer 영역  -->
	<tiles:insertAttribute name="footer" />
	
	<script src="slide/jquery.flexslider.js"></script>
	<!-- javascript 영역 -->
	<script type="text/javascript" src='<tiles:getAsString name="js"/>'></script>
</body>
</html>