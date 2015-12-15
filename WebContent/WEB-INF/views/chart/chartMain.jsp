<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable(${countItems});
        var options = {
          title: '사기피해 물품 종류'
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }
    </script>
    
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart2);
      function drawChart2() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['직거래피해',     ${countTrade}],
          ['게임, 비실물 피해',      ${countGame}],
          ['비매너 피해', ${countManner}]
        ]);
        var options = {
          title: '사기피해 종류'
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart1'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
    google.load('visualization', '1.1', {packages: ['line']});
    google.setOnLoadCallback(drawChart1);

    function drawChart1() {

      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Day');
      data.addColumn('number', '피해 사례 수');

      data.addRows(${list});

      var options = {
        chart: {
          title: '기간 별 등록된 피해 사례 수',
        },
        width: 1080,
        height: 600
      };

      var chart = new google.charts.Line(document.getElementById('linechart_material'));

      chart.draw(data, options);
    }
  </script>
<div class="container">
	<h2>피해사례 통계</h2> 
	
	<c:if test="${memberInfo.typeno==2}">
	
		<h3 align="right"><a href="/excel.go" target="_blank">피해순위테이블다운</a></h3>
		
	</c:if>	
	<div class="well well-sm">
		<FORM METHOD="POST" name="search_submit" ACTION="./?mod=_statistics">
			<div style="height: 20px;"></div>
			<div class="boxTypeC di_searchArea al">
				<strong>기간설정</strong> <select name="start_year" id="start_year"
					title="시작 년 선택">
					<c:forEach begin="${standard_year - 10}" end="${standard_year}"
						var="years">
						<c:choose>
							<c:when test="${years == start_year}">
								<option value="${years}" selected>${years}</option>
							</c:when>
							<c:otherwise>
								<option value="${years}">${years}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>년 <select name="start_month" id="start_month" title="시작 월 선택">
					<c:forEach begin="01" end="12" var="months">
						<c:choose>
							<c:when test="${months == start_month}">
								<option value="${months}" selected>${months}</option>
							</c:when>
							<c:otherwise>
								<option value="${months}">${months}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>월 - <select name="end_year" id="end_year" title="끝 년 선택">
					<c:forEach begin="${standard_year - 10}" end="${standard_year}"
						var="years">
						<c:choose>
							<c:when test="${years == end_year}">
								<option value="${years}" selected>${years}</option>
							</c:when>
							<c:otherwise>
								<option value="${years}">${years}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>년 <select name="end_month" id="end_month" title="끝 월 선택">
					<c:forEach begin="01" end="12" var="months">
						<c:choose>
							<c:when test="${months == end_month}">
								<option value="${months}" selected>${months}</option>
							</c:when>
							<c:otherwise>
								<option value="${months}">${months}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>월 <input type="button" id="chartSelect" class="btn btn-primary btn-xs" value="조회하기"> &nbsp;※ 2015년 10월까지를
				선택하면 2015년 10월 말일까지의 통계가 조회됩니다.
			</div>
		</FORM>
	</div>
</div>
<div class="container">
	<table class="table">
		<tr class="active">
			<th>피해사례 수 |</th>
			<td><span class="label label-danger">${allCheaterCount}</span> 건</td>
			<th>전화번호 수 |</th>
			<td><span class="label label-danger">${countPhone}</span> 건</td>
			<th>계좌번호 수 |</th>
			<td><span class="label label-danger">${countAccount}</span> 건</td>
			<th>피해금액 |</th>
			<td><span class="label label-danger">${countSum}</span> 원</td>
		</tr>
		<tr style="text-align: center;">
			<td colspan="8">
				<div id="linechart_material"></div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div id="piechart" style="width: 540px; height: 300px;"></div>
			</td>
			<td colspan="4">
				<div id="piechart1" style="width: 540px; height: 300px;"></div>
			</td>
		</tr>
	</table>
</div>
<div class="container">
	<table class="table table-hover table-bordered"
		style="text-align: center;">
		<tr>
			<th class="active" style="text-align: center;">순위</th>
			<th class="active" colspan="2" style="text-align: center;">용의자(명수)</th>
			<th class="active" colspan="2" style="text-align: center;">사이트(사이트수)</th>
			<th class="active" colspan="2" style="text-align: center;">은행(은행수)</th>
		</tr>
		<c:forEach begin="0" end="29" varStatus="i">
			<tr>
				<td>${i.index + 1}</td>
				<td style="border-right: 0px;">
					${countCheaterName[i.index].cheatername}</td>
				<td style="border-left: 0px;"><span class="label label-danger">${countCheaterName[i.index].count}</span>
					건</td>
				<td style="border-right: 0px;">${countDomain[i.index].domain}</td>
				<td style="border-left: 0px;"><span class="label label-danger">${countDomain[i.index].count}</span>
					건</td>
				<td style="border-right: 0px;">${countBankName[i.index].bankname}</td>
				<td style="border-left: 0px;"><span class="label label-danger">${countBankName[i.index].count}</span>
					건</td>
			</tr>
		</c:forEach>
	</table>
</div>