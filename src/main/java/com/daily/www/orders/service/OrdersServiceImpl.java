package com.daily.www.orders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.common.util.Criteria;
import com.daily.www.file.dao.FileDAO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.orders.dao.OrdersDAO;
import com.daily.www.orders.dto.OrdersDTO;
import com.daily.www.orders.vo.OrdersVO;
import com.daily.www.product.dto.ProductDTO;


@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	// 결제 등록
	@Override
	public int payment(OrdersVO ordersVO) {
		
		logger.info("OrdersService 실행중...");
		
		
		return ordersDAO.payment(ordersVO);
	}
	
	// 주문내역 총 개수 - 회원에 대한
	@Override
	public int listTotalCount(String id) {
		return ordersDAO.listTotalCount(id);
	}
	
	// 특정 회원에 해당하는 주문내역 조회 - paging
	@Override
	public List<OrdersDTO> listTotalOrders(Criteria cri){
		return ordersDAO.listTotalOrders(cri);
	}
	
	// 특정 회원에 해당하는 주문내역 조회
	@Override
	public List<OrdersDTO> listOrders(String id){
		List<OrdersDTO> oDTO = ordersDAO.listOrders(id);
		
		
		for (OrdersDTO ordersDTO : oDTO) { // forEach는 List를 꺼내서 적용시켜주는거라 따로 저장할 필요가없다.
			ordersDTO.setFileList(fileDAO.getFileList(ordersDTO.getProduct_id()));
		}
			
		return oDTO;
	}
	
	
}	// End - OrdersService
