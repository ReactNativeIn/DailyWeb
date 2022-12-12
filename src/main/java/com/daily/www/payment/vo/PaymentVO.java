package com.daily.www.payment.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("paymentVO")
@Data
public class PaymentVO {
	private int payment_id;
	private int orders_id;
	private int ordersItem_id;
}
