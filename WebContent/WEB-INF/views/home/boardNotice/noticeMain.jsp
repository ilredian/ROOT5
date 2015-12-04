<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se"
   uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
   <h2>공지사항 게시판</h2>
   <p>
      Total :
      <c:out value="${boardCount}" />
   </p>

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
         <c:forEach var="list" items="${list}">
            <tr>
               <td class="number">${list.boardno}</td>
               <td class="subject"><a
                  href="noticeView.go?pg=${param.pg}&bno=${list.boardno}">${list.title}</a> 
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
      <se:authorize ifAllGranted="ROLE_USER,ROLE_ADMIN">
         <br>
         <input class="btn btn-primary" type="button" value="글쓰기"
            id="noticeWrite" style="float: right;">
      </se:authorize>
      
       <input class="btn btn-primary" type="button" value="글쓰기"
            id="noticeWrite" style="float: right;">
   </div>
   
</div>
<div style="text-align: center;">
   <c:set var="pager" value="${pager.toString()}" />
   ${pager}
</div>