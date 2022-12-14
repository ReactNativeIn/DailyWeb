package com.daily.www.orders.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.daily.www.file.vo.FileVO;
import com.daily.www.ordersitem.vo.OrdersItemVO;
import com.daily.www.payment.vo.PaymentVO;

import lombok.Data;

@Component("ordersDTO")
@Data
public class OrdersDTO {
	private int orders_id;		// 주문 아이디(기본키)
	private int usePoint;		// 사용 포인트
	private String o_comment;	// 요청사항(선택)
	private Date o_enroll;		// 주문 날짜
	private String o_state;		// 배송상태
	private int deliverCost;	// 배송비
	private String addressee;	// 받는사람 이름
	private String address;		// 받는사람 주소
	private String o_phone;		// 받는사람 전화번호
	private String id;			// 멤버아이디
	
	
	private int product_id;
	private String p_name;
	private String oi_color;
	private String oi_size;
	private int p_price;
	private int oi_number;
	private List<FileVO> fileList;
	private List<PaymentVO> paymentVO;
	private List<OrdersItemVO> ordersItemVO;
}
