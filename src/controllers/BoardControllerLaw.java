package controllers;

import java.io.IOException;
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
import DAO.MemberDAO;
import DAO.ReplyDAO;
import DTO.BoardFreeDTO;
import DTO.BoardLawDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardControllerLaw {

	PrintWriter out;
	@Autowired
	private SqlSession sqlSession;

	///////////////////////////////////
	///////////////////////////////////
	////////// 변호사 게시판///////////
	////////// 변호사 게시판///////////
	////////// 변호사 게시판///////////
	///////////////////////////////////
	///////////////////////////////////

	// 1. 변호사게시판 메인(목록리스트)
	@RequestMapping("lawMain.go") // 자유게시판 메인- 목록보기 List
	public String lawMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재
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
			HttpSession session, Model model) throws Exception {

		// 로그 남기기
		System.out.println(page + " / " + field + " / " + query);

		BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);

		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "lawMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardLawDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
															// 갯수
		int start = (page - 1) * pageSize;
		BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);

		List<BoardLawDTO> list = boardLawDAO.getNotices(start, field, query, pageSize);

		System.out.println(session.getAttribute("memberInfo"));
		String email = ((MemberDTO) session.getAttribute("memberInfo")).getEmail();

		model.addAttribute("Email", email);
		model.addAttribute("pager", pager);
		model.addAttribute("list", list); // 자동 forward
		model.addAttribute("boardCount", boardCount);

		return "home.boardLaw.lawMain";
	}

	// 2. 변호사게시판 상세보기
	@RequestMapping("lawView.go") // 변호사게시판 상세보기 - 페이지
	public String lawView(@RequestParam("bno") int boardno, 
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "rpg", required = false, defaultValue = "1") int replypage, // 현재 페이지 번호
			HttpSession session, Model model) throws Exception {
		
		System.out.println("lawview");
		BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		BoardLawDTO boardLawDTO = boardLawDAO.getNotice(boardno);
		model.addAttribute("boardLawDTO", boardLawDTO); ///// DB 테이블 명--파라미터명 일치
														///// 여부 확인후 수정바람*****
		// 클릭한 사람이 게시자가 아니라면 조회수 증가
		if (((MemberDTO) session.getAttribute("memberInfo")).getMemberno() != boardLawDTO.getMemberno()) {
			System.out.println("조회수 증가");
			boardLawDAO.updateCountno(boardno);
			boardLawDTO.setCountno(boardLawDTO.getCountno() + 1);
		}
		
//		// 해당 게시판의 글쓴이 사진 가져오기
			MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
//			boardLawDTO.setPhoto(memberDAO.getPhoto(boardLawDTO.getMemberno()));
				
		// 게시글에 달린 리플들 정보값을 불러오기 위한 변수 선언 및 가져오기
			System.out.println("리플 정보 가져오기");
			ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
			int replycount = replyDAO.getBoardReplyCount("content", "%%", boardno);

			// 리플 페이징 처리
			int rstart = (replypage - 1) * 10;
			ReplyPager rpager = null;
			if (!query.equals("%%")) {// 검색값이 있을 경우
				rpager = new ReplyPager(replycount, replypage, 10, 10, "freeView.go", boardno, page, field, query);
			}else{// 검색값이 없을 경우
				rpager = new ReplyPager(replycount, replypage, 10, 10, "freeView.go", boardno, page);
			}
			
			
			// 해당 게시판에 대한 리플들 불러오기
			List<ReplyDTO> replyDTO = replyDAO.getBoardReply("content", "%%", boardno, rstart);

//			// 리플에 있는 사진 불러오기
//			for(int i = 0; i < replyDTO.size(); i++){
//				replyDTO.get(i).setPhoto(memberDAO.getPhoto(replyDTO.get(i).getMemberno()));
//			}
			
			// 상세보기 화면 밑에 메인 화면 리스트 뿌려주기
			boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			
			// 페이징 처리
			String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
			int boardCount = boardLawDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
																	// 갯수
			int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
			int start = (page - 1) * pageSize;
			
			BoardPager pager = null;
			if (!query.equals("%%")) {// 검색값이 있을 경우
				pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
			} else {// 검색 값이 없을 경우
				pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
			}

			// 메인 화면 게시물 리스트 얻기
			List<BoardLawDTO> list = boardLawDAO.getNotices(start, field, query, pageSize);

//			// 메인 리스트에 댓글 숫자 출력
//			for (int i = 0; i < list.size(); i++) {//리스트에 담기
//				list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", "%%", list.get(i).getBoardno()));
//			}
			
			// DB값 model 객체에 담기
			model.addAttribute("BoardLawDTO", boardLawDTO);
			model.addAttribute("replycount", replycount);
			model.addAttribute("rpager", rpager);
			model.addAttribute("replyDTO", replyDTO);
			model.addAttribute("pager", pager);
			model.addAttribute("boardCount", boardCount);
			model.addAttribute("list", list);

		
		return "home.boardLaw.lawView";
	}

	// 3.변호사게시판 글쓰기(화면만 뿌리기)
	@RequestMapping(value = "lawWrite.go", method = RequestMethod.GET)
	public String lawWrite(BoardLawDTO boardDTO) {
		System.out.println("변호사게시판 글쓰기 창");
		return "home.boardLaw.lawWrite";
	}

	// 4. 변호사게시판 글쓰기(실제 글 등록 -DB)
	@RequestMapping(value = "lawWrite.go", method = RequestMethod.POST)
	public String LawWrite(BoardLawDTO boardLawDTO, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
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
			System.out.println("변호사게시판 글쓰기 완료");
			out.print(
					"<script type='text/javascript'>alert('글이 성공적으로 등록되었습니다.'); location.replace('redirect:lawMain.go?pg=1');</script>");
		} else {
			System.out.println("변호사게시판 글쓰기 등록 실패");
			out.print(
					"<script type='text/javascript'>alert('글을 등록하는데 실패하였습니다.'); location.replace('redirect:lawMain.go?pg=1');</script>");
		}
		return "redirect:lawMain.go?pg=1";
	}

	// 5. 게시물 수정 (화면 (select)
	@RequestMapping(value = "lawEdit.go", method = RequestMethod.GET)
	public String lawEdit(@RequestParam("bno") int boardno, HttpSession session, Model model)
			throws ClassNotFoundException, SQLException {

		// 로그 남기기
		System.out.println("게시물 수정 페이지로 이동");

		// 페이지 이동 변수 선언
		String go = "";

		BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);

		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		BoardLawDTO boardLawDTO = boardLawDAO.getNotice(boardno);

		if (memberno == boardLawDTO.getMemberno()) {
			System.out.println("변호사게시판 원본글 가져오기");
			model.addAttribute("boardLawDTO", boardLawDTO);
			go = "home.boardLaw.lawEdit";
		} else {
			go = "redirect:index.go";
		}
		return go;
	}

	// 5-1. 게시물 수정 (실제 처리(update)
	@RequestMapping(value = "lawEdit.go", method = RequestMethod.POST)
	public String lawEdit(@RequestParam("pg") int page, BoardLawDTO boardLawDTO, HttpSession session,
			HttpServletRequest request) throws Exception {
		// 로그 남기기
		System.out.println("게시물 수정 작업 시작");
		// 페이지 이동 변수 선언
		String go = "";
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();

		if (memberno == boardLawDTO.getMemberno()) {
			BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			boardLawDAO.update(boardLawDTO);
			System.out.println("변호사게시판 수정완료");
			go = "redirect:lawMain.go?pg=" + page;
		} else {
			go = "redirect:index.go";
		}
		return go;
	}

	// 6. 게시물 삭제
	@RequestMapping("lawDelete.go")
	public String lawDelete(@RequestParam("bno") int boardno) throws ClassNotFoundException, SQLException {

		BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		boardLawDAO.delete(boardno);

		System.out.println("변호사게시판 삭제완료");
		return "home.boardLaw.lawMain";

	}
	
	
	
	

}
