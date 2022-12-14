package com.daily.www.board.dao;

import java.util.List;


import com.daily.www.board.vo.BoardVO;
import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.SearchCriteria;

//-----------------------------------------------------------------------------------------------------------
//public interface BoardDAO
//-----------------------------------------------------------------------------------------------------------
public interface BoardDAO {

	//-----------------------------------------------------------------------------------------------------------
	// 제일 큰 게시글 번호 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public Integer board_id();
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	public int boardRegister(BoardVO boardVO);
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public List<BoardVO> boardList() throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 조회수를 증가시키기
	//-----------------------------------------------------------------------------------------------------------
	public void updateReadCount(int board_id);
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 정보 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public BoardVO boardDetail(int board_id);

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public int boardDelete(int board_id);

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 내용 수정하기 
	//-----------------------------------------------------------------------------------------------------------
	public int boardUpdate(BoardVO boardVO);

	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	public int boardListTotalCount(Criteria cri) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	public List<BoardVO> boardListPaging(Criteria cri) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리 + 조건별 검색)
	//-----------------------------------------------------------------------------------------------------------
	public int boardListTotalCount(SearchCriteria cri) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기 (Paging 처리 + 조건별 검색)
	//-----------------------------------------------------------------------------------------------------------
	public List<BoardVO> boardListPaging(SearchCriteria cri) throws Exception;
	
	
} // End - public interface BoardDAO