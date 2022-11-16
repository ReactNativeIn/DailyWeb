package com.daily.www.category;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("categoryVO")
@Data
public class CategoryVO {
	private int category_id;
	private String c_name;
	private String c_detail;
}
