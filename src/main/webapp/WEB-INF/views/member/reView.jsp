<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link href="css/myPage.css" rel="stylesheet" type="text/css" media="all"/>
<meta charset="UTF-8">
<title>리뷰 정보 화면</title>
	<style>
		main {
			display: flex;
			min-width: 1000px;
		}
		
		#aside {
			width : 30%;
		}		
		
		#main{
			width : 70%;
		}
		</style>
<body>

	<!-- 상단 메뉴바 -->
	<jsp:include page="../common/header.jsp" flush="false"/>
	
	<main class="container-fluid p-5">
		<div id="aside">
		<!-- 사이드바 -->
		<jsp:include page="../common/sideNav.jsp" flush="false"  /> 
		</div>	
		<div id="main">
		<!-- 중앙 myPage 화면 -->		
		<jsp:include page="./reViewInfo.jsp" flush="false"/>	
		</div>	
	</main>
		<!-- 하단 바 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	
</body>
</html>


