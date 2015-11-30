<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%-- 
<%
	request.getParameter(arg0);
%>	
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container noticeMain">
  <h2>공지사항 게시판</h2>
  <p>Total : ${COUNTNO}</p>            
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
    	<c:forEach var="BOARDNOTICE" items="" >
	    	 <tr>
	    	 	<td>${BOARDNO}</td>
	    	 	<td class="content"><a href="noticeView.go?page=${page}&contentNo=${countNo}">${CONTENT}</a></td>
	    	 	<td>${REGDATE}</td>
	    	 	<td>${HIT}</td>
    	 	</tr>
 	 	</c:forEach>
    </tbody>
  </table>
</div>