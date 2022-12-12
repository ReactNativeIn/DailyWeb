<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 상세 조회</title>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../common/header.jsp" flush="false"/>

<main>
	<div class="container">
		<form class="form-horizontal" id="frm">
			<div class="form-group">
				<div>
					<h2 align="center">게시글 상세 조회</h2>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">제  목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="subject" name="subject" maxlength="200" value="${boardDetail.b_title}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">글쓴이</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="id" name="id" maxlength="20" value="${boardDetail.id}" disabled readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">내  용</label>
				<div class="col-sm-10">
					<textarea rows="10" cols="160" class="form-control" id="b_content" name="b_content" readonly>${boardDetail.b_content}</textarea>
				</div>
			</div>
			<div class="form-group">
				<p align="center">
					<button type="button" class="btn btn-primary" onclick="location.href='/board/boardList'">
						<span class="glyphicon glyphicon-list-alt"> 게시글 목록</span>
					</button>
					<button type="button" class="btn btn-warning" onclick="fn_boardUpdateForm(${boardDetail.board_id})">
						<span class="glyphicon glyphicon-pencil"> 게시글 수정</span>
					</button>
					<button type="button" class="btn btn-danger" onclick="fn_boardDelete(${boardDetail.board_id})">
						<span class="glyphicon glyphicon-erase"> 게시글 삭제</span>
					</button>
				</p>
			</div>
		</form>
	</div>
</main>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

	<script src="/resources/js/board.js"></script>
</body>
</html>

















