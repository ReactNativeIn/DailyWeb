package com.daily.www.orders;

public interface OrdersDAO {

	// 결제 등록
	int payment(OrdersVO ordersVO);

}