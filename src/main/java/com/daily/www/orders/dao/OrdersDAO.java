package com.daily.www.orders.dao;

import java.util.List;

import com.daily.www.common.util.Criteria;
import com.daily.www.orders.dto.OrdersDTO;

public interface OrdersDAO {

	// Orders 아이디 생성
	int createOrdersId();
	
	// 결제 등록
	int payment(OrdersDTO ordersDTO);

	// 특정 회원에 해당하는 주문내역 조회
	public List<OrdersDTO> listOrders(String id);
	
	// 주문내역 총 개수 - 회원에 대한
	int listTotalCount(String id);
	
	// 특정 회원에 해당하는 주문내역 조회 - paging
	List<OrdersDTO> listTotalOrders(Criteria cri);
}