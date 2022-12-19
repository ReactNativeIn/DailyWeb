package com.daily.www.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.daily.www.member.dao.MemberDAO;
import com.daily.www.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	// 전체 member 목록
	@Override
	public List<MemberVO> getMemberList(){
		return memberDAO.getMemberList();
	}

	// 회원가입 처리
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {

		return memberDAO.addMember(memberVO);
		
	}
	
	// 아이디 중복 검사 (AJAX)
	@Override
	public int idCheck(MemberVO memberVO) throws Exception {
		int	result = memberDAO.idCheck(memberVO);
		return result;
	}
	
	// 로그인 처리
	@Override
	public MemberVO getLoginById(MemberVO member) {
		
		MemberVO result = memberDAO.getLoginById(member);
		if(result != null) { // 아이디가 존재하면
			if(result.getPassword().equals(member.getPassword())) { // 비밀번호가 일치하면
				return result;
			}
		}
		return null;
	}
	
	//회원 한명의 정보
	@Override
	public MemberVO getMemberById(MemberVO member) {
		
		return 	memberDAO.getLoginById(member);
	}

	//회원 정보 수정
	@Override
	public int modify(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.modify(memberVO);
	}
	
	
	
	
}
