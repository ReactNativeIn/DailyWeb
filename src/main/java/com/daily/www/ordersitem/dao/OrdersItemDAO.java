package com.daily.www.ordersitem.dao;

import com.daily.www.ordersitem.vo.OrdersItemVO;

public interface OrdersItemDAO {

	// Ordersitem 아이디 생성
	int createOrdersItemId();
	
	// 주문상품 테이블 등록
	int insertOrdersItem(OrdersItemVO ordersItemVO);

}