<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
		<title>AhnCheat</title>
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
			<div class="container">
				<h1>관리자 페이지</h1>
				<p>이페이지는 고나리자 전용 페이지 입니다</p>
				<div class="container" style="margin-top: 20px">
					<div class="row">
						<div class="col-sm-2">
							<!-- Aside(Navi) 영역 -->
							<tiles:insertAttribute name="aside" />
						</div>

						<!-- Content 영역 -->
						<div class="col-sm-10">
							<tiles:insertAttribute name="content" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer 영역  -->
	<tiles:insertAttribute name="footer" />
	
	<!-- javascript 영역 -->
	<script type="text/javascript" src='<tiles:getAsString name="js"/>'></script>
	
<script>
    // html dom 이 다 로딩된 후 실행된다.
    $(document).ready(function(){
        // memu 클래스 바로 하위에 있는 a 태그를 클릭했을때
        $(".menu>a").click(function(){
            // 현재 클릭한 태그가 a 이기 때문에
            // a 옆의 태그중 ul 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
            $(this).next("ul").toggleClass("hide");
        });
    });
</script>
</body>
</html>