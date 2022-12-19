package com.daily.www.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.cart.dao.CartDAO;
import com.daily.www.cart.dto.CartDTO;
import com.daily.www.cartitem.vo.CartItemVO;
import com.daily.www.color.vo.ColorVO;
import com.daily.www.file.dao.FileDAO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.product.dao.ProductDAO;
import com.daily.www.product.dto.ProductDTO;

//------------------------------------------------------------------------------------
//[ public class CartServiceImpl implements CartService ]
//------------------------------------------------------------------------------------
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	// -----------------------------------------------------------------
	// [ 장바구니 추가(addCart) ]
	// -----------------------------------------------------------------
	@Override
	public int addCart(ProductDTO productDTO) {
		int check = 0;
		CartItemVO cartItemVO = new CartItemVO();
		cartItemVO.setId(productDTO.getId());
		cartItemVO.setProduct_id(productDTO.getProduct_id());
		
		for(int i = 0; i < productDTO.getColorList().size(); i++) {
			System.out.println("체크");
			cartItemVO.setCi_color(productDTO.getColorList().get(i).getColor());
			cartItemVO.setCi_size(productDTO.getSizeList().get(i).getSize());
			cartItemVO.setCi_number(productDTO.getSizeList().get(i).getS_stock());
			check = cartDAO.addCart(cartItemVO);
		}
		
		return check;
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
			// 상품 product_id에 대한 이미지 파일 정보 가져오기
			int product_id = dto.getProduct_id();
			List<FileVO> fileList = fileDAO.getFileList(product_id);
			
			dto.setFileList(fileList);		
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