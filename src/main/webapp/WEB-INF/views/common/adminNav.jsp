<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
	#category{
		width: 100%;
	    display: flex;
   		justify-content: space-between;
   		margin: 0px;
   		padding: 0px;
   		color: black;
   		list-style: none;
	}
	#category > li {
		text-align: center;
   		font-size: 1.5em;
	}
	#category > li > a {
		display: block;
		padding: 0px 15px;
	}
</style>
<nav style="border: 3px solid rgba(220, 220, 220, 1); border-left: none; border-right: none; margin-bottom: 15px">
	<div class="container">
		<div class="row">
			<ul id="category">
				<li>
					<a class="dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">상품관리</a>
					  <ul class="dropdown-menu">
				      	<li><a class="dropdown-item" href="/product/productList">상품목록</a></li>
					    <li><a class="dropdown-item" href="/product/productInsertForm">상품등록</a></li>
					  </ul>
				</li>
				<li class="">
					<a href="#">회원관리</a>
				</li>
				<li>
					<a href="#">Q&A관리</a>
				</li>
			</ul>
		</div>
	</div>
</nav>	