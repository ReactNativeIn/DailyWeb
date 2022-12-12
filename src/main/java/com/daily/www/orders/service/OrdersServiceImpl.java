package com.daily.www.orders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.file.dao.FileDAO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.orders.dao.OrdersDAO;
import com.daily.www.orders.dto.OrdersDTO;


@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	// 결제 등록
	@Override
	public int payment(OrdersDTO ordersDTO) {
		
		logger.info("OrdersService 실행중...");
		
		
		return ordersDAO.payment(ordersDTO);
	}
	
	// 특정 회원에 해당하는 주문내역 조회
	@Override
	public List<OrdersDTO> listOrders(String id){
		List<OrdersDTO> oDTO = ordersDAO.listOrders(id);
		
		// 부터
		String [] idList = new String[oDTO.size()];

		for(int i = 0; i < idList.length; i++) {
			idList[i] = Integer.toString(oDTO.get(i).getProduct_id());
		}
		
		if(idList.length > 0) {
			Map<String, String[]> product_id = new HashMap();
			product_id.put("products_id", idList);
			List<FileVO> file = fileDAO.getProductsFileList(product_id);
			
			if(file != null) {
				for(int i = 0; i < oDTO.size(); i++) {
					for(int j = 0; j < file.size(); j++) {
						if(oDTO.get(i).getProduct_id() == file.get(j).getProduct_id()) {
							if(oDTO.get(i).getFileList() == null) {
								oDTO.get(i).setFileList(new ArrayList<FileVO>());
							}
							oDTO.get(i).getFileList().add(file.get(j));
						}
					}
				}
			}
		}
		
	

		// 여까지 코드 겹칩 - listMain()메서드
		
		
		return oDTO;
	}
	
	
}	// End - OrdersService
