<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" id="pageNo" value="${param.pg}">
<input type="hidden" id="boardNo" value="${param.bno}">
<div class="container freeView">
   <h2>포토 게시판</h2>
   <table class="table table-striped">
      <tbody>
         <tr>
            <th colspan="3">${BoardPhotoDTO.title}</th>
         </tr>
         <tr>
            <td style="width: 10%;"><img alt="no_pic"
               src="${memverInfo.photo}"></td>
            <td style="width: 70%;">
               <table>
                  <tr>
                     <th>(피해회원)</th>
                  </tr>
                  <tr>
                     <td>${memberInfo.message}</td>
                  </tr>
               </table>
            </td>
            <td style="width: 20%">조회 ${BoardPhotoDTO.countno}
            <input type="button" class="btn btn-default" id="reportboard" value="신고">
            </td>
         </tr>
      </tbody>
   </table>
   <table class="table">
      <tr>
         <td>${BoardPhotoDTO.content}</td>
      </tr>
   </table>
   <table class="table">
      <thead>
         <tr>
            <td><b>게시물 주소 :</b>
               <div id="asdf">
                  <script>
                     $('#asdf').text(window.location.href)
                  </script>
                  <div style="float: right;">
                     <c:if test="${BoardPhotoDTO.memberno == memberInfo.memberno}">
                        <input class="btn btn-default" type="button" id="edit" value="수정">
                        <input class="btn btn-default" type="button" id="delete" value="삭제">
                     </c:if>
                     <input class="btn btn-default" type="button" id="list" value="목록">
                  </div>
               </div></td>
         </tr>
      </thead>
      <tbody>
         <tr>
            <td><b>댓글 </b> <c:out value="${replycount}" /><b>개</b></td>
         </tr>
      </tbody>
   </table>
   <table class="table">
      <tbody>
         <c:forEach var="replyDTO" items="${replyDTO}">
            <tr>
               <td style="width: 10%;"><img alt="no_pic"
                  src="images/boardFreeimages/user_no_pic.gif"></td>
               <td style="width: 90%;">
                  <table>
                     <tbody>
                        <tr>
                           <td>
                              <div>
                                 <div style="float: left;">
                                    <b>${replyDTO.name}</b> 님 | ${replyDTO.regdate}
                                 </div>
                                 <div style="float: right";>
                                    <input type="button" id="reportreply.go?pg=${param.pg}&bno=${param.bno}&cno=3&mno=${memberInfo.memberno}&rno=${replyDTO.replyno}" class="btn btn-default btn-sm reportreply" value="신고" style="float:right">
                                 </div>
                                 <div style="float: right;">
                                    <c:if test="${replyDTO.memberno == memberInfo.memberno}">
                                       <a class="updateReply" href="#"
                                          id="updateReply.go?pg=${param.pg}&bno=${param.bno}&cno=3&rno=${replyDTO.replyno}">수정</a>
                                       &nbsp;&nbsp;
                                       <a class="updateReplyActive"
                                          href="updateReplyActive.go?pg=${param.pg}&bno=${param.bno}&cno=3&rno=${replyDTO.replyno}">삭제</a>
                                       &nbsp;&nbsp;
                                    </c:if>
                                 </div>
                              </div>
                           </td>
                        </tr>
                        <tr style="clear: both;">
                           <td style="width: 1%;">${replyDTO.content}</td>
                        </tr>
                     </tbody>
                  </table>
               </td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   <div style="text-align: center;">
      <c:set var="rpager" value="${rpager.toString()}" />
      <ul class="pagination">
      ${rpager}
      </ul>
   </div>
   <br>
   <div style="text-align: center;">
      <form action="reply.go?pg=${param.pg}&bno=${param.bno}&cno=3"
         method="post">
         <input type="text" id="replyContent" name="content"
            style="width: 90%"> <input class="btn btn-primary"
            type="submit" id="replybtn" value="댓글 등록">
      </form>
   </div>
</div>