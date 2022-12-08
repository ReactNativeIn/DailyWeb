package com.daily.www.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.color.dao.ColorDAO;
import com.daily.www.color.vo.ColorVO;
import com.daily.www.file.dao.FileDAO;
import com.daily.www.file.vo.FileVO;
import com.daily.www.product.dao.ProductDAO;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.vo.ProductVO;
import com.daily.www.size.dao.SizeDAO;
import com.daily.www.size.vo.SizeVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ColorDAO colorDAO;
	
	@Autowired
	private SizeDAO sizeDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	// 상품 아이디 생성
	@Override
	public int createProductId() {
		return productDAO.createProductId();
	}
	
	// 상품 등록
	@Override
	public int insertProudct(ProductDTO productDTO) {
		int check = 0;
		
		System.out.println( "확인 : " +productDTO.getC_detail());
		
		// 상품 등록
		if(productDAO.insertProduct(productDTO) == 1) {
			
			// color 등록
			for (ColorVO colorVO : productDTO.getColorList()) {
				check = colorDAO.insertColor(colorVO);
			}
			
			// size 등록
			if(check == 1) {
				for (SizeVO sizeVO : productDTO.getSizeList()) {
					check = sizeDAO.inserSize(sizeVO);					
				}
			}else {
				System.out.println("color 실패");
			}
			
			// File 등록
			for(FileVO fileVO: productDTO.getFileList()) {
				check = fileDAO.insertFile(fileVO);				
			}
		}else {
			System.out.println("상품 실패");
		}
		
		return check;
	}
	
	// 상품 수정
	@Override
	public void updateProduct(ProductVO productVO) {
		productDAO.updateProduct(productVO);
	}
	
	// 상품 삭제
	@Override
	public void deleteProduct(int product_id) {
		productDAO.deleteProduct(product_id);
	}
	
}
