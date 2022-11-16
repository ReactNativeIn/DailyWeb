package com.daily.www.product;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("productVO")
@Data
public class ProductVO {
	private int product_id;
	private String p_name;
	private String p_explanation;
	private int p_price;
	private Date p_enroll;
	private int p_sell;
	private int category_id;
}
