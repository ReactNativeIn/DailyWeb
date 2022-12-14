<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 페이징 버튼 -->
<div style="display: flex; justify-content: center; align-items: center;">
	<ul class="btn-group pagination">
	    <c:if test="${pageMaker.prev}">
		    <li>
		        <a class="btn btn-outline-secondary" href="/${pageMaker.cri.name}?list=${pageMaker.cri.list}&page=${pageMaker.startPage-1}"><span class="glyphicon glyphicon-chevron-left"></span></a>
		    </li>
	    </c:if>
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	    	<c:choose>
	    		<c:when test="${pageNum == 1 && empty param.page}">
				    <li>
				        <a class="btn btn-secondary disabled" href="/${pageMaker.cri.name}?list=${pageMaker.cri.list}&page=1"><i>${pageNum}</i></a>
				    </li>		    		
	    		</c:when>
	    		<c:when test="${param.page eq pageNum}">
				    <li>
				        <a class="btn btn-secondary disabled" href="/${pageMaker.cri.name}?list=${pageMaker.cri.list}&page=${pageNum}"><i>${pageNum}</i></a>
				    </li>
				</c:when>
				<c:otherwise>
					<li>
				        <a class="btn btn-outline-secondary" href="/${pageMaker.cri.name}?list=${pageMaker.cri.list}&page=${pageNum}"><i>${pageNum}</i></a>
				    </li>
				</c:otherwise>
			</c:choose>
	    </c:forEach>
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		    <li>
		        <a class="btn btn-outline-secondary" href="/${pageMaker.cri.name}?list=${pageMaker.cri.list}&page=${pageMaker.endPage+1}"><span class="glyphicon glyphicon-chevron-right"></span></a>
		    </li>
	    </c:if>					
	</ul>
</div>