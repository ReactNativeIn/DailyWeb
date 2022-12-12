<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록</title>
	
<style>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
	
	h2 {
	}
	#h2-logo {
	margin-top: 150px;
	margin-bottom: 60px;
	border-top: solid;
	border-bottom: solid;
	border-top-width: 0px;
	border-bottom-width: 3px;
	padding-bottom: 30px;
	}
	#board-register {
	margin-bottom: 60px;
	}
	main {
	font-family: 'Jeju Gothic', sans-serif;
	}
</style>
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
	<section>
		<div class="container">
			<form class="form-horizontal" id="frm">
				<div class="form-group">
					<div>
						<h2 id="h2-logo">게시글 목록</h2>
					</div>
				</div>
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr class="info">
							<th class="col-sm-1 text-center">번호</th>
							<th class="col-sm-2 text-center">제    목</th>
							<th class="col-sm-3 text-center">내    용</th>
							<th class="col-sm-1 text-center">글쓴이</th>
							<th class="col-sm-2 text-center">작성일자</th>
							<th class="col-sm-1 text-center">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${boardList}">
							<tr>
								<td align="center">${list.board_id}</td>
								<td align="center"><a href="/board/boardDetail?board_id=${list.board_id}">${list.b_title}</a></td>
								<td align="center">${list.b_content}</td>
								<td align="center" style="color:gray;">${list.id}</td>
								<td align="center" style="color:gray;"><fmt:formatDate value="${list.b_enroll}" pattern="yyyy년 MM월 dd일"/></td>
								<td align="center">${list.b_state}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<p id="board-register" align="right">
					<button type="button" class="btn btn-success" onclick="location.href='/board/boardRegisterForm'">게시글 쓰기</button>
				</p>

			</form>
		</div>
	</section>
</main>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>
</body>
</html>













