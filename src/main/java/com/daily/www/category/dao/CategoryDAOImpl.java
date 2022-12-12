package com.daily.www.category.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.category.vo.CategoryVO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "CategoryDAO";
	
	// 카테고리(+세부) 검색 - 전체
	@Override
	public List<CategoryVO> categoryList(){
		return sqlSession.selectList(NAMESPACE + ".categoryList");
	}
	
	// 카테고리 검색 중복x
	@Override
	public List<CategoryVO> cNameList(){
		return sqlSession.selectList(NAMESPACE + ".c_namelList");
	}
	
	// 해당 카테고리의 세부 검색
	@Override
	public List<CategoryVO> detailList(String c_name){
		return sqlSession.selectList(NAMESPACE + ".detailList", c_name);
	}
	
}
