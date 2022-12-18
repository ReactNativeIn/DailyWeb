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

	// color_id에 해당하는 상품 color 정보 가져오기
	@Override
	public String getColor(int color_id) {
		return colorDAO.getColor(color_id);
	}

	@Override
	public int getProduct_id(ColorVO colorVO) {
		return colorDAO.getProduct_id(colorVO);
	}
	
	
}
