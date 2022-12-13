<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>personalInfo</title>
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

		</style>

	</head>
	<body>
			<section id="content">
				<div class="border-top border-5 border-dark w-100 list-group" >
					<table id="listTable" class="w-75 text-left" border="0" >
						<thead id="listTitle" class="fs-4 fw-bold">
							<tr>
								<td>
									회원 정보 조회
								</td>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td >																		
										<!-- <button <a href="#" class="btn btn-success"></a>>>>>>아이디</button>	  -->
									<button  class="list-group-item list-group-item-action action" >
									 ${reviewVO.userId}</button>	
									</td>
								</tr>
								<tr >
									<td>
									<input type="text" placeholder="이름"/>
									<button  onclick="location.href=''" class="list-group-item list-group-item-action action" disabled>
									</button>
									</td>
								</tr>
								<tr>
									<td>
										<input type="password" placeholder="비밀번호"/>
										<button  onclick="location.href=''" class="list-group-item list-group-item-action action" disabled>
										</button>
									</td>
								</tr>
								<tr>
									<td>
										<input type="text" placeholder="생년월일"/> <!-- 대철이나 한길이형이 데이터픽커(?) 가져다 쓰시면 됩니다. -->
										<button  onclick="location.href=''" class="list-group-item list-group-item-action action">
										</button>
									</td>
								</tr>
								<tr>
									<td>
										<input type="text" placeholder="닉네임"/>
										<button  onclick="location.href=''" class="list-group-item list-group-item-action action" disabled>
										</button>
									</td>
								</tr>
								<tr>
									<td>
										<input type="email" placeholder="이메일"/>
										<button  onclick="location.href=''" class="list-group-item list-group-item-action action" disabled>
										</button>
									</td>
								</tr>
								<tr>
									<td>
									<input type="number" placeholder="포인트조회"/>									
									<button  onclick="location.href=''" class="list-group-item list-group-item-action action" disabled>
									</button>
								</td>
								</tr>
							
						</tbody>
					</table>

				</div>
					<button type="button" onclick="location.href='${contextPath}/member/reView'" class="btn btn-outline-secondary">
					구매 리스트로 이동
					</button>
			</section>
		
	</body>
</html>