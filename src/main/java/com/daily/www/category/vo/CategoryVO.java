package com.daily.www.category.vo;

import org.springframework.stereotype.Component;

import com.daily.www.cart.vo.CartVO;

import lombok.Data;

@Component("categoryVO")
@Data
public class CategoryVO {
	private int c_id;
	private String c_name;
	private String c_detail;
}
