package com.daily.www.orders.service;

import java.util.List;

import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.orders.vo.OrdersVO;

public interface OrdersService {

	// 결제 등록
	int payment(OrdersVO ordersVO);
	
	// 특정 회원에 해당하는 주문내역 조회
	public List<OrdersDTO> listOrders(String id);

}