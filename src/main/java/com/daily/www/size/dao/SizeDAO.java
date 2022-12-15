package com.daily.www.size.dao;

import java.util.List;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.size.vo.SizeVO;

public interface SizeDAO {

	// 사이즈 등록
	int inserSize(SizeVO sizeVO);
	
	// color_id에 해당하는 size 리스트
	List<SizeVO> getListSize(List<ColorVO> colorList);

}