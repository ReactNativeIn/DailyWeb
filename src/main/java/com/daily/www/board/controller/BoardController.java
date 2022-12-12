package com.daily.www.board.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.daily.www.board.service.BoardService;
import com.daily.www.board.vo.BoardVO;
import com.daily.www.common.util.Criteria;
import com.daily.www.common.util.PageMaker;
import com.daily.www.common.util.SearchCriteria;

//-----------------------------------------------------------------------------------------------------------
//게시글 관리 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller		// Bean의 대상으로 인식시키기 위해서 servlet-context.xml에 등록한다.
@RequestMapping(value = "/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private	BoardService boardService;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardRegisterForm", method = RequestMethod.GET)
	public String boardRegisterForm() throws Exception {
		
		logger.info("BoardController 게시글 등록 화면 불러오기.....");
		return "/board/boardRegisterForm";
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardRegister", method = RequestMethod.POST)
	// request 객체를 통해 데이터를 가져온다.
	// 메서드의 파라미터에 객체를 전달하면 자동으로 데이터가 set가 된 상태로 전달하게 된다.
	public String boardRegister(BoardVO boardVO) throws Exception {
		
		logger.info("BoardController 게시글 등록하기.....");
		logger.info("BoardVO 값 : " + boardVO);
		// BoardVO 값 : BoardVO(seq=0, subject=길동이의 일기, content=소풍가는 날, writer=희동이, reg_date=null, readCount=0)
		
		// 다음 페이지는 board.js의 스크립트에 기술되어 있다.
		if(boardService.boardRegister(boardVO) == 1) { // 게시글 등록 완료
			return "Y";
		} else {	// 게시글 등록 실패
			return "N";
		}
		
	} // End - public String boardRegister(BoardVO boardVO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public void boardList(Model model) throws Exception {
		
		logger.info("BoardController 게시글 목록 가져오기.....");
		List<BoardVO> boardList = boardService.boardList();

		logger.info("게시글 목록 ==> " + boardList);
		model.addAttribute("boardList", boardList);
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 정보 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		BoardVO boardVO = boardService.boardDetail(Integer.parseInt((String)request.getParameter("board_id")));
		model.addAttribute("boardDetail", boardVO);
		return "/board/boardDetail";
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public String boardDelete(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("BoardController boardDelete() board_id : " + request.getParameter("board_id"));
		
		if(boardService.boardDelete(Integer.parseInt((String)request.getParameter("board_id"))) == 1) {
			return "Y";
		} else {
			return "N";
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardUpdateForm", method = RequestMethod.POST)
	public String boardUpdateForm(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		logger.info("BoardController 게시글 수정 화면 불러오기 board_id => " + request.getParameter("board_id"));
		
		BoardVO boardVO = boardService.boardDetail(Integer.parseInt((String)request.getParameter("board_id")));
		model.addAttribute("boardDetail", boardVO);
		
		return "/board/boardUpdate";
		
	} // End - public String boardUpdateForm(HttpServletRequest request)
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 내용 수정하기 
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(Locale locale, Model model, BoardVO boardVO) throws Exception {
		System.out.println("BoardController boardUpdate() BoardVO : " + boardVO);
		if(boardService.boardUpdate(boardVO) == 1) {
			return "Y";
		} else {
			return "N";
		}
		
	} // End - public String boardUpdate(BoardVO boardVO)
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기 (Paging 처리)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList2", method = RequestMethod.GET)
	public ModelAndView boardList2(Criteria cri) throws Exception {
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("BoardController boardList2(Criteria cri) CRI ==> " + cri);
		System.out.println("---------------------------------------------------------------------");
		
		ModelAndView mav = new ModelAndView("/board/boardList2");
		
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(boardService.boardListTotalCount(cri));	// 게시글 전체 개수를 구한다.
	    
	    System.out.println("BoardController openBoardList pageMaker.getTotalCount(cri) ==> " + pageMaker.getTotalCount());
		
		// cri에 해당하는 게시글을 가져와서 View에게 넘겨준다.
	    List<BoardVO>  boardList = boardService.boardListPaging(cri);
	    mav.addObject("boardList", boardList);
	    mav.addObject("pageMaker", pageMaker);
	        
	    return mav;
		
	} // End - public ModelAndView boardList2(Criteria cri)

	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보여주기 (Paging 처리 + 검색 기능)
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/boardList3", method = RequestMethod.GET)
	public ModelAndView boardList3(SearchCriteria cri) throws Exception {
	        
		logger.info("---------------------------------------------------------------------");
		logger.info("***** BoardController (SearchCriteria cri) CRI ==> " + cri);
		logger.info("---------------------------------------------------------------------");

		ModelAndView mav = new ModelAndView("/board/boardList3");
	        
		mav.addObject("searchType",	cri.getSearchType());
		mav.addObject("keyword",	cri.getKeyword());
		
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(boardService.boardListTotalCount(cri));
	        
	    List<BoardVO>  boardList = boardService.boardListPaging(cri);
	    mav.addObject("boardList", boardList);
	    mav.addObject("pageMaker", pageMaker);
	        
	    return mav;
	}
	
	//-------------------------------------------------------------------------------------------------
	// 댓글을 달 수 있는 상세 정보 화면
	// http://localhost:8090/board/detailComment/1
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/detailComment/{board_id}")
	private String boardDetailComment(@PathVariable int board_id, Model model) throws Exception {
		//board_id에 해당하는 자료를 찾아와서 model에 담는다.
		model.addAttribute("detail", boardService.boardDetail(board_id));	//게시글의 정보를 가져와서 저장한다.
		return "/board/detailComment";
	}
	
	
	
	
	
} // End - public class BoardController






