package com.daily.www.color.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.color.vo.ColorVO;

@Repository
public class ColorDAOImpl implements ColorDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "ColorDAO";
	
	// color 아이디 생성
	@Override
	public int createColorId() {
		return sqlSession.selectOne(NAMESPACE + ".creatId");
	}
	
	// 색상 등록
	@Override
	public int insertColor(ColorVO colorVO) {
		return sqlSession.insert(NAMESPACE + ".insertColor", colorVO);
	}

	// color_id에 해당하는 상품 color 정보 가져오기
	@Override
	public String getColor(int color_id) {
		return sqlSession.selectOne(NAMESPACE + ".getColor", color_id);
	}

	@Override
	public int getProduct_id(ColorVO colorVO) {
		return sqlSession.selectOne(NAMESPACE + ".getProduct_id", colorVO);
	}
}
