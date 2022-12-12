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