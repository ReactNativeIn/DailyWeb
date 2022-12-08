package com.daily.www.orders.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("ordersVO")
@Data
public class OrdersVO {
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
}
