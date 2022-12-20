/**
 * 결제화면에서 쓸 함수
 */
  let ordersId = -1; // -1이면 값이 정의되지 않은 상태
// ordersId 생성
function createOrdersId(){
	$.ajax({
		type: "post",
		url: "/orders/createOrdersId",
		dataType: "json",
		success: function(data){
			
			if(ordersId == -1){
				ordersId = data;
			}
		}
	});	
}

$(function () {
  // ordersId생성
  createOrdersId();

  $("#equal_ordererNumber").click(function () {
    var sameNumber = this.checked;
    $("#receiverNumber1").val(sameNumber ? $("#ordererNumber1").val() : "010");
    $("#receiverNumber2").val(sameNumber ? $("#ordererNumber2").val() : "");
    $("#receiverNumber3").val(sameNumber ? $("#ordererNumber3").val() : "");
  });

  $("#equal_ordererName").click(function () {
    var sameName = this.checked;
    $("#receiverName").val(sameName ? $("#ordererName").val() : "");
  });
});





// 주소 API
function daumZipCode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업창에서 검색한 결과 중 항목을 클릭하였을 경우에 실행할 코드를 이곳에 작성한다.

      // 각 주소의 노출 규칙에 따라서 주소를 조합해야 한다.
      // 내려오는 변수가 값이 없는 경우에는 공백('') 값을 가지므로 이름을 참고하여 분기한다.
      let fullAddr = ""; // 최종 주소를 담을 변수
      let subAddr = ""; // 조합형 주소를 담을 변수

      // 사용자가 선택한 주소의 타입에 따라서 해당 주소값을 가져온다.
      if (data.userSelectedType == "R") {
        // 도로명 주소를 선택한 경우
        fullAddr = data.roadAddress;
      } else {
        // 지번 주소를 선택한 경우
        fullAddr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일 때 조합
      if (data.userSelectedType == "R") {
        // 법정동명이 있을 경우에 추가한다.
        if (data.bname != "") {
          subAddr += data.bname;
        }
        // 건물명이 있을 경우에 추가한다.
        if (data.buildingName != "") {
          subAddr +=
            subAddr != "" ? ", " + data.buildingName : data.buildingName;
        }
        // 조합형 주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
        fullAddr += subAddr != "" ? "(" + subAddr + ")" : "";
      }

      // 우편번호와 주소정보를 화면의 해당 필드에 출력시킨다.
      // 5자리의 새 우편번호
      document.getElementById("zipcode").value = data.zonecode;
      document.getElementById("address01").value = fullAddr;

      // 커서를 상세주소 입력란으로 이동시킨다.
      document.getElementById("address02").focus();
    }, // End - oncomplete: function(data)
  }).open({
    // 우편번호 팝업 창이 여러개 뜨는것을 방지하기 위해 popupName을 사용한다.
    // 이거 안쓰면 버튼 누를때마다 주소입력 팝업 창 계속 뜸
    popupName: "postcodePopup",
  });
} // End - function daumZipCode()





// 결제하기 버튼 눌렀을때
function payment() {
  // 입력한 값을 가져온다.
  // let ordererName		= $("#ordererName").val();
  // let email				= $("#email").val();
  let ordererNumber 		= $("#ordererNumber1").val() + $("#ordererNumber2").val() + $("#ordererNumber3").val();
  let receiverName 			= $("#receiverName").val();
  let zipcode 				= $("#zipcode").val() + $("#address01").val();
  let sangse 				= $("#address02").val();
  let address 				= zipcode + $("#address02").val();
  let receiverNumber 		= $("#receiverNumber1").val() + $("#receiverNumber2").val() + $("#receiverNumber3").val();
  let request 				= $("#request").val();
  let id 					= $("#id").val();
  let usePoint 				= parseInt($("#t_price").val());
  let deliverCost			= $('#d_price').val();
  
  if(isNaN(deliverCost)) {
  	deliverCost = 0;
  }

  // 주문하시는 분에 값이 없으면 반환
  if ($("#ordererName").val() == "") {
    alert("주문하시는 분을 입력해주세요.");
    $("#ordererName").focus();
    return false;
  }

  // 이메일에 값이 없으면 반환
  if ($("#email").val() == "") {
    alert("이메일을 입력해주세요.");
    $("#email").focus();
    return false;
  }

  // 주문 연락처에 값이 없거나 숫자가 아니면 반환
  if (ordererNumber.length < 10 || isNaN(ordererNumber)) {
    alert("주문 연락처를 올바르게 입력해주세요.");
    $("#ordererNumber2").focus();
    return false;
  }

  // 받으시는 분에 값이 없으면 반환
  if ($("#receiverName").val() == "") {
    alert("받으시는 분을 입력해주세요.");
    $("#receiverName").focus();
    return false;
  }

  // 받으시는 분 연락처에 값이 없거나 숫자가 아니면 반환
  if (receiverNumber.length < 10 || isNaN(receiverNumber)) {
    alert("받으시는 분 연락처를 올바르게 입력해주세요.");
    $("#receiverNumber2").focus();
    return false;
  }

  // 주소입력 안했을 때
  if (zipcode == "") {
    alert("주소를 입력해주세요.");
    $("#zipcode").focus();
    return false;
  }
  // 주소입력 안했을 때(상세주소)
  if (sangse == "") {
    alert("상세주소를 입력해주세요.");
    $("#address02").focus();
    return false;
  }
  
  // 포인트가 부족할 때
  let mPoint = $("#point").val();
  
  if( mPoint < usePoint) {
  	alert("가지고 계신 포인트가 부족합니다.\n\n현재 보유금액: " + mPoint + "원");
  	return false;
  }
  
  if(confirm("구매하시겠습니까?\n\n현재 보유금액: " + mPoint + "원") == true) {
  		// 배열을 담을 form 생성
	    let newForm = document.createElement("form");
	    newForm.setAttribute("method", "Post");
	    newForm.setAttribute("action", "${contextPath}/orders/orderComplete");
	    newForm.setAttribute("enctype", "application/x-www-form-urlencoded");
	    newForm.setAttribute("id", "ordersItemForm");

			let hiddenInputReceiverName		= document.createElement("input");
			let hiddenInputAddress			= document.createElement("input");
			let hiddenInputReceiverNumber	= document.createElement("input");
			let hiddenInputRequest			= document.createElement("input");
			let hiddenInputId				= document.createElement("input");
			let hiddenInputUsePoint			= document.createElement("input");
			let hiddenInputDeliverCost		= document.createElement("input");
			let hiddenInputOrders_id		= document.createElement("input");
			
			hiddenInputReceiverName.setAttribute("type", "hidden");
			hiddenInputReceiverName.setAttribute("name", "addressee");
			hiddenInputReceiverName.setAttribute("value", receiverName);
			
			hiddenInputAddress.setAttribute("type", "hidden");
			hiddenInputAddress.setAttribute("name", "address");
			hiddenInputAddress.setAttribute("value", address);
			
			hiddenInputReceiverNumber.setAttribute("type", "hidden");
			hiddenInputReceiverNumber.setAttribute("name", "o_phone");
			hiddenInputReceiverNumber.setAttribute("value", receiverNumber);
			
			
			hiddenInputRequest.setAttribute("type", "hidden");
			hiddenInputRequest.setAttribute("name", "o_comment");
			hiddenInputRequest.setAttribute("value", request);
			
			hiddenInputId.setAttribute("type", "hidden");
			hiddenInputId.setAttribute("name", "id");
			hiddenInputId.setAttribute("value", id);
			
			hiddenInputUsePoint.setAttribute("type", "hidden");
			hiddenInputUsePoint.setAttribute("name", "usePoint");
			hiddenInputUsePoint.setAttribute("value", usePoint);
			
			hiddenInputDeliverCost.setAttribute("type", "hidden");
			hiddenInputDeliverCost.setAttribute("name", "deliverCost");
			hiddenInputDeliverCost.setAttribute("value", deliverCost);
			
			hiddenInputOrders_id.setAttribute("type", "hidden");
			hiddenInputOrders_id.setAttribute("name", "orders_id");
			hiddenInputOrders_id.setAttribute("value", ordersId);
			
			newForm.append(hiddenInputReceiverName);
			newForm.append(hiddenInputAddress);
			newForm.append(hiddenInputReceiverNumber);
			newForm.append(hiddenInputRequest);
			newForm.append(hiddenInputId);
			newForm.append(hiddenInputUsePoint);
			newForm.append(hiddenInputDeliverCost);
			newForm.append(hiddenInputOrders_id);

			
			
			
	    $("input[role='cartItem_id']").each(function (index) {
			let hiddenInputProductId = document.createElement("input");	// product_id
			let hiddenInputCount = document.createElement("input");		// oi_number
			
			
	      																
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
	    
		
		let ordersItemVO		= $("#ordersItemForm").serialize();
		let ordersItemVO_json	= JSON.stringify(ordersItemVO);
		alert(ordersItemVO_json);
		
	
	
	  $.ajax({
	    type: "POST",
	    url: "/orders/orderComplete",
	    data: $("#ordersItemForm").serialize(),
	    success: function (data) {
	      if (data == "Y") {
	        alert("주문이 완료되었습니다.");
	        // 결제 완료되면 메인화면으로 이동한다.
	        location.replace("/");
	      } else {
	        alert("결제하는데 실패하였습니다.\n\n잠시후 다시 시도해주세요.");
	        location.replace("/");
	      }
	    },
	    error: function (data) {
	      alert("결제하는데 실패하였습니다.\n\n잠시후 다시 시도해주세요.");
	    },
	  });
  
  
  } else {
  	return false;
  }
  

	
} // End - function payment();