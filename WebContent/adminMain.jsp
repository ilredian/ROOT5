<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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
</head>
<body>

	<div class="container-fluid">
  <h1>Hello World!</h1>
  <p>Resize the browser window to see the effect.</p>
  <div class="container">
  <div class="row">
  	
    <div class="col-sm-2" style="border: 1px; border-color: black;"><ul>
					<li class="menu"><a><img src="" alt="신고관리" /></a>
						<ul class="hide">
							<li>자유게시판</li>
							<li>사진게시판</li>
							<li>댓글</li>

						</ul></li>

					<li class="menu"><a><img src="" alt="메뉴관리" /></a>
						<ul class="hide">
							<li>사이트명</li>
							<li>거래물품종류</li>
							<li>은행종류</li>
							<li>회원가입</li>
							<li>사기종류</li>
							<li>회원관리</li>
						</ul></li>
					<li class="menu"><a><img src="" alt="메일함" /></a>
						<ul class="hide">
							<li>보낸함</li>
							<li>받은함</li>
						</ul></li>
				</ul>
				</div>
    
    <div class="col-sm-10" style="background-color:lavender;">
    	
    </div>
  </div>
  </div>
</div>
</body>
</html>