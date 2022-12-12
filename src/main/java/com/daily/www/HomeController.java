package com.daily.www;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.member.vo.MemberVO;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.service.ProductService;
import com.daily.www.product.vo.ProductVO;
import com.daily.www.size.vo.SizeVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(MemberVO memberVO, ProductVO productVO, SizeVO sizeVO, ColorVO colorVO, Model model) throws Exception {
		logger.info("HomeController 작동시작...");
		
		// 구매하기 눌렀을때,
		
		
		// 장바구니에서 구매하기 눌렀을때(얘는 항목이 여러개 일 수 있음 list)
		// List<CartItemVO> list = CartService.cartList(member);
		Map<String, List<ProductDTO>> list = productService.listMain();
		model.addAttribute("newList", list.get("new"));
		model.addAttribute("bestList", list.get("best"));
		return "home";
	}
	
}
