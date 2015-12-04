<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="se"
   uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>

<style>
A { color:#787878; text-decoration:none; }
A:Hover { color:#c0c0c0; text-decoration:none; }
</style>

<script language="javascript">

headstring = "<HTML><HEAD><TITLE>스크립트 예제</TITLE><HEAD>" 
bodystring = "<BODY bgcolor=#f3f3f3>" 
endstring = "</BODY></HTML>" 
iswin1 = 0; 
function me() { 
   if (iswin1==1) return; 
   win1 = open ("","","width=300,height=250"); 
   win1.document.open(); 
   win1.document.write (headstring); 
   win1.document.write (bodystring); 
   text1 ="<font color=#009999 size=2><b>=======내용 미리보기=======<b> </font><br>";
   text1 +="<table class='table'><tr><td>=> 내용: ${memberInfo.message}</td></tr></table>";
   text1 +="</tbody></table></div> <p>";
   text1 = "<center><font size=2 color=#0066cc>" + text1 + "</font></center>"; 
   win1.document.write (text1); 
   win1.document.write (endstring); 
   win1.document.close(); 
   iswin1 = 1; 
} 
function me1() { 
   if (iswin1==0) return; 	 
   win1.close(); 
   iswin1 = 0; 
}  
        
</script>






<div class="container lawMain">
	<h2>변호사게시판</h2>
	<p>
		Total :
		<c:out value="${boardCount}" />
	</p>

	<table class="table table-hover">
		<thead>
			<tr>
				<th class="number">번호</th>
				<th class="writer">작성자</th>
				<th class="subject">제목</th>
				<th class="career">경력</th>
				<th class="company">소속</th>
				<th class="date">등록일</th>
				<th class="count">조회</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="n" items="${list}">
				<tr>
					<td class="number">${n.boardno}</td>
					<td class="writer">${n.name}</td>
					<td class="subject"><a
					
						href="lawView.go?pg=${param.pg}&bno=${n.boardno}" onmouseover="setTimeout('me()', 1500)" onmouseout="me1()">&nbsp;&nbsp;&nbsp;
						${n.title}<br>->${n.message}</a></td>
				   <!-- 마우스를 올리면 게시물 번호에 따른 showlayer(게시물 미리보기 창)가 실행됨 -->
				
					<th class="career">${n.career}</th>
					<th class="company">${n.company}</th>
					<td class="date">${n.regdate}</td>
					<td class="count">${n.countno}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br>
	<div>
	<%--    <se:authorize ifAllGranted="ROLE_USER , ROLE_ADMIN">
		<br> <input type="button" value="글쓰기" id="lawWrite"
			style="float: right; width: 60px; height: 40px; margin-right: 40px; margin-top: -40px;">
	 </se:authorize> --%>
	 		<br> <input  class="btn btn-primary" type="button" value="글쓰기" id="lawWrite"
			style="float: right; width: 60px; height: 40px; margin-right: 40px; margin-top: -40px;">
	</div>                                                                                                                                                                                                                                                             
</div>
<div style="text-align: center;">
	<c:set var="pager" value="${pager.toString()}" />
	${pager}
</div>
</body>
<input type=button name=simabuttonviewsource value="View Source" onClick='window.location="view-source:"+window.location.href'>

</head>