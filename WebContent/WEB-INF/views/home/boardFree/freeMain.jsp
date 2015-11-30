<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container freeMain">
  <h2>자유게시판</h2>
  <p>Total :</p>            
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
      <tr>
   	 	<td>${BOARDNO}</td>
   	 	<td class="content"><a href="freeView.go?page=1&contentNo=1">${CONTENT}</a></td>
   	 	<td>${REGDATE}</td>
   	 	<td>${HIT}</td>
      </tr>
    </tbody>
  </table>
</div>
