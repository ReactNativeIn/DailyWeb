package com.daily.www.cart.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.cart.service.CartService;
import com.daily.www.member.vo.MemberVO;
import com.daily.www.product.dto.ProductDTO;

//------------------------------------------------------------------------------------
//[ public class CartController ]
//------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/cart/*")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);


	@Autowired
	private CartService cartService;
	

	@RequestMapping(value = "/cartForm", method = RequestMethod.GET)
	public String cartForm(String id, Model model) {
		
		logger.info("CartController 장바구니 화면 불러오기.....");
		
		model.addAttribute("cartInfo", cartService.getCartList(id));
		
		
		System.out.println(cartService.getCartList(id));

		return "/cart/cartForm";
		
	}
	
	@PostMapping("/add")
	@ResponseBody
	public int addCartPOST(ProductDTO productDTO, HttpSession session) {
		
		logger.info("CartController 장바구니에 상품 추가하기.....");	
		productDTO.setId(((MemberVO)session.getAttribute("member")).getId());
		
		System.out.println("확인 : " + productDTO);
		
		return cartService.addCart(productDTO);
		
	}
	
	@ResponseBody
	@PostMapping("/update")
	public int updateCartPOST(CartDTO cartDTO) {
	
		
		System.out.println("들림");
		
		// 변경된 수량 데이터 저장 후, 다시 장바구니 페이지로 돌아옴
		return cartService.modifyCount(cartDTO);
		
	}
	
	@PostMapping("/delete")
	public String deleteCartPOST(CartDTO cartDTO) {
		
		cartService.deleteCart(cartDTO.getCartItem_id());
		
		// 변경된 수량 데이터 저장 후, 다시 장바구니 페이지로 돌아옴
		return "redirect:/cart/cartForm?id=" + cartDTO.getId();
		
	}
	

	
}