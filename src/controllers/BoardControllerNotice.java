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

import DAO.BoardNoticeDAO;
import DAO.ReplyDAO;
import DTO.BoardNoticeDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardControllerNotice {
	
	PrintWriter out;
	@Autowired
	private SqlSession sqlSession;

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
	
}
