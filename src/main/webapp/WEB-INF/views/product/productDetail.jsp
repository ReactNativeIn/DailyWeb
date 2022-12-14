<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>상품 선택 화면</title>

</head>
<style>
	@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
	
	main {
		font-family: 'Jeju Gothic', sans-serif;
	}
	
</style>

<body>

	<!-- 상단 메뉴 -->
	
	<jsp:include page="../common/header.jsp" flush="false"/>


<main class="container">
	<div class="d-flex mt-5">
		<div class="conatiner text-align-center" id="con1">
			<c:choose>
				<c:when test="${productDetail.fileList == null || productDetail.fileList == '[]' }">
					<img class="img-reactive img-shadow" src="/resources/images/noImage.png"
					id="img_main"  style="width: 500px; height: 500px; margin-top: 30px; margin-left: 50px; margin-right: 50px;"/>
				</c:when>
				<c:otherwise>
					<img class="img-reactive img-shadow" src="/util/upload/displayFile?fileName=${productDetail.fileList[0].file_path }${productDetail.fileList[0].file_s_name}"
					id="img_main"  style="width: 500px; height: 500px; margin-top: 30px; margin-left: 50px; margin-right: 50px;"/>
					<div class="container" style="margin-left:34px; padding:0">
						<ul class="list-group list-group-horizontal list-group-flush p-0" id="product_thumb">
							<c:forEach var="item" items="${productDetail.fileList }">
								<li class="list-group-item p-1" >
									<button style="border:none; padding:0px;" onclick="changeImg()">
										<img src="/util/upload/displayFile?fileName=${item.file_path }${item.file_s_name}" id="img_thumb" 
											class="img-thumnail" style="width: 70px; height: 70px;"/>
									</button>							
								</li>
							</c:forEach>							
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="container d-flex align-items-center">
			<div id="con2">
				<div class="jumbotron" id="item_name">
					<div class="container">
						<h1 class="display-3" style="border-bottom:solid; padding-bottom: 5px; border-color:darkgreen;">${productDetail.p_name}</h1>
				<div class="container ">
					<div class="row">
						<div class="col-md-12" style="width:260; height:280;">
							<h3 style="margin-top:3px;"><fmt:formatNumber value="${productDetail.p_price}" /></h3>
							<div>적립 포인트 : <span class="point_span"></span>원</div>
							<p> <b>상품 코드 : <span class="badge bg-danger">new</span> <span class="badge bg-secondary">hit</span></b>
							<p> <b>색상 : </b>
								<select class="form-select" aria-label="Default select example">
								  <option selected>색상을 골라주세요</option>
								  <option value="1">${productDetail.colorList}</option>
								  <option value="2">WHITE</option>
								  <option value="3">GRAY</option>
								</select>
							<p> <b>사이즈 : </b>
								<select class="form-select align-items-center" aria-label="Default select example">
								  <option selected>사이즈를 골라주세요</option>
								  <option value="1">FREE Size</option>

								</select>
							</div>
							<div class="container" style="border-bottom:solid; padding-top: 5px; border-color:darkgreen;">
								<div class="container">
									<b class="mb-2">${productDetail.p_name} - </b>
									<b class="mb-2">Black / M </b>
									<div style="text-align: end;">
										<a class="align-items-center" id="sum"><fmt:formatNumber value="${productDetail.p_price}" /></a>
									  	<input id="order_num" type="number" value="1" min="1" max="10" class="numbox" placeholder="" aria-label="item_num" style="text"/>
									</div>
								</div>
								<div class="input-group mb-2">
								</div>
							</div>
							<div class="container" style="border-bottom:solid; padding-bottom: 5px; border-color:darkgreen; margin-bottom: 5px;">
								<h4 class="mb-2" id="sumAll">총 상품 금액 : <fmt:formatNumber value="${productDetail.p_price}" />원</h4>
							</div>
						</div>
					</div>
					<div class="container align-items-center" id="order_btn" style="margin-top: 5px;">
						<p class="m-0"> <a href="cart/cartForm" class="btn btn-light btn_cart"> 장바구니 &raquo;</a>
							<a href="./products.jsp" class="btn btn-success">바로 구매 &raquo;</a>
					</div>
					</div>
				</div>	
				</div>
			</div>
		</div>
		
		<!-- 상품 info -->
		
		<div class="container">
			<hr class="mt-5 mb-5">
		</div>
		
		<!-- 상품 리뷰 -->
		<div class="container">
		</div>
		
		<div class="sticky-bottom mb-5" style="text-align-last:right;">
			<p style="margin-bottom:0; width:1500px;"><a href="#top" id="right_top_btn" title="위로가기" class="btn btn-light">▲</a>
			<p style="margin-bottom:0; width:1500px;"><a href="#footer" id="right_bot_btn" title="아래로가기" class="btn btn-light">▼</a>
		</div>
		
		<div>
			<hr class="mt-5 mb-5">
		</div>		
	
	</main>

	<!-- 하단 메뉴 -->

		<jsp:include page="../common/footer.jsp" flush="false"/>
		
		<script src="/resources/js/product.js"></script>
		<script>
			$(function(){
				 //--------------------------------------
				 // 가격 계산
				 //--------------------------------------
				 var price = ${productDetail.p_price};
				 
				 var value_sum = document.getElementById("sum");	// 가격 합계 요소
				 var value_sumAll = document.querySelector("#sumAll");	// 총 금액
				 
				 $("#order_num").on("change", function(){
				 	 alert("pp_num");
				 	 var value_num = document.getElementById("order_num").value;	// 주문 수량
					 
					 var num = value_num * price;
					 alert(num);
					 console.log(num);
				  	 format = num.toLocaleString(); // 천의 자리마다 (,)콤마 넣어준다.
				  	 value_sum.innerHTML = (format + "원");
				  	 value_sumAll.innerHTML = "총 상품 금액 : " + value_sum.innerHTML;
				 });
				 
			});
		
		</script>
	
</body>
</html>