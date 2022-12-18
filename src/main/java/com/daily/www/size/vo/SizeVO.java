package com.daily.www.size.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("sizeVO")
@Data
public class SizeVO {
	private int size_id;
	private String size;
	private int s_stock;
	private int color_id;
	
}
