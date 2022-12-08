package com.daily.www.member.dao;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "MemberDAO";
	
	// 전체 member 목록
	@Override
	public List<MemberVO> getMemberList() {
		return sqlSession.selectList(NAMESPACE + ".allSelect");
	}

	// 로그인 처리
	@Override
	public MemberVO getLoginById(MemberVO member) {
		return sqlSession.selectOne(NAMESPACE + ".getMember", member);
	}
}
