package com.daily.www.color.dao;

import java.util.List;

import com.daily.www.color.vo.ColorVO;

public interface ColorDAO {

	// color 아이디 생성
	int createColorId();

	// 색상 등록
	int insertColor(ColorVO colorVO);
	
	// 상품에 대한 색상 아이디 가져오기
	List<ColorVO> getColorList(int color_id);

}