<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>아이디 찾기</title>
	</head>
	<body>
		<jsp:include page="../common/otherHeader.jsp" flush="false"/>
		<main class="container">
			<form id="findIdForm" class="findForm" method="post" action ="#">
				<h2 align="center" class="title">아이디 찾기</h2>
				<div class="input-group">
						<span class="input-group-text">
							<i class="bi bi-envelope fs-5"></i>
						</span>
						<input type="text" class="form-control" id="email" name="email" placeholder="이메일" />
				</div>
				<div class="form-group">
					<button class="btn btn-success" type="submit">
						<span>
							<i class="bi bi-send fs-5"></i>
						</span>
						전송
					</button>
				</div>
				<div id="aboutUser" class="form-group">
					<ul>
						<li>
							<a href="/member/findPasswordForm">비밀번호 찾기</a>
						</li>
					</ul>
				</div>
			</form>
		</main>
		<jsp:include page="../common/footer.jsp"/>
	</body>
</html>