<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage화면</title>
	<style>
		main {
			display: flex;
			min-width: 1000px;
		}
		#aside {
			width : 30%;
		}		
		
		#main{
			width : 70%;
		}
		.title {
			font-weight: bold;
  			color: black;
		}
		#content {
			min-width: 1200px;
			margin-top: 50px;
			flex : 1;
		}
		#listTable {
			margin-top: 50px;
		}
		#listTitle {
			border-top: 1px solid;
			border-bottom: 1px solid;
		}
		#listTitle th {
			padding: 20px 0px;
		}
	</style>

<body>

	<!-- 상단 메뉴바 -->
	<jsp:include page="../common/header.jsp" flush="false"/>	
	
	<main class="container-fluid p-5">
		<!-- 사이드바 -->
		<jsp:include page="../common/sideNav.jsp" flush="false"  /> 

		<!-- 중앙 myPage 화면 -->		
		<section id="content">
			<h2 class="title">회원 정보 조회</h2>
			<div class="border-top border-5 border-dark w-100">
				<table id="listTable" class="w-100 text-center">
					<tbody style="text-align: left;">
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이름</span>
							</td>
							<td>
								<input class="w-50" type="text" placeholder="이름"/>															
							</td>
							<td>
								<a class="btn btn-success" href="#">이름 수정</a>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이름</span>
							</td>
							<td>
								<input class="w-50" type="password" placeholder="비밀번호"/>														
							</td>
							<td>
								<a class="btn btn-success" href="#">이름 수정</a>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이름</span>
							</td>
							<td>
								<input class="w-50" type="text" placeholder="생년월일"/> <!-- 대철이나 한길이형이 데이터픽커(?) 가져다 쓰시면 됩니다. -->														
							</td>
							<td>
								<a class="btn btn-success" href="#">이름 수정</a>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이름</span>
							</td>
							<td>
								<input class="w-50" type="text" placeholder="닉네임"/>															
							</td>
							<td>
								<a class="btn btn-success" href="#">이름 수정</a>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이름</span>
							</td>
							<td>
								<input class="w-50" type="email" placeholder="이메일"/>															
							</td>
							<td>
								<a class="btn btn-success" href="#">이름 수정</a>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이름</span>
							</td>
							<td>
								<input class="w-50" type="number" placeholder="포인트조회"/>															
							</td>
							<td>
								<a class="btn btn-success" href="#">이름 수정</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<button type="button" onclick="location.href='${contextPath}/member/reView'" class="btn btn-outline-secondary">
			구매 리스트로 이동
			</button>
		
		</section>	
	</main>
	
	<!-- 하단 바 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	
</body>
</html>