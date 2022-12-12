package com.daily.www.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.PageMaker;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.service.ProductService;
import com.daily.www.product.vo.ProductVO;

@Controller
@RequestMapping(value = "/product/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	// 상품 등록 화면 이동 - 관리자
	@RequestMapping(value = "/productInsertForm", method = RequestMethod.GET)
	public String productInsertForm(Model model) { 
		model.addAttribute("product_id", productService.createProductId()); // 아이디 미리 생성해서 가져가기
		return "admin/productInsertForm";
	}	
	
	// 상품 등록 - 관리자
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
		return "admin/productList"; // ajax로 변경 이유 : SPA(single page application)로 해서 뒤로가기를 못하게 하기 위해 location.replace()
		
	}
	
	// 상품 리스트 화면 이동 - 관리자
	@RequestMapping(value ="/productList", method = RequestMethod.GET)
	public String productListForm(Model model) {
		
		return "admin/productList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newView(Criteria cri, String list, HttpServletResponse response) throws Exception {
		System.out.println("CategoryController 작동시작...");

		
		ModelAndView mav = new ModelAndView("product/itemList");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		cri.setList(list);
		
		cri.setName("new");
		
		// new 카테고리 전체 상품수를 구한다.
		pageMaker.setTotalCount(productService.listTotalCount(cri));
		
		// cri에 해당하는만큼 상품을 가져와서 view에게 넘겨준다.
		List<ProductVO> newList = productService.listPaging(cri);
		mav.addObject("List", newList);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("Name", cri.getName());
		
		return mav;

	}
	
	@RequestMapping(value = "/남녀공용", method = RequestMethod.GET)
	public ModelAndView uni(Criteria cri, String list, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView("product/itemList");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		cri.setList(list);
		
		cri.setName("남녀공용");
		
		// 남녀공용 카테고리 전체 상품수를 구한다.
		pageMaker.setTotalCount(productService.listTotalCount(cri));
		
		// cri에 해당하는만큼 상품을 가져와서 view에게 넘겨준다.
		List<ProductVO> unisexList = productService.listPaging(cri);
		mav.addObject("List", unisexList);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("Name", cri.getName());
		
		return mav;
	}
}
