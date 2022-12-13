package com.daily.www.board.service;

import java.util.List;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.daily.www.board.dao.BoardDAO;
import com.daily.www.board.vo.BoardVO;
import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.SearchCriteria;

//-----------------------------------------------------------------------------------------------------------
// public class BoardServiceImpl implements BoardService
//-----------------------------------------------------------------------------------------------------------
@Service	// Bean으로 인식시키기 위해서 사용한다.
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardRegister(BoardVO boardVO) throws Exception {

		/*
		if(boardDAO.getMaxSeq() == null) {	// 게시글이 하나도 존재하지 않았을 때(맨 처음으로 게시글을 등록할 때)
			boardVO.setSeq(1);				// 게시글 번호는 1로 한다.
		} else {	// 게시글이 하나라도 존재한다면
			boardVO.setSeqa(boardDAO.getMaxSeq() + 1);	// 최대값에 1을 더한 값을 게시글 번호로 한다.
		}
		*/

		logger.info("BoardServiceImpl 게시글 등록 등록 처리 ==> " + boardVO);
		return boardDAO.boardRegister(boardVO);
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> boardList() throws Exception {
		logger.info("BoardServiceImpl 게시글 목록 가져오기.....");
		return boardDAO.boardList();
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 정보 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public BoardVO boardDetail(int board_id) throws Exception {
		logger.info("BoardServiceImpl 게시글 번호에 해당하는 게시글 정보 가져오기....." + board_id);
		// 게시글 번호에 해당하는 게시글의 자료를 가져오기 전에 조회수를 1증가 시킨다.
		boardDAO.updateReadCount(board_id);
		return boardDAO.boardDetail(board_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int board_id) {
		logger.info("BoardServiceImpl 게시글 번호에 해당하는 게시글 삭제하기....." + board_id);
		return boardDAO.boardDelete(board_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 내용 수정하기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardVO boardVO) {
		logger.info("BoardServiceImpl 게시글 내용 수정하기 ....." + boardVO);
		return boardDAO.boardUpdate(boardVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(Criteria cri) throws Exception {
		logger.info("BoardServiceImpl 전체 게시글 수 구하기 (Paging 처리) => " + cri);
		return boardDAO.boardListTotalCount(cri);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> boardListPaging(Criteria cri) throws Exception {
		logger.info("BoardServiceImpl 게시글 목록 보기 (Paging 처리) => " + cri);
		return boardDAO.boardListPaging(cri);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리 + 조건별 검색)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(SearchCriteria cri) throws Exception {
		logger.info("BoardServiceImpl 전체 게시글 수 구하기 (Paging 처리 + 조건별 검색) => " + cri);
		return boardDAO.boardListTotalCount(cri);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기 (Paging 처리 + 조건별 검색)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> boardListPaging(SearchCriteria cri) throws Exception {
		logger.info("BoardServiceImpl 게시글 목록 보기 (Paging 처리 + 조건별 검색) => " + cri);
		return boardDAO.boardListPaging(cri);
	}

	
	
} // End - public class BoardServiceImpl implements BoardService
