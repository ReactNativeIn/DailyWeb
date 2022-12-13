<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성 화면</title>
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
		<!-- 사이드바 -->
		<div id="aside">
		<jsp:include page="../common/sideNav.jsp" flush="false"/> 
		</div>
		<!-- 중앙 reViewWriter 화면 -->		
		<div id="main">
		<jsp:include page="./reViewWriterInfo.jsp" flush="false"/>
		</div>
	</main>
	<!-- 하단 바 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	
</body>
</html>