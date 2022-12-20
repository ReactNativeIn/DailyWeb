package com.daily.www.cart.dao;

import java.util.List;

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.cartitem.vo.CartItemVO;
import com.daily.www.member.vo.MemberVO;
import com.daily.www.product.dto.ProductDTO;

//------------------------------------------------------------------------------------
//[ public interface CartDAO ]
//------------------------------------------------------------------------------------
public interface CartDAO {
	
	// 카트 추가(회원가입)
	int insertCart(MemberVO member);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 추가(addCart) ]
	//-----------------------------------------------------------------------------------------------------------
	public int addCart(CartItemVO cartItemVO);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 목록(cartAll) ]
	//-----------------------------------------------------------------------------------------------------------
	public List<CartDTO> getCart(String id);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 확인(checkCart) ]
	//-----------------------------------------------------------------------------------------------------------
	public CartDTO checkCart(CartDTO cartDTO);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 수량 수정(modifyCount) ]
	//-----------------------------------------------------------------------------------------------------------
	public int modifyCount(CartDTO cartDTO);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 삭제(deleteCart) ]
	//-----------------------------------------------------------------------------------------------------------
	public int deleteCart(int cartItem_id);

	//-----------------------------------------------------------------------------------------------------------
	// [ id로 cart_id 구하기(insertCartId) ]
	//-----------------------------------------------------------------------------------------------------------
	int insertCartId(CartDTO cartDTO);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ color_id로 size_id 구하기(selectSizeId) ]
	//-----------------------------------------------------------------------------------------------------------
	int selectSizeId(CartDTO cartDTO);
	
	//-----------------------------------------------------------------------------------------------------------
	// [ product_id로 color_id 구하기(selectColorId) ]
	//-----------------------------------------------------------------------------------------------------------
	int selectColorId(CartDTO cartDTO);
	
	// 카트 아이템 정보 가져오기 구매할
	List<CartDTO> getSelectedCartItem(CartDTO cartDTO);
	
}