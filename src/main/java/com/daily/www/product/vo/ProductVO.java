package com.daily.www.product.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.size.vo.SizeVO;

import lombok.Data;

@Component("productVO")
@Data
public class ProductVO {
	private int product_id;
	private String c_name;
	private String c_detail;
	private String p_name;
	private String p_explanation;
	private int p_price;
	private Date p_enroll;
	private List<ColorVO> colorList;
	private List<SizeVO> sizeList;
	private List<FileVO> fileList;
}
