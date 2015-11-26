<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<!-- header 영역 -->
	<tiles:insertAttribute name="header" />

	<!-- main 영역 -->
	<div id="main">
		<div class="top-wrapper clear">
			<tiles:insertAttribute name="content" />
		</div>
	</div>

	<!-- footer 영역 -->
	<tiles:insertAttribute name="footer" />
</body>
</html>