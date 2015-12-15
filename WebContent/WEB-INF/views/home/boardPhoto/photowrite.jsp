<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="frm" method="post" action="">
   <div class="container freeMain">
      <h2>사건 사진자료</h2>
      <table class="table">
         <tr>
            <th class="active">제목</th>
            <td><input type="text" name="title" style="WIDTH: 100%;"></td>
         </tr>
         <tr>
            <th class="active">작성자</th>
            <td>
               <c:out value="${memberInfo.name}"/>
               <input type="hidden" name="name" value="${memberInfo.name}">
               <input type="hidden" name="memberno" value="${memberInfo.memberno}">
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <textarea id="editor" style="HEIGHT: 300px; WIDTH: 100%" rows="10" cols="30" name="content">
               </textarea>
            </td>
         </tr>
         <tr>
            <th class="active">고운 언어가 좋아요!</th>
            <td>
            "말이 입힌 상처는 칼이 입힌 상처보다 깊다." - 모로코 속담<br>
            <c:out value="${memberInfo.name}"/>님의 글은 <c:out value="${memberInfo.name}"/>님의 품격을 보여주는 <c:out value="${memberInfo.name}"/>님의 얼굴입니다.
            글에 <c:out value="${memberInfo.name}"/>님의 성숙함을 담아주세요.
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <div style="text-align: center;">
                  <input class="btn btn-primary" id="savebutton" type="button" value="저장"> 
                  <input class="btn btn-default" onclick="history.go(-1);" type="button" value="취소">
               </div>
            </td>
         </tr>
      </table>
   </div>
</form>