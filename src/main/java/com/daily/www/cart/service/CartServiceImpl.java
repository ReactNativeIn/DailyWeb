package com.daily.www.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// [ 장바구니 리스트(cartAll) ]
	// -----------------------------------------------------------------
	@Override
	public List<CartDTO> getCartList(String id) {

		// 회원 id와 장바구니 연걸
		List<CartDTO> cartDTO = cartDAO.getCart(id);
		
		// 포인트 점수 적립과 이미지 정보를 
		// for문을 통해 추가되는 상품에 따라 계속해서 더해주기
		for(CartDTO dto : cartDTO) {
			
			// 상품 금액 총합
			dto.priceTotal();
			
			dto.setCart_id(cartDAO.insertCartId(id));
			// 상품 이미지 정보 얻기
			int product_id = dto.getProduct_id();
			List<FileVO> imageList = fileDAO.getFileList(product_id);
			
			dto.setImageList(imageList);
			
		}
		
		return cartDTO;
	}
	
	// 한길 실험용 코드
	public List<CartDTO> getCartList(CartDTO cart) {

		// 회원 id와 장바구니 연걸
		List<CartDTO> cartDTO = cartDAO.getCart(cart);
		
		// 포인트 점수 적립과 이미지 정보를 
		// for문을 통해 추가되는 상품에 따라 계속해서 더해주기
		for(CartDTO dto : cartDTO) {
			
			// 상품 금액 총합
			dto.priceTotal();
			
			dto.setCart_id(cartDAO.insertCartId(cart));
			// 상품 이미지 정보 얻기
			int product_id = dto.getProduct_id();
			List<FileVO> imageList = fileDAO.getFileList(product_id);
			
			dto.setImageList(imageList);
			
		}
		
		return cartDTO;
	}
	
	// -----------------------------------------------------------------
	// [ 장바구니 이미지 파일 리스트(listCartMember) ]
	// -----------------------------------------------------------------
//	@Override
//	public Map<String, List<CartDTO>> listCartMember(String id) {
//	
//		Map<String, List<CartDTO>> clist = new HashMap<String, List<CartDTO>>();
//		List<CartDTO> cDTO = cartDAO.listCartMember(id);
//
//		String [] cList = new String[cDTO.size()];
//		
//		for(CartDTO dto : cDTO) {
//			
//			// 상품 금액 총합
//			dto.priceTotal();
//		}
//		
//		for(int i = 0; i < cList.length; i++) {
//			cList[i] = Integer.toString(cDTO.get(i).getProduct_id());
//		}
//		
//		if(cList.length > 0) {
//			Map<String, String[]> product_id = new HashMap();
//			product_id.put("products_id", cList);
//			
//			List<FileVO> file = fileDAO.getProductsFileList(product_id);
//			
//			if(file != null) {
//				for(int i = 0; i < cDTO.size(); i++) {
//					for(int j = 0; j < file.size(); j++) {
//						if(cDTO.get(i).getProduct_id() == file.get(j).getProduct_id()) {
//							if(cDTO.get(i).getFileList() == null) {
//								cDTO.get(i).setFileList(new ArrayList<FileVO>());
//							}
//							cDTO.get(i).getFileList().add(file.get(j));
//						}
//					}
//				}
//			}
//			clist.put("cart", cDTO.subList(0, 5));
//		}
//
//		return clist;
//	}

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
