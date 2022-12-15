<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>상품 선택 화면</title>
	<style>
		@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
		
		main {
			font-family: 'Jeju Gothic', sans-serif;
		}
		#con {
			border-bottom: 3px solid rgba(220, 220, 220, 1);
			padding-bottom: 10px;
		}
		#con2 {
			flex: 1;
			min-width: 450px;
		}
		#img_thumb li {
			cursor: pointer;
		}
		#product_option_wrap {
			display: flex; 
			border-bottom:solid; 
			padding-top: 5px; 
			border-color:darkgreen; 
			margin:30px 0px; 
			justify-content: space-between;
		}
	</style>
	
</head>

<body>

	<!-- 상단 메뉴 -->
	<jsp:include page="../common/header.jsp" flush="false"/>

	<main class="container">
		<div id="con" class="d-flex flex-wrap">
			<div id="con1">
				<c:choose>
					<c:when test="${productDetail.fileList == null || productDetail.fileList == '[]' }">
						<img class="img-reactive img-shadow" src="/resources/images/noImage.png"
						id="img_main"  style="width: 500px; height: 500px; margin-top: 30px; margin-left: 50px; margin-right: 50px;"/>
					</c:when>
					<c:otherwise>
						<img class="img-reactive img-shadow" src="/util/upload/displayFile?fileName=${productDetail.fileList[0].file_path }${productDetail.fileList[0].file_s_name}"
						id="img_main"  style="width: 500px; height: 500px; margin-top: 30px; margin-left: 50px; margin-right: 50px;"/>
						<div style="margin-left:34px; padding:0">
							<ul id="img_thumb" class="list-group list-group-horizontal list-group-flush p-0">
								<c:forEach var="item" items="${productDetail.fileList }">
									<li class="list-group-item m-2" >
										<img src="/util/upload/displayFile?fileName=${item.file_path }${item.file_s_name}"
											class="img-thumnail" style="width: 70px; height: 70px;"/>							
									</li>
								</c:forEach>							
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="con2" class="m-auto">
				<h3 style="border-bottom:solid; padding-bottom: 5px; border-color:darkgreen;">${productDetail.p_name}</h3>
				<div style="width:260; height:280;">
					<h4 style="margin-top:3px;">가격: <fmt:formatNumber value="${productDetail.p_price}" /></h4>
					
					<select id="selectColor" class="form-select" style="margin-bottom: 15px" onchange="callSize();">
						<option id="colorNull" value="-1" hidden="hidden">색상을 선택해주세요</option>
						<c:forEach items="${productDetail.colorList }" var="color" varStatus="i">
							<option value="${color.color_id }">${color.color }</option>
						</c:forEach>
					</select>
					
					<select id="selectSize" class="form-select" onchange="selectSize();">
						<option id="sizeNull" value="-1" hidden="hidden">사이즈를 선택해주세요</option>
						<c:forEach items="${productDetail.sizeList }" var="size">
							<option class="${size.color_id }" value="${size.size_id }" hidden="hidden">${size.size }</option>
						</c:forEach>
					</select>
					
				</div>
				
				
				<div id="product_option_wrap">
					<div class="btn-group" role="group" aria-label="Basic outlined example">
						<button type="button" class="plus_btn btn btn-outline-dark btn-lg">+</button>					  	
						<input id="order_num" class="quantity_input" type="number" value="1" min="1" max="10"/>
						<button type="button" class="minus_btn btn btn-outline-dark btn-lg">-</button>
					</div>
					<div style="display: inline-block;">
						<span class="fs-4" id="sum"><fmt:formatNumber value="${productDetail.p_price}" />원</span>															
					</div>
				</div>
				
				
				
				<div style="border-bottom:solid; padding-bottom: 5px; border-color:darkgreen; margin-bottom: 5px;">
					<h4 class="mb-2" id="sumAll">총 상품 금액 : <fmt:formatNumber value="${productDetail.p_price}" />원</h4>
				</div>
				<div class="container align-items-center" id="order_btn" style="margin-top: 5px;">
					<p class="m-0"> <a href="cart/cartForm" class="btn btn-light btn_cart"> 장바구니 &raquo;</a>
						<a href="./products.jsp" class="btn btn-success">바로 구매 &raquo;</a>
				</div>
			</div>
		</div>
		
		<!-- 상품 설명 -->
		<div class="text-center p-5">
			${productDetail.p_explanation }
		</div>
	</main>
	
	<!-- 위, 아래 네비 -->
	<div class="text-end fixed-bottom" style="margin: 0px 30px 50px 0px;">
		<a href="#top" id="right_top_btn" title="위로가기" class="btn btn-light">▲</a>
		<a href="#footer" id="right_bot_btn" title="아래로가기" class="btn btn-light">▼</a>
	</div>
		
	<!-- 하단 메뉴 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	
</body>
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
				var value_num = document.getElementById("order_num").value;	// 주문 수량
				 
				var num = value_num * price;
			  	format = num.toLocaleString(); // 천의 자리마다 (,)콤마 넣어준다.
			  	value_sum.innerHTML = (format + "원");
			  	value_sumAll.innerHTML = "총 상품 금액 : " + value_sum.innerHTML;
			});
			 
			 
			// 썸네일을 메인이미지로 변경
			$("#img_thumb").on("click", "li", function(event){
				var th_src = $(this).children().attr("src");
				$("#img_main").attr("src", th_src);
			});
			 
			// 수량 버튼 조작
			let quantity = $(".quantity_input").val();
			
			$(".plus_btn").on("click", function(){
				console.log($("#order_num"));
				if(quantity < 10){
					$(".quantity_input").val(++quantity);					
				}
			});

			$(".minus_btn").on("click", function(){
				if(quantity > 1){
					$(".quantity_input").val(--quantity);	
				}
			});
			 
		});
	
	</script>
</html>