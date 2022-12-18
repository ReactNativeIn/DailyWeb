package com.daily.www.size.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.product.dto.ProductDTO;
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

	// size_id에 해당하는 size 정보 가져오기
	@Override
	public String getSize(int size_id) {
		return sqlSession.selectOne(NAMESPACE + ".getSize");
	}

	@Override
	public int getColorId(ColorVO color_id) {
		return sqlSession.selectOne(NAMESPACE + ".getColorId");
	}

	@Override
	public int getProductId(ProductDTO product_id) {
		return sqlSession.selectOne(NAMESPACE + ".getProductId");
	}


}
