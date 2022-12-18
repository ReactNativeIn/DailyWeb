package com.daily.www.ordersitem.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.ordersitem.vo.OrdersItemVO;

@Repository
public class OrdersItemDAOImpl implements OrdersItemDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "OrdersItemDAO";
	
	// 주문상품 테이블 등록
	@Override
	public int insertOrdersItem(OrdersItemVO ordersItemVO) {
		sqlSession.insert(NAMESPACE + ".insertOrdersItem", ordersItemVO);
		
		int result = ordersItemVO.getOrdersItem_id();
		
		return result;
	}
	
}
