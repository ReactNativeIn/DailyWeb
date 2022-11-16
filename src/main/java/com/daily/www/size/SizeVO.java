package com.daily.www.size;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("sizeVO")
@Data
public class SizeVO {
	private int product_id;
	private String color;
	private String size;
	private int s_stock;
}
