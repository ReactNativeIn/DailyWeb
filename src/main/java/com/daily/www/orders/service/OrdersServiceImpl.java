package com.daily.www.orders.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.common.util.Criteria;
import com.daily.www.file.dao.FileDAO;
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
	private OrdersDAO ordersDAO;
	
	@Autowired
	private PaymentDAO paymentDAO;

	@Autowired
	private FileDAO fileDAO;

	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

	
	// Orders 아이디 생성
	@Override
	public int createOrdersId() {
		return ordersDAO.createOrdersId();
	}
	
	// 결제 등록
	@Override
	public int payment(OrdersDTO ordersDTO) {

		int result = 0;
		int ordersItem_id = -1;
		logger.info("OrdersService 실행중...");
		List<OrdersItemVO> ordersItemVO = ordersDTO.getOrdersItemVO();
		
		// 주문(orders 테이블에 컬럼 입력)
		result = ordersDAO.payment(ordersDTO);
		
		for(OrdersItemVO orderVO : ordersItemVO) {
			ordersItem_id = ordersItemDAO.createOrdersItemId();
			System.out.println("확인 : " + ordersItem_id);
			
			orderVO.setOrders_id(ordersDTO.getOrders_id());
			orderVO.setProduct_id(ordersDTO.getProduct_id());
			orderVO.setOrdersItem_id(ordersItem_id);
			
			// 주문상품(ordersItem 테이블에 컬럼 입력)
			ordersItemDAO.insertOrdersItem(orderVO);
			System.out.println("ordersItemDAO 실행 결과" + orderVO);
			
			// 결제(payment) 테이블 컬럼 입력)
			PaymentVO payment = new PaymentVO();
			payment.setOrders_id(ordersDTO.getOrders_id());
			payment.setOrdersItem_id(ordersItem_id);
			
			result = paymentDAO.insertPayment(payment);
		}
		
		return result;
	}
	
	// 주문내역 총 개수 - 회원에 대한
	@Override
	public int listTotalCount(String id) {
		return ordersDAO.listTotalCount(id);
	}
	
	// 특정 회원에 해당하는 주문내역 조회 - paging
	@Override
	public List<OrdersDTO> listTotalOrders(Criteria cri){
		List<OrdersDTO> oDTO = ordersDAO.listTotalOrders(cri);
		
		for (OrdersDTO ordersDTO : oDTO) { // forEach는 List를 꺼내서 적용시켜주는거라 따로 저장할 필요가없다.
			ordersDTO.setFileList(fileDAO.getFileList(ordersDTO.getProduct_id()));
		}
		
		System.out.println("service " + oDTO);
		
		return oDTO;
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
