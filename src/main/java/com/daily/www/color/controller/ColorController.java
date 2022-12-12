package com.daily.www.color.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.www.color.service.ColorService;

@Controller
@RequestMapping("/color/*")
public class ColorController {
	
	@Autowired
	private ColorService colorService;
	
	// color 아이디 생성 - Ajax
	@ResponseBody
	@RequestMapping(value = "/createColorId", method = RequestMethod.POST)
	public int createColorId() {
		return colorService.createColorId();
	}
}
