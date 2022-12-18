<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Home</title>
</head>

<style>
	
	p {
		height: 30;
	}
	#finalInfo {
		border: 6px solid #808080;
		padding: 40px;
	}
	
	tr>th {
		border-bottom: 7px solid black;
	}
	
	.dontTouch {
		pointer-events: none;
	}
	
	
</style>
<body>
	<!-- 헤더 -->
	<jsp:include page="../common/header.jsp" flush="false"/>
	
	<main style="margin-top:60px;">
		<div class="container">
			<p><b>주문상품정보</b></p>
			
			<!-- 상품정보 상품에 대한 정보를 DB에서 받아와 보여줌 -->
			<form class="form-horizontal">
				<table class="table">
					<thead>
						<tr>
							<th colspan="2" class="col-sm-4 text-center">상품정보</th>
							<th class="col-sm-1 text-center">상품단일금액</th>
							<th class="col-sm-1 text-center">수량</th>
							<th class="col-sm-1 text-center">총금액</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${product}">
						
						
						
						
							<div>
							<input type="hidden" role="t_price" value="${product.p_price}"/>
							<input type="hidden" role="pNUM" value="${product.ci_number}"/>
							<input type="hidden" role="cartItem_id" value="${product.cartItem_id}"/>
							<input type="hidden" id="hidden_sum_${cartItem_id}" />
							<input type="hidden" role="product_id" value="${product.product_id}"/>
							</div>
							
							<tr valign="middle">
								<td class="col-md-1 text-center">
									<c:choose>
										<c:when test="${product.fileList == null || product.fileList == '[]'}">
											<img class="img-thumbnail border-2 w-75" src="/resources/images/noImage.png"/>
										</c:when>
										<c:otherwise>
											<img class="img-thumbnail border-2 w-75" src="/util/upload/displayFile?fileName=${product.fileList[0].file_path }${product.fileList[0].file_s_name}"/>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${product.p_name}</td>
								
								<td align="right"><fmt:formatNumber value="${product.p_price}" pattern="#,### 원" /></td>
								<td align="right"><input role="productNum" class="col-md-6" data-name="${product.cartItem_id}" type="number" value="${product.ci_number}" id="ci_number_${product.cartItem_id}" name="ci_number" min="1"/></td>
								<td align="right"><input id="p_price_${product.cartItem_id}" style="text-align:right; border: none; background: transparent;" value="${product.p_price * product.ci_number}" class="dontTouch"/> 원</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			
			<!-- 결제정보 상품 가격에 대한 정보를 DB에서 받아와 보여줌 -->
			<p><b>결제정보</b></p>
			<form class="form-horizontal">
				<div id="finalInfo">
					<h2>
					<span><input id="p_price" style="text-align:right; border: none; background: transparent;" class="dontTouch col-md-2"/></span>
					<i class="fa-solid fa-plus"></i>
					<span><input id="d_price" style="text-align:right; border: none; background: transparent;" class="dontTouch col-md-2"/></span>
					<i class="fa-solid fa-equals"></i>
					<span><input id="t_price" style="text-align:right; border: none; background: transparent;" class="dontTouch col-md-2"/> 원</span>
					</h2>
				</div>
			</form>
			
			<!-- 배송지정보, 입력한 데이터를 DB에 저장 -->
			<p><b>배송 정보</b></p>
			<form class="form-horizontal">
				<table class="table table-bordered table-hover">
						<tr>
							<td class="col-md-2 text-left align-middle">주문하시는 분</td>
							<td class="col-md-10">
								<div class="dontTouch col-md-3">
									<input type="text" class="form-control" id="ordererName" name="ordererName" value="${member.name}" readonly/>
								</div>
							</td>
						</tr>
						<tr>
							 <td class="col-md-3 text-left align-middle">주문 이메일</td>
							 <td>
							 	<div class="dontTouch col-md-5">
									<input type="email" class="form-control" id="email" name="email" value="test@test.com" readonly/>
								</div>
							 </td>
						</tr>
						<tr>
							 <td class="col-md-3 text-left align-middle">주문 연락처</td>
							 <td class="dontTouch">
								<input style="width: 81px;" type="text" id="ordererNumber1" name="ordererNumber1" title="주문자 앞번호" maxlength="3" value="010" readonly>
								&nbsp;-&nbsp;
								<input style="width: 81px;" type="text" id="ordererNumber2" name="ordererNumber2" title="주문자 중간번호" maxlength="4" value="3456" readonly>
								&nbsp;-&nbsp;
								<input style="width: 81px;" type="text" id="ordererNumber3" name="ordererNumber3" title="주문자 뒷번호" maxlength="4" value="7890" readonly>
							</td>
						</tr>
						<tr>
							 <td class="col-md-2 text-left align-middle">받으시는 분</td>
							 <td class="col-md-10">
							 	<div class="d-flex align-items-center">
									<div class="col-md-3">
										<input type="text" class="form-control" id="receiverName" name="receiverName" maxlength="10" 
										value=""/>
									</div>
									&nbsp;&nbsp;&nbsp;
									<label>
										<input type="checkbox" id="equal_ordererName" name="equal_ordererName"/>
									주문하시는 분과 동일합니다.
									</label>
							 	</div>
							</td>
						</tr>
						<tr>
							 <td class="col-md-3 text-left align-middle">받으시는 분 주소</td>
							 <td>
							 	<!-- 주소찾기 구분1 -->
							 	<div class="address1" style="padding-bottom: 8px;">
								 	<input readonly id="zipcode" name="zipcode" type="text" style="cursor:pointer;" maxLength="8"/>
								 	<button type="button" onclick="daumZipCode()">우편번호 찾기</button>
							 	</div>
							 	<!-- 주소찾기 구분2 -->
							 	<div class="address2">
							 		<input readonly id="address01" name="address01" class="col-md-5" type="text" style="cursor:pointer;" value=""/>
							 		<input id="address02" name="address02"type="text" class="col-md-6" placeholder="상세주소"/>
							 	</div>
							 </td>
						</tr>
						<tr>
							 <td class="col-md-3 text-left align-middle">받으시는 분 연락처</td>
							 <td>
								<select style="padding-left:4px; width: 71px; appearance:none;" name="receiverNumber1" id="receiverNumber1" title="주문자 앞번호">
									<option value='010' >010</option>
									<option value='011' >011</option>
									<option value='016' >016</option>
									<option value='017' >017</option>
									<option value='018' >018</option>
									<option value='019' >019</option>
								</select>
								&nbsp;-&nbsp;
								<input style="width: 81px;" type="text" id="receiverNumber2" name="receiverNumber2" title="주문자 중간번호" maxlength="4" value="">
								&nbsp;-&nbsp;
								<input style="width: 81px;" type="text" id="receiverNumber3" name="receiverNumber3" title="주문자 뒷번호" maxlength="4" value="">
								&nbsp;&nbsp;&nbsp;
								<label>
									<input type="checkbox" id="equal_ordererNumber" name="equal_ordererNumber"/>
								주문 연락처와 동일합니다.
								</label>
							</td>
						</tr>
						<tr>
							 <td class="col-md-3 text-left align-middle">주문 요청사항(선택)</td>
							 <td><input class="form-control" type="text" id="request" name="request" maxLength="200" placeholder="요청 사항을 적어주세요 (200자 이내)"/></td>
						</tr>
				</table>
				<input type='hidden' id ="id" name="id" value="${member.id}"/>
				<input type="hidden" id ="point" name="point" value="${member.point}"/>
				<div  class=" row d-flex justify-content-center align-content-center">
					<button type="button" class="btn btn-primary col-3 btn-lg" onclick="payment();">
						결제하기
					</button>
				</div>
			</form>
		</div>

		
	</main>
	
	
	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	

</body>
<!-- 다음 주소 가져오기 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<script>
	// 처음 화면이 로딩됐을때...
	let total = 0;
	$("input[role='t_price']").each(function () {
		let product_price = parseInt($(this).val());
		let cartNum = parseInt($(this).parent("div").find("input[role='cartItem_id']").val());
		let product_number = document.getElementById('ci_number_' + cartNum).value;
		
		
		
		$("#p_price_" + cartNum).val(product_price * product_number);
		
	    total += parseInt(product_price * product_number);
	    
	 	// 상품들 총 금액
		$('#p_price').val(total);
		
		let p_price = $('#p_price').val();
		
		// 배달비
		if(total < 30000) {
			$('#d_price').val(4000);
		} else {
			$('#d_price').val("배송비 (무료)");
		}
		
		let d_price = $('#d_price').val();
		
		// 총 금액 (배달비 + 상품들 총 금액)
		
		// 배달비가 없다면
		if(isNaN(d_price)) {
			$('#t_price').val($('#p_price').val());
		} else {
			$('#t_price').val(parseInt(p_price) + parseInt(d_price));		
		}
		
	});
	
	// 구매개수에 변화가 생겼을때...
	$("input[role='productNum']").change(function() {
		let total = 0;
		$("input[role='t_price']").each(function () {
			let product_price = parseInt($(this).val());
			let cartNum = parseInt($(this).parent("div").find("input[role='cartItem_id']").val());
			let product_number = document.getElementById('ci_number_' + cartNum).value;
			
			
			
			$("#p_price_" + cartNum).val(product_price * product_number);
			
		    total += parseInt(product_price * product_number);
		    
		 	// 상품들 총 금액
			$('#p_price').val(total);
			
			let p_price = $('#p_price').val();
			
			// 배달비
			if(total < 30000) {
				$('#d_price').val(4000);
			} else {
				$('#d_price').val("배송비 (무료)");
			}
			
			let d_price = $('#d_price').val();
			
			// 총 금액 (배달비 + 상품들 총 금액)
			
			// 배달비가 없다면
			if(isNaN(d_price)) {
				$('#t_price').val($('#p_price').val());
			} else {
				$('#t_price').val(parseInt(p_price) + parseInt(d_price));		
			}
			
		});
	});
	
	

		//post요청을 보낼 form 생성
	    let newForm = document.createElement("form");
	    newForm.setAttribute("method", "Post");
	    newForm.setAttribute("action", "${contextPath}/orders/orderComplete");
	    newForm.setAttribute("enctype", "application/x-www-form-urlencoded");

	    $("input[role='cartItem_id']").each(function (index) {
			let hiddenInputProductId = document.createElement("input");	// product_id
			let hiddenInputCount = document.createElement("input");		// oi_number
																		// 상품 가격
	      																// 배달비?
	      																
	      // 카트아이템을 인풋에 저장된 밸류를 통해서 가져옴
	      let cartItemId	= $(this).val();	// cartItem_id
	      
	      // 상품 아이디에 대한 정보를 가져옴
	      let productId		= $(this).parent("div").find("input[role='product_id']").val();
	      
	      let itemCount = $("#ci_number_" + cartItemId).val();	// 개수
	      
	      //cartId를 cartItemDTList의 변수로 넘겨줌
	      hiddenInputProductId.setAttribute("type", "hidden");
	      hiddenInputProductId.setAttribute("name", "ordersItemVO[" + index + "].product_id");
	      hiddenInputProductId.setAttribute("value", productId);
	      
	      hiddenInputCount.setAttribute("type", "hidden");
	      hiddenInputCount.setAttribute("name", "ordersItemVO[" + index + "].oi_number");
	      hiddenInputCount.setAttribute("value", itemCount);
	      

		  newForm.append(hiddenInputProductId);
	      newForm.append(hiddenInputCount);
	    });
	    document.body.append(newForm);
	
</script>

<!-- 함수 정의 -->
<script src="/resources/js/payment.js"></script>

</html>