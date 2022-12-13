<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입</title>
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	

	<style>
	@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
	@import url("//fonts.googleapis.com/earlyaccess/nanumgothic.css");
			
			input[type=password]{
				font-family:"Nanum Gothic", sans-serif !important;
			}
			input[type=password]::placeholder {
				font-family: 'Jeju Gothic', sans-serif; 
			}
			input[type=text]{
				font-family: 'Jeju Gothic', sans-serif; 
			}
			input[type=text]::placeholder {
				font-family: 'Jeju Gothic', sans-serif; 
			}	
			input[type=number]{
				font-family: 'Jeju Gothic', sans-serif; 
			}
			input[type=number]::placeholder {
				font-family: 'Jeju Gothic', sans-serif; 
			}				
			input[type=email]{
				font-family: 'Jeju Gothic', sans-serif; 
			}
			input[type=email]::placeholder {
				font-family: 'Jeju Gothic', sans-serif; 
			}	
			button[type=button]{
				font-family: 'Jeju Gothic', sans-serif; 
				font-size: 14px;
			}			
			button[type=submit]{
				font-family: 'Jeju Gothic', sans-serif;
				font-size: 13px;
			}			
			main {
				min-width: 600px;
				margin-top: 50px;
			}
			#memberForm, #memberForm div{
				max-width: 500px;
				min-width: 300px;
			}
			#memberForm {
				border: 3px solid rgba(220, 220, 220, 1); 
				padding: 20px 20px;
				margin: 0px auto;
			}
			#memberForm input{
				height: 50px;
			}
			#memberForm > .input-group:first-child {
				margin-top: 10px
			}
			#memberForm > .input-group {
				margin-bottom: 15px
			}
			#memberForm > .form-group {
				margin-top: 30px;
				text-align: center;
			}
	</style>
	<script src="${contextPath }/resources/js/register.js"></script>
</head>
<body>

<!-- 메뉴바 -->
<jsp:include page="../common/otherHeader.jsp" flush="false"/>

<main class="container">
<div>
	<form class="form-horizontal"  id="memberForm" method="post" action="${contextPath}/member/addMember">
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-person fs-5"></i>
					</span>
					<input type="text" class="form-control" id="id" name="id" placeholder="아이디"/>					
					<button class="idCheck btn btn-outline-success" type="button" id="idCheck" onClick="fn_idCheck();" value="N">중복확인
					</button>
				</div>
				<input type="hidden" id="checked_id" value="">
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-lock fs-5"></i>
					</span>
					<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호"/>
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-lock-fill fs-5"></i>
					</span>
					<input type="password" class="form-control" id="passwordRe" name="passwordRe" placeholder="비밀번호 재확인"/>
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-card-list fs-5"></i>
					</span>
					<input type="text" class="form-control" id="name" name="name" placeholder="이름"/>
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-calendar-date fs-5"></i>
					</span>
					<input type="text" class="form-control" id="birthday" name="birthday" placeholder="생년월일" readonly/>
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-phone fs-5"></i>
					</span>
					<input type="text" class="form-control" id="phone" name="phone" placeholder="'-'를 제외하고 입력해 주세요."/>
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-envelope fs-5"></i>
					</span>
					<input type="email" class="form-control" id="email" name="email" placeholder="이메일"/>
				</div>
				<div class="input-group">
					<span class="input-group-text">
						<i class="bi bi-person-circle fs-5"></i>
					</span>
					<input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임"/>
				</div>
				<div class="form-group btn-group-lg">
					<button class="btn btn-secondary cancel" type="button" style="width:75px; height:38px;">취소</button>
					<button id="submit" class="btn btn-success ms-1" type="submit" style="width:85px; height:38px;">회원가입</button>
				</div>
			</form>
			</div>
		</main>
		
</body>

<script>

//취소/회원가입 버튼 함수
$(document).ready(function () {
	 //input을 datepicker로 선언
    $("#birthday").datepicker({
        dateFormat: 'yy-mm-dd' //달력 날짜 형태
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
        ,changeYear: true //option값 년 선택 가능
        ,changeMonth: true //option값  월 선택 가능                        
        ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
        ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
    });                    
    
    $('#datepicker').datepicker('setDate', 'today'); // 초기값을 오늘로 설정. 
	
  //--------------------------------------
  //아이디 중복체크 여부확인
  //--------------------------------------

  $("#idCheck").click(function(){
  	$("#checked_id").val("y");
  });
	  // 취소 버튼이 눌렸을 경우 로그인 화면으로 이동한다.
	  $(".cancel").on("click", function () {
	    // location.href = "/member/loginForm.do";
	    history.back();
	  });

	  // 회원가입 버튼을 눌렀을 경우
	  $("#submit").on("click", function () {
	    
	    var p1 = $("#password").val(); // 비밀번호 값 변수
		var p2 = $("#passwordRe").val(); // 비밀번호 체크 값 변수
	    
	    if ($("#id").val() == "") {
	      alert("아이디를 입력하셔야 합니다.");
	      $("#id").focus();
	      return false;
	    }
		
	    if ($("#id").val().length < 4) {
	      alert("아이디는 최소 4자리를 입력하셔야 합니다.");
	      $("#id").focus();
	      return false;
	    }
	    if($("#checked_id").val()==''){
	      alert('아이디의 중복확인을 진행하셔야 합니다.');
	      $("#id").focus();
	      return false;
	    }
	    if ($("#password").val() == "") {
	      alert("비밀번호를 입력해 주세요.");
	      $("#password").focus();
	      return false;
	    }
	    if ($("#passwordRe").val() == "") {
	      alert("비밀번호 확인값을 입력해 주세요.");
	      $("#passwordRe").focus();
	      return false;
	    } else if (p1 != p2) {
		  alert("비밀번호가 다릅니다. 다시 입력해주세요");
		  $("#passwordRe").focus();
	      return false;
	    }    
	    if ($("#name").val() == "") {
	      alert("이름을 입력하셔야 합니다.");
	      $("#name").focus();
	      return false;
	    }
	    if ($("#birthday").val() == "") {
	      alert("생년월일을 입력하셔야 합니다.");
	      $("#birthday").focus();
	      return false;
	    }
	    if ($("#phone").val() == "") {
	      alert("연락처를 입력하셔야 합니다.");
	      $("#phone").focus();
	      return false;
	    } else if ($("#phone").val().length < 10 || isNaN($("#phone").val())) {
	      alert("올바른 형태의 연락처를 입력해주세요.");
	      $("#phone").focus();
	      return false;
	    }
	    
	    if ($("#email").val().trim() == "") {
	      alert("이메일을 입력하셔야 합니다.");
	      $("#email").focus();
	      return false;
	    }
	    
	    if ($("#nickname").val().trim() == '') {
	      alert("닉네임을 입력하셔야 합니다.");
	      $("#nickname").focus();
	      return false;
	    }
	    
	    alert("확인");
	  });
	});
</script>

</html>















