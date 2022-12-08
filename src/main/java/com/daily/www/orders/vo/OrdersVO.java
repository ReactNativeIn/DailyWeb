package com.daily.www.orders.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("ordersVO")
@Data
public class OrdersVO {
	private int orders_id;
	private int usePoint;
	private String o_comment;
	private Date o_enroll;
	private String o_state;
	private int deliverCost;
	private String addressee;
	private String address;
	private String phone;
	private String id;
}
