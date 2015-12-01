package controllers;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

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

	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;

	@Autowired
	private SqlSession sqlSession;

	//////////// 자유게시판////////////////
	//////////// 자유게시판////////////////
	//////////// 자유게시판////////////////
	//////////// 자유게시판////////////////
	//////////// 자유게시판////////////////

	// 1. 자유게시판 메인(목록리스트)
	@RequestMapping("freeMain.go") // 자유게시판 메인- 목록보기 List
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
		int boardCount = boardFreeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
																// 갯수
		int start = (page - 1) * pageSize;
		HomePager pager = new HomePager(boardCount, page, pageSize, pagerSize, linkUrl);
		
		List<BoardFreeDTO> list= boardFreeDAO.getNotices(start, field, query, pageSize);
		
		System.out.println(session.getAttribute("memberInfo"));
		String email = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();

		model.addAttribute("Email", email);
		model.addAttribute("pager", pager);
		model.addAttribute("list", list); // 자동 forward
		model.addAttribute("boardCount", boardCount);

		return "home.boardFree.freeMain";
	}

	// 2. 자유게시판 상세보기
	@RequestMapping("freeView.go") // 자유게시판 상세보기 - 페이지
	public String freeView(@RequestParam("bno") int boardno, HttpSession session, Model model) throws Exception {
		// 로그남기기
		System.out.println("자유게시판 상세보기 페이지 이동");

		System.out.println("게시판 정보 가져오기");
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);

		if (((MemberDTO) session.getAttribute("memberInfo")).getMemberno() != boardFreeDTO.getMemberno()) {
			System.out.println("조회수 증가");
			boardFreeDAO.updateCountno(boardno);
			boardFreeDTO.setCountno(boardFreeDTO.getCountno() + 1);
		}

		model.addAttribute("boardFreeDTO", boardFreeDTO);

		return "home.boardFree.freeView";
	}

	// 3. 자유게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value = "freeWrite.go", method = RequestMethod.GET)
	public String freeWrite(BoardFreeDTO boardDTO) {
		System.out.println("자유게시판 글쓰기 창");
		return "home.boardFree.freeWrite";
	}

	// 4. 자유게시판 글쓰기(실제 글 등록 -DB)
	@RequestMapping(value = "freeWrite.go", method = RequestMethod.POST)
	public String freeWrite(BoardFreeDTO boardFreeDTO, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		System.out.println("실제 글 등록 처리");

		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		int result = boardFreeDAO.insert(boardFreeDTO);

		if (result != 0) {
			System.out.println("자유게시판 글쓰기 완료");
			out.print(
					"<script type='text/javascript'>alert('글이 성공적으로 등록되었습니다.'); location.replace('redirect:freeMain.go?cno=1');</script>");
		} else {
			System.out.println("자유게시판 글쓰기 등록 실패");
			out.print(
					"<script type='text/javascript'>alert('글을 등록하는데 실패하였습니다.'); location.replace('redirect:freeMain.go?cno=1');</script>");
		}
		return "redirect:freeMain.go?cno=1";
	}

	// 5. 게시물 수정 (화면 (select)
	@RequestMapping(value = "freeEdit.go", method = RequestMethod.GET)
	public String freeEdit(@RequestParam("bno") int boardno, HttpSession session, Model model) throws Exception {

		// 로그 남기기
		System.out.println("게시물 수정 페이지로 이동");

		// 페이지 이동 변수 선언
		String go = "";

		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);

		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);
		if (memberno == boardFreeDTO.getMemberno()) {
			System.out.println("자유게시판 원본글 가져오기");
			model.addAttribute("boardFreeDTO", boardFreeDTO);
			go = "home.boardFree.freeEdit";
		} else {
			go = "redirect:index.go";
		}
		return go;
	}

	// 5-1. 게시물 수정 (실제 처리(update)
	@RequestMapping(value = "freeEdit.go", method = RequestMethod.POST)
	public String freeEdit(
							@RequestParam("pg") int page,
							BoardFreeDTO boardFreeDTO,
							HttpSession session,
							HttpServletRequest request) throws Exception {

		//로그 남기기
		System.out.println("게시물 수정 작업 시작");
		
		//페이지 이동 변수 선언
		String go = "";
		
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		
		if(memberno == boardFreeDTO.getMemberno()){
			BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
			boardFreeDAO.update(boardFreeDTO);
			System.out.println("자유게시판 수정완료");
			go = "redirect:freeMain.go?pg"+page;
		}else{
			go = "redirect:index.go";
		}
		return go;
	}

	// 6. 게시물 삭제
	@RequestMapping("freeDelete.go")
	public String freeDelete(@RequestParam("bno") int boardno, HttpSession session) throws Exception {

		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);

		int memberno = boardFreeDAO.getNotice(boardno).getMemberno();
		if (((MemberDTO) session.getAttribute("memberInfo")).getMemberno() == memberno) {
			boardFreeDAO.updateActive(boardno);
			System.out.println("자유게시판 게시물 삭제(active 수정)완료");
		} else {
			System.out.println("자유게시판 게시물을 삭제(active 수정)할 수 없습니다.");
		}

		return "redirect:freeMain.go?pg=1";

	}

	//////////// 공지게시판////////////
	//////////// 공지게시판////////////
	//////////// 공지게시판////////////
	//////////// 공지게시판////////////

	// 1. 공지게시판 메인(목록 리스트)
	@RequestMapping("noticeMain.go")
	public String noticeMain(@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재
																											// 페이지
																											// 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색
																								// 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색
																							// 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한
																								// 페이지에
																								// 보여줄
																								// 게시글
																								// 갯수
			Model model) throws Exception {

		System.out.println(page + " / " + field + " / " + query);

		BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardNoticeDAO.getCount(field, query);// 검색 결과에 따른 게시글
																// 총 갯수
		int start = (page - 1) * pageSize;
		HomePager pager = new HomePager(boardCount, page, pageSize, pagerSize, linkUrl);

		List<BoardNoticeDTO> list = boardNoticeDAO.getNotices(start, field, query, pageSize);
		System.out.println("zzz");

		model.addAttribute("list", list); // 자동 forward
		System.out.println("xxx");

		return "home.boardNotice.noticeMain";
	}

	// 2. 공지게시판 상세보기
	@RequestMapping("noticeView.go")
	public String noticeView(String seq , Model model) throws ClassNotFoundException, SQLException{
	
	  	 BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		 BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(seq);
		 model.addAttribute("boardNoticeDTO", boardNoticeDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****

		return "home.boardNotice.noticeView";
	}

	// 3. 공지게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value = "noticeWrite.go", method = RequestMethod.GET)
	public String noticeWriteView(BoardNoticeDTO DTO) {
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

	/*
	 * // 5. 게시물 수정
	 * 
	 * @RequestMapping(value = "noticeEdit.go", method = RequestMethod.GET)
	 * public String noticeEdit(
	 * 
	 * @RequestParam("bno") int boardno, HttpSession session, Model model)
	 * throws Exception {
	 * 
	 * //로그 남기기 System.out.println("게시물 수정 페이지로 이동");
	 * 
	 * //페이지 이동 변수 선언 String go = "";
	 * 
	 * BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
	 * 
	 * int memberno =
	 * ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
	 * BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno); if(memberno
	 * == boardFreeDTO.getMemberno()){ System.out.println("자유게시판 원본글 가져오기");
	 * model.addAttribute("boardFreeDTO", boardFreeDTO); go =
	 * "home.boardNotice.noticeEdit"; }else{ go = "redirect:index.go"; } return
	 * go;
	 * 
	 * }
	 */
	// 5-1. 게시물 수정 (실제 처리(update)
	@RequestMapping(value = "noticeEdit.go", method = RequestMethod.POST)
	public String noticeEdit(BoardNoticeDTO boardDTO, HttpServletRequest request)
			throws ClassNotFoundException, SQLException {

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
	
	//1. 변호사게시판 메인(목록리스트)
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
			String linkUrl = "lawMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardLawDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
			int start = (page - 1) * pageSize;
			HomePager pager = new HomePager(boardCount, page, pageSize, pagerSize, linkUrl);
			
			List<BoardLawDTO> list= boardLawDAO.getNotices(start, field, query, pageSize);
			
			System.out.println(session.getAttribute("memberInfo"));
			String email = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();

			model.addAttribute("Email", email);
			model.addAttribute("pager", pager);
		    model.addAttribute("list", list); //자동 forward
		    model.addAttribute("boardCount", boardCount);
		    
			return "home.boardLaw.lawMain";
		}
		//2. 변호사게시판 상세보기
		@RequestMapping("lawView.go")	//자유게시판 상세보기 - 페이지
		public String lawView(String boardno , Model model) throws ClassNotFoundException, SQLException{
		    
			System.out.println("lawview");
			 BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			 BoardLawDTO boardLawDTO = boardLawDAO.getNotice(boardno);
			 model.addAttribute("boardLawDTO", boardLawDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
			
			 return "home.boardLaw.lawView";
		}
		//3.변호사게시판 글쓰기(화면만 뿌리기)
		@RequestMapping(value="lawWrite.go" , method=RequestMethod.GET)	
		public String lawWrite(BoardLawDTO boardDTO){
			System.out.println("자유게시판 글쓰기 창");
			return "home.boardLaw.lawWrite";
		}
		//4. 변호사게시판 글쓰기(실제 글 등록 -DB)
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
