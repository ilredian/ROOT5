package controllers;

import java.io.PrintWriter;
import java.util.List;

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
import DAO.MemberDAO;
import DAO.ReplyDAO;
import DTO.BoardNoticeDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardControllerNotice {//공지사항 게시판
	
	//자바 스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;

	// 1. 공지 게시판 메인(목록리스트)
		@RequestMapping("noticeMain.go") // 공지 게시판 메인- 목록보기 List
		public String noticeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
				@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
				@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
				@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
				@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
				HttpSession session,Model model) throws Exception {

			// 로그 남기기
			System.out.println(page + " / " + field + " / " + query);

			// 맵퍼 설정하여 DAO 변수 선언
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

			// 페이징 처리
			int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
			String linkUrl = "noticeMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardNoticeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
																	// 갯수
			int start = (page - 1) * pageSize;
			
			BoardPager pager = null;
			if (!query.equals("%%")) {// 검색값이 있을 경우
				pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
			} else {// 검색 값이 없을 경우
				pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
			}
			
			// 게시물 불러오기
			List<BoardNoticeDTO> list= boardNoticeDAO.getNotices(start, field, query, pageSize);
			
			//메인 리스트에 댓글 숫자 출력
			ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
			for(int i=0; i < list.size(); i++){
				list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno(), 2));
			}
			int typeno = ((MemberDTO) session.getAttribute("memberInfo")).getTypeno();

			model.addAttribute("typeno",typeno);
			
			// DB값 model 객체에 담기
			model.addAttribute("pager", pager);
			model.addAttribute("boardCount", boardCount);
			model.addAttribute("list", list);
					
			//로그 남기기
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
			System.out.println(page + " / " + field + " / " + query);

			// 게시판 상세 정보 가져오기
			System.out.println("공지 게시판 상세 정보 가져오기");
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(boardno);

			// 조회수 증가
			System.out.println("조회수 증가");
			boardNoticeDAO.updateCountno(boardno);
			boardNoticeDTO.setCountno(boardNoticeDTO.getCountno() + 1);
				
			// 해당 게시판의 글쓴이 사진 가져오기
			MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
			boardNoticeDTO.setPhoto("");
			
			// 게시글에 달린 리플들 정보값을 불러오기 위한 변수 선언 및 가져오기
			System.out.println("리플 정보 가져오기");
			ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
			int replycount = replyDAO.getBoardReplyCount("content", "%%", boardno, 2);
			
			// 리플 페이지 처리
			int rstart = (replypage - 1) * 10;
			ReplyPager rpager = null;
			if (!query.equals("%%")) {// 검색값이 있을 경우
				rpager = new ReplyPager(replycount, replypage, 10, 10, "noticeView.go", boardno, page, field, query);
			}else{// 검색값이 없을 경우
				rpager = new ReplyPager(replycount, replypage, 10, 10, "noticeView.go", boardno, page);
			}
			
			// 해당 게시판에 대한 리플들 불러오기
			List<ReplyDTO> replyDTO = replyDAO.getBoardReply("content", "%%", boardno, rstart, 2);
			
			// 리플에 있는 사진 불러오기
			for(int i = 0; i < replyDTO.size(); i++){
				replyDTO.get(i).setPhoto(memberDAO.getPhoto(replyDTO.get(i).getMemberno()));
			}
			
			// 상세보기 화면 밑에 메인 화면 리스트 뿌려주기
			boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);

			// 페이징 처리
			String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardNoticeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총 갯수
			int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
			int start = (page - 1) * pageSize;
			
			
			BoardPager pager = null;
			if (!query.equals("%%")) {// 검색값이 있을 경우		
				pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
			} else {// 검색 값이 없을 경우
				pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
			}
			// 메인 화면 게시물 리스트 얻기
			List<BoardNoticeDTO> list= boardNoticeDAO.getNotices(start, field, query, pageSize);
					
			// 메인 리스트에 댓글 숫자 출력
			for(int i=0; i < list.size(); i++){
				list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno(), 2));
			}
			
			// DB값 model 객체에 담기
			model.addAttribute("boardNoticeDTO", boardNoticeDTO);
			model.addAttribute("replycount", replycount);
			model.addAttribute("rpager", rpager);
			model.addAttribute("replyDTO", replyDTO);
			model.addAttribute("pager", pager);
			model.addAttribute("boardCount", boardCount);
			model.addAttribute("list", list);
			
			return "home.boardNotice.noticeView";
		}

		// 3. 공지 게시판 글쓰기(페이지 이동)
		@RequestMapping(value = "noticeWrite.go", method = RequestMethod.GET)
		public String noticeWrite() {
			
			//로그 남기기
			System.out.println("공지 게시판 글쓰기 페이지 이동");
			
			return "home.boardNotice.noticeWrite";
		}

		// 4. 공지 게시판 글쓰기(실제 글 등록)
		@RequestMapping(value = "noticeWrite.go", method = RequestMethod.POST)
		public void freeWrite(BoardNoticeDTO boardNoticeDTO, HttpServletResponse response) throws Exception {
			
			//로그남기기
			System.out.println("실제 글 등록 처리");

			//경고문 띄우기 전 한글 처리
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();

			//글 등록 진행
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			int result = boardNoticeDAO.insert(boardNoticeDTO);

			if (result != 0) {
				System.out.println("공지사항 게시판 글쓰기 완료");
				out.print(
						"<script type='text/javascript'>alert('글이 성공적으로 등록되었습니다.'); location.replace('noticeMain.go?pg=1');</script>");
			} else {
				System.out.println("공지사항 게시판 글쓰기 등록 실패");
				out.print(
						"<script type='text/javascript'>alert('글을 등록하는데 실패하였습니다.'); location.replace('noticeMain.go?pg=1');</script>");
			}
			out.close();
		}

		// 5. 게시물 수정 (페이지 이동)
		@RequestMapping(value = "noticeEdit.go", method = RequestMethod.GET)
		public String noticeEdit(@RequestParam("bno") int boardno, Model model) throws Exception {

			// 로그 남기기
			System.out.println("게시물 수정 페이지로 이동");

			// 게시물 정보 가져오기
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			BoardNoticeDTO boardNoticeDTO = boardNoticeDAO.getNotice(boardno);
			
			// 정모를 모델 객체애 담기
			model.addAttribute("boardNoticeDTO", boardNoticeDTO);

			return "home.boardNotice.noticeEdit";
		}

		// 5-1. 게시물 수정 (수정 처리)
		@RequestMapping(value = "noticeEdit.go", method = RequestMethod.POST)
		public void noticeEdit(
								@RequestParam("pg") int page,
								@RequestParam(value="f", required = false, defaultValue = "title") String field,
								@RequestParam(value="q", required = false, defaultValue = "%%") String query,
								BoardNoticeDTO boardNoticeDTO,
								HttpServletResponse response
								) throws Exception {

			// 로그 남기기
			System.out.println("게시물 수정 작업 시작");
			
			// 경고문 띄우기 전 한글 처리
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			
			// 검색한 후 수정했을 경우
			String addURI = "";
			if(!query.equals("%%")){
				addURI += "&f="+field+"&q="+query;
			}
			
			// 글 수정 진행
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			int result = boardNoticeDAO.update(boardNoticeDTO);
			
			if(result == 1){
				System.out.println("공지사항 게시판 수정완료");
				out.print("<script>alert('게시물 수정이 성공적으로 처리되었습니다.');location.replace('noticeMain.go?pg=" + page + addURI + "');</script>");
			}else{
				out.print("<script>alert('게시물 수정에 실패하였습니다.');location.replace('index.go');</script>");
			}
			out.close();
		}

		// 6. 게시물 삭제
		@RequestMapping("noticeDelete.go")
		public void noticeDelete(@RequestParam("bno") int boardno, HttpServletResponse response) throws Exception {

			// 로그 남기기
			System.out.println("게시물 삭제");
			
			// 경고문 띄우기 전 한글 처리
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			
			// 글 삭제 진행
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			int result = boardNoticeDAO.updateActive(boardno);
			
			if(result == 1){
				System.out.println("공지사항 게시판 게시물 삭제(active 수정)완료");
				out.print("<script>alert('게시물 삭제가 성공적으로 처리되었습니다.');location.replace('noticeMain.go?pg=1');</script>");
			}else{
				System.out.println("공지사항 게시판 게시물을 삭제(active 수정)할 수 없습니다.");
				out.print("<script>alert('게시물 삭제에 실패하였습니다.');location.replace('noticeMain.go?pg=1');</script>");
			}
			out.close();
		}
}