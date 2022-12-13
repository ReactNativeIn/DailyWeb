package com.daily.www.file.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.file.vo.FileVO;

@Repository
public class FileDAOImpl implements FileDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "FileDAO";
	
	// 파일 등록
	@Override
	public int insertFile(FileVO fileVO) {
		return sqlSession.insert(NAMESPACE + ".insertFile", fileVO);
	}
	
	// 파일 삭제
	@Override
	public int deleteFile(String uuid) {
		return sqlSession.delete(NAMESPACE + ".deleteFile",uuid);
	}
	
	// 파일 수정
	@Override
	public int updateFile(FileVO fileVO) {
		return sqlSession.update(NAMESPACE + ".updateFile", fileVO);
	}
	
	// 파일 검색 - 전체
	@Override
	public List<FileVO> fileList(){
		return sqlSession.selectList(NAMESPACE + ".fileList");
	}
	
	// 파일 검색 - 해당 상품에 대한
	@Override
	public List<FileVO> getFileList(int product_id){
		return sqlSession.selectList(NAMESPACE + ".getFileList", product_id);
	}
	
}
