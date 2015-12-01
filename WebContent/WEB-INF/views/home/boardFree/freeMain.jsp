<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se"
   uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
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
         <c:forEach var="n" items="${list}">
            <tr>
               <td class="number">${n.boardno}</td>
               <td class="writer">${n.name}</td>
               <td class="subject"><a
                  href="freeView.go?pg=${param.pg}&bno=${n.boardno}">${n.title}</a></td>
               <td class="date">${n.regdate}</td>
               <td class="count">${n.countno}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   <div>
      <%-- <se:authorize ifAllGranted="ROLE_USER , ROLE_ADMIN"> --%>
         <input class="btn btn-primary" type="button" value="글쓰기"
            id="freeWrite" style="float: right;">
      <%-- </se:authorize> --%>
   </div>
</div>
<div style="text-align: center;">
   <c:set var="pager" value="${pager.toString()}" />
   ${pager}
</div>