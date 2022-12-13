/**
 * 게시글 관련 함수
 */

//-----------------------------------------------------------------------------------------------------------
// 게시글 등록
//-----------------------------------------------------------------------------------------------------------
function fn_boardRegister() {
  // alert("게시글 등록 버튼을 눌렀습니다.");

  // 입력한 값을 가져온다.
  let b_title = $("#b_title").val();
  let id = $("#id").val();
  let b_content = $("#b_content").val();

  // alert(b_title + ":" + id + ":" + b_content);

  // 제목 항목에 값이 없으면 입력하도록 한다.
  if ($("#b_title").val() == "") {
    alert("제목은 필수 입력 항목입니다.");
    $("#b_title").focus();
    return false;
  }

  // 글쓴이 항목에 값이 없으면 입력하도록 한다.
  if ($("#id").val() == "") {
    alert("글쓴이는 필수 입력 항목입니다.");
    $("#id").focus();
    return false;
  }

  // 내용 항목에 값이 없으면 입력하도록 한다.
  if ($("#b_content").val() == "") {
    alert("내용은 필수 입력 항목입니다.");
    $("#b_content").focus();
    return false;
  }

  $.ajax({
    type: "POST",
    url: "/board/boardRegister",
    data: { b_title: b_title, id: id, b_content: b_content },
    success: function (data) {
      if (data == "Y") {
        alert("게시글을 등록하였습니다.");
        // 게시글 등록이 완료되면 게시글 목록 화면으로 이동한다.
        location.href = "boardList";
      }
    },
    error: function (data) {
      alert("게시글을 등록하는데 실패하였습니다.");
    },
  });
} // End - function fn_boardRegister(

//-----------------------------------------------------------------------------------------------------------
// 게시글 수정 화면 불러오기
// form에 action과 method가 없기 때문에 여기에서 만든다. 
// <form action="/board/boardUpdateForm", method="POST" id="form_board">
// 전송할 seq를 담아 놓은 것이 없으므로, input을 만들어 seq를 전송한다.
// frm.append($('만들 태그',  { 태그에 사용할 속성 : 값, 속성 : 값, .....  } ));
//-----------------------------------------------------------------------------------------------------------
function fn_boardUpdateForm(seq) {

	alert("fn_boardUpdateForm(seq)......");
	
	var f = $("#frm");
	f.attr("action", "/board/boardUpdateForm");
	f.attr("method", "POST");
	f.append($('<input/>', {type: 'hidden', name: 'seq', value: seq }));
	f.appendTo('body');
	f.submit();
	
}

//-----------------------------------------------------------------------------------------------------------
// 게시글 수정
//-----------------------------------------------------------------------------------------------------------
function fn_boardUpdate() {

	var	seq		= $("#seq").val();
	var	b_title	= $("#b_title").val();
	var	id	= $("#id").val();
	var	b_content	= $("#b_content").val();
	
	alert(seq + " - " + b_title + " - " + id + " - " + b_content);
	 
	$.ajax({
		type:		"POST",
		url:		"/board/boardUpdate",
		data:		{seq: seq, b_title: b_title, id: id, b_content: b_content},
		success:	function(data) {
			if(data == "Y") {
				alert("게시글 수정이 완료되었습니다.");
				fn_boardDetail(seq);
			} else {
				alert("게시글 수정이 되지 않았습니다.\n\n잠시 후에 다시 해주십시오.");
			}		
		},
		error:		function(data) {
			alert("실패");
			console.log(data);
		}
	});

}


//-----------------------------------------------------------------------------------------------------------
// 게시글 삭제
//-----------------------------------------------------------------------------------------------------------
function fn_boardDelete(seq) {

	if(!confirm("게시글을 삭제하시겠습니까?\n\n삭제하시려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오!")) {
		alert("게시글 삭제를 취소하셨습니다.");
	} else {
		$.ajax({
			type:		"POST",
			url:		"/board/boardDelete",
			data:		{seq : seq},
			success:	function(data) {
				if(data == "Y") {
					alert("게시글의 삭제를 완료하였습니다.");
					location.href = "/board/boardList";
				}
			},
			error:		function(data) {
				alert("게시글을 삭제하는데 문제가 발생하였습니다.");
			}
		
		});
	}


} // End - fn_boardDelete(seq) 


//-----------------------------------------------------------------------------------------------------------
// 게시글 번호에 해당하는 상세페이지로 이동하기
//-----------------------------------------------------------------------------------------------------------
function fn_boardDetail(seq)
{
	location.href = "/board/boardDetail?seq=" + seq;
} // End - function fn_boardDetail(seq)

