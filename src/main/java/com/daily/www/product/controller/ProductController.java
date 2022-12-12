package com.daily.www.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	@RequestMapping(value = "/productInsert", method = RequestMethod.POST)
	public String productInsert(ProductDTO productDTO) {
		int check = -1;
		System.out.println("productInsert");
		System.out.println("확인 : " + productDTO);
		
		check = productService.insertProudct(productDTO);
		
		if(check == 1) {
			return "Y";			
		}else {
			return "N";
		}
	}
	
	// 상품 리스트 화면 이동 - 관리자
	@RequestMapping(value ="/productList", method = RequestMethod.GET)
	public String productListForm(Model model) {
		System.out.println("들림");
		return "admin/productList_A";
	}
	
	// 상품 상세 조회(상세화면)
	@RequestMapping(value="/productDetail", method=RequestMethod.GET)
	public String productDetail(int product_id, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("ProductController productDetail() product_id : " + Integer.parseInt((String)request.getParameter("product_id")));


		ProductDTO productDTO = productService.productDetail(product_id);
		
		model.addAttribute("productDetail", productDTO);
		
		return "/product/productDetail";
	}
	
	// New 상품 목록 보여주기
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
	
	// 남성 상품 목록 보여주기
	@RequestMapping(value = "/남성", method = RequestMethod.GET)
	public ModelAndView men(Criteria cri, String list, String detail) throws Exception {
		
		ModelAndView mav = new ModelAndView("product/itemList");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		cri.setList(list);
		
		cri.setName("남성");
		
		cri.setDetail(detail);
		
		pageMaker.setTotalCount(productService.listTotalCount(cri));
		
		// cri에 해당하는만큼 상품을 가져와서 view에게 넘겨준다.
		List<ProductVO> menList = productService.listPaging(cri);
		
		mav.addObject("List", menList);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("Name", cri.getName());

		
		return mav;
	}
	
	// 여성 상품 목록 보여주기
	@RequestMapping(value = "/여성", method = RequestMethod.GET)
	public ModelAndView women(Criteria cri, String list, String detail) throws Exception {
		
		ModelAndView mav = new ModelAndView("product/itemList");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		cri.setList(list);
		
		cri.setName("여성");
		
		cri.setDetail(detail);
		
		pageMaker.setTotalCount(productService.listTotalCount(cri));
		
		// cri에 해당하는만큼 상품을 가져와서 view에게 넘겨준다.
		List<ProductVO> womenList = productService.listPaging(cri);
		mav.addObject("List", womenList);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("Name", cri.getName());

		
		return mav;
	}
	
	// 남녀공용 상품 목록 보여주기
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
