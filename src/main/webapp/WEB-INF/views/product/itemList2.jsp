<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- 더미데이터 -->
<c:set var="sale" value="" />
<!-- 컨트롤러에서 받아온 Name객체 소문자를 대문자로 -->
<c:set var = "title" value = "${fn:toUpperCase(Name)}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title}</title>
</head>
<style>
	.mainImg {
		width: 80%;
		height: 600px;
	}
	
	.productImg {
		
		height: 400px;
		overflow:hidden;
		margin: 0 auto;
	}
	
	#productInfo {

		margin-bottom: 20px;
	}
	
	.productListLi {
		margin-bottom: 60px;
	}
	
	#productInfoUl, #productListUl, .descriptionUl {
    list-style:none;
    margin:0 0 6px 0;
    padding:0;
	}

	.descriptionLi {
		float: left;
	}
	
	.descriptionUl {
		float: left;
	}

	.productInfoLi {
		float: right;
		padding-left: 26px;
		font-size:12px;
		
	}
	
	/* 안보이게 하기용 css*/
	.displaynone {
		display: none;
	}
	
</style>

<script>
	var today = new Date();
	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2);
	var dateString = year + '-' + month  + '-' + day;
	// alert(dateString);
</script>

<body>

	<!-- 헤더 -->
	<jsp:include page="../common/header.jsp" flush="false"/>
	<style>
		/* 헤더 a태그와 속성 겹쳐서 body에 적어줌 */
		.productInfoLi>a {
			cursor:pointer;
		}
		
		.productInfoLi>a:hover, .productName>a:hover {
			opacity: 0.5;
		}
	</style>
	
	<main class="container" style="margin-top:60px;">

		
			<!-- 슬라이드 이미지 -->
			<div id="myCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-pause="false">
			  <div class="carousel-inner">
			    <div class="carousel-item active mainImg" data-bs-interval="6000">
			      <img style="object-fit: contain;"class="img-fluid mx-auto d-block w-100 h-100" src="/resources/images/cat01.jpg" alt="cat01"/>
			    </div>
			    <div class="carousel-item mainImg" data-bs-interval="6000">
			      <img style="object-fit: contain;" class="img-fluid mx-auto d-block w-100 h-100" src="/resources/images/cat02.jpg" alt="cat02"/>
			    </div>
			    <div class="carousel-item mainImg" data-bs-interval="6000">
			      <img style="object-fit: contain;" class="img-fluid mx-auto d-block w-100 h-100" src="/resources/images/cat03.jpg" alt="cat03"/>
			    </div>
			  </div>
			</div>
			
			
			<div id="sort" style="margin-top: 20px; scroll-margin: 20px; max-width:1296px; min-width:900px;">
			
				<!-- 상품개수 & 정렬방법 -->
				<!-- 인기상품 == p_sell, 높은가격 == p_price, == 낮은가격 == p_price, 최신상품 == p_enroll -->
				<div id="productInfo" class="row">
					<div class="col-md-6" style="font-size:12px;">
						총 상품 개수 : <strong>${pageMaker.totalCount} 개</strong>
					</div>
					<div class="col-md-6">
						<ul id="productInfoUl">
							<li class="productInfoLi"><a onclick="sort('popular');">인기상품</a></li>
							<li class="productInfoLi"><a onclick="sort('high');">높은가격</a></li>
							<li class="productInfoLi"><a onclick="sort('low');">낮은가격</a></li>
							<li class="productInfoLi"><a onclick="sort('new');">최신상품</a></li>
						</ul>
					</div>
				</div>
				
				
				<!-- 상품사진과 정보 -->
				<div class="row row-cols-4 row-cols-md-4 g-4">

					<!-- 카테고리에서 데이터를 받아 출력 -->
					<c:forEach var="List" items="${List}">
						<div class="col" style="margin-bottom: 20px;">
							<div class="productImg">
								<a href="${contextPath}/product/productDetail?product_id=${List.product_id}">
									<img style="width: 100%; height: 400px;" src="/resources/images/cat01.jpg" alt="..."/>
								</a>
							</div>
							<!-- 정보 -->
							<div class="description">
								<div class="productName" style="margin:10px 0 10px 0;">
									<a href="#">
										<span class="title displaynone">상품명 : </span>
										<span style="font-size:12px;">${List.p_name}</span>
									</a>
								</div>
								<hr/>
								<c:choose>
									<c:when test="${not empty sale}">
										<div class="descriptionDiv">
											<ul class="descriptionUl">
												<li class="descriptionLi" title="판매가">
													<span class="title displaynone">판매가 : </span>
													<span style="font-size:13px; text-decoration:line-through">${List.p_price}원</span>
												&nbsp;
												</li>
												<li class="descriptionLi" style="text-align: right;" title="할인판매가">
													<span class="displaynone">할인판매가 : </span>
													<span style="font-size:13px;">
														할인가 
														<span style="color: red;">할인금액(판매가-할인가)</span>
													</span>
												</li>
											</ul>
										</div>
										<div style="line-height:200%;">
											<br/>
										</div>
										<p style="font-size:12px; margin-bottom:8px;">할인기간</p>
									</c:when>
									<c:otherwise>
										<div class="descriptionDiv" style="margin-bottom:8px;">
											<ul class="descriptionUl">
												<li class="descriptionLi" title="판매가">
													<span class="title displaynone">판매가 : </span>
													<span style="font-size:13px;">${List.p_price}원</span>
												</li>
											</ul>
										</div>
									</c:otherwise>
								</c:choose>
								<!-- 상품 업로드한지 7일 지났으면 신상품 배지 안보이게 -->
								<c:if test="${sale == 1 }">
									<h6><span class="badge bg-secondary">신상품</span></h6>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		
		<!-- 페이징 버튼 -->
		<div style="display: flex; justify-content: center; align-items: center;">
			<ul class="btn-group pagination">
				<c:choose>
				
					<c:when test="${empty param.list}">
					    <c:if test="${pageMaker.prev}">
						    <li>
						        <a class="btn btn-outline-secondary" href='<c:url value="/product/${Name}?page=${pageMaker.startPage-1}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
						    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
					    	<c:choose>
					    		<c:when test="${pageNum == 1 && empty param.page}">
								    <li>
								        <a class="btn btn-secondary disabled" href='<c:url value="/product/${Name}?page=1"/>'><i>${pageNum}</i></a>
								    </li>		    		
					    		</c:when>
					    		<c:when test="${param.page eq pageNum}">
								    <li>
								        <a class="btn btn-secondary disabled" href='<c:url value="/product/${Name}?page=${pageNum}"/>'><i>${pageNum}</i></a>
								    </li>
								</c:when>
								<c:otherwise>
									<li>
								        <a class="btn btn-outline-secondary" href='<c:url value="/product/${Name}?page=${pageNum}"/>'><i>${pageNum}</i></a>
								    </li>
								</c:otherwise>
							</c:choose>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						    <li>
						        <a class="btn btn-outline-secondary" href='<c:url value="/product/${Name}?page=${pageMaker.endPage+1}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
						    </li>
					    </c:if>
					</c:when>
					
					<c:otherwise>
					    <c:if test="${pageMaker.prev}">
						    <li>
						        <a class="btn btn-outline-secondary" href='<c:url value="/product/${Name}?list=${param.list}&page=${pageMaker.startPage-1}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
						    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
					    	<c:choose>
					    		<c:when test="${pageNum == 1 && empty param.page}">
								    <li>
								        <a class="btn btn-secondary disabled" href='<c:url value="/product/${Name}?list=${param.list}&page=1"/>'><i>${pageNum}</i></a>
								    </li>		    		
					    		</c:when>
					    		<c:when test="${param.page eq pageNum}">
								    <li>
								        <a class="btn btn-secondary disabled" href='<c:url value="/product/${Name}?list=${param.list}&page=${pageNum}"/>'><i>${pageNum}</i></a>
								    </li>
								</c:when>
								<c:otherwise>
									<li>
								        <a class="btn btn-outline-secondary" href='<c:url value="/product/${Name}?list=${param.list}&page=${pageNum}"/>'><i>${pageNum}</i></a>
								    </li>
								</c:otherwise>
							</c:choose>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						    <li>
						        <a class="btn btn-outline-secondary" href='<c:url value="/product/${Name}?list=${param.list}&page=${pageMaker.endPage+1}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
						    </li>
					    </c:if>					
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		
		
	</main>
	
	
	<!-- 푸터 -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
		
</body>

<script src="/resources/js/sort.js"></script>
<script>

// var element = document.getElementById("sort");

	<c:if test="${not empty param.list || not empty param.page}">
		document.getElementById("sort").scrollIntoView(true);
	</c:if>
	
</script>

</html>