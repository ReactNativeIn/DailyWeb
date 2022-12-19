<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<script src="/resources/js/member.js"></script>

<p>기존 비밀번호</p>

<input class="w-50" type="text" placeholder="이름" value="${member.password }" disabled="disabled">
<br/><br/><hr>
<p>바꿀 비밀번호</p>

	<input class="w-50" type="text" id="repassword" placeholder="입력란"  value="" />
	<button type="button"  class="btn btn-primary" onclick="fn_memberRegister1();">바꾸기</button>

</body>
</html>