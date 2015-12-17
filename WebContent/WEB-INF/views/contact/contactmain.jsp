<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<div style="margin-left:200px;">
		<h2>컨택센터</h2>
		<div style="padding: 20px; border: 4px solid #eff0f1; float:left">
			<div style="width: 350px; height: 200px; float:left; margin-right:20px;">
				<h3>피해사례 삭제 요청</h3>
				<div>
					<p>피해사례 등록자에게 삭제를 요청 할 수 있습니다.</p>
					<!-- 링크 -->
					<div class="well">
						<!-- 삭제 요청하기 -->
						<a href="deletepls.go"><input type="button" class="btn btn-info btn-lg"
							value="삭제 요청하기"></a>
						<!-- 나의 피해사례 -->
						<a href="memberStatement.go?mno=2"><input type="button"
							class="btn btn-default btn-lg" value="나의 피해사례"></a>
					</div>
				</div>
			</div>
			<div style="width: 350px; height: 200px; float:right;">
				<h3>문의하기</h3>
				<div>
					<p>서비스 사용과 관련한 궁금증을 해결 할 수 있습니다.</p>
					<!-- 링크 -->
					<div class="well" align="center">
						<!--button-->
						<a href="question.go"><input type="button" class="btn btn-info btn-lg"
							value="1:1 문의하기"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>