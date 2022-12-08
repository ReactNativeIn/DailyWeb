package com.daily.www.product.service;

import java.util.List;

import com.daily.www.category.vo.CategoryVO;
import com.daily.www.common.util.Criteria;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.vo.ProductVO;

public interface ProductService {

	// 상품 아이디 생성
	int createProductId();

	// 상품 등록
	int insertProudct(ProductDTO productDTO);

	// 상품 수정
	void updateProduct(ProductVO productVO);

	// 상품 삭제
	void deleteProduct(int product_id);
	
	// new 상품 리스트 총 개수
	int listTotalCount(Criteria cri) throws Exception;
	
	// new 상품 목록 보기 (페이징 처리)
	List<ProductVO> listPaging(Criteria cri) throws Exception;

}