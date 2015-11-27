package controllers;

import java.security.Principal;
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

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	////////////자유게시판////////////////
	
	//1. 자유게시판 메인(목록리스트)
	@RequestMapping("freeMain.go")   //자유게시판 메인- 목록보기 List
	public String freeMain(String pg , String f , String q , Model model) throws ClassNotFoundException, SQLException{
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
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
		
		model.addAttribute("list", list); //자동 forward
	*/	return "home.boardFree.freeMain";
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
	@RequestMapping("noticeMain.go")
	public String noticeMain(){
		return "home.boardNotice.noticeMain";
	}
	
	@RequestMapping()
	public String noticeView(){
		return "home.boardNotice.noticeView";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String noticeWriteView(BoardFreeDTO boardDTO){
		return "home.boardNotice.noticeWrite";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String noticeWrite(){

		
		
		
		return "home.boardNotice.noticeMain";
	}


	////////////////////////////////////////////


}
