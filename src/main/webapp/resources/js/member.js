/**
 * 회원 정보 변경 함수
 */
 
 /////////////////////////////////////////////////////////////
 //이름 변경
 /////////////////////////////////////////////////////////////
 function fn_memberRegister() {

	// 입력한 값을 가져온다.
	let	rename	= $("#rename").val();

	
	// 이름 항목에 값이 없으면 입력하도록 한다.
	if($("#rename").val() == "") {
		alert("이름은 필수 입력 항목입니다.");
		$("rename").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/member/modify",
		data:		{name:rename},
		success:	function(result) {
			if(result == "Y") {
				alert("이름을 바꾸었습니다.");
				location.href = "/member/mypage"; 
			}
			else{
			 	alert("실패해따 이새끼야.");
			}
		},
		error:		function(result) {
			alert("이름 바꾸기에 실패하였습니다.");
		}
	});		
}
	/////////////////////////////////////////////////////////////
	//비밀 번호 변경
	/////////////////////////////////////////////////////////////
	function fn_memberRegister1() {

	// 입력한 값을 가져온다.
	let	repassword	= $("#repassword").val();

	
	// 비밀번호 항목에 값이 없으면 입력하도록 한다.
	if($("#repassword").val() == "") {
		alert("비밀번호는 필수 입력 항목입니다.");
		$("repassword").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/member/modify",
		data:		{password:repassword},
		success:	function(result) {
			if(result == "Y") {
				alert("비밀번호를 바꾸었습니다.");
				location.href = "/member/mypage"; 
			}
			else{
			 	alert("실패해따 이새끼야.");
			}
		},
		error:		function(result) {
			alert("비밀번호 바꾸기에 실패하였습니다.");
		}
	});
}
	/////////////////////////////////////////////////////////////
	//nickname  변경
	/////////////////////////////////////////////////////////////
	function fn_memberRegister2() {

	// 입력한 값을 가져온다.
	let	renickname	= $("#renickname").val();

	
	// 비밀번호 항목에 값이 없으면 입력하도록 한다.
	if($("#renickname").val() == "") {
		alert("닉네임은 필수 입력 항목입니다.");
		$("renickname").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/member/modify",
		data:		{nickname:renickname},
		success:	function(result) {
			if(result == "Y") {
				alert("닉네임을 바꾸었습니다.");
				location.href = "/member/mypage"; 
			}
			else{
			 	alert("실패해따 이새끼야.");
			}
		},
		error:		function(result) {
			alert("닉네임 바꾸기에 실패하였습니다.");
		}
	});
}
	/////////////////////////////////////////////////////////////
	//email  변경
	/////////////////////////////////////////////////////////////
	function fn_memberRegister3() {

	// 입력한 값을 가져온다.
	let	reemail	= $("#reemail").val();

	
	// 이메일 항목에 값이 없으면 입력하도록 한다.
	if($("#reemail").val() == "") {
		alert("이메일은 필수 입력 항목입니다.");
		$("reemail").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/member/modify",
		data:		{email:reemail},
		success:	function(result) {
			if(result == "Y") {
				alert("이메일을 바꾸었습니다.");
				location.href = "/member/mypage"; 
			}
			else{
			 	alert("실패해따 이새끼야.");
			}
		},
		error:		function(result) {
			alert("이메일 바꾸기에 실패하였습니다.");
		}
	});
}
	/////////////////////////////////////////////////////////////
	//생년월얼일  변경
	/////////////////////////////////////////////////////////////
	function fn_memberRegister4() {

	// 입력한 값을 가져온다.
	let	rebirthday	= $("#rebirthday").val();

	
	// 이메일 항목에 값이 없으면 입력하도록 한다.
	if($("#rebirthday").val() == "") {
		alert("생년월일은 필수 입력 항목입니다.");
		$("rebirthday").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/member/modify",
		data:		{birthday:rebirthday},
		success:	function(result) {
			if(result == "Y") {
				alert("생년월일을 바꾸었습니다.");
				location.href = "/member/mypage"; 
			}
			else{
			 	alert("실패해따 이새끼야.");
			}
		},
		error:		function(result) {
			alert("생년월일 바꾸기에 실패하였습니다.");
		}
	});
}
	
 