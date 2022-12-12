package com.daily.www.cart.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.cart.dao.CartDAO;
import com.daily.www.cart.dto.CartDTO;

//------------------------------------------------------------------------------------
//[ public class CartServiceImpl implements CartService ]
//------------------------------------------------------------------------------------
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	
	// -----------------------------------------------------------------
	// [ 장바구니 추가(addCart) ]
	// -----------------------------------------------------------------
	@Override
	public int addCart(CartDTO cartDTO) {

		// 등록하고자 하는 데이터가 이미 DB에 존재하는지 확인
		CartDTO checkCart = cartDAO.checkCart(cartDTO);
		
		// 장바구니에 같은 상품이 있을 때 2를 반환
		if(checkCart != null) {
			return 2;
		}
		// 장바구니에 같은 상품이 없고, 다른 error 사항이 없을 때 상품을 장바구니에 추가
        try {
        	return cartDAO.addCart(cartDTO);
        } catch(Exception e) {
        	return 0;
        }
       		
	} 

	// -----------------------------------------------------------------
	// [ 장바구니 리스트(cartAll) ]
	// -----------------------------------------------------------------
	@Override
	public List<CartDTO> getCartList(String cart_id) {

		// 현재는 cart_id로 연결돼 있지만, 추후 테이블 member의 id와 연결할 예정
		List<CartDTO> cartDTO = cartDAO.getCart(cart_id);
		
		// 포인트 점수 적립을 for문을 통해 추가되는 상품에 따라 계속해서 더해주기
		for(CartDTO dto : cartDTO) {
			dto.priceTotal();
		}
		
		return cartDTO;
	}

	// -----------------------------------------------------------------
	// [ 장바구니 수량 수정(modifyCount) ]
	// -----------------------------------------------------------------
	@Override
	public int modifyCount(CartDTO cartDTO) {

		return cartDAO.modifyCount(cartDTO);
	}

	// -----------------------------------------------------------------
	// [ 장바구니 삭제(deleteCart) ]
	// -----------------------------------------------------------------
	@Override
	public int deleteCart(int cartItem_id) {

		return cartDAO.deleteCart(cartItem_id);
	}

	
	
	
}
