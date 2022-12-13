/**
 * 
 */

let colorIndex = 0;
let sizeIndex = 0;
let colorId = -1; // -1이면 값이 정의되지 않은 상태

// colorId 생성
function createColorId(){
	$.ajax({
		type: "post",
		url: "/color/createColorId",
		dataType: "json",
		success: function(data){
			
			if(colorId == -1){
				colorId = data;
				$("input[name='sizeList[0].color_id']").val(colorId);
			}else {
				colorId = colorId + 1;
			}
			console.log("확인: " + colorId);
			
			$("input[name='sizeList["+ sizeIndex +"].color_id']").val(colorId);
			
			var newColorId = document.createElement("input");
			newColorId.setAttribute("type", "hidden");

			newColorId.setAttribute("name", "colorList["+ colorIndex +"].color_id");
			newColorId.setAttribute("value", colorId);			
			$("#productForm").append(newColorId);
		}
	});	
}

// 카테고리 불러오기
function categoryCall(){
	$.ajax({
		type:			"post",
		url:			"/category/c_name",
		dataType:		"json",
		success: 		function(data){
			if(data != ''){	
				for(var i = 0; i < data.length; i++){
					var newOption = document.createElement("option");

					var text = document.createTextNode(data[i].c_name);
					newOption.appendChild(text);
					newOption.setAttribute("value", data[i].c_name);
					// select 붙이기
					$("#c_name").append(newOption);	
				}	

				// 보이기
				$("#c_name").show();
			} else{
				$("#c_name").empty();
				$("#c_name").hide();
			}// End - if
		}
	});
}

 // 세부 카테고리 불러오기
 function optionChange(){
 	 var imsi = $("#c_name").val();
 	 var that = imsi == null ? "New" : imsi;
 	 console.log(that);
 	 
 	 $.ajax({
		type:			"post",
		url:			"/category/c_detail",
		data:			{c_name:that},
		dataType:		"text",
		success:		function(data) {
		
			if(data != ''){
				
				var dataN = JSON.parse(data);	
			
				// 기존 select 클린(자식 삭제)
				$("#c_detail").empty();

				for(var i = 0; i < dataN.length; i++){
					var newOption = document.createElement("option");

					var text = document.createTextNode(dataN[i].c_detail);
					newOption.appendChild(text);
					newOption.setAttribute("value", dataN[i].c_detail);
					
					// select 붙이기
					$("#c_detail").append(newOption);	
				}	

				// 보이기
				$("#c_detail").show();
			} else{
				$("#c_detail").empty();
				$("#c_detail").hide();
			}// End - if
		}
	}); // End - ajax
 }
 
// 사이즈 / 재고 추가
function sizeBtn(event){
	sizeIndex = sizeIndex + 1;
	
	var that = $(event).next();
	
	let sizeOption =$(that).eq(0).clone();
	
	sizeOption.children().eq(0).val("");
	sizeOption.children().eq(1).val("");
	
	sizeOption.children().eq(0).attr("name", "sizeList["+ sizeIndex +"].size");
	sizeOption.children().eq(1).attr("name", "sizeList["+ sizeIndex +"].s_stock");
	sizeOption.children().eq(2).attr("name", "sizeList["+ sizeIndex +"].color_id");	
	
	that.parent().append(sizeOption);
}

// 상품 등록 - 상품 등록 버튼
function insertBtn(){
	alert("클릭");
	console.log($("#productForm"));
	let productForm = $("#productForm").serialize();
	$.ajax({
			type:			"post",
			url:			"/product/productInsert",
			data:			productForm,
			dataType:		"text",
			success:		function(data) {
				if(data == "Y"){
					alert("상품 등록 성공");
					location.replace("/product/productList");	
				} else if(data == "N"){
					alert("상품 등록 실패");
				}
			},
			error : function(){
				alert("예상치 못한 에러");
			}
	});
}
///---------------------------------------- 대철
/**
 * 상품 디테일 관련 함수
 */
 
 //----------------------------------
 // 썸네일을 메인이미지로 변경
 //----------------------------------
 
 var main_img = document.querySelector("#img_main");	// 메인 이미지
 var thumb_img = document.querySelectorAll("#img_thumb");	// 작은 사진(여러개)
 var format = 0;
 
 function changeImg() {
 for(var i = 0; i< thumb_img.length; i++) {
 	thumb_img[i].addEventListener("click", changepic);	// 이벤트 처리
 	/*
 	onclick으로 하면 하나의 요소에 하나의 이벤트만 사용가능
 	thumb_img[i].onclick = changepics;
 	*/
 	}
 }
 
 function changepic(){	//사진 바꾸는 함수
 	var thumb_imgAttribute = this.getAttribute("src");
 	main_img.setAttribute("src",thumb_imgAttribute);
 }

 
 //--------------------------------------
 // 가격 계산
 //--------------------------------------

 var price = 10000;
 
 var value_sum = document.getElementById("sum");	// 가격 합계 요소
 var value_sumAll = document.querySelector("#sumAll");	// 총 금액
 
 for(var i = 0; i< value_num.length; i++) {
 	value_num[i].addEventListener("click", pp_num);	//이벤트 처리
 }
 
 function pp_num(){	// 주문 개수에 따른 가격 변경 함수
  	var value_num = document.getElementById("order_num").value;	// 주문 수량
  	var num = Number(value_num * price);
  	format = Number(num).toLocaleString(); // 천의 자리마다 (,)콤마 넣어준다.

 	value_sum.innerHTML = (format + "원");
 	
 	value_sumAll.innerHTML = "총 상품 금액 : " + value_sum.innerHTML;
 }
 
 
  //--------------------------------------------------
  // 선택된 썸네일에 효과주기
  //--------------------------------------------------
  
function imgClick(){
		$('#img_thumb').css('border','solid');
		$('#img_thumb').css('border','1px');
}