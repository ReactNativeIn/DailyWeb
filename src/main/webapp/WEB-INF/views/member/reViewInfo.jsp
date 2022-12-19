<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>구매 후기</title>
		<style>

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
			
			#hover:hover{
			opacity: 1; 
			background:silver;
			
			}
							
			.box1{ border:1px solid; padding:10px;} 				
		</style>
	</head>
	<body>	
		<main class="container-fluid p-5">
			<section id="content">
				<h2 class="title">구매 후기 작성</h2>
				<div class="border-top border-5 border-dark w-100">
					<table id="listTable" class="w-100 text-center	">
						<thead id="listTitle" class="fs-4 fw-bold">
							<tr>
								<th>
									상품정보
								</th>
								<th>
									구매/확정일
								</th>
						
								<th>
									스타일 후기
								</th>
								
								<th>
									좋아요 별 갯수
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="1" end="5" var="i">
								<tr>
									<td>
										<div class="a">
										<img class="img-thumbnail border-2 w-25" src="/resources/images/test3.jpg"/>
										상품명과사진
										</div>
									</td>
									<td>
										2022-11-15 / 2022-12-01
									</td>
									<td>
										스타일후기
									</td>
								
									<td>
										좋아요 별 갯수
									</td>				
									<td class="text-break">	
									<button type="button" onclick="location.href='${contextPath}/reView/reViewWriter'"
									class="btn btn-outline-secondary">
									후기작성하러가기
									</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<button type="button" onclick="location.href='${contextPath}/member/mypage'"
					class="btn btn-outline-secondary">
				
					나의 정보 조회 화면으로 이동
					</button>
				</div>
			</section>
		</main>	
	</body>
</html>