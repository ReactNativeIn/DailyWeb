package com.daily.www.orders.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.orders.vo.OrdersVO;

@Repository("OrdersDAO")
public class OrdersDAOImpl implements OrdersDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String NAMESPACE = "OrdersDAO";
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersDAOImpl.class);
	
	
	// 결제 등록
	@Override
	public int payment(OrdersVO ordersVO) {
		
		logger.info("OrdersDAO 실행중...");
		
		return sqlSession.insert(NAMESPACE + ".payment", ordersVO);
	}
	
	// 특정 회원에 해당하는 주문내역 조회
	@Override
	public List<OrdersDTO> listOrders(String id){
		return sqlSession.selectList(NAMESPACE + ".listOrders", id);
	}
	
	
	

}
