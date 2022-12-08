package com.daily.www.orders;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Inject
	OrdersDAO ordersDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	// 결제 등록
	@Override
	public int payment(OrdersVO ordersVO) {
		
		logger.info("OrdersService 실행중...");
		
		
		return ordersDAO.payment(ordersVO);
	}
	
	
}	// End - OrdersService
