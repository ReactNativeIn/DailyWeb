package com.daily.www.orders.service;

import com.daily.www.orders.vo.OrdersVO;

public interface OrdersService {

	// 결제 등록
	int payment(OrdersVO ordersVO);

}