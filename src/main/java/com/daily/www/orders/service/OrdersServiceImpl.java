package com.daily.www.orders.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.file.dao.FileDAO;
import com.daily.www.member.dao.MemberDAO;
import com.daily.www.orders.dao.OrdersDAO;
import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.ordersitem.dao.OrdersItemDAO;
import com.daily.www.ordersitem.vo.OrdersItemVO;
import com.daily.www.payment.dao.PaymentDAO;
import com.daily.www.payment.vo.PaymentVO;

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersItemDAO ordersItemDAO;

	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	PaymentDAO paymentDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	@Autowired
	private MemberDAO memberDAO;

	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

	// 결제 등록
	@Override
	public int payment(OrdersDTO ordersDTO) throws Exception {
		
		int result = 0;
		logger.info("OrdersService 실행중...");
		List<OrdersItemVO> ordersItemVO = ordersDTO.getOrdersItemVO();
		
		// 주문(orders 테이블에 컬럼 입력)
		int ordersId = ordersDAO.payment(ordersDTO);
				memberDAO.updatePoint(ordersDTO);
				
				
				for(OrdersItemVO orderVO : ordersItemVO) {
					orderVO.setOrders_id(ordersId);
					
					// 주문상품(ordersItem 테이블에 컬럼 입력)
					int ordersItemId = ordersItemDAO.insertOrdersItem(orderVO);
					System.out.println("ordersItemDAO 실행 결과" + orderVO);
					// 결제(payment) 테이블 컬럼 입력)
					PaymentVO payment = new PaymentVO();
					payment.setOrders_id(ordersId);
					payment.setOrdersItem_id(ordersItemId);
					
					result = paymentDAO.insertPayment(payment);
					
					// 로그인 유저의 포인트를 상품 금액만큼 삭제
				}
				
		
			
		return result;
	}

	// 특정 회원에 해당하는 주문내역 조회
	@Override
	public List<OrdersDTO> listOrders(String id) {
		List<OrdersDTO> oDTO = ordersDAO.listOrders(id);
		
		for (OrdersDTO ordersDTO : oDTO) { // forEach는 List를 꺼내서 적용시켜주는거라 따로 저장할 필요가없다.
			ordersDTO.setFileList(fileDAO.getFileList(ordersDTO.getProduct_id()));
		}
		
		return oDTO;
	}

} // End - OrdersService
