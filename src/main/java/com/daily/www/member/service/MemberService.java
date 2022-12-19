package com.daily.www.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.daily.www.member.dao.MemberDAO;
import com.daily.www.member.vo.MemberVO;

public interface MemberService {

	// 전체 member 목록
	List<MemberVO> getMemberList();

	// 회원가입 처리
	int addMember(MemberVO memberVO) throws DataAccessException;

	// 아이디 중복 검사 (AJAX)
	int idCheck(MemberVO memberVO) throws Exception;

	// 로그인 처리
	MemberVO getLoginById(MemberVO member);
	
	//회원 한명의 정보
	MemberVO getMemberById(MemberVO member);
	
	//회원 정보 수정
	public int modify(MemberVO memberVO) throws Exception;
		
}