package com.daily.www.color;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("colorVO")
@Data
public class ColorVO {
	private int product_id;
	private String color;
}
