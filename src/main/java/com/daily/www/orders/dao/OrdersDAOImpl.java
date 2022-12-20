package com.daily.www.orders.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.common.util.Criteria;
import com.daily.www.orders.dto.OrdersDTO;

@Repository("OrdersDAO")
public class OrdersDAOImpl implements OrdersDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String NAMESPACE = "OrdersDAO";
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersDAOImpl.class);
	
	
	// Orders 아이디 생성
	@Override
	public int createOrdersId() {
		return sqlSession.selectOne(NAMESPACE + ".creatId");
	}
	
	// 결제 등록
	@Override
	public int payment(OrdersDTO ordersDTO) {
		
		logger.info("OrdersDAO 실행중...");
		
		return sqlSession.insert(NAMESPACE + ".payment", ordersDTO);
	}
	
	// 주문내역 총 개수 - 회원에 대한
	@Override
	public int listTotalCount(String id) {
		return sqlSession.selectOne(NAMESPACE + ".listTotalOrders", id);
	}
	
	// 특정 회원에 해당하는 주문내역 조회 - paging
	@Override
	public List<OrdersDTO> listTotalOrders(Criteria cri){
		return sqlSession.selectList(NAMESPACE + ".listPagingOrders", cri);
	}
	
	
	// 특정 회원에 해당하는 주문내역 조회
	@Override
	public List<OrdersDTO> listOrders(String id){
		return sqlSession.selectList(NAMESPACE + ".listOrders", id);
	}	

}
