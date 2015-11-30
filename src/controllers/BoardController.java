package controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.BoardNoticeDAO;
import DTO.BoardFreeDTO;
import DTO.BoardNoticeDTO;
import common.HomePager;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	////////////자유게시판////////////////
	
	//1. 자유게시판 메인(목록리스트)
	@RequestMapping("freeMain.go")   //자유게시판 메인- 목록보기 List
	public String freeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "cheatername") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			Model model) throws Exception {

		// 로그 남기기
		System.out.println(page + " / " + field + " / " + query);
		
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardFreeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
		int start = (page - 1) * pageSize;
		HomePager pager = new HomePager(boardCount, page, pageSize, pagerSize, linkUrl);
		
		List<BoardFreeDTO> list= boardFreeDAO.getNotices(start, field, query, pageSize);
		
		model.addAttribute("pager", pager);
	    model.addAttribute("list", list); //자동 forward
	    model.addAttribute("boardCount", boardCount);
	    
		return "home.boardFree.freeMain";
	}
	//2. 자유게시판 상세보기
	@RequestMapping("freeView.go")	//자유게시판 상세보기 - 페이지
	public String freeView(String seq , Model model) throws ClassNotFoundException, SQLException{
	/*
	     BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		 BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(seq);
		 model.addAttribute("boardFreeDTO", boardFreeDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		*/
		return "home.boardFree.freeView";
	}
	//3. 자유게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value="freeWrite.go" , method=RequestMethod.GET)	
	public String freeWrite(BoardFreeDTO boardDTO){
		return "home.boardFree.freeWrite";
	}
	//4. 자유게시판 글쓰기(실제 글 등록 -DB)
	@RequestMapping(value="freeWrite.go" , method=RequestMethod.POST)   
	public String freeWrite(BoardFreeDTO DTO, HttpServletRequest request) throws ClassNotFoundException, SQLException{
	    System.out.println("실제 글 등록 처리"); 
	    System.out.println("n : " + DTO.getTitle()); 
	    System.out.println("n : " + DTO.getContent());
	  
	    /*
	    BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	    boardFreeDAO.insert(DTO);
	    */
	    
	  return "home.boardFree.freeMain";
	}


	////////////공지게시판////////////
	
	//1. 공지게시판 메인(목록 리스트)
	@RequestMapping("noticeMain.go")
	public String noticeMain(String pg , String f , String q , Model model) throws Exception{
		//게시판 기본 설정(기본값 처리)/////////////
				int page = 1;
				String field = "TITLE";
				String query ="%%";
				//////////////////////////////////////
				if(pg != null && pg.equals("")){
					page = Integer.parseInt(pg);
				}
				if(f != null && f.equals("")){
					field = f;
				}
				if(q != null && q.equals("")){
					query = q;
				}
				System.out.println(page + " / " + field + " / " + query);
				
				BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
				System.out.println("yyy");
				
				List<BoardNoticeDTO> list= boardNoticeDAO.getNotices();
				System.out.println("zzz");
				
				model.addAttribute("list", list); //자동 forward
				System.out.println("xxx");
				
		return "home.boardNotice.noticeMain";
	}
	
	//2. 공지게시판 상세보기
	@RequestMapping("noticeView.go")
	public String noticeView(String seq , Model model){
	
		/*	
	  	 BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		 BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(seq);
		 model.addAttribute("boardNoticeDTO", boardNoticeDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
	*/	
		
		return "home.boardNotice.noticeView";
	}
	
	//3. 공지게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value="noticeWrite.go" , method=RequestMethod.GET)
	public String noticeWriteView(BoardNoticeDTO DTO){
		return "home.boardNotice.noticeWrite";
	}
	
	
	//4. 공지게시판 글쓰기(실제 글 등록 -DB)
	@RequestMapping(value="noticeWrite.go" , method=RequestMethod.POST)
	public String noticeWrite(BoardNoticeDTO DTO, HttpServletRequest request ){
			System.out.println("실제 글 등록 처리"); 
/*		    System.out.println("n : " + DTO.getTitle());
		    System.out.println("n : " + DTO.getContent());*/
	/*
		    BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		    boardNoticeDAO.insert(DTO);
	*/
		return "home.boardNotice.noticeMain";
	}
	////////////////////////////////////////////


}
