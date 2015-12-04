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

	<div class="container" >
  <h1>관리자 페이지</h1>
  <p>이페이지는 고나리자 전용 페이지 입니다</p>
  <div class="container"style="margin-top: 20px">
  <div class="row">
  	
     <div class="col-sm-2" ><ul>
					<li class="menu"><a><img src="" alt="신고관리" /></a>
						<ul class="hide">
							<li><a href="free.go">자유게시판</a></li>
							<li><a href="poto.go">사진게시판</a></li>
							<li><a href="commment.go">댓글</a></li>

						</ul></li>

					<li class="menu"><a><img src="" alt="메뉴관리" /></a>
						<ul class="hide">
							<li><a href="site.go">사이트명</a></li>
							<li><a href="deallist.go">거래물품종류</a></li>
							<li><a href="bank.go">은행종류</a></li>
							<li><a href="join.go">회원가입</a></li>
							<li><a href="fake.go">사기종류</a></li>
							<li><a href="memberagent.go">회원관리</a></li>
							<li><a href="catrgory.go">카테고리</a></li>
						</ul></li>
					<li class="menu"><a><img src="" alt="메일함" /></a>
						<ul class="hide">
							<li><a href="mail.go">보낸함</a></li>
						</ul></li>
				</ul>
				</div>

    <div class="col-sm-10" >
    <table class="table table-hover">
    <h3>사이트명</h3>
    	<tr>
    	<th>
    	주소
    	</th>
    	<th>
    	이름
    	</th>
    	</tr>
    
    </table>
    	<div class="container">
    		<input type="button" class="btn btn-info" value="수정">
    		<input type="button" class="btn btn-danger" value="삭제">
    		<input type="button" class="btn btn-success" value="추가">
    	</div>
    </div>
  </div>
  </div>
</div>
</body>
</html>