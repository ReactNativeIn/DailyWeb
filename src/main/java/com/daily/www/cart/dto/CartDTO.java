package com.daily.www.cart.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daily.www.color.vo.ColorVO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.size.vo.SizeVO;

import lombok.Data;

// ------------------------------------------------------------------------------------
// [ public class CartDTO ]
// ------------------------------------------------------------------------------------
@Data
@Component("cartDTO")
public class CartDTO {
	
	// ------------------------------------------------------------------------------------
	// [ 테이블 cart 칼럼 ALL ]
	// ------------------------------------------------------------------------------------
	private int 	cartItem_id;	// 장바구니 아이템 아이디(기본키)
	private String 	id;				// 회원 아이디(외래키)
	private int 	cart_id;		// 장바구니 아이디(외래키)
	private int 	ci_number;		// 상품 수량
	private int 	product_id;		// 상품 아이디(외래키)
	
	private List<FileVO> imageList;
	
	private List<ColorVO> colorlist;
	private List<SizeVO> sizelist;
	
	// ------------------------------------------------------------------------------------
	// [ 테이블 product 칼럼 중 활용 가능한 것들 ]
	// ------------------------------------------------------------------------------------
	private String 	p_name;		// 상품명
	private int 	p_price;		// 상품 금액
	
	// ------------------------------------------------------------------------------------
	// [ 포인트 점수 적립 변수 ]
	// ------------------------------------------------------------------------------------
    private int point;
    private int totalPoint;
    
	public int getPoint() {
		return point;
	}

	public int getTotalPoint() {
		return totalPoint;
	}
	
	// ------------------------------------------------------------------------------------
	// [ 포인트 점수 계산 ]
	// ------------------------------------------------------------------------------------
	public void priceTotal() {
		this.point = (int)(Math.floor(this.p_price*0.05));
		this.totalPoint =this.point * this.ci_number;
	}
}

