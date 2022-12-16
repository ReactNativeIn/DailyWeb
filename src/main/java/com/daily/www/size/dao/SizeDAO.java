package com.daily.www.size.dao;

import java.util.List;

import com.daily.www.size.vo.SizeVO;

public interface SizeDAO {

	// 사이즈 등록
	int inserSize(SizeVO sizeVO);

	// 상품에 대한 사이즈 아이디 가져오기
	List<SizeVO> getSizeList(int size_id);
	
}