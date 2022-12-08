package com.daily.www.orders;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("OrdersDAO")
public class OrdersDAOImpl implements OrdersDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.daily.www.orders";
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersDAOImpl.class);
	
	
	// 결제 등록
	@Override
	public int payment(OrdersVO ordersVO) {
		
		logger.info("OrdersDAO 실행중...");
		
		return sqlSession.insert(namespace + ".payment", ordersVO);
	}
	

}
