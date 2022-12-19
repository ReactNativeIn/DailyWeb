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
		},
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
	console.log(productForm);
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

// productDetail 화면 - 색상에 따른 사이즈
function callSize(){
	
	var selectColor = $("#selectColor").val();
	console.log(selectColor);
	$("." + selectColor).removeAttr("hidden");
	
	// 다시 hidden
	$("#selectSize").find("option[class!=" + selectColor + "]").each(function(index, value){
		console.log(index);
		console.log(value);
		$(value).attr("hidden","hidden");
		$(value).prop("selected", false); // 선택 초기화
	});
}

// 총 금액 구하는 함수
function sumAllResult(){
	var sumAll = $("#sumAll"); // 총금액
	var sumList = $("span[data-class='sum']");
	let sumResultValue = 0;
	console.log(sumList);
	
	sumList.each(function(index, value){
		console.log("a : " + value.innerHTML.replace("원",""));
		var toNum = value.innerHTML.replace("원","");
		console.log(toNum.replace(/,/g,""));
		sumResultValue += Number(toNum.replace(/,/g,"")); 
	});
	console.log(sumResultValue);
	sumAll.text("총 상품 금액 : " + sumResultValue.toLocaleString() + "원");
}


// 수량 조작 버튼 함수
function buttonPM(event){
	//$(event).data("class") // data-값 => 원하는 속성, 찾는 것은 이렇게 찾음 

	var par = $(event).parent();
	var btn = $(event).attr("name"); 
	var cInput = $(par).children().eq(1);
	var sum = $(par).next().children().eq(0);
	
	
	
	if(btn == "plus_btn"){
		cInput.val(Number(cInput.val())+1);
		$(sum).text(
			(cInput.val() * $("input[name=p_price]").val()).toLocaleString()+ "원"
		);

	}else if(btn == "minus_btn"){
		
		if(cInput.val() > 1){
			cInput.val(Number(cInput.val())-1);
			$(sum).text(
				(cInput.val() * $("input[name=p_price]").val()).toLocaleString() + "원"
			);
		}
		
	}
	sumAllResult();
}

let detailColorIndex = 00;
let detailSizeIndex = 00;

// productDetail 화면 - 색상, 사이즈까지 선택시 추가
function selectSize(){
	var color = $("#selectColor option:selected");
	var size = $("#selectSize option:selected");
	
	var colorT = color.text();
	var sizeT = size.text();

	console.log(colorT + ": " + sizeT);
	
	var divWrap = document.createElement("div");
	
	var nameDiv = document.createElement("div");
	var selText = document.createTextNode(colorT + " / " + sizeT);
	nameDiv.appendChild(selText);
	divWrap.append(nameDiv);
	
	
	var newDiv = document.createElement("div");
	newDiv.setAttribute("class", "btn-group");
	newDiv.setAttribute("role", "group")
	
	var newPButton = document.createElement("button");
	newPButton.setAttribute("name", "plus_btn");
	newPButton.setAttribute("class", "btn btn-outline-dark btn-lg");
	newPButton.setAttribute("onclick", "buttonPM(this)");
	var textP = document.createTextNode("+");
	newPButton.appendChild(textP);
	
	var newMButton = document.createElement("button");
	newMButton.setAttribute("name", "minus_btn");
	newMButton.setAttribute("class", "btn btn-outline-dark btn-lg");
	newMButton.setAttribute("onclick", "buttonPM(this)");
	var textM = document.createTextNode("-");
	newMButton.appendChild(textM);
	
	var newInput = document.createElement("input");
	newInput.setAttribute("class", "stock_input" + detailSizeIndex);
	newInput.setAttribute("type", "number");
	newInput.setAttribute("value", "1");
	newInput.setAttribute("min", "1");

	newDiv.append(newMButton);
	newDiv.append(newInput);
	newDiv.append(newPButton);
	
	var newDiv2 = document.createElement("div");
	newDiv2.setAttribute("style", "display: inline-block");
	
	var newSpan = document.createElement("span");
	newSpan.setAttribute("class", "fs-4 dsa");
	newSpan.setAttribute("data-class", "sum");
	var textS = document.createTextNode((Number($("input[name=p_price]").val()).toLocaleString())+"원");
	newSpan.appendChild(textS);
	
	newDiv2.append(newSpan);
	
	divWrap.append(newDiv);
	divWrap.append(newDiv2);
	
	
	// 	option 결과 붙이기
	$("#product_option_wrap").append(divWrap);
	//$("#product_option_wrap").append(newDiv2);
	
	sumAllResult(); // 총 금액
	
	// 다음 선택을 위해 초기화
	$("#selectColor").children().prop("selected", false);
	$("#selectSize").children().prop("selected", false);
	
	// 다시 hidden
	$("#selectSize").find("option").each(function(index, value){
		console.log(index);
		console.log(value);
		$(value).attr("hidden","hidden");
		$(value).prop("selected", false); // 선택 초기화
	});
	
	// form에 데이터 추가
	
	// color
	var colorDataInput = document.createElement("input");
	colorDataInput.setAttribute("type", "hidden");
	colorDataInput.setAttribute("name", "colorList[" + detailColorIndex + "].color");
	colorDataInput.setAttribute("value", colorT);
	
	$("#detail_form").append(colorDataInput);
	
	//size
	var sId_dataInput = document.createElement("input");
	sId_dataInput.setAttribute("type", "hidden");
	sId_dataInput.setAttribute("name", "sizeList[" + detailSizeIndex + "].size_id");
	sId_dataInput.setAttribute("value", size.val());
	
	var sdataInput = document.createElement("input");
	sdataInput.setAttribute("type", "hidden");
	sdataInput.setAttribute("name", "sizeList[" + detailSizeIndex + "].size");
	sdataInput.setAttribute("value", size.text());
	
	var stock_dataInput = document.createElement("input");
	stock_dataInput.setAttribute("type", "hidden");
	stock_dataInput.setAttribute("class", "stock");
	stock_dataInput.setAttribute("name", "sizeList[" + detailSizeIndex + "].s_stock");
	stock_dataInput.setAttribute("value", "1");
	
	$("#detail_form").append(sId_dataInput);
	$("#detail_form").append(sdataInput);
	$("#detail_form").append(stock_dataInput);
	
	
	//index 증가
	detailColorIndex += 1;
	detailSizeIndex += 1;
}

function onSubmit(){
	var detailFormObj = $("#detail_form");

	$(".stock").each(function(index, value){
		$("input[name='sizeList[" + index + "].s_stock']").val(
			$("input[class='stock_input" + index + "']").val()
		);
	});
	
	
	detailFormObj.submit();
}


// 장바구니 추가 버튼
$(".btn_cart").on("click", function(e){

	$(".stock").each(function(index, value){
		$("input[name='sizeList[" + index + "].s_stock']").val(
			$("input[class='stock_input" + index + "']").val()
		);
	});
	
	var detailFormObj = $("#detail_form").serialize();
	
	$.ajax({
		url: '/cart/add',
		type: 'POST',
		data: detailFormObj,
		success: function(result){
			if(result == '0'){
				alert("장바구니에 추가를 하지 못하였습니다.");
			} else if(result == '1'){
				alert("장바구니에 추가되었습니다.");
			}
		}
	})
});
