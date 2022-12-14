package com.daily.www.cart.service;

import java.util.List;

import com.daily.www.cart.dto.CartDTO;

//------------------------------------------------------------------------------------
//[ public interface CartService ]
//------------------------------------------------------------------------------------
public interface CartService {

	// -----------------------------------------------------------------
	// [ 장바구니 추가(addCart) ]
	// -----------------------------------------------------------------
	public int addCart(CartDTO cartDTO);
	
	
	// -----------------------------------------------------------------
	// [ 장바구니 정보 리스트(cartAll) ]
	// -----------------------------------------------------------------
	public List<CartDTO> getCartList(String cart_id);
	
	// -----------------------------------------------------------------
	// [ 장바구니 수량 수정(modifyCount) ]
	// -----------------------------------------------------------------
	public int modifyCount(CartDTO cartDTO);
	
	// -----------------------------------------------------------------
	// [ 장바구니 삭제(deleteCart) ]
	// -----------------------------------------------------------------
	public int deleteCart(int cartItem_id);
	
}
