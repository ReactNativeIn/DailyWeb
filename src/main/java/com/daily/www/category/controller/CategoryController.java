package com.daily.www.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.category.service.CategoryService;
import com.daily.www.category.vo.CategoryVO;

@Controller
@RequestMapping("/category/*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 상품 등록 화면 - 카테고리(중복x) Ajax
	@RequestMapping(value = "/c_name", method = RequestMethod.POST)
	@ResponseBody
	public List<CategoryVO> categoryList(){
		return categoryService.cNameList();
	}
	
	// 상품 등록 화면 세부 카테고리 Ajax
	@RequestMapping(value ="/detail", method = RequestMethod.POST)
	@ResponseBody
	public List<CategoryVO> detailList(String c_name) { 
		System.out.println(categoryService.detailList(c_name));
		return categoryService.detailList(c_name);
	}
}
