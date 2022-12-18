package com.daily.www.size.dao;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.size.vo.SizeVO;

public interface SizeDAO {

	// 사이즈 등록
	int inserSize(SizeVO sizeVO);
	
	// size_id에 해당하는 size 정보 가져오기
	String getSize(int size_id);
	
	int getColorId(ColorVO color_id);
	
	int getProductId(ProductDTO product_id);
}