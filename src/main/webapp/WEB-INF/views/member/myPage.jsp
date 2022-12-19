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
<Script>
	console.log("${member}")
	
	
	

</Script>
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
								<input class="w-50" type="text" placeholder="이름" value="${member.name }" disabled="disabled">															
							</td>
							<td>
							<button type="button" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalName">이름 변경</button>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">비밀번호</span>
							</td>
							<td>
								<input class="w-50" type="password" placeholder="********" value="${member.password }" disabled="disabled"/>														
							</td>
							<td>
							<button type="button" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalPassword">비밀번호 변경</button>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">생년월일</span>
							</td>
							<td>
								<input class="w-50" type="text" placeholder="생년월일" value="${member.birthday }" disabled="disabled"/>														
							</td>
							<td>
							<button type="button" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalBirthday">생년월일 변경</button>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">닉네임</span>
							</td>
							<td>
								<input class="w-50" type="text" placeholder="닉네임" value="${member.nickname }" disabled="disabled"/>															
							</td>
							<td>
							<button type="button" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalNickname">닉네임 변경</button>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">이메일</span>
							</td>
							<td>
								<input class="w-50" type="email" placeholder="이메일" value="${member.email }" disabled="disabled"/>															
							</td>
							<td>
							<button type="button" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#ModalEmail">이메일 변경</button>
							</td>
						</tr>
						<tr>
							<td style="width: 10%">
								<span style="font-weight: bold;">포인트 조회</span>
							</td>
							<td>
								<input class="w-50" type="number" placeholder="포인트조회" value="${member.point }" disabled="disabled"/>															
							</td>
							
						</tr>
					</tbody>
				</table>
			</div>
			<button type="button" onclick="location.href='${contextPath}/reView/reView'" class="btn btn-outline-secondary">
			구매 리스트로 이동
			</button>
		</section>	
	</main>


<!-- Modal -->
<!-- 이름바꾸기 -->
<div class="modal" id="ModalName" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 바디 안에다가 인클루드도 가능하고. 꾸밀것들 -->
        <jsp:include page="./modal/modalName.jsp" flush="false"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">창 닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- 비밀번호 바꾸기 -->	
<div class="modal" id="ModalPassword" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 바디 안에다가 인클루드도 가능하고. 꾸밀것들 -->
        <jsp:include page="./modal/modalPassword.jsp" flush="false"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">창 닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- nickname 바꾸기 -->	
<div class="modal" id="ModalNickname" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 바디 안에다가 인클루드도 가능하고. 꾸밀것들 -->
        <jsp:include page="./modal/modalNickname.jsp" flush="false"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">창 닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- email 바꾸기 -->	
<div class="modal" id="ModalEmail" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 바디 안에다가 인클루드도 가능하고. 꾸밀것들 -->
        <jsp:include page="./modal/modalEmail.jsp" flush="false"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">창 닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- 생년월일 바꾸기 -->	
<div class="modal" id="ModalBirthday" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 바디 안에다가 인클루드도 가능하고. 꾸밀것들 -->
        <jsp:include page="./modal/modalBirthday.jsp" flush="false"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">창 닫기</button>
      </div>
    </div>
  </div>
</div>


	<!-- 하단 바 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	
</body>
</html>