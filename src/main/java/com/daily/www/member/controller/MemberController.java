package com.daily.www.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daily.www.member.service.MemberService;
import com.daily.www.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
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
	
	// 내정보 - 주문내역 화면 이동
	@RequestMapping(value = "/ordersHistory")
	public String ordersHistory(HttpSession session, Model model) {
		
		if(session.getAttribute("member") == null) { // 로그인 상태가 아니면 주문내역 화면 이동 못함
			return "member/loginForm";
		}
		return "/member/ordersHistory";
	}
	
	// 주문/결제 화면 이동
	@RequestMapping(value = "/payment")
	public String paymentForm() {
		return "member/payment";
	}
}
