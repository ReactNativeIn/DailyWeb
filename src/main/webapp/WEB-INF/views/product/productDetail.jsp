<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 조회</title>

<link rel="stylesheet" href="/resources/css/productDetail.css">

</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../common/header.jsp" flush="false"/>

<div class="wrapper">
	<div class="wrap">
		<div class="content_area">
			<div class="line">
			</div>			
			<div class="content_top">
				<div class="ct_left_area">
					<div class="image_wrap">
						<img src="/resources/images/women_outer.jpg" alt="women_outer">		
					</div>
				</div>
				<div class="ct_right_area">
					<div class="title">
						<h1>
							${productDetail.p_name}
						</h1>
					</div>
					<div class="line">
					</div>
					<div class="author">
						 <span> 상품 번호 :
						 	${productDetail.product_id}
						 </span>
						 <span>|</span>
						 <span> 판매수량 :
						 	${productDetail.p_sell}
						 </span>
						 <span>|</span>
						 <span class="p_enroll">
						 	${productDetail.p_enroll}
						 </span>
					</div>
					<div class="line">
					</div>	
					<div class="price">
						<div class="p_price">상품 금액 : <fmt:formatNumber value="${productDetail.p_price}" pattern="#,### 원" /></div>
					</div>
					<div>
						적립 포인트 : <span class="point_span"></span>원
					</div>			
					<div class="line">
					</div>	
					<div class="button">						
						<div class="button_quantity">
							주문수량
							<input type="text" class="quantity_input" value="1">
							<span>
								<button class="plus_btn">+</button>
								<button class="minus_btn">-</button>
							</span>
						</div>
						<div class="button_set">
							<a class="btn_cart">장바구니 담기</a>
							<a class="btn_buy">바로구매</a>
						</div>
					</div>
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_middle">
				<div class="product_id">
					${productDetail.product_id}
				</div>
				<div class="p_explanation">
					${productDetail.p_explanation}
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_bottom">
				리뷰
		</div>
	</div>
</div>
</div>


<!-- Footer -->
<jsp:include page="../common/footer.jsp" flush="false"/>
</body>

<script>

$(document).ready(function() {
	
	/* p_enroll */
	const year = "${productDetail.p_enroll}";
	
	let tempYear = year.substr(0,10);
	
	let yearArray = tempYear.split("-")
	let p_enroll = yearArray[0] + "년 " + yearArray[1] + "월 " + yearArray[2] + "일";
	
	$(".p_enroll").html(p_enroll);
	
	/* 포인트 삽입 */
	let Price = "${productDetail.p_price}"
	let point = Price*0.05;
	// Math.floor : 소수점 나머지 버리는 메서드
	point = Math.floor(point);
	$(".point_span").text(point);
	
	
});


// 수량 버튼 조작 -----------------------------------------------------
let quantity = $(".quantity_input").val();

$(".plus_btn").on("click", function(){
	$(".quantity_input").val(++quantity);
});

$(".minus_btn").on("click", function(){
	if(quantity > 1){
		$(".quantity_input").val(--quantity);	
	}
});

const form = {
		product_id	: '${productDetail.product_id}',
		ci_number	: '',
		p_name		: '${productDetail.p_name}',
		cart_id		: 1
}

// 장바구니 추가 버튼 -----------------------------------------------------
$(".btn_cart").on("click", function(e){
	form.ci_number = $(".quantity_input").val();

	$.ajax({
		url: '/cart/add',
		type: 'POST',
		data: form,
		success: function(result){
			if(result == '0'){
				alert("장바구니에 추가를 하지 못하였습니다.");
			} else if(result == '1'){
				alert("장바구니에 추가되었습니다.");
			} else if(result == '2'){
				alert("장바구니에 이미 추가되어져 있습니다.");
			} 
			//cartAlert(result);
		}
	})
});

function cartAlert(result){
}

</script>



</html>