package com.daily.www.file.service;

import java.util.List;
import java.util.Map;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.www.file.dao.FileDAO;
import com.daily.www.file.vo.FileVO;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDAO fileDAO;
	
	// 파일 등록
	@Override
	public void insertFile(FileVO fileVO) {
		fileDAO.insertFile(fileVO);
	};

	// 파일 삭제
	@Override
	public void deleteFile(String uuid) {
		fileDAO.deleteFile(uuid);
	};

	// 파일 수정
	@Override
	public void updateFile(FileVO fileVO) {
		fileDAO.updateFile(fileVO);
	};

	// 파일 검색 - 전체
	@Override
	public List<FileVO> fileList(){
		return fileDAO.fileList();
	};

	// 파일 검색 - 해당 상품에 대한
	@Override
	public List<FileVO> getFileList(int product_id){
		return fileDAO.getFileList(product_id);
	};
	
	// 파일 검색 - 상품들에 해당하는 파일 리스트
	@Override
	public List<FileVO> getProductsFileList(Map<String, String[]> product_id){
		return fileDAO.getProductsFileList(product_id);
	}
}
