<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

   <!-- datepick 사용하기 위해 추가 -->
   <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  
   <!-- end -->
	
	<!-- icon -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

<!-- 폰트어썸 -->
<script src="https://kit.fontawesome.com/52943f354a.js" crossorigin="anonymous"></script>

<style>
	header {
		min-width: 600px;
	}
	main {
		min-width: 600px;
	}
	.title {
		padding: 20px 0px;
		font-weight: bold;
	}
	a, a:link, a:visited, a:hover, a:active{
		color: black;
		text-decoration: none;
	}
	#logo {
		color : black;
		font-weight: bold;
		font-size: 3em;
	}
	#logo {
		text-decoration: none; 
	}
	.btn-success {
		width: 100%;
		font-size: 25px;
		font-weight: bold;
	}
	#aboutUser {
		list-style: none;
		padding:0px;
		width: 100%;
		text-align: center;
		font-size: 20px;
	}
	#aboutUser li{
		display:inline-block;
		text-align: center;
		margin-right: 5px;
	}
	.findForm {
		border: 3px solid rgba(220, 220, 220, 1); 
		padding: 20px 20px;
		margin: 0px auto;
	}
	.findForm, .findForm div {
		max-width: 500px;
		min-width: 300px;
		min-height: 60px;
	}
	.findForm > .form-group {
		margin-top : 30px;
	}

</style>

<header>
	<div class="container">
		<div class="row text-center" style="padding:200px 0px 100px 0">
			<span id="logo">
				<a href="/">D A I L Y</a>
			</span>
		</div>
	</div>
	<c:if test="${member.id == 'admin' }">
		<jsp:include page="../common/adminNav.jsp" flush="false"/>
	</c:if>
</header>
<div class="clear"></div>
