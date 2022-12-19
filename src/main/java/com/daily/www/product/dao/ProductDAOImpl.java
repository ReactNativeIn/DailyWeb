package com.daily.www.product.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.SearchCriteria;
import com.daily.www.product.dto.ProductDTO;
import com.daily.www.product.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "ProductDAO";
	
	// 상품 아이디 생성
	@Override
	public int createProductId() {
		return sqlSession.selectOne(NAMESPACE + ".createId");
	}
	
	// 상품 등록
	@Override
	public int insertProduct(ProductDTO productDTO) {
		return sqlSession.insert(NAMESPACE + ".insertProduct", productDTO);
	}
	
	// 상품 수정
	@Override
	public int updateProduct(ProductVO productVO) {
		return sqlSession.insert(NAMESPACE + ".updateProduct", productVO);
	}
	
	// 상품 삭제
	@Override
	public int deleteProduct(int product_id) {
		return sqlSession.insert(NAMESPACE + ".deleteProduct", product_id);
	}
	
	// (카테고리별) 상품 리스트 총 개수
	@Override
	public int listTotalCount(Criteria cri) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".listTotalCount", cri);
	}
	
	// (카테고리별) 상품 목록 보기 (페이징 처리)
	@Override
	public List<ProductDTO> listPaging(Criteria cri) throws Exception {

		return sqlSession.selectList(NAMESPACE + ".listPaging", cri);
	}
	
	// 메인 - New랑 BestItem
	@Override
	public List<ProductDTO> listMainNew(){
		return sqlSession.selectList(NAMESPACE + ".main");
	}
	
	// 상품 번호에 해당하는 상품 정보 가져오기
	@Override
	public ProductDTO productDetail(int product_id) {
		return sqlSession.selectOne(NAMESPACE + ".detail", product_id);
	}
	
	// 상품 정보 가져와서 상세화면에 보여주기
	@Override
	public ProductDTO productOrderDetail(ProductDTO productDTO) {
		
		return sqlSession.selectOne(NAMESPACE + ".productOrderDetail", productDTO);
	}
	
	// (카테고리별) 모든 상품 리스트 총 개수 (어드민리스트 페이징 처리)
	@Override
	public int adminListTotalCount(SearchCriteria cri) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".AdminListTotalCount", cri);
	}
	
	// (카테고리별) 상품 목록 보기 (어드민리스트 페이징 처리 + 검색)
	@Override
	public List<ProductDTO> adminListPaging(SearchCriteria cri) throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".AdminListPaging", cri);
	}
	
}
