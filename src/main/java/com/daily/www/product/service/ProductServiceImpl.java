package com.daily.www.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.color.dao.ColorDAO;
import com.daily.www.color.vo.ColorVO;
import com.daily.www.common.util.Criteria;
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
			} else {
				System.out.println("color 실패");
			}
			
			// File 등록
			for(FileVO fileVO: productDTO.getFileList()) {
				check = fileDAO.insertFile(fileVO);				
			}
		} else {
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
	
	// (카테고리별) 상품 리스트 총 개수
	@Override
	public int listTotalCount(Criteria cri) throws Exception {	
		//	logger.info("BoardServiceImpl 전체 게시글 수 구하기 (페이징 처리) ==> " + cri);
		return productDAO.listTotalCount(cri);
	}
	
	// (카테고리별) 상품 목록 보기 (페이징 처리)
	@Override
	public List<ProductDTO> listPaging(Criteria cri) throws Exception {
		//	logger.info("BoardServiceImpl 게시글 목록 보기 (페이징 처리) ==> " + cri);
		return productDAO.listPaging(cri);
	}
	
	// 메인
	@Override
	public List<ProductDTO> listMain(){
		List<ProductDTO> pDTO = productDAO.listMainNew();
	
		for (ProductDTO productDTO : pDTO) { // forEach는 List를 꺼내서 적용시켜주는거라 따로 저장할 필요가없다.
			productDTO.setFileList(fileDAO.getFileList(productDTO.getProduct_id()));
		}
			
		return pDTO;
	}
	
	// 상품 번호에 해당하는 상품 정보 가져오기
	@Override
	public ProductDTO productDetail(int product_id) {
		return productDAO.productDetail(product_id);
	}
}
