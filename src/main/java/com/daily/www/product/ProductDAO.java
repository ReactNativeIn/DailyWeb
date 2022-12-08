package com.daily.www.product;

import java.util.List;

import com.daily.common.util.Criteria;

public interface ProductDAO {

	//===================================================
	// new 상품 리스트 총 개수
	//===================================================
	int listTotalCount(Criteria cri) throws Exception;

	//===================================================
	// new 상품 목록 보기 (페이징 처리)
	//===================================================
	List<ProductVO> listPaging(Criteria cri) throws Exception;



}