package com.daily.www.orders.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.cart.service.CartService;
import com.daily.www.member.vo.MemberVO;
import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.orders.service.OrdersService;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.service.ProductService;

@Controller
@RequestMapping(value = "/orders/*")
public class OrdersController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	// 장바구니에서 주문/결제 화면 이동
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment(HttpSession session, CartDTO cartDTO, Model model) {
		
		// 수정중!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		List<CartDTO> cartList = cartService.getCartList(cartDTO);
		
		System.out.println("장바구니에서 결제페이지 배열 가져오기 결과 => " + cartList);
		model.addAttribute("product", cartList);
		
		return "/member/payment";
	}
	
	// 물품 상세정보에서 바로구매 눌렀을때
	@RequestMapping(value = "/directPayment", method = RequestMethod.GET)
	public String directPayment(ProductDTO product, Model model) {
		List<ProductDTO> productDTO = new ArrayList<>();
		ProductDTO pDTO = productService.productOrderDetail(product);
		productDTO.add(pDTO);
		System.out.println(productDTO);
		model.addAttribute("product", productDTO);
		
		return "/member/payment";
	}

	// 내정보 - 주문내역 화면 이동
	@RequestMapping(value = "/ordersHistory")
	public String ordersHistory(String id, HttpSession session, Model model) {

		if (session.getAttribute("member") == null) { // 로그인 상태가 아니면 주문내역 화면 이동 못함
			return "member/loginForm";
		}

		MemberVO member = (MemberVO) session.getAttribute("member");
		model.addAttribute("list", ordersService.listOrders(member.getId()));

		return "/member/ordersHistory";
	}

	// 결제 버튼 눌렀을때
	@ResponseBody
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(OrdersDTO ordersDTO, HttpSession session) throws Exception {
		
		logger.info("OrdersControllerImpl 결제 시작..." + ordersDTO);
		
		
		
		if (ordersService.payment(ordersDTO) == 1) { // 결제성공
			return "Y";
		} else { // 결제실패
			return "N";
		}
		
		
	} // End - public String payment(Model model, HttpServletRequest request) throws
	
		// Exception

} // End - public class OrdersControllerImpl implements OrdersController
