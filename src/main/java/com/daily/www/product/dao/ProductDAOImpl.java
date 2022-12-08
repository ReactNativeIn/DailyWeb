package com.daily.www.product.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "ProductDAO";
	
	// 상품 아이디 생성
	@Override
	public int createProductId() {
		return sqlSession.selectOne(NAMESPACE + ".createId");
	}
	
	// 상품 등록
	@Override
	public int insertProduct(ProductDTO productDTO) {
		return sqlSession.insert(NAMESPACE + ".insertProduct", productDTO);
	}
	
	// 상품 수정
	@Override
	public int updateProduct(ProductVO productVO) {
		return sqlSession.insert(NAMESPACE + ".updateProduct", productVO);
	}
	
	// 상품 삭제
	@Override
	public int deleteProduct(int product_id) {
		return sqlSession.insert(NAMESPACE + ".deleteProduct", product_id);
	}
	
	
	
	
}
