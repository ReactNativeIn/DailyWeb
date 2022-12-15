package com.daily.www.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.cart.dto.CartDTO;

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

	//------------------------------------------------------------------------------------
	// [ 마이바티스 ]
	//
	// Connection	:	단순히 물리적(네트워크)인 연결
	// SqlSession	:	RDB에 인증을 거친 논리적인 상태
	//
	//------------------------------------------------------------------------------------
	@Autowired
	private SqlSession sqlSession;
	
	private final static String NAMESPACE = "CartDAO";

	
	//------------------------------------------------------------------------
	// [ 장바구니에 추가(addCart) ]
	//------------------------------------------------------------------------
	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		logger.info("CartDAOImpl 장바구니에 추가 처리 ==> " + cartDTO);

		return sqlSession.insert(NAMESPACE + ".addCart", cartDTO);
	}
	
	//------------------------------------------------------------------------
	// [ 장바구니 리스트 불러오기(cartAll) ]
	//------------------------------------------------------------------------
	@Override
	public List<CartDTO> getCart(String id) {
		
		logger.info("CartDAOImpl cartList() 장바구니 목록 가져오기.....");
		List<CartDTO> cartList = sqlSession.selectList(NAMESPACE + ".cartAll", id);
		
		logger.info("CartDAOImpl cartList() Data ==> " + cartList);
		return cartList;
		
	}
	
	@Override
	public int insertCartId(CartDTO cartDTO) {
		
		return sqlSession.selectOne(NAMESPACE + ".insertCartId", cartDTO);
	}
	
	//------------------------------------------------------------------------
	// [ 장바구니 리스트 불러오기(listCartMember) ]
	//------------------------------------------------------------------------
//	@Override
//	public List<CartDTO> listCartMember(String id) {
//	
//		List<CartDTO> car = sqlSession.selectList(NAMESPACE + ".listCartMember");
//		
//		return car;
//	}

	
	
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

}
