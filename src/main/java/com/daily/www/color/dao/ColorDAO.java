package com.daily.www.color.dao;

import java.util.List;

import com.daily.www.color.vo.ColorVO;

public interface ColorDAO {

	// color 아이디 생성
	int createColorId();

	// 색상 등록
	int insertColor(ColorVO colorVO);
	
	// 해당 상품의 색상 리스트
	List<ColorVO> getListColor(int product_id);
}