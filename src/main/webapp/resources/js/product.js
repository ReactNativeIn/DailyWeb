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
		url:			"/category/detail",
		data:			{c_name:that},
		dataType:		"text",
		success:		function(data) {
		
			if(data != ''){
				
				var dataN = JSON.parse(data);	
			
				// 기존 select 클린(자식 삭제)
				$("#detail").empty();

				for(var i = 0; i < dataN.length; i++){
					var newOption = document.createElement("option");

					var text = document.createTextNode(dataN[i].c_detail);
					newOption.appendChild(text);
					newOption.setAttribute("value", dataN[i].c_detail);
					
					// select 붙이기
					$("#detail").append(newOption);	
				}	

				// 보이기
				$("#detail").show();
			} else{
				$("#detail").empty();
				$("#detail").hide();
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

// 색상 입력시 값 넣어주기(동적)
function colorInput(event){
	var that = $(event).parent().next().next().children().eq(0);
	that.val(event.value);
	
}
 
// 상품 등록 버튼 클릭시, ajax처리 , 성공 시 상품 리스트 화면 / 실패 시 이전의 데이터 유지 및 현재 상품 등록 화면
function productInsert(){
	alert("시작");
	
	
	alert($("#c_name option:selected").val());
	
	$.ajax({
		type: "POST",
		url: "/product/productInsert",
		data: {},
		success: function(data){
			if(data == "Y") {
				alert("상품을 등록하였습니다.");
			} else if(data == "N") {
				alert("상품 등록이 실패하였습니다.");
			}
		},
		error : function(data) {
			alert("예상치 못한 에러");
		}
	});
}