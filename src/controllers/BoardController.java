package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.BoardLawDAO;
import DAO.BoardNoticeDAO;
import DAO.ReplyDAO;
import DTO.BoardFreeDTO;
import DTO.BoardNoticeDTO;

import DTO.BoardLawDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardController {
	 private JdbcTemplate jdbcTemplate;

	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;

	@Autowired
	private SqlSession sqlSession;
	
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	@RequestMapping(value="reply.go", method = RequestMethod.POST)
	public String reply(@RequestParam("pg") int page,
						@RequestParam("bno") int boardno,
						@RequestParam("cno") int categoryno,
						HttpSession session,
						ReplyDTO replyDTO
						) throws Exception{
		// 로그남기기
		System.out.println("리플 등록 시작");
		
		// 페이지 이동을 위한 변수 선언
		String go = "";
		
		// 리플 DB 넘기기
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		replyDTO.setName(((MemberDTO)session.getAttribute("memberInfo")).getName());
		replyDTO.setBoardno(boardno);
		replyDTO.setMemberno(((MemberDTO)session.getAttribute("memberInfo")).getMemberno());
		replyDTO.setCategoryno(categoryno);
		int result = replyDAO.insertReply(replyDTO);
		
		if(result>0){
			System.out.println("리플 등록 완료");
		}else{
			System.out.println("리플 등록 실패");
		}
		
		// 페이지 이동 스위치 문
		switch(categoryno){
			case 1 :
				go="redirect:freeView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 2 :
				go="redirect:noticeView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 3 :
				go="redirect:photoView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 4 :
				go="redirect:lawView.go?pg="+page+"&bno="+boardno;
			break;
		}
		
		return go;
	}

	@RequestMapping("updateReply.go")
	public String updateReply(
			@RequestParam("pg") int page,
			@RequestParam("bno") int boardno,
			@RequestParam("cno") int categoryno,
			@RequestParam("rno") int replyno,
			ReplyDTO replyDTO
			) throws Exception{
		// 로그남기기
		System.out.println("리플 수정 시작");
				
		// 페이지 이동을 위한 변수 선언
		String go = "";
		
		// 리플 DB 넘기기
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		replyDTO.setReplyno(replyno);
		int result = replyDAO.updateReply(replyDTO);
		
		if(result>0){
			System.out.println("리플 수정 성공");
		}else{
			System.out.println("리플 수정 실패");
		}
		
		// 페이지 이동 스위치 문
		switch(categoryno){
			case 1 :
				go="redirect:freeView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 2 :
				go="redirect:noticeView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 3 :
				go="redirect:photoView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 4 :
				go="redirect:lawView.go?pg="+page+"&bno="+boardno;
			break;
		}
		
		System.out.println("리플 수정 완료");
				
		return go;
	}
	
	@RequestMapping("updateReplyActive.go")
	public String updateReplyActive(@RequestParam("pg") int page,
									@RequestParam("bno") int boardno,
									@RequestParam("cno") int categoryno,
									@RequestParam("rno") int replyno,
									HttpSession session,
									ReplyDTO replyDTO
									) throws Exception{
		// 로그남기기
		System.out.println("리플 삭제 (active 값 변경) 시작");
		
		// 페이지 이동을 위한 변수 선언
		String go = "";
				
		// 리플 DB 넘기기
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		replyDAO.updateReplyActive(replyno);
		
		// 페이지 이동 스위치 문
		switch(categoryno){
			case 1 :
				go="redirect:freeView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 2 :
				go="redirect:noticeView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 3 :
				go="redirect:photoView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 4 :
				go="redirect:lawView.go?pg="+page+"&bno="+boardno;
			break;
		}
		
		System.out.println("리플 삭제(active값 변경) 성공");
		
		return go;
	}
	
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
		BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		
		List<BoardFreeDTO> list= boardFreeDAO.getNotices(start, field, query, pageSize);
		
		String email = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();

		model.addAttribute("Email", email);
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);

		//메인 리스트에 댓글 숫자 출력
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		for(int i=0; i < list.size(); i++){
			list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno()));
		}
		model.addAttribute("list", list); // 자동 forward
				
		System.out.println("자유게시판 메인 출력 완료");
		
		return "home.boardFree.freeMain";
	}

	// 2. 자유게시판 상세보기
	@RequestMapping("freeView.go") // 자유게시판 상세보기 - 페이지
	public String freeView(
			@RequestParam("bno") int boardno, // 게시글 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "rpg", required = false, defaultValue = "1") int replypage, // 현재 페이지 번호
			HttpSession session, 
			Model model) throws Exception {
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
		System.out.println("리플 정보 가져오기");
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		int replycount = replyDAO.getBoardReplyCount("content", "%%", boardno);
		
		// 리플 페이지 처리
		int rstart = (replypage - 1) * 10;
		ReplyPager rpager = new ReplyPager(replycount, replypage, 10, 10, "freeView.go", boardno, page);
		
		List<ReplyDTO> replyDTO = replyDAO.getBoardReply("content", "%%", boardno, rstart);
		
		model.addAttribute("boardFreeDTO", boardFreeDTO);
		model.addAttribute("replycount", replycount);
		model.addAttribute("rpager", rpager);
		model.addAttribute("replyDTO", replyDTO);
		
		//상세보기 밑 리스트 뿌려주기
		// 로그 남기기
		System.out.println(page + " / " + field + " / " + query);

		boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);

		String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardFreeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		int start = (page - 1) * pageSize;
		BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
				
		List<BoardFreeDTO> list= boardFreeDAO.getNotices(start, field, query, pageSize);
				
		System.out.println(session.getAttribute("memberInfo"));
		String email = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();

		model.addAttribute("Email", email);
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		
		//메인 리스트에 댓글 숫자 출력
		for(int i=0; i < list.size(); i++){
			list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno()));
		}
		
		model.addAttribute("list", list); // 자동 forward
		
		return "home.boardFree.freeView";
	}

	// 3. 자유게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value = "freeWrite.go", method = RequestMethod.GET)
	public String freeWrite() {
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
		return "redirect:freeMain.go?pg=1";
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
			go = "redirect:freeMain.go?pg="+page;
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

	// 1. 공지 게시판 메인(목록리스트)
		@RequestMapping("noticeMain.go") // 공지 게시판 메인- 목록보기 List
		public String noticeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
				@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
				@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
				@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
				@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
				HttpSession session,
				Model model) throws Exception {

			// 로그 남기기
			System.out.println(page + " / " + field + " / " + query);

			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

			int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
			String linkUrl = "noticeMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardNoticeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
																	// 갯수
			int start = (page - 1) * pageSize;
			BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
			
			List<BoardNoticeDTO> list= boardNoticeDAO.getNotices(start, field, query, pageSize);
			
			String email = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();

			model.addAttribute("Email", email);
			model.addAttribute("pager", pager);
			model.addAttribute("boardCount", boardCount);

			//메인 리스트에 댓글 숫자 출력
			ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
			for(int i=0; i < list.size(); i++){
				list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno()));
			}
			model.addAttribute("list", list); // 자동 forward
					
			System.out.println("공지 게시판 메인 출력 완료");
			
			return "home.boardNotice.noticeMain";
		}

		// 2. 공지 게시판 상세보기
		@RequestMapping("noticeView.go") // 공지 게시판 상세보기 - 페이지
		public String noticeView(
				@RequestParam("bno") int boardno, // 게시글 번호
				@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
				@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
				@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
				@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
				@RequestParam(value = "rpg", required = false, defaultValue = "1") int replypage, // 현재 페이지 번호
				HttpSession session, 
				Model model) throws Exception {
			// 로그남기기
			System.out.println("공지 게시판 상세보기 페이지 이동");

			System.out.println("게시판 정보 가져오기");
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(boardno);

			//if (((MemberDTO) session.getAttribute("memberInfo")).getMemberno() != boardNoticeDTO.getMemberno()) {
				System.out.println("조회수 증가");
				boardNoticeDAO.updateCountno(boardno);
				boardNoticeDTO.setCountno(boardNoticeDTO.getCountno() + 1);
			//}
			System.out.println("리플 정보 가져오기");
			ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
			int replycount = replyDAO.getBoardReplyCount("content", "%%", boardno);
			
			// 리플 페이지 처리
			int rstart = (replypage - 1) * 10;
			ReplyPager rpager = new ReplyPager(replycount, replypage, 10, 10, "noticeView.go", boardno, page);
			
			List<ReplyDTO> replyDTO = replyDAO.getBoardReply("content", "%%", boardno, rstart);
			
			model.addAttribute("boardNoticeDTO", boardNoticeDTO);
			model.addAttribute("replycount", replycount);
			model.addAttribute("rpager", rpager);
			model.addAttribute("replyDTO", replyDTO);
			
			//상세보기 밑 리스트 뿌려주기
			// 로그 남기기
			System.out.println(page + " / " + field + " / " + query);

			boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

			String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardNoticeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
			int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
			int start = (page - 1) * pageSize;
			BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
					
			List<BoardNoticeDTO> list= boardNoticeDAO.getNotices(start, field, query, pageSize);
					
			String email = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();

			model.addAttribute("Email", email);
			model.addAttribute("pager", pager);
			model.addAttribute("boardCount", boardCount);
			
			//메인 리스트에 댓글 숫자 출력
			for(int i=0; i < list.size(); i++){
				list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno()));
			}
			
			model.addAttribute("list", list); // 자동 forward
			
			return "home.boardNotice.noticeView";
		}

		// 3. 공지 게시판 글쓰기(화면만 뿌리기)
		@RequestMapping(value = "noticeWrite.go", method = RequestMethod.GET)
		public String noticeWrite() {
			System.out.println("공지 게시판 글쓰기 창");
			return "home.boardNotice.noticeWrite";
		}

		// 4. 공지 게시판 글쓰기(실제 글 등록 -DB)
		@RequestMapping(value = "noticeWrite.go", method = RequestMethod.POST)
		public String freeWrite(BoardNoticeDTO boardNoticeDTO, HttpSession session, HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			System.out.println("실제 글 등록 처리");

			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();

			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			int result = boardNoticeDAO.insert(boardNoticeDTO);

			if (result != 0) {
				System.out.println("공지 게시판 글쓰기 완료");
			} else {
				System.out.println("공지 게시판 글쓰기 등록 실패");
			}
			return "redirect:noticeMain.go?pg=1";
		}

		// 5. 게시물 수정 (화면 (select)
		@RequestMapping(value = "noticeEdit.go", method = RequestMethod.GET)
		public String noticeEdit(@RequestParam("bno") int boardno, HttpSession session, Model model) throws Exception {

			// 로그 남기기
			System.out.println("게시물 수정 페이지로 이동");

			// 페이지 이동 변수 선언
			String go = "";

			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

//			int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
			BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(boardno);
			//if (memberno == boardNoticeDTO.getMemberno()) {
				System.out.println("공지게시판 원본글 가져오기");
				model.addAttribute("boardNoticeDTO", boardNoticeDTO);
				go = "home.boardNotice.noticeEdit";
/*			} else {
				go = "redirect:index.go";
			}*/
			return go;
		}

		// 5-1. 게시물 수정 (실제 처리(update)
		@RequestMapping(value = "noticeEdit.go", method = RequestMethod.POST)
		public String noticeEdit(
								@RequestParam("pg") int page,
								BoardNoticeDTO boardNoticeDTO,
								HttpSession session,
								HttpServletRequest request) throws Exception {

			//로그 남기기
			System.out.println("게시물 수정 작업 시작");
			
			//페이지 이동 변수 선언
			String go = "";
			
//			int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
			
			//if(memberno == boardFreeDTO.getMemberno()){
				BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
				boardNoticeDAO.update(boardNoticeDTO);
				System.out.println("공지 게시판 수정완료");
				go = "redirect:noticeMain.go?pg="+page;
/*			}else{
				go = "redirect:index.go";
			}*/
			return go;
		}

		// 6. 게시물 삭제
		@RequestMapping("noticeDelete.go")
		public String noticeDelete(@RequestParam("bno") int boardno, HttpSession session) throws Exception {

			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

//			int memberno = boardNoticeDAO.getNotice(boardno).getMemberno();
//			if (((MemberDTO) session.getAttribute("memberInfo")).getMemberno() == memberno) {
				boardNoticeDAO.updateActive(boardno);
				System.out.println("공지 게시판 게시물 삭제(active 수정)완료");
/*			} else {
				System.out.println("공지 게시판 게시물을 삭제(active 수정)할 수 없습니다.");
			}*/

			return "redirect:noticeMain.go?pg=1";

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
			BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
			
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
		public String lawView(@RequestParam("bno") int boardno , Model model) throws ClassNotFoundException, SQLException{
		    
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
		public String LawWrite(BoardLawDTO boardLawDTO,  HttpSession session, HttpServletResponse response,
				HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException{
		    System.out.println("실제 글 등록 처리"); 
		    
		    response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
		    BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		    
		    System.out.println("n : " + boardLawDTO.getTitle()); 
		    System.out.println("getMessage : " + boardLawDTO.getMessage());
		    System.out.println("getCareer : " + boardLawDTO.getCareer());
		    System.out.println("getCompany: " + boardLawDTO.getCompany());
		    System.out.println("getEdu : " + boardLawDTO.getEdu());
		    System.out.println("getFee : " + boardLawDTO.getFee());
		    
		    int result = boardLawDAO.insert(boardLawDTO);

		    

		    
			if (result != 0) {
				System.out.println("자유게시판 글쓰기 완료");
				out.print(
						"<script type='text/javascript'>alert('글이 성공적으로 등록되었습니다.'); location.replace('redirect:lawMain.go?pg=1');</script>");
			} else {
				System.out.println("자유게시판 글쓰기 등록 실패");
				out.print(
						"<script type='text/javascript'>alert('글을 등록하는데 실패하였습니다.'); location.replace('redirect:lawMain.go?pg=1');</script>");
			}
			return "redirect:lawMain.go?pg=1";
		}

		//5. 게시물 수정 (화면 (select)
		@RequestMapping(value = "lawEdit.go",  method = RequestMethod.GET)   
		public String lawEdit(@RequestParam("bno") int boardno,HttpSession session, Model model) throws ClassNotFoundException, SQLException{

			// 로그 남기기
			System.out.println("게시물 수정 페이지로 이동");

			// 페이지 이동 변수 선언
			String go = "";

			BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			
			int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
			BoardLawDTO boardLawDTO = boardLawDAO.getNotice(boardno);
			
			if (memberno == boardLawDTO.getMemberno()) {
				System.out.println("자유게시판 원본글 가져오기");
				model.addAttribute("boardLawDTO", boardLawDTO);
				go = "home.boardLaw.lawEdit";
			} else {
				go = "redirect:index.go";
			}
			return go;
		}

		
		//5-1. 게시물 수정 (실제 처리(update)
		@RequestMapping(value = "lawEdit.go",  method = RequestMethod.POST)   
		public String lawEdit(	@RequestParam("pg") int page,
				BoardLawDTO boardLawDTO,
				HttpSession session,
				HttpServletRequest request) throws Exception {
			//로그 남기기
			System.out.println("게시물 수정 작업 시작");
			//페이지 이동 변수 선언
			String go = "";
			int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();

		    if(memberno == boardLawDTO.getMemberno()){
				BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
				boardLawDAO.update(boardLawDTO);
				System.out.println("자유게시판 수정완료");
				go = "redirect:freeMain.go?pg="+page;
			}else{
				go = "redirect:index.go";
			}
			return go;
		}
		//6. 게시물 삭제
		@RequestMapping("lawDelete.go")   
		public String lawDelete(@RequestParam("bno") int boardno) throws ClassNotFoundException, SQLException{
			
		    BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		    boardLawDAO.delete(boardno);
		    
		    System.out.println("변호사게시판 삭제완료");
		    return "home.boardLaw.lawMain";
		    
		}
	
	//7       // 게시물 본문내용 미리보기(/preView)
		
		@RequestMapping("preView.go")   
		public String preView(@RequestParam("bno") int boardno, Model model) throws ClassNotFoundException, SQLException{
			System.out.println("lawpreView");
			 BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			 BoardLawDTO boardLawDTO = boardLawDAO.getNotice(boardno);
			 model.addAttribute("boardLawDTO", boardLawDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
			
			 
	              return "home.boardLaw.lawView";
	       }
	
	
	
	
}//End BoardController
