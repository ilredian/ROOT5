<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
h1,h3 {
    font-family: "배달의민족 한나는 열한살"
/* 	 "배달의민족 주아", "배달의민족 도현",
     "배달의민족 한나는 열한살" ,
     "나눔고딕", "다음_Regular" , 
     "다음_SemiBold"   */;
}
</style>
<SCRIPT TYPE="TEXT/JAVASCRIPT"> 
 expireDate = new Date;
 expireDate.setMonth(expireDate.getMonth()+6);
 hitCt = eval(cookieVal("pageHit"));
 hitCt++;
 document.cookie = "pageHit="+hitCt+";expires=" + expireDate.toGMTString();
    
 function cookieVal(cookieName) {
  thisCookie = document.cookie.split("; ")
  for (i=0; i<thisCookie.length; i++) {
    if (cookieName == thisCookie[i].split("=")[0]) return thisCookie[i].split("=")[1];
  }
  return 0;
 }
 window.onload = function(){
  document.getElementById("pageView").appendChild(document.createTextNode(hitCt));
 }
</script>

</head>
</html>
<div class="container">

      <div class="jumbotron" align="center" style="background-color: white; margin-top: 70px; margin-bottom: 30px;">
         <h1 align="center" style="color: #D83939;font-size: 80px; font-style: inherit;">사기피해사례 검색</h1><br><br>
         <form id="search" class="navbar-form navbar-center"
            action="totalSearch.go">

				<table>
					<tr>
						<td><select name="f" class="input-lg">
								<option value="cheatername">명의자 성명</option>
								<option value="account">계좌번호</option>
								<option value="feature">용의자 특징</option>
								<option value="cheaterid">용의자 아이디</option>
						</select></td>
						<td>
							<div class="ui-widget">
								<label for="tags"></label> <input type="text" name="q" id="tags"
									class="form-control search-query input-lg" placeholder="검색하세요"
									align="top" />
							</div>
						</td>
						<td><span class="input-group-btn"> <input value="검색"
								class="btn btn-danger btn-lg" type="submit"> <i
								class="fa fa-search fa-lg" data-type="last"></i>
						</span></td>
					</tr>
				</table>
				<table style="margin-top:50px;">
					<tr><th style="color: #D83939;">오늘 방문자수&nbsp;&nbsp;&nbsp;&nbsp;</th><td id="pageView" style="color:orange"></td></tr>
				</table>
			</form>
		</div>
	</div>
</div>