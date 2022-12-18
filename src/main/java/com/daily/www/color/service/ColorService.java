package com.daily.www.color.service;

import com.daily.www.color.vo.ColorVO;

public interface ColorService {

	// color 아이디 생성
	int createColorId();
	
	// 색상 등록
	void insertColor(ColorVO colorVO);

	// color_id에 해당하는 상품 color 정보 가져오기
	String getColor(int color_id);
	
	int getProduct_id(ColorVO colorVO);
}