package com.daily.www.member.service;

import java.util.List;

import com.daily.www.member.vo.MemberVO;

public interface MemberService {

	// 전체 member 목록
	public List<MemberVO> getMemberList();
	
	// 로그인 처리
	public MemberVO getLoginById(MemberVO member);

}