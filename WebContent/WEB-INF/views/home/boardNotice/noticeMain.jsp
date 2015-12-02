<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se"
   uri="http://www.springframework.org/security/tags"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container noticeMain">
  <h2>공지사항 게시판</h2>
  <p>Total : </p>         
  
  <table class="table table-hover">
    <thead>
      <tr>
        <th class="number">번호</th>
        <th class="subject">제목</th>
        <th class="date">등록일</th>
        <th class="count">조회</th>
      </tr>
    </thead>
    <tbody>
<%--     	<c:forEach items="${list}" var="n"> --%>
    	<c:forEach var="n" items="${list}" >
	    	 <tr>
	    	    <th class="number">${n.boardno}</th>
		        <th class="subject"><a href="noticeView.go?page=${page}&contentNo=${countNo}">${n.title}</a></th>
		        <th class="date">${n.regdate}</th>
		        <th class="count">${n.countno}</th>
    	 	</tr>
 	 	</c:forEach>
    </tbody>
  </table>
  
   <div>
      <se:authorize ifAllGranted="ROLE_ADMIN">
         <br>
         <input class="btn btn-primary" type="button" value="글쓰기"
            id="freeWrite" style="float: right;">
      </se:authorize>
      
       <input class="btn btn-primary" type="button" value="글쓰기"
            id="freeWrite" style="float: right;">
   </div>
  
</div>