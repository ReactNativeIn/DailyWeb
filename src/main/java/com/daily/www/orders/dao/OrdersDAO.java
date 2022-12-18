package com.daily.www.orders.dao;

import java.util.List;

import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.ordersitem.vo.OrdersItemVO;

public interface OrdersDAO {

	// 결제 등록
	int payment(OrdersDTO ordersDTO);

	// 특정 회원에 해당하는 주문내역 조회
	public List<OrdersDTO> listOrders(String id);
	

}