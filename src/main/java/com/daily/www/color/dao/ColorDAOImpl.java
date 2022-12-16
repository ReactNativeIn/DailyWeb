package com.daily.www.color.dao;

import java.util.List;

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

	// 상품에 대한 색상 아이디 가져오기
	public List<ColorVO> getColorList(int color_id) {
		
		return sqlSession.selectList(NAMESPACE + ".getColorList", color_id);
	}
}
