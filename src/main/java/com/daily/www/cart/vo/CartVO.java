package com.daily.www.cart.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("cartVO")
@Data
public class CartVO {
	private int cart_id;
	private String id;
}
