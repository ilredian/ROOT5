package controllers;

import java.io.PrintWriter;
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
import DAO.ReplyDAO;
import DTO.BoardFreeDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardControllerFree {

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
	public String freeEdit(@RequestParam("bno") int boardno,
			HttpSession session, 
			Model model ,
			HttpServletResponse response) throws Exception {

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
			System.out.println("작성자 본인이 아니다!");
			
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			
			out.write("<script>alert('작성자 본인이 아닙니다!')</script>");

			out.print("<script>alert('작성자 본인이 아닙니다!')</script>");
			out.flush();

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
	
	// 7. 답글 입력_창이동
	@RequestMapping(value = "freeAnswer.go", method = RequestMethod.GET)
	public String freeAnswer(@RequestParam("bno") int boardno,
								HttpSession session, 
								Model model ,
								HttpServletResponse response) throws Exception {
		System.out.println("자유게시판 답글 글쓰기 창");
		
		// 페이지 이동 변수 선언
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);
			System.out.println("자유게시판 원본글 가져오기");
			model.addAttribute("boardFreeDTO", boardFreeDTO);
		return "home.boardFree.freeAnswer";
	}

	//7-2. 답글 실제 등록
	@RequestMapping(value = "freeAnswer.go", method = RequestMethod.POST)
	public String freeAnswer(@RequestParam("bno") int boardno, BoardFreeDTO boardFreeDTO, HttpSession session) throws Exception {
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		//답글 입력_
		boardFreeDAO.answer(boardFreeDTO);

		return "redirect:freeMain.go?pg=1";
		}	
	
}
