<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주문내역 조회</title>
		<style>
			main {
				display: flex;
				min-width: 1000px;
			}
			.title {
				font-weight: bold;
    			color: black;
			}
			#content {
				min-width: 1200px;
				margin-top: 50px;
				flex : 1;
			}
			#listTable {
				margin-top: 50px;
			}
			#listTitle {
				border-top: 1px solid;
				border-bottom: 1px solid;
			}
			#listTitle th {
				padding: 20px 0px;
			}
			
		</style>
	</head>
	<body>
		<jsp:include page="../common/header.jsp" flush="false"/>
		
		<main class="container-fluid p-5">
			
			<jsp:include page="../common/sideNav.jsp"/>
			
			<section id="content">
				<h2 class="title">주문내역 조회</h2>
				<div class="border-top border-5 border-dark w-100">
					<table id="listTable" class="w-100 text-center	">
						<thead id="listTitle" class="fs-4 fw-bold">
							<tr>
								<th>
									상품정보
								</th>
								<th>
									주문일자
								</th>
								<th>
									주문번호
								</th>
								<th>
									주문금액(수량)
								</th>
								<th>
									주문상태
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="1" end="20" var="i">
								<tr>
									<td class="text-start" style="width: 500px;">
										<img class="img-thumbnail border-2 w-25" src="/resources/images/test3.jpg"/>
										상품명
									</td>
									<td>
										2022-11-15
									</td>
									<td>
										주문번호(111)
									</td>
									<td>
										주문금액 666666
									</td>
									<td class="text-break">
										<span>
											배송중										
										</span>
										<span>
											<a href="#" class="btn btn-success fw-bold" style="margin-left: 50px;">후기작성</a>															
										</span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</main>
		
		<jsp:include page="../common/footer.jsp"/>
	</body>
</html>