<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Custom CSS -->
<link href="sb/dist/css/sb-admin-2.css" rel="stylesheet">
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	var a = ${countItems};
        var data = google.visualization.arrayToDataTable(${countItems});
        var options = {
          title: '사기피해 물품 종류'
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }
</script>
<body>
	<div id="page-wrapper">
		<!--헤더 로우  -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">AhnCheat</h1>
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
								<i class="fa fa-money fa-5x"></i>
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
					<a href="lawMain.go?pg=1"> <!-- 진술서 게시판으로  -->
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
								<i class="fa fa-save fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<!-- 사진_  -->
								<div class="huge">13</div>
								<div>사건 사진!</div>
							</div>
						</div>
					</div>
					<a href="PhotoMain.go?pg=1">
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
						<i class="fa fa-bar-chart-o fa-fw"></i>검색
						<div class="pull-right">
							<div class="btn-group"></div>
						</div>
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<form id="search" class="navbar-form navbar-center"
							action="totalSearch.go">
							<table>
								<tr>
									<td><select name="f" class="input-lg">
											<option value="cheatername">명의자 성명</option>
											<option value="account">계좌번호</option>
											<option value="feature">용의자 특징</option>
											<option value="cheaterid">용의자 아이디</option>
									</select></td>
									<td>
										<div class="ui-widget">
											<label for="tags"></label> <input type="text" name="q"
												id="tags" class="form-control search-query input-lg"
												placeholder="검색하세요" align="top" />
										</div>
									</td>
									<td><span class="input-group-btn"> <input
											value="검색" class="btn btn-default btn-lg" type="submit">
											<i class="fa fa-search fa-lg" data-type="last"></i>
									</span></td>
								</tr>
							</table>

						</form>
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
								<li><img src="images/cheatImage/cheat_1.jpg" width="50%"
									height="50%"></li>
								<li><img src="images/cheatImage/cheat_2.jpg" width="50%"
									height="50%"></li>
								<li><img src="images/cheatImage/cheat_6.jpg" width="50%"
									height="50%"></li>
								<li><img src="images/cheatImage/cheat_5.jpg" width="50%"
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
							<a href="#" id="messageWindow1" class="list-group-item"> <i
								class="fa fa-comment fa-fw"></i> 내 쪽지함
							</a> <a
								href="http://cyberbureau.police.go.kr/prevention/prevention2.jsp?mid=020302"
								class="list-group-item"> <i class="fa fa-twitter fa-fw"></i>
								사기를 막는 예방법
							</a> <a href="question.go" class="list-group-item"> <i
								class="fa fa-envelope fa-fw"></i> 관리자에게 메일 보내기
							</a>
						</div>
						<!-- /.list-group -->
						<button class="btn btn-default btn-block" data-toggle="collapse"
							data-target="#demo">
							View All Alert
							<c:if test="${not empty notification}">
								<!-- 알림이 있거나  -->
								<c:if test="${isRead}">
									<!-- 알림을 읽었을 때 -->
									<img src="/images/common/new.gif">
								</c:if>
							</c:if>
						</button>
						<br>
						<c:if test="${empty list}">
							<div id="demo" class="collapse">
								<!-- 없을때 -->
								<div class="alert alert-info">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>Info!</strong>
									<c:out value="접수된 진술서가 없습니다." />
								</div>
							</div>
						</c:if>
						<!-- 있을때  -->
						<c:if test="${not empty list}">
							<div id="demo" class="collapse">
								<c:forEach items="${list}" var="lists" begin="0" end="1">
									<div class="alert alert-info">
										<a href="#" class="close" data-dismiss="alert"
											aria-label="close">&times;</a> <strong>Info!</strong> <br>
										<font color="blue"><label> 진술서 번호 :</label></font>
										${lists.stateno}<br> <font color="blue"><label>
												용의자 이름 :</label></font> ${lists.cheatername}<br> <font color="blue"><label>
												등록한 멤버 번호 :</label></font> ${lists.memberno}<br>
									</div>
								</c:forEach>
								<!-- /.panel-body -->
							</div>
						</c:if>
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
						<i class="fa fa-bar-chart-o fa-fw"></i>사기를 막는 예방법
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
						<div id="piechart" style="width: 540px; height: 300px;"></div>
						<a href="chartMain.go" class="btn btn-default btn-block"
							id="piechart">View Details</a>
					</div>
					<!--  /.panel-body -->
				</div>
				<!--     /.panel -->
			</div>

			<!-- /.세번째 row -->
		</div>

		<!-- 네번째 row -->
		<div class="row">
			<div class="col-lg-6">
				<!-- 테이블 표  -->
				<div class="chat-panel panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> 테이블 표
						<div class="btn-group pull-right">
						</div>
					</div>
					<!--    /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered"
								style="text-align: center;">
								<tr>
									<th class="active" style="text-align: center; width: 10%;">순위</th>
									<th class="active" style="text-align: center;">용의자</th>
									<th class="active" style="text-align: center;">사이트</th>
									<th class="active" style="text-align: center;">은행</th>
								</tr>
								<c:forEach begin="0" end="9" varStatus="i">
									<tr>
										<td>${i.index + 1}</td>
										<td style="text-align: right;">
											${countCheaterName[i.index].cheatername} <span
											class="label label-danger">${countCheaterName[i.index].count}</span>
											건
										</td>
										<td style="text-align: right;">${countDomain[i.index].domain}
											<span class="label label-danger">${countDomain[i.index].count}</span>
											건
										</td>
										<td style="text-align: right;">${countBankName[i.index].bankname}
											<span class="label label-danger">${countBankName[i.index].count}</span>
											건
										</td>
									</tr>
								</c:forEach>
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

			<div class="col-lg-6">
				<!-- 3. 타임라인 부분 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-clock-o fa-fw"></i> 내 사건의 타임라인 <br>
						<p align="right">
							<c:choose>
								<c:when test="${not empty timeLineRegpoliceDate}">
									<small class="text-muted"><i class="fa fa-clock-o"> </i>첫 접수일로부터 발생경과 ${timeLineRegpoliceDate}일 지났습니다.</small>
								</c:when>
								<c:otherwise>
									<small class="text-muted"><i class="fa fa-clock-o"> </i>사건이 아직 접수되지 않았습니다.</small>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
					<!-- /.panel-heading -->
					<br>
					<p align="center">
						<strong> <c:if test="${empty list}">
								<c:out value="접수하신 내역이 없습니다." />
							</c:if></strong>
					<div class="panel-body">
						<ul class="timeline">
						<c:if test="${not empty complete}">
							<li><br>
								<div class="timeline-badge">
									<i class="fa fa-check"></i>
								</div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">검거 완료</h4>
									</div>
									<div class="timeline-body">
										<p>${complete} - ${policeName} -</p>
									</div>
								</div></li>
						</c:if>			
							<c:if test="${not empty trace}">
								<li class="timeline-inverted">
									<div class="timeline-badge warning">
										<i class="fa fa-credit-card"></i>
									</div>
									<div class="timeline-panel">
										<div class="timeline-heading">
											<h4 class="timeline-title">추적중</h4>
										</div>
										<div class="timeline-body">
											<p>${trace}</p>
										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${not empty policeName}">
								<li>
									<div class="timeline-badge danger">
										<i class="fa fa-bomb"></i>
									</div>
									<div class="timeline-panel">
										<div class="timeline-heading">
											<h4 class="timeline-title">${policeCompany}에 의뢰접수</h4>
										</div>
										<div class="timeline-body">
											<p>${policeName}</p>
										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${not empty timeLinedate}">
								<li class="timeline-inverted">
									<div class="timeline-panel">
										<div class="timeline-heading">
											<h4 class="timeline-title">등록일로부터 '${timeLinedate}'일 지났습니다.</h4>
	
										</div>
										<div class="timeline-body"></div>
									</div>
								</li>
							</c:if>
							<c:if test="${not empty regdate}">
								<li>
									<div class="timeline-badge info">
										<i class="fa fa-save"></i>
									</div>
									<div class="timeline-panel">
										<div class="timeline-heading">
											<h4 class="timeline-title">피해 사례 진술서 등록</h4>
										</div>
										<div class="timeline-body">
											<p>'${regdate}' 피해 사례 진술서를 등록하였습니다.</p>
										</div>
									</div>
								</li>
							</c:if>
						</ul>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
		</div>
	</div>
	<!-- /#page-wrapper -->
</body>
</html>
