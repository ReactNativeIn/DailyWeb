package com.daily.www.orders.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.PageMaker;
import com.daily.www.member.vo.MemberVO;
import com.daily.www.orders.service.OrdersService;
import com.daily.www.orders.vo.OrdersVO;

@Controller
@RequestMapping(value = "/orders/*")
public class OrdersController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	OrdersService ordersService;

	// 주문/결제 화면 이동
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment() {

		return "/member/payment";
	}

	// 내정보 - 주문내역 화면 이동
	@RequestMapping(value = "/ordersHistory")
	public String ordersHistory(Criteria cri, String id, HttpSession session, Model model) {

		if (session.getAttribute("member") == null) { // 로그인 상태가 아니면 주문내역 화면 이동 못함
			return "member/loginForm";
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		cri.setId(id);
		
		pageMaker.setTotalCount(ordersService.listTotalCount(id));
		
//		MemberVO member = (MemberVO) session.getAttribute("member");
//		model.addAttribute("list", ordersService.listOrders(member.getId()));
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", ordersService.listTotalOrders(cri));
		System.out.println(ordersService.listTotalOrders(cri));
		
		return "/member/ordersHistory";
	}

	@ResponseBody
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(OrdersVO ordersVO) throws Exception {

		logger.info("OrdersControllerImpl 결제 시작...");

		if (ordersService.payment(ordersVO) == 1) { // 결제성공
			return "Y";
		} else { // 결제실패
			return "N";
		}

	} // End - public String payment(Model model, HttpServletRequest request) throws
		// Exception

} // End - public class OrdersControllerImpl implements OrdersController
