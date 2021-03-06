<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
		AhnCheat<tiles:getAsString name="title"/>
</title>
<!-- StyleSheet  Link 영역 -->
<link href='<tiles:getAsString name="css"/>' type="text/css"
	rel="stylesheet" />
</head>
<body>
	<input type="hidden" id="mno" value="${param.mno}">
	<!-- Header 영역 -->
	<tiles:insertAttribute name="header" />
	<!-- Visual 영역 -->
	<div class="container">
		<tiles:insertAttribute name="visual" />
		<!-- Main   영역  -->
		<div id="main">
			<!-- Content 영역 -->
			<tiles:insertAttribute name="content" />
			<!-- Aside(Navi) 영역 -->
			<tiles:insertAttribute name="aside" />
		</div>

	</div>
	<!-- Footer 영역  -->
	<tiles:insertAttribute name="footer" />
	
	<!-- javascript 영역 -->
	<script type="text/javascript" src='<tiles:getAsString name="js"/>' ></script>
		
	<!-- javascript 영역 -->
	<script type="text/javascript" src='<tiles:getAsString name="commonjs"/>' ></script>
	
</body>
</html>