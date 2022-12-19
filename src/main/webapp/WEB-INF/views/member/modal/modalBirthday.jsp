<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<script src="/resources/js/member.js"></script>

<p>기존 생년월일</p>

<input class="w-50" type="text" placeholder="생년월일" value="${member.birthday }" disabled="disabled">
<br/><br/><hr>
<p>바꿀 생년월일</p>

	<input class="w-50" type="text" id="rebirthday" placeholder="생년월일을 선택하세요"  value="" readonly/>
	<button type="button"  class="btn btn-primary" onclick="fn_memberRegister4();">바꾸기</button>

</body>

<script>
//취소/회원가입 버튼 함수
$(document).ready(function () {
	 //input을 datepicker로 선언
    $("#rebirthday").datepicker({
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
});
</script>
</html>