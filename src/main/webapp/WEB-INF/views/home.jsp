<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
	<style>
		main {
			min-width: 600px;
		}
		.banner {
			margin: 0px auto;
			height: 500px;
		}
		.title {
			color: black;
			font-weight: bold;
			text-align: center;
		}
		.itemSection {
			margin-top: 100px;
		}
	</style>
</head>
<body>
	<jsp:include page="./common/header.jsp" flush="false"/>
	
	<main>
		<div class="container-fluid">
			<div class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block banner" src="/resources/images/test1.jpg"/>
					</div>
					<div class="carousel-item">
						<img class="d-block banner" src="/resources/images/test2.jpg"/>
					</div>
					<div class="carousel-item">
						<img class="d-block banner" src="/resources/images/test3.jpg"/>
					</div>
				</div>
			</div>
		</div>
		
		<section class="itemSection">
			<div class="contaioner">
				<h1 class="title">NEW</h1>
				<table class="table table-borderless">
					<tr>
						<c:forEach items="${newList}" var="item">
							<td class="text-center">
								<div>
									<c:choose>
										<c:when test="${item.fileList == null }">
											<img class="img-thumbnail border-2 w-75" src="/resources/images/noImage.png"/>
										</c:when>
										<c:otherwise>
											<img class="img-thumbnail border-2 w-75" src="/util/upload/displayFile?fileName=${item.fileList[0].file_path }${item.fileList[0].file_s_name}"/>
										</c:otherwise>
									</c:choose>
									<div class="text-center">
										<div class="card-body">
											<h5 class="card-title">
												${item.p_name }
											</h5>
											<p class="card-text">
												${item.p_price}원
											</p>
										</div>
									</div>
								</div>
							</td>						
						</c:forEach>
					</tr>
				</table>
			</div>
		</section>
		<section class="itemSection">
			<div class="contaioner">
				<h1 class="title">BEST ITEM</h1>
				<table class="table table-borderless">
					<c:forEach var="item" items="${bestList }" varStatus="i">
						<c:if test="${(i.index + 1)%5 == 1 }">
							<tr>
						</c:if>
						<c:choose>
							<c:when test="${(i.index + 1)%5 == 0 }">
								<td class="text-center">
									<div>									
										<c:choose>
											<c:when test="${item.fileList == null }">
												<img class="img-thumbnail border-2 w-75" src="/resources/images/noImage.png"/>
											</c:when>
											<c:otherwise>
												<img class="img-thumbnail border-2 w-75" src="/util/upload/displayFile?fileName=${item.fileList[0].file_path }${item.fileList[0].file_s_name}"/>
											</c:otherwise>
										</c:choose>
										<div class="text-center">
											<div class="card-body">
												<h5 class="card-title">
													${item.p_name }
												</h5>
												<p class="card-text">
													${item.p_price}원
												</p>		
											</div>
										</div>
									</div>
								</td>					
								</tr>			
							</c:when>
							<c:otherwise>
								<td class="text-center">
									<div>
										<c:choose>
											<c:when test="${item.fileList == null }">
												<img class="img-thumbnail border-2 w-75" src="/resources/images/noImage.png"/>
											</c:when>
											<c:otherwise>
												<img class="img-thumbnail border-2 w-75" src="/util/upload/displayFile?fileName=${item.fileList[0].file_path }/${item.fileList[0].file_s_name}"/>
											</c:otherwise>
										</c:choose>
										<div class="text-center">
											<div class="card-body">
												<h5 class="card-title">
													${item.p_name }
												</h5>
												<p class="card-text">
													${item.p_price}원
												</p>		
											</div>
										</div>
									</div>
								</td>	
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
			</div>
		</section>	
	</main>
	
	<jsp:include page="./common/footer.jsp"/>
</body>

</html>
