package com.daily.www.color.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("colorVO")
@Data
public class ColorVO {
	private int color_id;
	private int product_id;
	private String color;
}
