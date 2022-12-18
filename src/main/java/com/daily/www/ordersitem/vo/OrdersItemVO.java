package com.daily.www.ordersitem.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("ordersItemVO")
@Data
public class OrdersItemVO {
	private int ordersItem_id;
	private int oi_number;
	private String oi_color;
	private String oi_size;
	private int orders_id;
	private int product_id;
}
