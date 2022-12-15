package com.daily.www.product.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.common.util.Criteria;
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
	public List<ProductVO> listPaging(Criteria cri) throws Exception {
				
		return sqlSession.selectList(NAMESPACE + ".listPaging", cri);
	}
	
	// 메인 - New랑 BestItem
	@Override
	public List<ProductDTO> listMainNew(){
		List<ProductDTO> pro = sqlSession.selectList(NAMESPACE + ".main_new");
		pro.addAll(sqlSession.selectList(NAMESPACE + ".main_BestItem"));
		return pro;
	}
	
	// 남자 상품 목록
	@Override
	public List<ProductDTO> listMenProducts(){
		List<ProductDTO> men = sqlSession.selectList(NAMESPACE + ".product_men");
		men.addAll(sqlSession.selectList(NAMESPACE + ".outer"));
		men.addAll(sqlSession.selectList(NAMESPACE + ".top"));
		men.addAll(sqlSession.selectList(NAMESPACE + ".bottom"));
		return men;
	}
	
	// 여자 상품 목록
	@Override
	public List<ProductDTO> listWomenProducts(){
		List<ProductDTO> women = sqlSession.selectList(NAMESPACE + ".product_women");
		women.addAll(sqlSession.selectList(NAMESPACE + ".outer"));
		women.addAll(sqlSession.selectList(NAMESPACE + ".top"));
		women.addAll(sqlSession.selectList(NAMESPACE + ".bottom"));
		return women;
	}
	
	// 상품 번호에 해당하는 상품 정보 가져오기
	@Override
	public ProductDTO productDetail(int product_id) {
		return sqlSession.selectOne(NAMESPACE + ".detail", product_id);
	}


	
	
}
