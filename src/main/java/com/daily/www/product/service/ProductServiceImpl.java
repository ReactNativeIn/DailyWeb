package com.daily.www.product.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// (카테고리별) 상품 리스트 총 개수
	@Override
	public int listTotalCount(Criteria cri) throws Exception {	
		//	logger.info("BoardServiceImpl 전체 게시글 수 구하기 (페이징 처리) ==> " + cri);
		return productDAO.listTotalCount(cri);
	}
	
	// (카테고리별) 상품 목록 보기 (페이징 처리)
	@Override
	public List<ProductVO> listPaging(Criteria cri) throws Exception {
		//	logger.info("BoardServiceImpl 게시글 목록 보기 (페이징 처리) ==> " + cri);
		return productDAO.listPaging(cri);
	}
	
	// 메인
	@Override
	public Map<String, List<ProductDTO>> listMain(){
		Map<String, List<ProductDTO>> list = new HashMap<String, List<ProductDTO>>();
		List<ProductDTO> pDTO = productDAO.listMainNew();

		String [] idList = new String[pDTO.size()];

		for(int i = 0; i < idList.length; i++) {
			idList[i] = Integer.toString(pDTO.get(i).getProduct_id());
		}
		
		if(idList.length > 0) {
			Map<String, String[]> product_id = new HashMap();
			product_id.put("products_id", idList);
			
			List<FileVO> file = fileDAO.getProductsFileList(product_id);
			
			if(file != null) {
				for(int i = 0; i < pDTO.size(); i++) {
					for(int j = 0; j < file.size(); j++) {
						if(pDTO.get(i).getProduct_id() == file.get(j).getProduct_id()) {
							if(pDTO.get(i).getFileList() == null) {
								pDTO.get(i).setFileList(new ArrayList<FileVO>());
							}
							pDTO.get(i).getFileList().add(file.get(j));
						}
					}
				}
			}
			list.put("new", pDTO.subList(0, 5));
			list.put("best", pDTO.subList(5, pDTO.size()));
		}
		
		return list;
	}
	
	// 남자 상품 목록
	@Override
	public Map<String, List<ProductDTO>> listMenProduct() {
		Map<String, List<ProductDTO>> list = new HashMap<String, List<ProductDTO>>();
		List<ProductDTO> pDTO = productDAO.listMenProducts();

		String [] idList = new String[pDTO.size()];

		for(int i = 0; i < idList.length; i++) {
			idList[i] = Integer.toString(pDTO.get(i).getProduct_id());
		}
		
		if(idList.length > 0) {
			Map<String, String[]> product_id = new HashMap();
			product_id.put("products_id", idList);
			
			List<FileVO> file = fileDAO.getProductsFileList(product_id);
			
			if(file != null) {
				for(int i = 0; i < pDTO.size(); i++) {
					for(int j = 0; j < file.size(); j++) {
						if(pDTO.get(i).getProduct_id() == file.get(j).getProduct_id()) {
							if(pDTO.get(i).getFileList() == null) {
								pDTO.get(i).setFileList(new ArrayList<FileVO>());
							}
							pDTO.get(i).getFileList().add(file.get(j));
						}
					}
				}
			}
			list.put("men", pDTO.subList(0, 5));
			
			list.put("outer", pDTO.subList(5, pDTO.size()));
			list.put("top", pDTO.subList(5, pDTO.size()));
			list.put("bottom", pDTO.subList(5, pDTO.size()));
		}
		
		
		return list;
	}
	
	@Override
	public Map<String, List<ProductDTO>> listWomenProduct() {
		Map<String, List<ProductDTO>> list = new HashMap<String, List<ProductDTO>>();
		List<ProductDTO> pDTO = productDAO.listMenProducts();

		String [] idList = new String[pDTO.size()];

		for(int i = 0; i < idList.length; i++) {
			idList[i] = Integer.toString(pDTO.get(i).getProduct_id());
		}
		
		if(idList.length > 0) {
			Map<String, String[]> product_id = new HashMap();
			product_id.put("products_id", idList);
			
			List<FileVO> file = fileDAO.getProductsFileList(product_id);
			
			if(file != null) {
				for(int i = 0; i < pDTO.size(); i++) {
					for(int j = 0; j < file.size(); j++) {
						if(pDTO.get(i).getProduct_id() == file.get(j).getProduct_id()) {
							if(pDTO.get(i).getFileList() == null) {
								pDTO.get(i).setFileList(new ArrayList<FileVO>());
							}
							pDTO.get(i).getFileList().add(file.get(j));
						}
					}
				}
			}
			list.put("women", pDTO.subList(0, 5));
			
			list.put("outer", pDTO.subList(5, pDTO.size()));
			list.put("top", pDTO.subList(5, pDTO.size()));
			list.put("bottom", pDTO.subList(5, pDTO.size()));
		}
		
		
		return list;
	}
	
	// 상품 번호에 해당하는 상품 정보 가져오기
	@Override
	public ProductDTO productDetail(int product_id) {
		return productDAO.productDetail(product_id);
	}


}
