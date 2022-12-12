<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>

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
						<!-- 
							<c:forEach var="productList" items="${list}">
								<tr>
								</tr>
							</c:forEach>
						 -->
						 <tr>
							 <td class="col-sm-1 text-center">상품 그림</td>
							 <td>상품 정보</td>
							 <td align="right">상품가격(p_price)</td>
							 <td align="right">구매개수</td>
							 <td align="right">상품가격 * 구매개수</td>
						 </tr>
					</tbody>
				</table>
			</form>
			
			<!-- 결제정보 상품 가격에 대한 정보를 DB에서 받아와 보여줌 -->
			<p><b>결제정보</b></p>
			<form class="form-horizontal">
				<div id="finalInfo">
					<h2>
					<span>현재 상품 금액(총금액)</span>
					<i class="fa-solid fa-plus"></i>
					<span>기타 부가 금액</span>
					<i class="fa-solid fa-equals"></i>
					<span>최종 금액</span>
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
									<input type="text" class="form-control" id="ordererName" name="ordererName" value="고길동" readonly/>
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
							 <!--
								<select style="padding-left:4px; width: 71px; appearance:none;" id="ordererNumber1" name="ordererNumber1" title="주문자 앞번호">
									<option value='010' >010</option>
									<option value='011' >011</option>
									<option value='016' >016</option>
									<option value='017' >017</option>
									<option value='018' >018</option>
									<option value='019' >019</option>
								</select>
								-->
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
				<input type='hidden' id="id" name="id" value="test10"/>
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

<!-- 함수 정의 -->
<script src="${contextPath}/resources/js/payment.js"></script>
</html>