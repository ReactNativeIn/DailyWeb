<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<style>
			#loginForm, #loginForm div{
				max-width: 500px;
				min-width: 300px;
			}
			#loginForm {
				border: 3px solid rgba(220, 220, 220, 1); 
				padding: 20px 20px;
				margin: 0px auto;
			}
			#loginForm input{
				height: 50px;
			}
			#loginForm > .input-group:first-child {
				margin-bottom: 30px
			}
			#loginForm > .form-group {
				margin-top: 30px;
			}
		</style>
	</head>
	<body>
		<!-- header -->
		<jsp:include page="../common/otherHeader.jsp" flush="false"/>

		<main class="container">
			<form id="loginForm" method="post" action="/member/login">
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-person fs-5"></i>
					</span>
					<input type="text" class="form-control" id="id" name="id" placeholder="아이디"/>					
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-lock fs-5"></i>
					</span>
					<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호"/>
				</div>
				<div class="form-group btn-group-lg">
					<button class="btn btn-success" type="submit">로그인</button>
				</div>
				<div id="aboutUser" class="form-group">
					<ul>
						<li>
							<a href="/member/findIdForm">아이디 찾기 | </a>
						</li>
						<li>
							<a href="/member/findPasswordForm">비밀번호 찾기 | </a>
						</li>
						<li>
							<a href="#">회원가입</a>
						</li>
					</ul>
				</div>
			</form>
		</main>
		
		<!-- footer -->
		<jsp:include page="../common/footer.jsp"/>
	</body>
	<c:if test="${message != null }">
		<script>
		window.onload = function() {
			alert("${message}");
		}
		</script>
	</c:if>
</html>