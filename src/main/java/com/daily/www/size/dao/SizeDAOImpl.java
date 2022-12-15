package com.daily.www.size.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.size.vo.SizeVO;

@Repository
public class SizeDAOImpl implements SizeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "SizeDAO";
	
	// 사이즈 등록
	@Override
	public int inserSize(SizeVO sizeVO) {
		return sqlSession.insert(NAMESPACE + ".insertSize", sizeVO);
	}

	// color_id에 해당하는 size 리스트
	@Override
	public List<SizeVO> getListSize(List<ColorVO> colorList) {
		return sqlSession.selectList(NAMESPACE + ".getListSize", colorList);
	}
	
}
