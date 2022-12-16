package com.daily.www.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.cart.dto.CartDTO;
import com.daily.www.cart.service.CartService;
import com.daily.www.member.vo.MemberVO;

//------------------------------------------------------------------------------------
//[ public class CartController ]
//------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/cart/*")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	// ------------------------------------------------------------------------------------
	// [ CartService.java 의존성 주입 ]
	//
	// @Resource 	:	java 지원 어노테이션, 특정 프레임워크에 비종석적임
	//					name => type => @Qualifier => fail
	//
	// @Autowired	:	Spring 지원 어노테이션
	//					type => name => @Qualifier => fail
	//					주입 객체 타입 일치 여부 탐색 및 객체 자동 주입
	//
	// @Inject		:	java 지원 어노테이션, 특정 프레임워크에 비종석적임
	//					Autowired와 찾는 순서가 다름
	//					type => @Qualifier => name => fail	
	// ------------------------------------------------------------------------------------
	@Autowired
	private CartService cartService;
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 화면 불러오기(cartForm) ]
	//
	// @RequestMapping	:	특정 uri로 요청을 보냈을 때, Controller에서 어떠한 방식으로 처리할지 정의
	//						이때 들어온 요청을 특정 메서드와 매핑하기 위해 사용
	//						value 	: 	요청 받을 url 설정
	//						method	: 	GET, POST, PUT, DELETE 등
	//									GET		:	SELECT 기능, 고정 주소 및 링크에 사용, 캐시 有
	//									POST	:	UPDATE, DELETE, INSERT 기능, URL 길이 한계 해결, 캐시 無
	//						배열 지정을 통해 다중 요청 가능
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/cartForm/{id}", method = RequestMethod.GET)
	public String cartForm(@PathVariable("id") String id, Model model) {
		
		logger.info("CartController 장바구니 화면 불러오기.....");
		
		//-----------------------------------------------------------------------------------------------------------
		// [ 장바구니 목록 불러오기(cartAll) ]
		//
		// model	:	Controller에서 생성한 데이터를 담아 View로 전달할 때 사용하는 객체
		//				Servlet request.setAttribute()와 유사함
		//				addAttribute("키", "값") 메소드를 사용해 전달할 데이터 세팅
		//-----------------------------------------------------------------------------------------------------------
		
		// model 객체를 통해 데이터 저장
		// "cart_info"를 키로 지정
		// cartService의 getCartList에 id를 파라미터로 설정해 데이터 전달
		model.addAttribute("cartInfo", cartService.getCartList(id));
		
		System.out.println(cartService.getCartList(id));

		return "/cart/cartForm";
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니에 담기(addCart) ]
	// 
	// @PostMapping	:	테이블 cartItem에 테이블 product의 데이터를 INSERT
	//-----------------------------------------------------------------------------------------------------------
	@PostMapping("/cart/add")
	@ResponseBody
	public String addCartPOST(CartDTO cartDTO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("member");
		
		if(mvo == null) {
			return "5";
		}
		
		
		logger.info("CartController 장바구니에 상품 추가하기.....");
		// int로 result 타입 지정
		// cartService에 addCart로 데이터 전달
		int result = cartService.addCart(cartDTO);
		
		return result + "";
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 수량 수정(modifyCount) ]
	//
	// @PostMapping	:	테이블 cartItem의 ci_number를 UPDATE
	//-----------------------------------------------------------------------------------------------------------
	@PostMapping("/cart/update")
	public String updateCartPOST(CartDTO cartDTO) {
		
		cartService.modifyCount(cartDTO);
		
		// 변경된 수량 데이터 저장 후, 다시 장바구니 페이지로 돌아옴
		return "redirect:/cart/cartForm/" + cartDTO.getId();
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// [ 장바구니 삭제(deleteCart) ]
	//
	// @PostMapping	:	테이블 cartItem의 특정 cartItem_id가 지니고 있는 데이터 DELETE
	//-----------------------------------------------------------------------------------------------------------
	@PostMapping("/cart/delete")
	public String deleteCartPOST(CartDTO cartDTO) {
		
		cartService.deleteCart(cartDTO.getCartItem_id());
		
		// 변경된 수량 데이터 저장 후, 다시 장바구니 페이지로 돌아옴
		return "redirect:/cart/cartForm/" + cartDTO.getId();
		
	}
	

	
}
