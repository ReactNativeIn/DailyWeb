package com.daily.www.orders.dao;

import com.daily.www.orders.vo.OrdersVO;

public interface OrdersDAO {

	// 결제 등록
	int payment(OrdersVO ordersVO);

}