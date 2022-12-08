package com.daily.www.color.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.color.dao.ColorDAO;
import com.daily.www.color.vo.ColorVO;

@Service
public class ColorServiceImpl implements ColorService {
	
	@Autowired
	private ColorDAO colorDAO;
	
	// color 아이디 생성
	@Override
	public int createColorId() {
		return colorDAO.createColorId();
	}
	
	// 색상 등록
	@Override
	public void insertColor(ColorVO colorVO) {
		colorDAO.insertColor(colorVO);
	}
	
	
}
