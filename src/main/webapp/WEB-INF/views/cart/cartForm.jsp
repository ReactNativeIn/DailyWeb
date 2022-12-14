<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/cart.css">

</head>

<body>

<!-- 메뉴바 -->
<jsp:include page="../common/header.jsp" flush="false"/>

<div class="wrapper">
	<div class="wrap">
		<div class="content_area">
			<div class="content_subject"><span>장바구니</span></div>
			<!-- 장바구니 리스트 -->
			<div class="img_middle_section">
				<img src="${contextPath}/resources/images/men_outer_19.jpg"/>
				<img src="${contextPath}/resources/images/women_outer.jpg"/>
				<img src="${contextPath}/resources/images/men_outer_20.jpg"/>
				<img src="${contextPath}/resources/images/women_bottom.jpg"/>
			</div>

			<!-- cartInfo -->
			<div class="content_totalCount_section">
				<!-- 체크박스 전체 여부 -->
				<div class="all_check_input_div">
					<input type="checkbox" class="all_check_input input_size_20" checked="checked"><span class="all_chcek_span">전체선택</span>
				</div>
				<table class="subject_table">
					<caption>표 제목 부분</caption>
					<tbody>

						<tr>
							<th class="td_width_1"></th>
							<th class="td_width_2"></th>
							<th class="td_width_3">상품명</th>
							<th class="td_width_4">금액</th>
							<th class="td_width_4">수량</th>
							<th class="td_width_4">합계</th>
							<th class="td_width_4">삭제</th>
						</tr>
					</tbody>
				</table>
				<table class="cart_table">
					<caption>표 내용 부분</caption>
					<tbody>
						<c:forEach items="${cartInfo}" var="cartInfo">
							<tr>
								<td class="td_width_1 cart_info_td">
									<input type="checkbox" class="individual_cart_checkbox input_size_20" checked="checked" role="cartItemCheckbox">
									<input type="hidden" class="individual_p_price_input" value="${cartInfo.p_price}">
									<input type="hidden" class="individual_ci_number_input" value="${cartInfo.ci_number}">
									<input type="hidden" class="individual_totalPrice_input" value="${cartInfo.p_price * cartInfo.ci_number}">
									<input type="hidden" class="individual_point_input" value="${cartInfo.point}">
									<input type="hidden" class="individual_totalPoint_input" value="${cartInfo.totalPoint}">
								</td>
								<td class="td_width_2"></td>
								<td class="td_width_3">${cartInfo.p_name}</td>
								<td class="td_width_4 price_td">
									금액 : <fmt:formatNumber value="${cartInfo.p_price}" pattern="#,### 원" /><br>
									마일리지 : <span class="green_color"><fmt:formatNumber value="${cartInfo.point}" pattern="#,###" /></span>
								</td>
								<td class="td_width_4 table_text_align_center">
									<div class="table_text_align_center quantity_div">
										<input type="text" value="${cartInfo.ci_number}" class="quantity_input">	
										<button class="quantity_btn plus_btn">+</button>
										<button class="quantity_btn minus_btn">-</button>
									</div>
									<a class="quantity_modify_btn" data-name="${cartInfo.cartItem_id}">변경</a>
								</td>
								<td class="td_width_4 table_text_align_center">
									<fmt:formatNumber value="${cartInfo.p_price * cartInfo.ci_number}" pattern="#,### 원" />
								</td>
								<td class="td_width_4 table_text_align_center">
									<button class="delete_btn" data-name1="${cartInfo.cartItem_id}">삭제</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="list_table">
				</table>
			</div>
			<!-- 가격 종합 -->
			<div class="content_total_section">
				<div class="total_wrap">
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td>총 상품 가격</td>
										<td>
											<span class="totalPrice_span"></span> 원
										</td>
									</tr>
									<tr>
										<td>배송비</td>
										<td>
											<span class="delivery_price"></span>원
										</td>
									</tr>									
									<tr>
										<td>총 주문 상품수</td>
										<td><span class="totalCount_span"></span>개</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</table>							
							</td>
						</tr>
					</table>
					<div class="boundary_div">구분선</div>
					<table>
						<tr>
							<td>
								<table>
									<tbody>
										<tr>
											<td>
												<strong>총 결제 예상 금액</strong>
											</td>
											<td>
												<span class="finalTotalPrice_span"></span> 원
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td>
								<table>
									<tbody>
										<tr>
											<td>
												<strong>총 적립 예상 마일리지</strong>
											
											</td>
											<td>
												<span class="totalPoint_span"></span> 원
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 구매 버튼 영역 -->
			<div class="content_btn_section">
				<a href="#">주문하기</a>
				<a href="#">쇼핑계속하기</a>
			</div>
			<div class="boundary_div">구분선</div>
			<form>
				<p><b>장바구니 이용 안내</b></p>
				<div class="boundary_div">구분선</div>
				<p>⊙ 선택하신 상품의 수량을 변경하시려면 수량 변경 후 <b>[변경]</b> 버튼을 누르시면 됩니다.</p>
				<p>⊙ 선택하신 상품을 장바구니에서 삭제하시려면 <b>[삭제]</b> 버튼을 누르시면 됩니다.</p>
				<p>⊙ <b>[쇼핑계속하기]</b> 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</p>
				<p>⊙ <b>[주문하기]</b> 버튼을 누르시면 장바구니에 추가한 상품을 구매하실 수 있습니다.</p>
				<p>⊙ 장바구니에 상품이 맞게 추가되었는지 한 번 더 확인하셔 주시기 바랍니다.</p>
				<p>⊙ 구매하시고자 하는 상품의 총 금액이 <b>30,000원</b> 이상이면 배송비는 <b>무료</b>입니다.</p>
			</form>
			<div class="boundary_div">구분선</div>
		</div>
	</div>
</div>

<!-- 수량 조정 form -->
<form action="/cart/update" method="post" class="quantity_update_form" id="quantity_update_form">
	<input type="hidden" name="cartItem_id" class="update_cartItem_id" id="update_cartItem_id">
	<input type="hidden" name="ci_number" class="update_ci_number" id="update_ci_number">
</form>  

<!-- 삭제 form -->
<form action="/cart/delete" method="post" class="quantity_delete_form">
	<input type="hidden" name="cartItem_id" class="delete_cartItem_id">
</form>
 
<!-- 푸터영역 -->
<jsp:include page="../common/footer.jsp" flush="false"/>
  
    
    
</body>

<script>

$(document).ready(function(){
	
	/* 종합 정보 섹션 정보 삽입 */
	setTotalInfo();
	
});

/* 체크여부에따른 종합 정보 변화 */
// '종합 정보 섹션' 정보들 업데이트되도록 setTotalInfo() 메서드 호출
$(".individual_cart_checkbox").on("change", function(){
	/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
	setTotalInfo($(".cart_info_td"));
});

/* 체크박스 전체 선택 */
$(".all_check_input").on("click", function(){
	/* 체크박스 체크/해제 */
	if($(".all_check_input").prop("checked")){
		$(".individual_cart_checkbox").attr("checked", true);
	} else{
		$(".individual_cart_checkbox").attr("checked", false);
	}
	/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
	setTotalInfo($(".cart_info_td"));
});


/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
function setTotalInfo(){
	
	let totalPrice 		= 0;	// 총 가격	
	let totalCount		= 0;	// 총 개수
	let totalPoint 		= 0;	// 총 포인트 점수
	let deliveryPrice 	= 0;	// 배송비
	let finalTotalPrice = 0;	// 최종 가격(총 가격 + 배송비)
	
	// element(<td> 객체)에 있는 체크박스('.individual_cart_checkbox' <input>)가 
	// checked 상태일 때 true이면 참이된다는 의미
	$(".cart_info_td").each(function(index, element){
		
		if($(element).find(".individual_cart_checkbox").is(":checked") === true) {
			
			// 총 가격
			totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
			// 총 개수
			totalCount += parseInt($(element).find(".individual_ci_number_input").val());
			// 총 마일리지
			totalPoint += parseInt($(element).find(".individual_totalPoint_input").val());
			
		}

	});
	
	/* 배송비 결정 */
	if(totalPrice >= 30000){
		deliveryPrice = 0;
	} else if(totalPrice == 0){
		deliveryPrice = 0;
	} else {
		deliveryPrice = 3000;	
	}
	
	/* 최종 가격 */
	finalTotalPrice = totalPrice + deliveryPrice;
	
	/* 값 삽입 */
	// 총 가격
	$(".totalPrice_span").text(totalPrice.toLocaleString());
	// 총 갯수
	$(".totalCount_span").text(totalCount);
	// 총 마일리지
	$(".totalPoint_span").text(totalPoint.toLocaleString());
	// 배송비
	$(".delivery_price").text(deliveryPrice);	
	// 최종 가격(총 가격 + 배송비)
	$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());
	
};

/* 수량버튼 */
$(".plus_btn").on("click", function(){
	let quantity = $(this).parent("div").find("input").val();
	$(this).parent("div").find("input").val(++quantity);
});
$(".minus_btn").on("click", function(){
	let quantity = $(this).parent("div").find("input").val();
	if(quantity > 1){
		$(this).parent("div").find("input").val(--quantity);		
	}
});

/* 수량 수정 버튼 */
$(".quantity_modify_btn").on("click", function(){
	
	let cartItem_id = $(this).data("name");
	let ci_number 	= $(this).parent("td").find("input").val();
	

	$(".update_cartItem_id").val(cartItem_id);
	$(".update_ci_number").val(ci_number);
	$(".quantity_update_form").submit();
	
});

/* 장바구니 삭제 버튼 */
$(".delete_btn").on("click", function(e){
	e.preventDefault();
	const cartItem_id = $(this).data("name1");
	$(".delete_cartItem_id").val(cartItem_id);
	$(".quantity_delete_form").submit();
	
	alert("확인");
});


// 선택된 상품을 리스트로 만들어서 orderController의 postOrder에서 처리하게 됨
// 아직 수정해야됨 적용 ㄴㄴ
function fn_buySelectedItem() {
  //post요청을 보낼 form 생성
  let newForm = document.createElement("form");
  newForm.setAttribute("method", "Post");
  newForm.setAttribute("action", "${contextPath}/orders/payment");
  newForm.setAttribute("enctype", "application/x-www-form-urlencoded");

  $("input[role='cartItemCheckbox']:checked").each(function (index) {
    let hiddenInputId = document.createElement("input");
    let hiddenInputSize = document.createElement("input");
    let hiddenInputCount = document.createElement("input");
    //카트아이템을 인풋에 저장된 밸류를 통해서 가져옴
    let cartItemId = $(this).val();
    //가져온 카트 아이템 아이디로 개수와 사이즈를 가져옴
    let itemSize = $("#size_" + cartItemId).text();
    let itemCount = $("#resultQuantity_" + cartItemId).text();
    let itemCountNum = Number(itemCount);
    //cartId를 cartItemVOList의 변수로 넘겨줌
    hiddenInputId.setAttribute("type", "hidden");
    hiddenInputId.setAttribute("name", "cartItemVOList[" + index + "].id");
    hiddenInputId.setAttribute("value", cartItemId);

    newForm.append(hiddenInputId);

    hiddenInputSize.setAttribute("type", "hidden");
    hiddenInputSize.setAttribute(
      "name",
      "cartItemVOList[" + index + "].productSize"
    );
    hiddenInputSize.setAttribute("value", itemSize);

    newForm.append(hiddenInputSize);

    hiddenInputCount.setAttribute("type", "hidden");
    hiddenInputCount.setAttribute(
      "name",
      "cartItemVOList[" + index + "].productCount"
    );
    hiddenInputCount.setAttribute("value", itemCount);

    newForm.append(hiddenInputCount);
  });
  document.body.append(newForm);
  newForm.submit();
}
</script>





</html>