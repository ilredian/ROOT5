<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se"
   uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>

   <h2>자유게시판</h2>
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
            <th class="date">등록일</th>
            <th class="count">조회</th>
         </tr>
      </thead>

      <tbody>
         <c:forEach var="list" items="${list}">
            <tr>
               <td class="number">${list.boardno}</td>
               <td class="writer">${list.name}</td>
               <td class="subject"><a
                  href="freeView.go?pg=${param.pg}&bno=${list.boardno}" data-toggle="tooltip"
                  title="${list.content}" data-placement="right">${list.title}</a> 
                  <c:if test="${list.boardReplyCount > 0}">
                  	<span style="color:red;">${list.boardReplyCount}</span>
                  </c:if>
                </td>
               <td class="date">${list.regdate}</td>
               <td class="count">${list.countno}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   
   <div>
      <se:authorize ifAnyGranted="ROLE_USER"><!-- Any 아무거나 만족해도  -->
      <%--    <se:authorize ifAnyGranted="hasRole('ROLE_USER')">  --%>
         <br>
         <input class="btn btn-primary" type="button" value="글쓰기"
            id="freeWrite" style="float: right;">
      </se:authorize>

       <input class="btn btn-primary" type="button" value="글쓰기"
            id="freeWrite" style="float: right; text-align: center;">
   </div>
</div>
<div style="text-align: center;">
   <c:set var="pager" value="${pager.toString()}" />
   ${pager}
</div>