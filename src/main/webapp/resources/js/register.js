/**
 * 회원가입 관련 함수
 */

//-----------------------------------
// 아이디 중복체크
//-----------------------------------

function fn_idCheck() {
  // alert($("#id").val());
  $.ajax({
    url: "/member/idCheck",
    type: "post",
    dataType: "json",
    data: { id: $("#id").val() },
    success: function (data) {
      if (data == 1) {
        alert("이미 사용 중인 아이디입니다.");
      } else if (data == 0) {
        // 버튼 id="idCheck"의 속성인 value의 값을 "Y"로 변경한다.
        $("#idCheck").attr("value", "Y");
        alert("사용 가능한 아이디입니다.");
      }
    },
  });
}
