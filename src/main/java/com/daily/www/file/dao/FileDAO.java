package com.daily.www.file.dao;

import java.util.List;

import com.daily.www.file.vo.FileVO;

public interface FileDAO {

	// 파일 등록
	int insertFile(FileVO fileVO);

	// 파일 삭제
	int deleteFile(String uuid);

	// 파일 수정
	int updateFile(FileVO fileVO);

	// 파일 검색 - 전체
	List<FileVO> fileList();

	// 파일 검색 - 해당 상품에 대한
	List<FileVO> getFileList(int product_id);

}