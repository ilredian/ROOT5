<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>가로메뉴</title>
<link rel="stylesheet" href="css/demo.css" type="text/css" media="all" />
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("ul#topnav li").hover(function() {
		$(this).css({ 'background' : '#999CA1'});
		$(this).find("span").show(); 
		} , function() {
		$(this).css({ 'background' : 'none'});
		$(this).find("span").hide();
	});
});
</script>
</head>
<style type="text/css">
ul#topnav { margin: 0; padding: 0; float: left; width: 100%; list-style: none; position: relative; font-size: 1em; background: #151515; margin-bottom:50px; }
ul#topnav li { float: left; margin: 0; padding: 0; border-right: 1px solid #555; }
ul#topnav li a { padding: 10px 15px; display: block; color: #f0f0f0; text-decoration: none; }
ul#topnav li:hover { background: #999CA1; }
ul#topnav li span { ;float: left; padding: 6px 0; position: absolute; left: 0; top:35px; display: none; width: 100%; background: #999CA1; color: #fff; -moz-border-radius-bottomright: 5px; -khtml-border-radius-bottomright: 5px; -webkit-border-bottom-right-radius: 5px; -moz-border-radius-bottomleft: 5px; -khtml-border-radius-bottomleft: 5px; -webkit-border-bottom-left-radius: 5px; }
ul#topnav li:hover span { display: block; }
ul#topnav li span a { display: inline; }
ul#topnav li span a:hover { text-decoration: underline; }
ul#topnav li:hover 
</style>
<body>
<ul id="topnav">
  <li><a href="#self">사기피해사례 현황</a><span> <a href="statementMain.go?cno=1">직거래 피해사례</a> | <a href="statementMain.go?cno=2">게임.비실물 피해사례</a> | <a href="statementMain.go?cno=4">비매너 피해사례</a> | <a href="chartMain.go">사기피해사례 통계</a> | <a href="#self">사건사진 자료</a></span></li>
  <li><a href="#self">커뮤니티 폴리스</a> <span> <a href="freeMain.go?pg=1">자유게시판</a> | <a href="statementMain.go?cno=1">진술서게시판</a> | <a href="lawMain.go?pg=1">변호사게시판</a> | <a href="adhome.go">관리자 임시링크</a></span> </li>
  <li><a href="contact.go">컨택센터</a> <span> <a href="noticeMain.go?pg=1">공지사항</a> | <a href="question.go">문의하기</a> | <a href="deletepls.go">피해사례 삭제요청</a> | <a href="contlist.go">피해사례 분류</a> </span></li>
</ul>

