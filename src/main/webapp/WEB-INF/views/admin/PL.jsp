<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- 더미데이터 -->
<c:set var="sale" value="" />
<!-- 컨트롤러에서 받아온 Name객체 소문자를 대문자로 -->
<!--<c:set var = "title" value = "${fn:toUpperCase(Name)}" /> -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!--<title>${title}</title> -->
	<title>상품 목록</title>
</head>
	<style>
	.navbar-inverse .navbar-nav > .active > a, 
	.navbar-inverse .navbar-nav > .active > a:focus, 
	.navbar-inverse .navbar-nav > .active > a:hover {
	    color: rgb(255, 255, 255);
	    background-color: red
	}
	</style>
<body>

<!-- 메뉴바 -->
<jsp:include page="../common/otherHeader.jsp" flush="false"/>


<div class="container">

	<div align="center">
		<h2>상품목록</h2>
	</div>
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>제품번호</th>
				<th>품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>등록일자</th>
				<th>팔린 횟수</th>
			</tr>
		</thead>
			<c:forEach var="List" items="${List}">
			<tr>
				<td align="right">${List.product_id}</td>
				<td><a href="${contextPath}/board/boardDetail?bno=${List.product_id}">${product.p_name}</a></td>
				<td>${List.p_explanation}</td>
				<td>${List.p_price}</td>
				<% 
				// <fmt:parseDate value="${board.reg_date}" var="currentDate" pattern="yy/MM/dd"/>
				// <td><fmt:formatDate value="${currentDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/></td>
				%>
				<td><fmt:formatDate value="${List.p_enroll}" pattern="yyyy년 MM월 dd일"/></td>
				<td>${List.p_sell}</td>
			</tr>
			</c:forEach>
		<tbody>
		</tbody>
	</table>
	
	<div class="col-sm-offset-3">
	
		<select id='searchType'>
			<option>검색종류</option>
				<option value="t" <c:if test="${searchType == 't'}">selected</c:if>>제목</option>
				<option value="c" <c:if test="${searchType == 'c'}">selected</c:if>>내용</option>
				<option value="w" <c:if test="${searchType == 'w'}">selected</c:if>>글쓴이</option>
		</select>
		<!-- <input type='text' id='searchKeyword' value="${pageVO.keyword}"> --> 
		<input type='text' id='searchKeyword' value="${keyword}">
		<button id='searchBtn'>Search</button> 
	</div>
	
	<div class="col-sm-offset-3">
		<ul class="btn-group pagination">
		    <c:if test="${pageMaker.prev }">
			    <li>
			        <a href='<c:url value="/product/productList?page=${pageMaker.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
			    </li>
		    </c:if>
		    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
			    <li>
			        <a href='<c:url value="/product/productList?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum }</i></a>
			    </li>
		    </c:forEach>
		    <c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			    <li>
			        <a href='<c:url value="/product/productList?page=${pageMaker.endPage+1 }&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
	                <a href="/product/productList${pageMaker.makeSearch(pageMaker.endPage+1)}&searchType=${searchType}&keyword=${keyword}" class="paginate_button next">다음</a>
			    </li>
		    </c:if>
		</ul>
	</div>
	
	<form id="formList" action="/product/productList" method="get">
		<input type='hidden' name='page'		value="${result.currentPageNum}">
		<input type='hidden' name='size'		value="${result.currentPage.pageSize}">
		<input type='hidden' name='searchType' 	value="${pageVO.type}">
		<input type='hidden' name='keyword'		value="${pageVO.keyword}">
	</form>
	
	
</div>

<script>

$(document).ready(function() {
	
	var formObj = $("#formList");
	
	// 검색 버튼을 눌렀을 경우
	$("#searchBtn").click(function(e){
		
		var typeStr = $("#searchType").find(":selected").val();
		var keywordStr = $("#searchKeyword").val();
		
		console.log(typeStr, "" , keywordStr);
		
		//alert("검색 타입" + typeStr);
		//alert("검색 키워드" + keywordStr);
		//typeStr 	= ${searchType };
		//keywordStr	= ${keyword };
		
		//formObj.find("[name='type']").val(typeStr);
		formObj.find("[name='searchType']").val(typeStr);
		formObj.find("[name='keyword']").val(keywordStr);
		formObj.find("[name='page']").val("1");
		formObj.submit();
	});

});

</script>

</body>

</html>

