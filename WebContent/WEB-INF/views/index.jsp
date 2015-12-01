<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container-12">
	<div class="jumbotron">
		<h1 align="center">사기피해사례 검색</h1>
		<p align="center">아난안아낭</p>
		<form id="search_fix" class="navbar-form navbar-center" role="search"
			style="padding-left: 650px">
			<div class="input-group form-search" align="center">
				<input type="text" name="search" value=""
					onkeypress="if (event.keyCode == 13) { try{window.location.href='/search/' + looseURIEncode(document.getElementsByName('search')[0].value);document.getElementsByName('search')[0].value = ''; return false;}catch(e){} }"
					class="form-control search-query" placeholder="Enter to search..."
					align="top" /> <span class="input-group-btn">
					<button value="검색" class="btn btn-inverse"
						onclick="try{window.location.href='/search/' + looseURIEncode(document.getElementsByName('search')[0].value);document.getElementsByName('search')[0].value = ''; return false;}catch(e){}"
						class="submit">
						<i class="fa fa-search fa-lg" data-type="last"></i>
					</button>
				</span>
			</div>
		</form>
	</div>
</div>