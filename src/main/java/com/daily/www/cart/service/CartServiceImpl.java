package com.daily.www.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.cart.dao.CartDAO;
import com.daily.www.cart.dto.CartDTO;
import com.daily.www.color.dao.ColorDAO;
import com.daily.www.file.dao.FileDAO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.product.dao.ProductDAO;
import com.daily.www.size.dao.SizeDAO;

//------------------------------------------------------------------------------------
//[ public class CartServiceImpl implements CartService ]
//------------------------------------------------------------------------------------
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ColorDAO colorDAO;
	
	@Autowired
	private SizeDAO sizeDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	// -----------------------------------------------------------------
	// [ 장바구니 추가(addCart) ]
	// -----------------------------------------------------------------
	@Override
	public int addCart(CartDTO cartDTO) {
		
		// cartDTO에 cart_id 넣어주기
		cartDTO.setCart_id(cartDAO.insertCartId(cartDTO));
		
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
	// [ 장바구니 리스트(getCartList) ]
	// -----------------------------------------------------------------
	@Override
	public List<CartDTO> getCartList(String id) {

		// 회원 id와 장바구니 연걸
		List<CartDTO> cartDTO = cartDAO.getCart(id);
		
		// 상품 정보를 for문을 통해 추가되는 상품에 따라 계속해서 더해주기
		for(CartDTO dto : cartDTO) {
			
			// 상품 color_id에 대한 color 정보 가져오기
			int color_id = dto.getColor_id();
			String color = colorDAO.getColor(color_id);
			
			// 상품 size_id에 대한 size 정보 가져오기
			int size_id = dto.getSize_id();
			String size = sizeDAO.getSize(size_id);
			
			// 상품 product_id에 대한 이미지 파일 정보 가져오기
			int product_id = dto.getProduct_id();
			List<FileVO> fileList = fileDAO.getFileList(product_id);
			
			dto.setFileList(fileList);
			dto.setColor(color);
			dto.setSize(size);
			
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
