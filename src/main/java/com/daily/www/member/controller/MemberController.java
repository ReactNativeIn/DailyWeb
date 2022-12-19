package com.daily.www.member.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.daily.www.board.vo.BoardVO;
import com.daily.www.member.dao.MemberDAO;
import com.daily.www.member.service.MemberService;
import com.daily.www.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	// 회원 가입 화면 불러오기
	@RequestMapping(value = "/registerAjaxForm", method = RequestMethod.GET)
	public String getRegisterAjaxForm() throws Exception {

		System.out.println("MemberController 회원 가입 화면 불러오기 (AJAX) ==> ");
		return "/member/registerAjax";
		
	}
	
	// 아이디 중복 검사 (AJAX)
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberVO memberVO) throws Exception {

		System.out.println("MemberController 아이디 중복 검사 (AJAX) id ==> " + memberVO.getId());
		
		int result = memberService.idCheck(memberVO);
		System.out.println("result : " + result);
		return result;
	}
	
	
	// 회원가입 처리
	@RequestMapping(value = "/addMember", method=RequestMethod.POST)
	public String addMember(MemberVO memberVO) throws Exception {

		logger.info("MemberControllerImpl 회원가입 처리() 시작.....");
		
		
		int result = 0;
		// 사용자가 입력한 정보를 서비스에게 넘겨주어 처리하게 한다.
		result = memberService.addMember(memberVO);
		
		return "redirect:/";
		
	}
	
	// 관리자 화면 이동
	@RequestMapping(value = "/adminHome")
	public String amdinHome() {
		
		return "admin/adminHome";
	}
	
	// 로그인 화면 이동
	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		System.out.println("로그인 화면 불러오기");
		return "member/loginForm";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, Model model,HttpSession session) {
		System.out.println("로그인");
		
		MemberVO member = memberService.getLoginById(vo);
		if(member == null) {
			model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "member/loginForm";
		} 
		
		session.setAttribute("member", member);
		
		if(member.getId().equals("admin")) {
			return "redirect:/member/adminHome";
		} 
		
		return "redirect:/";			
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("member");
		
		return "redirect:/";
	}
	
	// 아이디 찾기 화면 이동
	@RequestMapping(value = "/findIdForm")
	public String findIdForm() {
		return "/member/findId";
	}
	
	// 비밀번호 찾기 화면 이동
	@RequestMapping(value = "/findPasswordForm")
	public String findPasswordForm() {
		return "/member/findPassword";
	}
	
	// 주문/결제 화면 이동
	@RequestMapping(value = "/payment")
	public String paymentForm() {
		return "member/payment";
	}
	
	// MyPage 화면 이동
	@RequestMapping(value = "/mypage")
	public String mypageForm(HttpSession session, Model model) {
		
		model.addAttribute("member", memberService.getMemberById((MemberVO)session.getAttribute("member")));
		return "member/myPage";
	}
	
	
	

	//-----------------------------------------------------------------------------------------------------------
	// 회원정보변경 
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody //뷰 이동 없다. 결과값만 넣어서 보내주는것. 
	@RequestMapping(value ="/modify", method = RequestMethod.POST)
	public String modify(MemberVO memberVO, HttpSession httpSession ) throws Exception{
		
		log.info("memberVO" + memberVO);
			
		////////
		MemberVO member = (MemberVO)httpSession.getAttribute("member");
		
		if(memberVO.getName()!=null) {
			member.setName(memberVO.getName());
		}
			
		if(memberVO.getPassword()!=null) {
			member.setPassword(memberVO.getPassword());
		}
		
		if(memberVO.getNickname()!=null) {
			member.setNickname(memberVO.getNickname());
		}
		if(memberVO.getEmail()!=null) {
			member.setEmail(memberVO.getEmail());
		}
		if(memberVO.getBirthday()!=null) {
			member.setBirthday(memberVO.getBirthday());
		}
		
		if(memberService.modify(member)==1){
		
		return "Y";
		}
		else {
			return "N";
		}	
	}
}
