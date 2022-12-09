package com.daily.www;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daily.www.cartitem.CartItemVO;
import com.daily.www.color.ColorVO;
import com.daily.www.member.MemberVO;
import com.daily.www.product.ProductService;
import com.daily.www.product.ProductVO;
import com.daily.www.size.SizeVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(MemberVO memberVO, ProductVO productVO, SizeVO sizeVO, ColorVO colorVO, Model model) throws Exception {
		logger.info("HomeController 작동시작...");
		
		// 구매하기 눌렀을때,
		
		
		// 장바구니에서 구매하기 눌렀을때(얘는 항목이 여러개 일 수 있음 list)
		// List<CartItemVO> list = CartService.cartList(member);
		
		
		return "payment";
	}
	

	

	

	
}
