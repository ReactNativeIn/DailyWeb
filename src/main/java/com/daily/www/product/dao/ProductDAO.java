package com.daily.www.product.dao;

import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.vo.ProductVO;

public interface ProductDAO {

	// 상품 아이디 생성
	int createProductId();

	// 상품 등록
	int insertProduct(ProductDTO productDTO);

	// 상품 수정
	int updateProduct(ProductVO productVO);

	// 상품 삭제
	int deleteProduct(int product_id);

}