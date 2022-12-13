<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 등록 화면</title>
	<script src="/resources/js/board.js"></script>
	
<style>
	@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
	
	#h2-logo {
	margin-top: 150px;
	margin-bottom: 100px;
	border-top: solid;
	border-bottom: solid;
	border-top-width: 0px;
	border-bottom-width: 3px;
	padding-bottom: 30px;
	font-family: 'Jeju Gothic', sans-serif;
	}
	#form-font {
	font-family: 'Jeju Gothic', sans-serif;
	margin-bottom: 30px;
	margin-left: 150px;
	display: flex;
	align-items: center;
	}
	.form-control {
	margin-left: 15px;
	margin-top: 15px;
	}
	.control-label {
	font-size: 1.5rem;
	text-align: center;
	}
	
	
</style>
	
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../common/header.jsp" flush="false"/>

<main>
	<div id="middle" class="container">
		<form class="form-horizontal" name="boardRegisterForm">
			<div id="middle">
				<div id= "h2-logo" class="form-group">
					<div class="col-md-offset-2 col-md-5">
						<h2>게시글 등록</h2>
					</div>
				</div>
					<div id="form-font" class="form-group">
						<label class="col-sm-1 control-label">제 목</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="b_title" name="subject" maxlength="200" placeholder="제목 입력"/>
						</div>
					</div>
					<div id="form-font" class="form-group">
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-md-1">
							<input type="text" class="form-control" id="id" name="writer" maxlength="20" placeholder="아이디"/>
						</div>
					</div>
					<div id="form-font" class="form-group">
						<label class="col-sm-1 control-label">내 용</label>
						<div class="col-md-8">
							<textarea class="form-control" id="b_content" name="content" rows="10" cols="160"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-4 Right">
							<button type="button" class="btn btn-success" onclick="fn_boardRegister();">글작성</button>
						</div>
					</div>
				</div>
		</form>
	</div>
</main>

<!-- Footer -->
<jsp:include page="../common/footer.jsp" flush="false"/>

</body>
</html>







