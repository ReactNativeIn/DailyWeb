package com.daily.www.ordersitem.dao;

import com.daily.www.ordersitem.vo.OrdersItemVO;

public interface OrdersItemDAO {

	// 주문상품 테이블 등록
	public int insertOrdersItem(OrdersItemVO ordersItemVO);

}