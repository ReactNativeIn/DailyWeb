package com.daily.www.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.service.ProductService;

@Controller
@RequestMapping(value = "/product/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	// 상품 등록 화면 이동
	@RequestMapping(value = "/productInsertForm", method = RequestMethod.GET)
	public String productInsertForm(Model model) { 
		model.addAttribute("product_id", productService.createProductId()); // 아이디 미리 생성해서 가져가기
		return "product/productInsertForm";
	}	
	
	// 상품 등록
	@RequestMapping(value = "/productInsert", method = RequestMethod.POST)
	public String productInsert(ProductDTO productDTO, Model model) {
		int check = -1;
		System.out.println("productInsert");
		System.out.println("확인 : " + productDTO);
		
		check = productService.insertProudct(productDTO);
		
		if(check == 1) {
			model.addAttribute("message", "상품 등록 성공");			
		}else {
			model.addAttribute("message", "상품 등록 실패");
		}
		return "/product/productList"; // 성공시 상품 리스트 화면 이동
		
	}
	
	
	
	// 상품 리스트 화면 이동
	@RequestMapping(value ="/productList", method = RequestMethod.GET)
	public String productListForm() {
		
		return "product/productList";
	}
}
