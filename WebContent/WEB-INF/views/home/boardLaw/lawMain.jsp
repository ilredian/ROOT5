<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="se"
   uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
A { color:#787878; text-decoration:none; }
A:Hover { color:#c0c0c0; text-decoration:none; }
</style>

<script language="javascript">
var no=${typeno};

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
    console.log(no);
    if(no==3){
    	$('#lawWrite').show();
    	alert('글쓰기가 보입니다');
    	
    }else{
    	$('#lawWrite').hide();
    	alert('글쓰기가 안보입니다');
    }
    
});



</script>
<div class="container lawMain">
	<h2>--º&nbsp;변호사게시판&nbsp; º--</h2> 
	변호사가 필요한 회원들을 위한 게시판.
	&nbsp;(**해당 게시판은 변호사만 글을 올릴수있습니다.)<br><br><br>
	<p>
		▶Total :
		<c:out value="${boardCount}" />
	</p>
	<table class="table table-hover">
		<thead>
			<tr>
				<th class="number">번호</th>
				<th class="writer">작 성 자</th>
				<th class="subject">제 목</th>
				<th class="career">경 력</th>
				<th class="company">소 속</th>
				<th class="date">등 록 일</th>
				<th class="count">조 회</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="n" items="${list}">
				<tr>
					<td class="number">${n.boardno}</td>
					<td class="writer">${n.name}</td>
					<td class="subject">
				<a href="lawView.go?pg=${param.pg}&bno=${n.boardno}" 
				data-toggle="tooltip"  title="${n.message}" data-placement="right">
						${n.title}</a>
						
						<!--	onmouseover="setTimeout('me()', 1500)" onmouseout="me1()">&nbsp;&nbsp;&nbsp;  -->
					</td>
				   <!-- 마우스를 올리면 게시물 번호에 따른 showlayer(게시물 미리보기 창)가 실행됨 -->
					<td class="career">${n.career}</td>
					<td class="company">${n.company}</td>
					<td class="date">${n.regdate}</td>
					<td class="count">${n.countno}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br>
	<div id="a">
	<%--    <se:authorize ifAllGranted="ROLE_USER , ROLE_ADMIN">
		<br> <input type="button" value="글쓰기" id="lawWrite"
			style="float: right; width: 60px; height: 40px; margin-right: 40px; margin-top: -40px;">
	 </se:authorize> --%>
	 		<br> <input  class="btn btn-primary" type="button" value="글쓰기" id="lawWrite"
			style="float: right; text-align:center;  width: 60px; height: 40px; margin-right: 40px; margin-top: -40px;">
	</div>                                                                                                                                                                                                                                                             
</div>
<div style="text-align: center;">
	<c:set var="pager" value="${pager.toString()}" />
	<ul class="pagination">${pager}</ul>
</div>
</body>
</head>