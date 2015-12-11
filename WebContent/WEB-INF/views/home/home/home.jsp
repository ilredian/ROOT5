<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Bootstrap Core CSS -->
<link href="sb/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="sb/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<!-- Timeline CSS -->
<link href="sb/dist/css/timeline.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="sb/dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="sb/bower_components/morrisjs/morris.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="sb/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" href="slide/flexslider.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="slide/jquery.flexslider.js"></script>
<script type="text/javascript">
//Can also be used with $(document).ready()
 $(window).load(function() {
  $('.flexslider').flexslider({
    animation: "slide"
  });
});
</script>
<body>
	<div id="page-wrapper">
		<!--헤더 로우  -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">AhnCheat-</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<!-- 첫번째 로우  -->
		<div class="row">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-comments fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- 자유게시판 글 카운트 불러오기 -->
								<div class="huge">
									<c:out value="${boardCountF}" />
								</div>
								<div>자유게시판</div>
							</div>
						</div>
					</div>
					<!-- 자유게시판 불러오기  -->
					<a href="freeMain.go?pg=1">
						<div class="panel-footer">
							<span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-green">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-tasks fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- 공지 게시판 글 카운트  -->
								<div class="huge">
									<c:out value="${boardCountN}" />
								</div>
								<div>공지게시판!</div>
							</div>
						</div>
					</div>
					<!--  공지 게시판 위치로 -->
					<a href="noticeMain.go?pg=1">
						<div class="panel-footer">
							<span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-yellow">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-shopping-cart fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!--  변호사게시판 개수  -->
								<div class="huge">
									<c:out value="${boardCountL}" />
								</div>
								<div>변호사 게시판!</div>
							</div>
						</div>
					</div>
					<a href="lawMain.go?pg=1">
						<!-- 진술서 게시판으로  -->
						<div class="panel-footer">
							<span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-red">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-support fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- 사진_  -->
								<div class="huge">13</div>
								<div>사건 사진!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left">View Details</span> <span
								class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
		</div>
		<!-- 두번째 줄 -->
		<div class="row">
			<!-- 1. 검색 -->
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-bar-chart-o fa-fw"></i>검색 <span
							class="label label-primary">new</span>
						<div class="pull-right">
							<div class="btn-group"></div>
						</div>
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div id="morris-area-chart"></div>

						<!-- 검색 옵션 적용 -->
						<label>피해사례검색 : </label> <input type="text" size=45px class="txt"
							name="keyword" id="hm_damageSearch" title="피해사례 검색어 입력"
							placeholder="로그인 후 피해사례를 검색할 수 있습니다 (무료)"
							onclick="blockLayerOpen(this); return false;">&nbsp;&nbsp;
						<a href="#"><img src="homeimages/search.PNG"></a> <select>
							<option>명의자 성명</option>
							<option>계좌번호</option>
							<option>용의자 아이디</option>
						</select>
					</div>
					<!-- /.panel-body -->
				</div>
				<!--사건 사진- 슬라이드 적용  -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-bar-chart-o fa-fw"></i>사건-사진 <span
							class="label label-warning">new</span>
						<div class="pull-right"></div>
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div id="morris-area-chart"></div>

						<div class="flexslider">
							<ul class="slides">
								<li><img src="cheatImage/cheat_1.jpg" width="50%"
									height="50%"></li>
								<li><img src="cheatImage/cheat_2.jpg" width="50%"
									height="50%"></li>
								<li><img src="cheatImage/cheat_6.jpg" width="50%"
									height="50%"></li>
								<li><img src="cheatImage/cheat_5.jpg" width="50%"
									height="50%"></li>
							</ul>
						</div>
					</div>
					<!-- /.panel-body -->
				</div>




				<!-- /.panel -->
			</div>

			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-bell fa-fw"></i> 알림!
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="list-group">
							<a href="#" class="list-group-item"> <i
								class="fa fa-comment fa-fw"></i> 내 쪽지함 <!-- 시간 푸쉬로 받기--> <span
								class="pull-right text-muted small"><em>4 minutes
										ago</em> </span>
							</a> <a href="#" class="list-group-item"> <i
								class="fa fa-twitter fa-fw"></i> 알림 <span
								class="pull-right text-muted small"><em>12 minutes
										ago</em> </span>
							</a> <a href="#" class="list-group-item"> <i
								class="fa fa-envelope fa-fw"></i> 관리자에게 메일 보내기 <span
								class="pull-right text-muted small"><em>27 minutes
										ago</em> </span>
							</a>
						</div>
						<!-- /.list-group -->
						<button class="btn btn-default btn-block"
							data-toggle="collapse in" data-target="#demo">View All
							Alerts</button>

						<div class="alert alert-success" id="alert">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Success!</strong> Indicates a successful or positive
							action.
						</div>
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Success!</strong> This alert box could indicate a
							successful or positive action.
						</div>
						<div class="alert alert-info">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Info!</strong> This alert box could indicate a neutral
							informative change or action.
						</div>
						<div class="alert alert-warning">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Warning!</strong>
							<table>
							<thead>
							<tr>
								<th class="active" style="text-align: center;">순위</th>
								<th class="active" style="text-align: center;">용의자(명수)</th>
								<th class="active" style="text-align: center;">사이트(사이트수)</th>
								<th class="active" style="text-align: center;">은행(은행수)</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
							</table>

							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped"
									style="text-align: center;">
									<c:if test="${not empty list}">
										<P>예제 테이블. 다른걸로 바꿔야함 interestStatementDTO 참고해서 변수 불러옴</P>
										<tr>
											<th class="active" style="text-align: center;">진술서 번호</th>
											<th class="active" style="text-align: center;">용의자 이름</th>
											<th class="active" style="text-align: center;">등록한 멤버 번호</th>
										</tr>
										<c:forEach items="${list}" var="lists" begin="0" end="9">
											<tr>
												<td>${lists.stateno}</td>
												<td>${lists.cheatername}</td>
												<td>${lists.memberno}</td>
											</tr>
										</c:forEach>
									</c:if>
								</table>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<!-- 옆 -->
				<!-- /.panel -->
			</div>
			<!-- /.두번째 row -->

</div>

			<!--세번째 row  -->
			<div class="row">

				<div class="col-lg-6">
					<!-- 2. 사기를 막는 예방법# 글/동영상  -아코디언  -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i>#사기를 막는 예방법#
							<div class="pull-right">
								<div class="btn-group">
									<button type="button"
										class="btn btn-default btn-xs dropdown-toggle"
										data-toggle="dropdown">
										Actions <span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right" role="menu">
										<li><a href="#">Action</a> <iframe width="560"
												height="315" src="https://www.youtube.com/embed/0-gdXa5spAU"
												frameborder="0" allowfullscreen></iframe></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-6">
									<div>
										<iframe width="560" height="315"
											src="https://www.youtube.com/embed/0-gdXa5spAU"
											frameborder="0" allowfullscreen></iframe>
									</div>
								</div>
								<!-- /.col-lg-4 (nested) -->
								<div class="col-lg-8">
									<div id="morris-bar-chart"></div>
								</div>
								<!-- /.col-lg-8 (nested) -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>

				<!-- 4.도넛 차트  -->
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i>도넛 차트
						</div>
						<div class="panel-body">
							<div id="morris-donut-chart"></div>
							<a href="#" class="btn btn-default btn-block">View Details</a>
						</div>
						<!--  /.panel-body -->
					</div>
					<!--     /.panel -->
				</div>
				
			<div class="col-lg-6">
					<!-- 테이블 표  -->
					<div class="chat-panel panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-comments fa-fw"></i> 테이블 표
							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default btn-xs dropdown-toggle"
									data-toggle="dropdown">
									<i class="fa fa-chevron-down"></i>
								</button>
								<ul class="dropdown-menu slidedown">
									<li><a href="#"> <i class="fa fa-refresh fa-fw"></i>
											Refresh
									</a></li>
									<li><a href="#"> <i class="fa fa-check-circle fa-fw"></i>
											Available
									</a></li>
									<li><a href="#"> <i class="fa fa-times fa-fw"></i>
											Busy
									</a></li>
									<li><a href="#"> <i class="fa fa-clock-o fa-fw"></i>
											Away
									</a></li>
									<li class="divider"></li>
									<li><a href="#"> <i class="fa fa-sign-out fa-fw"></i>
											Sign Out
									</a></li>
								</ul>
							</div>
						</div>
						<!--    /.panel-heading -->

						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped"
									style="text-align: center;">
									<thead>
										<tr>
											<th class="active" style="text-align: center;">순위</th>
											<th class="active" style="text-align: center;">용의자(명수)</th>
											<th class="active" style="text-align: center;">사이트(사이트수)</th>
											<th class="active" style="text-align: center;">은행(은행수)</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach begin="0" end="9" varStatus="i">
											<tr>
												<td>${i.index + 1}</td>
												<td>${countCheaterName[i.index].cheatername} <span
													class="label label-danger">${countCheaterName[i.index].count}</span>
													건
												</td>
												<td>${countDomain[i.index].domain}<span
													class="label label-danger">${countDomain[i.index].count}</span>
													건
												</td>
												<td>${countBankName[i.index].bankname}<span
													class="label label-danger">${countBankName[i.index].count}</span>
													건
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- table div  -->
						</div>
						<!--    /.panel-body -->
						<div class="panel-footer">
							<div class="input-group">
								<input id="btn-input" type="text" class="form-control input-sm"
									placeholder="Type your message here..." /> <span
									class="input-group-btn">
									<button class="btn btn-warning btn-sm" id="btn-chat">
										Send</button>
								</span>
							</div>
						</div>
						<!--    /.panel-footer -->
					</div>
					<!-- /.panel .chat-panel -->
			</div>
				<!-- /col-lg-6 -->
	
		<!-- /.세번째 row -->
	</div>
	<!-- /#page-wrapper -->



</body>
</html>
