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
			<img src="../../../resources/images/product/item/chiwawa01.jpg" id="img_main" class="img-reactive img-shadow" alt="상품 이미지" 
				style="width: 500px; height: 500px; margin-top: 30px; margin-left: 50px; margin-right: 50px;"/>
			<div class="container" style="margin-left:34px; padding:0">
				<ul class="list-group list-group-horizontal list-group-flush p-0" id="product_thumb">
					<li class="list-group-item p-1" >
					<button style="border:none; padding:0px;" onclick="changeImg()">
						<img src="../../../resources/images/product/item/chiwawa01.jpg" id="img_thumb" class="img-thumnail" alt="썸네일01" 
							style="width: 70px; height: 70px;"/>
					</button>
					</li>				
					<li class="list-group-item p-1" >
					<button style="border:none; padding:0px;" onclick="changeImg()">
						<img src="../../../resources/images/product/item/chiwawa02.jpg" id="img_thumb" class="img-thumnail" alt="썸네일02" 
							style="width: 70px; height: 70px;"/>
					</button>
					</li>				
					<li class="list-group-item p-1" >
					<button style="border:none; padding:0px;" onclick="changeImg()">
						<img src="../../../resources/images/product/item/chiwawa03.jpg" id="img_thumb" class="img-thumnail" alt="썸네일03" 
							style="width: 70px; height: 70px;"/>
					</button>
					</li>				
					<li class="list-group-item p-1" >
					<button style="border:none; padding:0px;" onclick="changeImg()">
						<img src="../../../resources/images/product/item/chiwawa04.jpg" id="img_thumb" class="img-thumnail" alt="썸네일04" 
							style="width: 70px; height: 70px;"/>
					</button>
					</li>				
					<li class="list-group-item p-1" >
					<button style="border:none; padding:0px;" onclick="changeImg()">
						<img src="../../../resources/images/product/item/chiwawa05.jpg" id="img_thumb" class="img-thumnail" alt="썸네일05" 
							style="width: 70px; height: 70px;"/>
					</button>
					</li>				
					<li class="list-group-item p-1" >
					<button style="border:none; padding:0px;" onclick="changeImg()">
						<img src="../../../resources/images/product/item/chiwawa06.jpg" id="img_thumb" class="img-thumnail" alt="썸네일06" 
							style="width: 70px; height: 70px;"/>
					</button>
					</li>									
				</ul>
			</div>
		</div>
		<div class="container d-flex align-items-center">
			<div id="con2">
				<div class="jumbotron" id="item_name">
					<div class="container">
						<h1 class="display-3" style="border-bottom:solid; padding-bottom: 5px; border-color:darkgreen;">${productDetail.p_name}</h1>
				<div class="container ">
					<div class="row">
						<div class="col-md-12" style="width:260; height:280;">
							<h3 style="margin-top:3px;">10,000원</h3>
							<p> <b>상품 코드 : <span class="badge bg-danger">new</span> <span class="badge bg-secondary">hit</span></b>
							<p> <b>색상 : </b>
								<select class="form-select" aria-label="Default select example">
								  <option selected>색상을 골라주세요</option>
								  <option value="1">BLACK</option>
								  <option value="2">WHITE</option>
								  <option value="3">GRAY</option>
								</select>
							<p> <b>사이즈 : </b>
								<select class="form-select align-items-center" aria-label="Default select example">
								  <option selected>사이즈를 골라주세요</option>
								  <option value="1">FREE Size</option>

								</select>
							</div>
							<div class="container" style="border-top:solid; border-bottom:solid; padding-top: 5px; border-color:darkgreen;">
								<div class="container">
									<b class="mb-2">멋쟁이 치와와 - </b>
									<b class="mb-2">Black / M </b>
									<div style="text-align: end;">
										<a class="align-items-center" id="sum"><fmt:formatNumber value="${productDetail.p_price}" /></a>
									  	<input id="order_num" type="number" value="1" min="1" max="10" class="numbox" placeholder="" aria-label="item_num" style="text" onchange="pp_num()">
									</div>
								</div>
								<div class="input-group mb-2">
								</div>
							</div>
							<div class="container" style="border-bottom:solid; padding-bottom: 5px; border-color:darkgreen; margin-bottom: 5px;">
								<p> <h4 class="mb-2" id="sumAll">총 상품 금액 : <fmt:formatNumber value="${productDetail.p_price}" /></h4>
							</div>
						</div>
					</div>
					<div class="container align-items-center" id="order_btn" style="margin-top: 5px;">
						<p class="m-0"> <a href="cart/cartForm" class="btn btn-light"> 장바구니 &raquo;</a>
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
			<img src="../../../resources/images/product/info/chiwawa_info01.jpg" id="img_main" class="img-reactive img-shadow" alt="상품 이미지" 
				style="margin-top: 30px; margin-left: 200px;"/>
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
	
	
</body>
</html>