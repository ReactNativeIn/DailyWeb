package com.daily.www.orders.dto;

import java.util.List;

import com.daily.www.file.vo.FileVO;

import lombok.Data;

@Data
public class OrdersDTO {
	private String id;
	private int product_id;
	private String p_name;
	private String oi_color;
	private String oi_size;
	private String o_enroll;
	private String o_state;
	private int orders_id;
	private int p_price;
	private int oi_number;
	private List<FileVO> fileList;
}
