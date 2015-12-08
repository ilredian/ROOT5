<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
   src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
   <div class="container" align="center">
      <div class="homeContArea bodyCont">
         <h2 align="left">피해사례 분류</h2>
         <div style="padding: 20px; border: 4px solid #eff0f1; float: left;">
            <div class="fl section" style="float: left; margin-right: 20px;">
               <div class="cont">
                  <!-- 링크 -->
                  
                  <!-- 직거래 -->
<!--                   <input type="submit" value="직거래" class="btn btn-danger btn btn-lg trade">
                  <input type="submit" value="게임" class="btn btn-danger btn btn-lg game">
                  <input type="submit" value="비매너" class="btn btn-danger btn btn-lg bemanner">
                  <input type="submit" value="스팸" class="btn btn-danger btn btn-lg spam">
                  <input type="submit" value="피해사례등록" class="btn btn-danger btn btn-lg registration"> -->
					<a href="statementMain.go?cno=1"><img src="homenavimages/q.PNG"></a>
					<a href="statementMain.go?cno=2"><img src="homenavimages/w.PNG"></a>
					<a href="statementMain.go?cno=3"><img src="homenavimages/e.PNG"></a>
					<a href="statementMain.go?cno=4"><img src="homenavimages/r.PNG"></a>
					<a href="registration.go"><img src="homenavimages/t.PNG"></a>
               </div>
            </div>
         </div>
      </div>
      <!-- homeContArea bodyCont end -->
      <div class="container"
         style="float: left; margin-top: 20px; ">
         <div align="left">
            <strong>피해 사례 통계</strong>
         </div>
         <div align="right">
         	<a href="#">더보기</a>
         </div>
         
         <hr>
         <div class="container">
            <ul class="nav nav-tabs">
               <li class="active"><a data-toggle="tab" href="#home">사이트</a></li>
               <li><a data-toggle="tab" href="#menu1">용의자</a></li>
               <li><a data-toggle="tab" href="#menu2">은행</a></li>
            </ul>
            <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h3>사이트</h3>
     <table class="table">
     		<tr>
     			<td>
     			1
     			</td>
     			<td>
     			사이트명
     			</td>
     			<td>
     			건수
     			</td>
     		</tr>
     	</table>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>용의자</h3>
     	<table class="table">
     		<tr>
     			<td>
     			1
     			</td>
     			<td>
     			사람이름
     			</td>
     			<td>
     			건수
     			</td>
     		</tr>
     	</table>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>은행</h3>
     <table class="table">
     		<tr>
     			<td>
     			1
     			</td>
     			<td>
     			은행명
     			</td>
     			<td>
     			건수
     			</td>
     		</tr>
     	</table>
    </div>
  </div>
</div>
</div>
</div>

</body>
</html>

</html>