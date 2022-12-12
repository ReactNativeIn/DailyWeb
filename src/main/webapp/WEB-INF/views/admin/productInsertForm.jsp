<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 등록 화면 - 관리자</title>
	
	<style>
			#productForm, #productForm div{
				min-width: 300px;
			}
			#productForm {
				border: 3px solid rgba(220, 220, 220, 1); 
				padding: 20px 20px;
				margin: 0px auto;
			}
			#productForm input{
				height: 50px;
			}
			#productForm > div, #categoryDiv > div {
				margin-bottom: 30px
			}
			#productForm > .form-group {
				margin-top: 30px;
			}
			.fileDrop	{
				width:		100%;
				height:		200px;
				border:		1px dotted blue;
				text-align: center;
				padding: 100px 0px;
			}
			.uploadedList {
			    justify-content: space-around;
			}
			.imgDiv {
				display: inline-block;
				margin-right: 20px;
			}
			.imgT {
				display: block;
			}
			.imgRemove {

			}
			.colorDiv, .sizeDiv{
				width: 100%;
			    display: flex;
			    justify-content: space-around;
			}
	</style>
</head>
<body>

	<!-- 상단 메뉴 -->
	<jsp:include page="../common/otherHeader.jsp" flush="false"/>

	<script src="/resources/js/product.js"></script>

	<main class="container">
		<form id="productForm" method="post" enctype="multipart/form-data" action="/product/productInsert">
			<input type="hidden" name="product_id" value="${product_id }"/>
			<div id="categoryDiv" class="form-group">
				<div>
					<span class="fs-5">카테고리</span>
				</div>
				<div id="c_nameDiv">
					<select id="c_name" name="c_name" class="w-100" onchange="optionChange();">
						
					</select>
				</div>
				<div id = "c_detailDiv">
					<select id="c_detail" name="c_detail" class="w-100">
						
					</select>
				</div>				
			</div>
			<hr>
			<div id="optionDiv" class="form-group">
				<span class="fs-5">색상 / 사이즈 / 재고</span>
				<button id="colorBtn" type="button">색상 추가</button>
				<div class="optionWrap" style="border: 1px solid; margin-bottom: 20px; padding: 20px 5px">
					<div class="colorDiv">			
						<input type="hidden" name="colorList[0].product_id" value="${product_id}"/>
						<input required class="form-control w-25" type="text" name="colorList[0].color" placeholder="색상 입력"/>											
					</div>
					<button class="sizeBtn" type="button" onclick="sizeBtn(this);">사이즈 / 재고 추가</button>
					<div class="sizeDiv">
						<input required class="form-control w-25" type="text" name="sizeList[0].size" placeholder="사이즈 입력"/>
						<input required class="form-control w-25" type="number" name="sizeList[0].s_stock" min="0" placeholder="재고량 입력"/>										
						<input type="hidden" name="sizeList[0].color_id"/>	
					</div>
				</div>
			</div>
			<hr>
			<div class="input-group">
				<span class="input-group-text fs-5">
					상품명
				</span>
				<input required type="text" class="form-control" id="p_name" name="p_name" placeholder="상품명을 입력해주세요"/>
			</div>
			<div class="input-group">
				<span class="input-group-text fs-5">
					상품설명
				</span>
				<textarea rows="10" cols="160" class="form-control" id="p_explanation" name="p_explanation" placeholder="상품설명을 입력해주세요"></textarea>
			</div>
			<div class="input-group">
				<span class="input-group-text fs-5">
					상품가격
				</span>
				<input required min="0" type="number" class="form-control" id="p_price" name="p_price" placeholder="상품가격"/>
			</div>
			<div class="input-group">
				<div class="fs-5">
					상품 사진
				</div>
				<!-- accept="image/*" -> 이미지 파일만 선택하도록 설정됨 -->
				<!--  
				<div>
					<input id="fileButton" name="file_data" type="file" multiple="multiple" class="btn-success" accept="image/*" />				
				</div>
				-->
				<div class="fileDrop">사진을 드래그...</div> <!-- 파일을 업로드할 영역 -->
				<div class="w-100 d-flex flex-wrap uploadedList" ></div><!-- 선택한 파일목록을 뿌려주는 영역 -->
			</div>
			<div class="form-group btn-group-lg">
				<button class="btn btn-success" type="button" onclick="insertBtn();">상품 등록</button>
			</div>
		</form>
	</main>
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"/>

</body>
<script>
	$(function () {

		// colorId 생성
		createColorId();
		
		// 처음 카테고리 + 세부 카테고리 숨기기
		$("#c_detail").hide();
		$("#c_name").hide();
		
		// 카테고리 불러오기
		categoryCall();
		
		// 옵션 초기 상태 // 복사코드	
		var imsi = $(".optionWrap").eq(0).clone();
		
	    var number = $("input[type='number']");

		// 음수 입력 방지 
		number.on("keydown", function(e) {
	        if(!((e.keyCode > 95 && e.keyCode < 106)
		        	|| (e.keyCode > 47 && e.keyCode < 58) 
		        	|| e.keyCode == 8)) {
		            return false;
	        }
	    });
			

		
		// 카테고리 세부 정보 불러오는 메서드
		optionChange();
		
		// 색상 추가
		$("#colorBtn").on("click", function(){
			colorIndex = colorIndex + 1;
			sizeIndex = sizeIndex + 1;
			var colorOption = imsi.clone();
			
			colorOption.children().eq(0).children().eq(0).attr("name", "colorList["+ colorIndex +"].product_id");
			colorOption.children().eq(0).children().eq(1).attr("name", "colorList["+ colorIndex +"].color");
			
			colorOption.children().eq(2).children().eq(0).attr("name", "sizeList["+ sizeIndex +"].size");
			colorOption.children().eq(2).children().eq(1).attr("name", "sizeList["+ sizeIndex +"].s_stock");
			colorOption.children().eq(2).children().eq(2).attr("name", "sizeList["+ sizeIndex +"].color_id");			
			
			createColorId();
			
			
			colorOption.children().eq(2).children().eq(0).val("");
			colorOption.children().eq(2).children().eq(1).val("");			
			
			$("#optionDiv").append(colorOption);
		});
				
		// 드래그의 기본효과를 막는다.
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop", function(event) {
			alert("그림을 떨어뜨렸습니다.");
			
			// 그림이 drop될 때의 기본효과를 막는다.
			event.preventDefault();
		
			// 첨부파일 배열 : 드래그된 파일의 정보
			// Ctrl + 클릭으로 여러개의 파일을 동시에 올릴 수 있다.
			var files = event.originalEvent.dataTransfer.files;
			
			var fileList = Array.prototype.slice.call(files); // files가 노드 형식이여서 배열로 변환해줘야 한다.
			
			// form객체 생성
			var formData = new FormData();	
		
			for(var i = 0; i < fileList.length; i++){
				if(!checkImageType(fileList[i].name)){
					alert("jpg, png, gif 이미지 파일만 지원합니다.");
					return ;
				}
				console.log(checkImageType(fileList[i].name));
				formData.append("uploadFile", fileList[i]);
				
			}
			
			$.ajax({
				type:			"post",
				url:			"/util/upload/uploadAjax",
				data:			formData,
				dataType:		"text",
				processData:	false,
				contentType:	false,
				success:		function(data, status, req) {
					console.log("data : "	+ data);		// 업로드된 파일이름
					console.log("status : "	+ status);		// 성공, 실패여부
					console.log("req : "	+ req.status);	// 요청 코드값
					
					var fileList = JSON.parse(data); // json문자열을 javascript객체로 변환
					var index = $(".index").length;
					imgApppend(index, fileList);
					
				}
			}); // End - ajax
			
		});	
		
		// 이미지 파일 붙이는 공통 함수 및 form에 파일 데이터 추가
		function imgApppend(index, fileList){
			for(var i = 0; i < fileList.length; i++){
				// 엘리먼트 생성
				console.log("fileList["+i+"].uuid");
				var newInput1 = document.createElement("input");
				newInput1.setAttribute("name", "fileList["+(index + i)+"].uuid");
				newInput1.setAttribute("type", "hidden");
				newInput1.setAttribute("class", "index");
				newInput1.setAttribute("value", fileList[i].uuid);
				
				var newInput2 = document.createElement("input");
				newInput2.setAttribute("name", "fileList["+(index + i)+"].file_o_name");
				newInput2.setAttribute("type", "hidden");
				newInput2.setAttribute("value", fileList[i].file_o_name);
				
				var newInput3 = document.createElement("input");
				newInput3.setAttribute("name", "fileList["+(index + i)+"].file_path");
				newInput3.setAttribute("type", "hidden");
				newInput3.setAttribute("value", fileList[i].file_path);
				
				var newInput4 = document.createElement("input");
				newInput4.setAttribute("name", "fileList["+(index + i)+"].file_s_name");
				newInput4.setAttribute("type", "hidden");
				newInput4.setAttribute("value", fileList[i].file_s_name);
				
				var newInput5 = document.createElement("input");
				newInput5.setAttribute("name", "fileList["+(index + i)+"].product_id");
				newInput5.setAttribute("type", "hidden");
				newInput5.setAttribute("value", ${product_id});
				
				$(".uploadedList").append(newInput1);
				$(".uploadedList").append(newInput2);
				$(".uploadedList").append(newInput3);
				$(".uploadedList").append(newInput4);
				$(".uploadedList").append(newInput5);
				
			
				
				var newImage = document.createElement("img");
				newImage.setAttribute("class", 'img-thumbnail imgT');
				newImage.src = "/util/upload/displayFile?fileName=" + fileList[i].file_path + fileList[i].file_s_name;
				
				var newButton = document.createElement("button");
				newButton.setAttribute("class", "btn btn-danger w-100 imgRemove ");
				newButton.setAttribute("type", "button");
				newButton.setAttribute("data-src", fileList[i].file_path + fileList[i].uuid);
				
				var spanText = document.createTextNode("삭제");
				newButton.appendChild(spanText);
				
				var newDiv = document.createElement("div");
				newDiv.setAttribute("class", "align-middle imgDiv");
				newDiv.append(newImage);
				newDiv.append(newButton);
				
				// 이미지 붙이기
				$(".uploadedList").append(newDiv);
			}
		}
		
		// 이미지 파일인지 아닌지 검사하는 함수
		// i : ignore case()
		//-----------------------------------------------------------------------------------------------------------
		function checkImageType(fileName) {
			var pattern = /jpg|png|jpeg|gif/i;	// 정규표현식(대소문자 무시)
			return fileName.match(pattern);		// 규칙에 맞으면 true, 아니면 false	
		} // End - function checkImageType(fileName)
		
		// 삭제 버튼을 눌렀을 경우 : 사용자가 올린 이미지 파일 삭제
		// .uploadedList의 하위 button 요소에 click 이벤트를 바인딩
		$(".uploadedList").on("click", "button", function(event) {
			alert("첨부파일 삭제하기");
			var that = $(this);
			$.ajax({
				url:		"/util/upload/deleteFile",
				type:		"post",
				data:		"fileName=" + $(this).attr("data-src"),
				dataType:	"text",
				success:	function(result) {
					console.log($(this));
					if(result == "deleted") {
						console.log($(this).parent("div"));
						that.parent("div").remove();
					}
				},
				error:		function(error) {
					alert("Error !!!");
				},
			});
			
		});
	});

</script>

</html>