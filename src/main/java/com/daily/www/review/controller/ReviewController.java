package com.daily.www.review.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daily.www.member.controller.MemberController;
import com.daily.www.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reView")
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	// reView 화면 이동
	@RequestMapping(value = "/reView")
	public String reViewInfoFrom() {
		return "member/reView";
	}
	
	
	// reViewWriter 화면 이동
	@RequestMapping(value = "/reViewWriter")
	public String reViewWriterFrom() {
		return "member/reViewWriter";
		
		
		
		
		
	}
	

}
