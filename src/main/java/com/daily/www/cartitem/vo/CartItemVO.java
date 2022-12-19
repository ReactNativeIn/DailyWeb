package com.daily.www.cartitem.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("cartItemVO")
@Data
public class CartItemVO {
	private int cartItem_id;
	private String ci_color;
	private String ci_size;
	private int ci_number;
	private String id;
	private int product_id;
}
