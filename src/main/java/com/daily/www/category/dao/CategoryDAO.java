package com.daily.www.category.dao;

import java.util.List;

import com.daily.www.category.vo.CategoryVO;

public interface CategoryDAO {

	// 카테고리(+세부) 검색 - 전체
	List<CategoryVO> categoryList();

	// 카테고리 검색 중복x
	List<CategoryVO> cNameList();

	// 해당 카테고리의 세부 검색
	List<CategoryVO> detailList(String c_name);
	

}