package com.daily.www.member.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
