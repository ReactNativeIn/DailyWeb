package com.daily.www.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.daily.www.member.vo.MemberVO;
import com.daily.www.orders.dto.OrdersDTO;

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
	
	// 아이디 중복 검사 (AJAX)
	@Override
	public int idCheck(MemberVO memberVO) throws DataAccessException {

		return sqlSession.selectOne(NAMESPACE + ".idCheck", memberVO);
		
	}
	
	// 회원가입 처리
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {

		int result = sqlSession.insert(NAMESPACE + ".addMember", memberVO);
		return result;
		
	}
	
	// 포인트 변환
	@Override
	public int updatePoint(OrdersDTO ordersDTO) throws Exception {
		
		
		
		return sqlSession.update(NAMESPACE + ".updatePoint", ordersDTO);
	}
	
}
