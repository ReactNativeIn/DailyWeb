package com.daily.www.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.category.dao.CategoryDAO;
import com.daily.www.category.vo.CategoryVO;

@Service
public class CateogryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	// 카테고리(+세부) 검색 - 전체
	@Override
	public List<CategoryVO> categoryList(){
		return categoryDAO.categoryList();
	}
	
	// 카테고리 검색 중복x
	@Override
	public List<CategoryVO> cNameList(){
		return categoryDAO.cNameList();
	}
	
	// 해당 카테고리의 세부 검색
	@Override
	public List<CategoryVO> detailList(String c_name){
		List<CategoryVO> detail = categoryDAO.detailList(c_name);
		if(detail.get(0) != null) { // null이면 null을 0번 방에 담아서 줌
			return detail;
		}else {
			return null;			
		}
	}
}
