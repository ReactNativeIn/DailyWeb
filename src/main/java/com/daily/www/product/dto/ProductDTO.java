package com.daily.www.product.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.size.vo.SizeVO;

import lombok.Data;

@Component("productDTO")
@Data
public class ProductDTO {
	private int product_id;
	private String c_name;
	private String c_detail;
	private String p_name;
	private String p_explanation;
	private int p_price;
	private Date p_enroll;
	private int p_sell;
	private int category_id;
	
	
	private int p_count;	// 상품구매 체크 개수
	private List<ColorVO> colorList;
	private List<SizeVO> sizeList;
	private List<FileVO> fileList;
	//private List<ProductDTO> productDTO;

}
