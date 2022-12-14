package com.daily.www.ordersitem.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.daily.www.ordersitem.vo.OrdersItemVO;

public class OrdersItemDAOImpl {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "OrdersItemDAO";
	
	// 주문상품 테이블 등록
	public int insertOrdersItem(OrdersItemVO ordersItemVO) {
		return sqlSession.insert(NAMESPACE + ".insertOrdersItem", ordersItemVO);
	}
	
}
