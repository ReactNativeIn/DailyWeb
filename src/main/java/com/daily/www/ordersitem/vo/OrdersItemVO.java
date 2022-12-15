package com.daily.www.ordersitem.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("ordersItemVO")
@Data
public class OrdersItemVO {
	private int ordersItem_id;
	private int oi_number;
	private int orders_id;
	
	/* 뷰로부터 전달받을 값 */
	private int product_id;
	private int ci_number;
	
	/* DB로부터 꺼내올 값 */
	private String p_name;
	private int p_price;

	/* 만들어 낼 값 */
	private int totalPrice;
	
	public void priceTotal() {
		this.totalPrice = this.p_price*this.ci_number;
	}
}
