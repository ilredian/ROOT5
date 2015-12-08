<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<div class="container-12">
		<div class="jumbotron" align="center">
			<h1 align="center">사기피해사례 검색</h1>
			<form id="search" class="navbar-form navbar-center"
				action="totalSearch.go">
				<table>
					<tr>
						<td><select name="f">
								<option value="cheatername">명의자 성명</option>
								<option value="account">계좌번호</option>
								<option value="feature">용의자 특징</option>
								<option value="cheaterid">용의자 아이디</option>
						</select></td>
						<td>
							<div class="ui-widget">
								<label for="tags"></label> <input type="text" name="q" id="tags"
									class="form-control search-query" placeholder="검색하세요"
									align="top" />
							</div>
						</td>
						<td><span class="input-group-btn"> <input value="검색"
								class="btn btn-primary" type="submit"> <i
								class="fa fa-search fa-lg" data-type="last"></i>
						</span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<input type="button" id="messagebtn" value="메시지 창 열기">