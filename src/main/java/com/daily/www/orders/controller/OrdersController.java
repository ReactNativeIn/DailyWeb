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

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.PageMaker;
import com.daily.www.member.vo.MemberVO;
import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.orders.service.OrdersService;
import com.daily.www.product.dto.ProductDTO;

@Controller
@RequestMapping(value = "/orders/*")
public class OrdersController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	OrdersService ordersService;
	
	
	// Orders 아이디 생성 - Ajax
	@ResponseBody
	@RequestMapping(value = "/createOrdersId", method = RequestMethod.POST)
	public int createOrdersId() {
		return ordersService.createOrdersId();
	}
	
	// 장바구니에서 주문/결제 화면 이동
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment(HttpSession session, List<CartDTO> cartDTOList, Model model) {
		
		System.out.println("장바구니에서 결제페이지 배열 가져오기 결과 => " + cartDTOList);
		// model.addAttribute("product", cartList);
		
		return "/member/payment";
	}
	
	// 물품 상세정보에서 바로구매 눌렀을때
	@RequestMapping(value = "/directPayment", method = RequestMethod.POST)
	public String directPayment(ProductDTO productDTO, Model model) {

		
		System.out.println(productDTO);
		model.addAttribute("product", productDTO);
		
		return "/member/payment";
	}

	// 내정보 - 주문내역 화면 이동
	@RequestMapping(value = "/ordersHistory")
	public String ordersHistory(Criteria cri, String id, HttpSession session, Model model) {
		MemberVO me = (MemberVO) session.getAttribute("member");
		
		if (me == null) { // 로그인 상태가 아니면 주문내역 화면 이동 못함
			return "member/loginForm";
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		cri.setName("orders/ordersHistory");
		cri.setId(me.getId());
		
		pageMaker.setTotalCount(ordersService.listTotalCount(me.getId()));
		
		System.out.println("con" +ordersService.listTotalOrders(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", ordersService.listTotalOrders(cri));
		
		return "/member/ordersHistory";
	}

	@ResponseBody
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(OrdersDTO ordersDTO) throws Exception {
		
		logger.info("OrdersControllerImpl 결제 시작...");
		System.out.println("123 : " + ordersDTO);
		if (ordersService.payment(ordersDTO) == 1) { // 결제성공
			return "Y";
		} else { // 결제실패
			return "N";
		}

	} // End - public String payment(Model model, HttpServletRequest request) throws

} // End - public class OrdersControllerImpl implements OrdersController
