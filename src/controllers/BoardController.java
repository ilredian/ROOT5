package controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.BoardLawDAO;
import DAO.BoardNoticeDAO;
import DTO.BoardFreeDTO;
import DTO.BoardNoticeDTO;
import DTO.BoardLawDTO;
import DTO.MemberDTO;
import common.HomePager;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
////////////자유게시판////////////////
////////////자유게시판////////////////
////////////자유게시판////////////////
////////////자유게시판////////////////
////////////자유게시판////////////////
	
	//1. 자유게시판 메인(목록리스트)
	@RequestMapping("freeMain.go")   //자유게시판 메인- 목록보기 List
	public String freeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			HttpSession session,
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
		
		System.out.println(session.getAttribute("MemberInfo"));
		String email = ((MemberDTO)session.getAttribute("MemberInfo")).getEmail();

		model.addAttribute("Email", email);
		model.addAttribute("pager", pager);
	    model.addAttribute("list", list); //자동 forward
	    model.addAttribute("boardCount", boardCount);
	    
		return "home.boardFree.freeMain";
	}
	//2. 자유게시판 상세보기
	@RequestMapping("freeView.go")	//자유게시판 상세보기 - 페이지
	public String freeView(String boardno , Model model) throws ClassNotFoundException, SQLException{
	    
		 BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		 BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);
		 model.addAttribute("boardFreeDTO", boardFreeDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		
		 return "home.boardFree.freeView";
	}
	//3. 자유게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value="freeWrite.go" , method=RequestMethod.GET)	
	public String freeWrite(BoardFreeDTO boardDTO){
		System.out.println("자유게시판 글쓰기 창");
		return "home.boardFree.freeWrite";
	}
	//4. 자유게시판 글쓰기(실제 글 등록 -DB)
	@RequestMapping(value="freeWrite.go" , method=RequestMethod.POST)   
	public String freeWrite(BoardFreeDTO DTO, HttpServletRequest request) throws ClassNotFoundException, SQLException{
	    System.out.println("실제 글 등록 처리"); 
	    System.out.println("n : " + DTO.getTitle()); 
	    System.out.println("n : " + DTO.getContent());
	  
	    BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	    boardFreeDAO.insert(DTO);
	    System.out.println("자유게시판 글쓰기 완료");
	  return "home.boardFree.freeMain";
	}

	//5. 게시물 수정 (화면 (select)
	@RequestMapping(value = "freeEdit.go",  method = RequestMethod.GET)   
	public String freeEdit(String boardno, Model model) throws ClassNotFoundException, SQLException{
		
	    BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	    BoardFreeDTO freedto = boardFreeDAO.getNotice(boardno);
	    System.out.println("자유게시판 원본글 가져오기");
	    model.addAttribute("freedto", freedto);
		  
	    return "home.boardFree.freeEdit";		  
	}
	
	//5-1. 게시물 수정 (실제 처리(update)
	@RequestMapping(value = "freeEdit.go",  method = RequestMethod.POST)   
	public String freeEdit(BoardFreeDTO boardFreeDTO, HttpServletRequest request) throws ClassNotFoundException, SQLException{
		
	    BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	    boardFreeDAO.update(boardFreeDTO);
	    System.out.println("자유게시판 수정완료");
	    
		return "home.boardFree.freeMain";
	}
	
	//6. 게시물 삭제
	@RequestMapping("freeDelete.go")   
	public String freeDelete(String boardno) throws ClassNotFoundException, SQLException{
		
	    BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	    boardFreeDAO.delete(boardno);
	    
	    System.out.println("자유게시판 삭제완료");
	    
//		return "redirect:freeMain";
	    return "home.boardFree.freeMain";
	    
	}
	
	
////////////공지게시판////////////
////////////공지게시판////////////
////////////공지게시판////////////
////////////공지게시판////////////
	
	//1. 공지게시판 메인(목록 리스트)
	@RequestMapping("noticeMain.go")
	public String noticeMain(	
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			Model model) throws Exception {
		
				System.out.println(page + " / " + field + " / " + query);
				
				BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

				int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
				String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
				int boardCount = boardNoticeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
				int start = (page - 1) * pageSize;
				HomePager pager = new HomePager(boardCount, page, pageSize, pagerSize, linkUrl);
				
				List<BoardNoticeDTO> list= boardNoticeDAO.getNotices(start, field, query, pageSize);
				System.out.println("zzz");
				
				model.addAttribute("list", list); //자동 forward
				System.out.println("xxx");
				
		return "home.boardNotice.noticeMain";
	}
	
	//2. 공지게시판 상세보기
	@RequestMapping("noticeView.go")
	public String noticeView(String seq , Model model) throws ClassNotFoundException, SQLException{
	
	  	 BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		 BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(seq);
		 model.addAttribute("boardNoticeDTO", boardNoticeDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		
		return "home.boardNotice.noticeView";
	}
	
	//3. 공지게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value="noticeWrite.go" , method=RequestMethod.GET)
	public String noticeWriteView(BoardNoticeDTO DTO){
		return "home.boardNotice.noticeWrite";
	}
	
	
	//4. 공지게시판 글쓰기(실제 글 등록 -DB)
	@RequestMapping(value="noticeWrite.go" , method=RequestMethod.POST)
	public String noticeWrite(BoardNoticeDTO DTO, HttpServletRequest request ) throws ClassNotFoundException, SQLException{
			System.out.println("실제 글 등록 처리"); 
			
		    System.out.println("n : " + DTO.getTitle());
		    System.out.println("n : " + DTO.getContent());
		    
		    BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		    boardNoticeDAO.insert(DTO);
		return "home.boardNotice.noticeMain";
	}
	
	
	//5. 게시물 수정
	@RequestMapping(value = "noticeEdit.go",  method = RequestMethod.GET)   
	public String noticeEdit(String boardno, Model model) throws ClassNotFoundException, SQLException{
		
	    BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	    BoardFreeDTO freedto = boardFreeDAO.getNotice(boardno);
	    System.out.println("자유게시판 원본글 가져오기");
	    model.addAttribute("freedto", freedto);
		  
	    return "home.boardNotice.noticeEdit";		  
	}
	
	//5-1. 게시물 수정 (실제 처리(update)
	@RequestMapping(value = "noticeEdit.go",  method = RequestMethod.POST)   
	public String noticeEdit(BoardNoticeDTO boardDTO, HttpServletRequest request) throws ClassNotFoundException, SQLException{
		
     	BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
	    boardNoticeDAO.update(boardDTO);
	    System.out.println("공지게시판 수정완료");
	    
	    return "home.boardNotice.noticeMain";
	}
	
	//6. 게시물 삭제
	@RequestMapping("noticeDelete.go")   
	public String noticeDelete(String boardno) throws ClassNotFoundException, SQLException{
		
	    BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
	    boardNoticeDAO.delete(boardno);
	    
	    System.out.println("공지게시판 삭제완료");
	    
	    return "home.boardNotice.noticeMain";

	}
	
	///////////////////////////////////
	///////////////////////////////////
	////////// 변호사 게시판///////////
	//////////변호사 게시판///////////
	//////////변호사 게시판///////////
	///////////////////////////////////
	///////////////////////////////////
	
	//1. 자유게시판 메인(목록리스트)
		@RequestMapping("lawMain.go")   //자유게시판 메인- 목록보기 List
		public String lawMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
				@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
				@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
				@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
				@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
				HttpSession session,
				Model model) throws Exception {

			// 로그 남기기
			System.out.println(page + " / " + field + " / " + query);
			
			BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			
			int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
			String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardLawDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
			int start = (page - 1) * pageSize;
			HomePager pager = new HomePager(boardCount, page, pageSize, pagerSize, linkUrl);
			
			List<BoardLawDTO> list= boardLawDAO.getNotices(start, field, query, pageSize);
			
			System.out.println(session.getAttribute("MemberInfo"));
			String email = ((MemberDTO)session.getAttribute("MemberInfo")).getEmail();

			model.addAttribute("Email", email);
			model.addAttribute("pager", pager);
		    model.addAttribute("list", list); //자동 forward
		    model.addAttribute("boardCount", boardCount);
		    
			return "home.boardLaw.lawMain";
		}
		//2. 자유게시판 상세보기
		@RequestMapping("lawView.go")	//자유게시판 상세보기 - 페이지
		public String lawView(String boardno , Model model) throws ClassNotFoundException, SQLException{
		    
			 BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			 BoardLawDTO boardLawDTO = boardLawDAO.getNotice(boardno);
			 model.addAttribute("boardLawDTO", boardLawDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
			
			 return "home.boardLaw.lawView";
		}
		//3. 자유게시판 글쓰기(화면만 뿌리기)
		@RequestMapping(value="lawWrite.go" , method=RequestMethod.GET)	
		public String lawWrite(BoardLawDTO boardDTO){
			System.out.println("자유게시판 글쓰기 창");
			return "home.boardLaw.lawWrite";
		}
		//4. 자유게시판 글쓰기(실제 글 등록 -DB)
		@RequestMapping(value="lawWrite.go" , method=RequestMethod.POST)   
		public String LawWrite(BoardLawDTO DTO, HttpServletRequest request) throws ClassNotFoundException, SQLException{
		    System.out.println("실제 글 등록 처리"); 
		    System.out.println("n : " + DTO.getTitle()); 
		    System.out.println("n : " + DTO.getContent());
		  
		    BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		    boardLawDAO.insert(DTO);
		    System.out.println("자유게시판 글쓰기 완료");
		  return "home.boardLaw.lawMain";
		}

		//5. 게시물 수정 (화면 (select)
		@RequestMapping(value = "lawEdit.go",  method = RequestMethod.GET)   
		public String lawEdit(String boardno, Model model) throws ClassNotFoundException, SQLException{
			
		    BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		    BoardLawDTO lawdto = boardLawDAO.getNotice(boardno);
		    System.out.println("자유게시판 원본글 가져오기");
		    model.addAttribute("lawdto", lawdto);
			  
		    return "home.boardLaw.lawEdit";		  
		}
		
		//5-1. 게시물 수정 (실제 처리(update)
		@RequestMapping(value = "lawEdit.go",  method = RequestMethod.POST)   
		public String lawEdit(BoardLawDTO boardLawDTO, HttpServletRequest request) throws ClassNotFoundException, SQLException{
			
		    BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		    boardLawDAO.update(boardLawDTO);
		    System.out.println("자유게시판 수정완료");
		    
			return "home.boardLaw.lawMain";
		}
		
		//6. 게시물 삭제
		@RequestMapping("lawDelete.go")   
		public String lawDelete(String boardno) throws ClassNotFoundException, SQLException{
			
		    BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		    boardLawDAO.delete(boardno);
		    
		    System.out.println("변호사게시판 삭제완료");
		    return "home.boardLaw.lawMain";
		    
		}
	
	
	
	
	
	
}//End BoardController
