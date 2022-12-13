package com.daily.www.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.daily.www.member.vo.MemberVO;

public interface MemberDAO {

	// 전체 member 목록
	List<MemberVO> getMemberList();

	// 로그인 처리
	MemberVO getLoginById(MemberVO member);

	// 아이디 중복 검사 (AJAX)
	int idCheck(MemberVO memberVO) throws DataAccessException;

	// 회원가입 처리
	int addMember(MemberVO memberVO) throws DataAccessException;

}