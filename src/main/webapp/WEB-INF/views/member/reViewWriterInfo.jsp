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
	<form action="/" method="POST">
		
		<div><h1>구매후기(리뷰) 작성 화면</h1></div>
		  <div class="container text-center border-top border-5 border-dark w-100 " ><br><br>
		      <div class="col">
		        <div class="col" id="size">상품은 만족스러웠나요?<h5>(별 개수 선택)</h5></div>	
		        <div class="col" >
				<input type="radio" name="chk_info" >☆
				<input type="radio" name="chk_info">☆☆
				<input type="radio" name="chk_info">☆☆☆
				<input type="radio" name="chk_info">☆☆☆☆
				<input type="radio" name="chk_info" value="">☆☆☆☆☆				
				</div><br><br>      
		        <div class="col" id="size">어떤 점이 좋았나요?</div>
		        <div class="row" >
		        <input type="text" id="size" placeholder="여기에 입력해주세요"></input></div><br><br>     	        	
			    <div class="col" id="size">
			    	상품 사진 첨부하기
			    </div>
			    <div class="input-group mb-3">		    
				  <input type="file" class="form-control" id="inputGroupFile01">
				</div> <br>     	 		
			  	  <button class="btn btn-outline-secondary" type="button" id="button-addon1">저장</button>
				  <button class="btn btn-outline-secondary" type="button" onclick="location.href='${contextPath}/member/reView'">이전화면으로</button>
		  	 </div>
	 	 </div>	 
</form>
</body>
</html>