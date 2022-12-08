package com.daily.www.orders.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.orders.service.OrdersService;
import com.daily.www.orders.vo.OrdersVO;

@Controller
@RequestMapping(value="/orders/*")
public class OrdersController{
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
	
	@Inject
	OrdersService ordersService;
	
	// 주문/결제 화면 이동
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public void test() {
		System.out.println("test");
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/orderComplete", method=RequestMethod.POST)
	public String payment(OrdersVO ordersVO) throws Exception {
		
		logger.info("OrdersControllerImpl 결제 시작...");
		
		if(ordersService.payment(ordersVO) == 1) {	// 결제성공
			return "Y";
		} else {	// 결제실패
			return "N";
		}
		
		
	}	// End - public String payment(Model model, HttpServletRequest request) throws Exception

}	// End - public class OrdersControllerImpl implements OrdersController