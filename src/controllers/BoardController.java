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

import DAO.BoardFreeDAO;
import DTO.BoardFreeDTO;
import DTO.BoardNoticeDTO;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	////////////자유게시판////////////////
	
	//1. 자유게시판 메인(목록리스트)
	@RequestMapping("freeMain.go")   //자유게시판 메인- 목록보기 List
	public String freeMain(String pg , String f , String q , Model model) throws Exception{
		//게시판 기본 설정(기본값 처리)/////////////
		//검색 처리를 위한 코드
		int page = 1;
		String field = "TITLE"; 	// 제목명 
		String query ="%%";			// 
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
		
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		System.out.println("asdf");
		
		List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
		System.out.println("fghj");
		
	    model.addAttribute("list", list); //자동 forward
	    System.out.println("tuio");
	    
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
	public String noticeMain(String pg , String f , String q , Model model){
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
				
				/*	
				BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
				List<BoardNoticeDAO> list= boardNoticeDAO.getNotices(page, field, query);
				model.addAttribute("list", list); //자동 forward
				*/
				
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
