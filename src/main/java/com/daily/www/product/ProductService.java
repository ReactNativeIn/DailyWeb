package com.daily.www.product;

import java.util.List;

import com.daily.common.util.Criteria;
import com.daily.www.member.MemberVO;

public interface ProductService {

	//===================================================
	// new 카테고리 전체 상품 수 구하기 (페이징 처리)
	//===================================================
	int listTotalCount(Criteria cri) throws Exception;

	//===================================================
	// new 카테고리 목록 보기 (페이징 처리)
	//===================================================
	List<ProductVO> listPaging(Criteria cri) throws Exception;





}