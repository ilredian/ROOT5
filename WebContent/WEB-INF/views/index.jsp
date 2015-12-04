<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
<div class="container-12">
	<div class="jumbotron" align="center">
		<h1 align="center">사기피해사례 검색</h1>
		<form id="search" class="navbar-form navbar-center" action="totalSearch.go">
			<div class="input-group form-search">
				<div>
				<input type="text" name="q"
					class="form-control search-query" placeholder="검색하세요"
					align="top" /></div> 
					<span class="input-group-btn">
					<input value="검색" class="btn btn-primary" type="submit">
						<i class="fa fa-search fa-lg" data-type="last"></i>
				</span>
			</div>
		</form>
	</div>
</div>
</div>