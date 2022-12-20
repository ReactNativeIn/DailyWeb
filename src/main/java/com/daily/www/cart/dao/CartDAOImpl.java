package com.daily.www.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.cartitem.vo.CartItemVO;
import com.daily.www.member.vo.MemberVO;

//------------------------------------------------------------------------
// [ public class CartDAOImpl implements CartDAO ]
//
// DAO(Data Access Object)
//
// @Repository	:	객체의 상태를 관리하는 저장소, 도메인 레이어에 포함됨
//					객체(Entity)에 대한 CRUD 수행
//------------------------------------------------------------------------
@Repository
public class CartDAOImpl implements CartDAO {

	private static final Logger logger = LoggerFactory.getLogger(CartDAOImpl.class);

	@Autowired
	private SqlSession sqlSession;
	
	private final static String NAMESPACE = "CartDAO";

	// 카트 추가(회원가입)
	@Override
	public int insertCart(MemberVO member) {
		return sqlSession.insert(NAMESPACE + ".insertCart", member);
	}
	
	
	//------------------------------------------------------------------------
	// [ 장바구니에 추가(addCart) ]
	//------------------------------------------------------------------------
	@Override
	public int addCart(CartItemVO cartItemVO) {
		logger.info("CartDAOImpl 장바구니에 추가 처리 ==> " + cartItemVO);

		return sqlSession.insert(NAMESPACE + ".addCart", cartItemVO);
	}
	
	//------------------------------------------------------------------------
	// [ 장바구니 리스트 불러오기(getCartList) ]
	//------------------------------------------------------------------------
	@Override
	public List<CartDTO> getCart(String id) {
		
		logger.info("CartDAOImpl cartList() 장바구니 목록 가져오기.....");

		return sqlSession.selectList(NAMESPACE + ".getCartList", id);
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ id로 cart_id 구하기(insertCartId) ]
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertCartId(CartDTO cartDTO) {
		
		return sqlSession.selectOne(NAMESPACE + ".insertCartId", cartDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ product_id로 color_id 구하기(selectColorId) ]
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int selectColorId(CartDTO cartDTO) {
		
		return sqlSession.selectOne(NAMESPACE + ".selectColorId", cartDTO);
	}

	
	//-----------------------------------------------------------------------------------------------------------
	// [ color_id로 size_id 구하기(selectSizeId) ]
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int selectSizeId(CartDTO cartDTO) {

		return sqlSession.selectOne(NAMESPACE + ".selectSizeId", cartDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 카트 수량 수정(modifyCount) ]
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int modifyCount(CartDTO cartDTO) {

		logger.info("CartDAOImpl 장바구니 수량 수정 처리 ==> " + cartDTO);
		System.out.println("CartDAOImpl modifyCount() 시작");

		return sqlSession.update(NAMESPACE + ".modifyCount", cartDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 삭제(deleteCart) ]
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteCart(int cartItem_id) {

		System.out.println("CartDAOImpl cartDelete() 시작");
		return sqlSession.delete(NAMESPACE + ".deleteCart", cartItem_id);
	} 

	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 추가 체크(checkCart) ]
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public CartDTO checkCart(CartDTO cartDTO) {

		return null;
	}

	// 카트 아이템 정보 가져오기 구매할
	@Override
	public List<CartDTO> getSelectedCartItem(CartDTO cartDTO){
		return sqlSession.selectList(NAMESPACE + ".getSelectedCartItem", cartDTO.getCartItemList());
	}


}