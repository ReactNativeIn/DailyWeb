package com.daily.www.file.service;

import java.util.List;
import java.util.Map;

import com.daily.www.file.vo.FileVO;

public interface FileService {

	// 파일 등록
	void insertFile(FileVO fileVO);

	// 파일 삭제
	void deleteFile(String uuid);

	// 파일 수정
	void updateFile(FileVO fileVO);

	// 파일 검색 - 전체
	List<FileVO> fileList();

	// 파일 검색 - 해당 상품에 대한
	List<FileVO> getFileList(int product_id);

}