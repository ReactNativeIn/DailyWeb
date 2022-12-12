<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 수정</title>
	<script src="${contextPath}/resources/js/board.js"></script>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/header.jsp" flush="false"/>


<h1>게시글 수정</h1>


<div class="container">
	<form class="form-horizontal" id="frm">
		<div class="form-group">
			<div>
				<h2 align="center">게시글 수정</h2>
			</div>
		</div>
		<div class="form-group">
			<label for="subject" class="col-sm-2 control-label">제  목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="b_title" name="b_title" maxlength="200" value="${boardDetail.b_title}"/>
				<!-- 게시글 번호가 없으면 게시글 수정을 어떤 것으로 할지 알 수가 없으므로 hidden으로 숨겨 놓는다. -->
				<!-- <input type="hidden" class="form-control" id="bno" name="bno" value-"${boardDetail.bno}"/> -->
			</div>
		</div>
		<div class="form-group">
			<label for="subject" class="col-sm-2 control-label">번  호</label>
			<div class="col-sm-1">
				<input type="text" class="form-control" id="board_id" name="board_id" value="${boardDetail.board_id}" readonly/>
			</div>
		</div>
		<div class="form-group">
			<label for="subject" class="col-sm-2 control-label">글쓴이</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="id" name="id" maxlength="20" value="${boardDetail.id}"/>
			</div>
		</div>
		<div class="form-group">
			<label for="subject" class="col-sm-2 control-label">내 용</label>
			<div class="col-sm-10">
				<textarea rows="10" cols="160" class="form-control" id="b_content" name="b_content">${boardDetail.b_content}</textarea>
			</div>
		</div>
		<div class="form-group">
			<p align="center">
				<button type="reset"  class="btn btn-warning">
					<span class="glyphicon glyphicon-erase"> 다시 입력</span>
				</button>
				<button type="button" class="btn btn-primary" onclick="fn_boardUpdate();">
					<span class="glyphicon glyphicon-pencil"> 게시글 수정</span>
				</button>
				<button type="button" class="btn btn-info" onclick="location.href='${contextPath}/board/boardList'">
					<span class="glyphicon glyphicon-list-alt"> 게시글 목록</span>
				</button>
			</p>
		</div>
	</form>
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

</body>
</html>

















