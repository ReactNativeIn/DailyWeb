package com.daily.www.product;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daily.common.util.Criteria;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.daily.www.product";
	
	//===================================================
	// 카테고리 총 상품 수
	//===================================================
	@Override
	public int listTotalCount(Criteria cri) throws Exception {
		
		return sqlSession.selectOne(namespace + ".listTotalCount", cri);
	}
	
	//===================================================
	// 상품 목록 보기 (페이징 처리)
	//===================================================
	@Override
	public List<ProductVO> listPaging(Criteria cri) throws Exception {
				
		return sqlSession.selectList(namespace + ".listPaging", cri);
	}
}
