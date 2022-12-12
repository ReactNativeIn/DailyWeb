package com.daily.www.color.service;

import com.daily.www.color.vo.ColorVO;

public interface ColorService {

	// color 아이디 생성
	int createColorId();

	// 색상 등록
	void insertColor(ColorVO colorVO);

}