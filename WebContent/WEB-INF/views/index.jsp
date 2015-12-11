<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css"></style>
<style>
h1,h3 {
    font-family: "배달의민족 한나는 열한살"
/* 	 "배달의민족 주아", "배달의민족 도현",
     "배달의민족 한나는 열한살" ,
     "나눔고딕", "다음_Regular" , 
     "다음_SemiBold"   */;
     
     /* 텍스트에 그림자 */
     text-shadow: 0 0 5px #f00;
      
}
    

</style>

<!-- <style type="text/css">
div.jumbotron {
background-size:  1140px 490px;
background-image:url('images/common/c4.PNG');
background-repeat: no-repeat;
}

</style>
 -->
 
  <style type="text/css">
div#one,div.jumbotron {
background-size:  100% 100%;
background-image:url('images/common/c3.jpg');
background-repeat: no-repeat;
padding:120px;

}

</style>
 
 

</head>
</html>
<!-- <div class="container" id="one" style="margin-top: 35px;"> -->


<div class="jumbotron" align="center" style="hight:100%; "/> <!-- /* background-color: white; */  /* margin-bottom: 20px; */ -->
         <h1 align="center" style="color:blac \\k/*  #D83939 */;font-size: 80px; font-style: inherit;">사기피해사례 검색</h1><br><br>
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

		 </form>
		</div>
</div>
	
