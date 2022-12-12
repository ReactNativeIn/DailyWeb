package com.daily.www.cartitem.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("cartItemVO")
@Data
public class CartItemVO {
	private int cartItem_id;
	private int ci_number;
	private int cart_id;
	private int product_id;
}
