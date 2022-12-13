package com.daily.www.orders.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	OrdersService ordersService;
	
	@Autowired
	private ProductService productService;
	
	// 주문/결제 화면 이동
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(HttpSession session, int product_id, Model model) {
		
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();
		productDTO.add(productService.productDetail(product_id));
		model.addAttribute("product" , productDTO);
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

	@ResponseBody
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(OrdersDTO ordersDTO) throws Exception {
		
		logger.info("OrdersControllerImpl 결제 시작...");
		
		if (ordersService.payment(ordersDTO) == 1) { // 결제성공
			return "Y";
		} else { // 결제실패
			return "N";
		}

	} // End - public String payment(Model model, HttpServletRequest request) throws
		// Exception

} // End - public class OrdersControllerImpl implements OrdersController
