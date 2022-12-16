package com.daily.www.color.service;

import java.util.List;

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

	// 상품에 대한 색상 아이디 가져오기
	@Override
	public List<ColorVO> getColorList(int color_id) {

		return colorDAO.getColorList(color_id);
	}
	
	
}
