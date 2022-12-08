package com.daily.www.product;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.daily.common.util.Criteria;
import com.daily.www.member.MemberVO;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Inject
	ProductDAO productDAO;
	
	//===================================================
	// 카테고리 전체 상품 수 구하기 (페이징 처리)
	//===================================================
	@Override
	public int listTotalCount(Criteria cri) throws Exception {
		
	//	logger.info("BoardServiceImpl 전체 게시글 수 구하기 (페이징 처리) ==> " + cri);
		
		
		return productDAO.listTotalCount(cri);
	}
	
	//===================================================
	// 카테고리 목록 보기 (페이징 처리)
	//===================================================
	@Override
	public List<ProductVO> listPaging(Criteria cri) throws Exception {
		
	//	logger.info("BoardServiceImpl 게시글 목록 보기 (페이징 처리) ==> " + cri);
		
		return productDAO.listPaging(cri);
	}
	

	

	
}
