package com.daily.www.product;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daily.www.product.ProductVO;
import com.daily.common.util.Criteria;
import com.daily.common.util.PageMaker;

//===================================================
// 상품 목록 보여주기 (페이징 처리)
//===================================================

@RestController
@RequestMapping(value="/list")
public class ProductController {
	
	@Inject
	ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newView(Criteria cri, String list, HttpServletResponse response) throws Exception {
		logger.info("CategoryController 작동시작...");
		
		ModelAndView mav = new ModelAndView("/productList");
		
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
		
		logger.info("CategoryController 작동시작...");
		
		ModelAndView mav = new ModelAndView("/productList");
		
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
