<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

	#size {	 	 
	  font-size: 20px;
	}
	

</style>

<title>리뷰 작성 화면</title>
</head>
<body>
	<form id="Writer" name="WrierForm" method="POST" > 		
		<div><h1>구매후기(리뷰) 작성 화면</h1></div>
		  <div class="container text-center border-top border-5 border-dark w-100 " ><br><br>
		      <div class="col">
		        <div class="col" id="size">상품은 만족스러웠나요?<h5>(별 개수 선택)</h5></div>	
		        <div class="col" >		        
		            <select name="score" class="form-select form-select-lg mb-3" id="selectStar" aria-label=".form-select-lg example">
				      <option>별 개수를 선택해 주세요</option>
				      <option id="1" value="1">☆</option>
				      <option id="2" value="2">☆☆</option>
				      <option id="3" value="3">☆☆☆</option>
				      <option id="4" value="4">☆☆☆☆</option>
				      <option id="5" value="5">☆☆☆☆☆</option>				   
				    </select>
    				<!-- <input type="hidden" id="star" name="score" value="" /> -->
				</div><br><br>     
				
				 
		        <div class="col" id="size">어떤 점이 좋았나요?</div>
		        <div class="row" >
		        <input type="text"  name="content"   placeholder="200자 이내로 입력해주세요" value="" ></input></div><br><br>     	        	
			    <div class="col">
			    	상품 사진 첨부하기
			    </div>
			    <div class="input-group mb-3">		    
				  <input type="file" id="picture" class="form-control" id="inputGroupFile01">
				</div> <br>     	 		
			  	  <button class="btn btn-outline-secondary" type="submit" id="saveData" >저장</button>
				  <button class="btn btn-outline-secondary" type="button" onclick="location.href='${contextPath}/member/reView'">이전화면으로</button>		  	
		  	
		  	 </div>
	 	 </div>	 
	</form>

<script>
//e.프리벤트디폴트 찾아서 저장 버튼에 넣어야함.

$(document).ready(function() {
	$("#saveData").click(function() {
		fn_star();
		console.log($("#star").val());
	})
})
function fn_star() {  //셀렉트스타로 가져오면 굳이 이거 쓸 필요 없다.
	
	let starC = "";
	
	if($("#1").is(":selected")) {
		starC = 1;
	} else if($("#2").is(":selected")) {
		starC = 2;
	} else if($("#3").is(":selected")) {
		starC = 3;
	} else if($("#4").is(":selected")) {
		starC = 4;
	} else if($("#5").is(":selected")) {
		starC = 5;
	}
	
	$("#star").val(starC);
}


/////////////////////////////////////////////////////////////
//리뷰 정보 변경 함수
/////////////////////////////////////////////////////////////
function fn_reviewRegister() {

	// 입력한 값을 가져온다.
	let	WrierForm	= $("#WrierForm").val();
	
	// 이름 항목에 값이 없으면 입력하도록 한다.
	if($("#WrierForm").val() == "") {
		alert("리뷰 입력은 필수 항목입니다.");
		$("WrierForm").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/reView/reViewWriter",
		data:		WrierForm 
		success:	function(result) {
			if(result == "Y") {
				alert("리뷰가 등록되었습니다.");
				location.href = "/reView/reView"; 
			}
			else{
			 	alert("실패해따 이새끼야.");
			}
		},
		error:		function(result) {
			alert("리뷰 등록에 실패하였습니다.");
		}
	});		
}


</script>


</body>
</html>