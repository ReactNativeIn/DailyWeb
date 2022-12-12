package com.daily.www.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daily.www.board.vo.BoardVO;
import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.SearchCriteria;

import lombok.extern.java.Log;

//-----------------------------------------------------------------------------------------------------------
//public class BoardDAOImpl implements BoardDAO
//-----------------------------------------------------------------------------------------------------------
@Log
@Repository
public class BoardDAOImpl implements BoardDAO {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	private	static String namespace = "BoardDAO";
	
	//-----------------------------------------------------------------------------------------------------------
	// 제일 큰 게시글 번호 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public Integer board_id() {
		return sqlSession.selectOne(namespace + ".board_id");
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardRegister(BoardVO boardVO) {
		logger.info("BoardDAOImpl 게시글 등록 처리 ==> "  + boardVO);
		return sqlSession.insert(namespace + ".insertBoard", boardVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> boardList() throws Exception {
		logger.info("BoardDAOImpl boardList() 게시글 목록 가져오기.....");
		List<BoardVO> boardList = sqlSession.selectList(namespace + ".listAll");
		logger.info("BoardDAOImpl boardList() Data ==> " + boardList);
		return boardList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 조회수를 증가시키기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void updateReadCount(int board_id) {
		logger.info("BoardDAOImpl boardList() 게시글 번호에 해당하는 게시글의 조회수를 증가시키기.....");
		sqlSession.update(namespace + ".updateReadCount", board_id);
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 정보 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public BoardVO boardDetail(int board_id) {
		logger.info("BoardDAOImpl boardList() 게시글 번호에 해당하는 게시글 정보 가져오기.....");
		return sqlSession.selectOne(namespace + ".detatil", board_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int board_id) {
		logger.info("BoardDAOImpl boardList() 게시글 번호에 해당하는 게시글 삭제하기.....");
		return sqlSession.delete(namespace + ".delete", board_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 내용 수정하기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardVO boardVO) {
		logger.info("BoardDAOImpl boardList() 게시글 내용 수정하기 .....");
		return sqlSession.update(namespace + ".update", boardVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(Criteria cri) throws Exception {
		logger.info("BoardDAOImpl boardListTotalCount(Criteria cri) 전체 게시글 수 구하기 (Paging 처리) ==> " + cri);
		return sqlSession.selectOne(namespace + ".boardListTotalCount", cri);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> boardListPaging(Criteria cri) throws Exception {
		logger.info("BoardDAOImpl boardListPaging(Criteria cri) 게시글 목록 보기 (Paging 처리) ==> " + cri);
		return sqlSession.selectList(namespace + ".boardListPaging", cri);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리 + 조건별 검색)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(SearchCriteria cri) throws Exception {
		logger.info("BoardDAOImpl boardListTotalCount(Criteria cri) 전체 게시글 수 구하기 (Paging 처리 + 조건별 검색) ==> " + cri);
		return sqlSession.selectOne(namespace + ".boardListTotalCountSearchType", cri);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보기 (Paging 처리 + 조건별 검색)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> boardListPaging(SearchCriteria cri) throws Exception {
		logger.info("BoardDAOImpl boardListPaging(Criteria cri) 게시글 목록 보기 (Paging 처리 + 조건별 검색) ==> " + cri);
		return sqlSession.selectList(namespace + ".boardListPagingSearchType", cri);
	}
	
	
	
} // End - public class BoardDAOImpl implements BoardDAO





