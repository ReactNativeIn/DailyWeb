package com.daily.www.color.dao;

import com.daily.www.color.vo.ColorVO;

public interface ColorDAO {

	// color 아이디 생성
	int createColorId();

	// 색상 등록
	int insertColor(ColorVO colorVO);

}