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

import DAO.BoardFreeDAO;
import DAO.MemberDAO;
import DAO.ReplyDAO;
import DTO.BoardFreeDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardControllerFree {// 자유게시판

	// 자바스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;
	
	// 1. 자유게시판 메인(목록리스트)
	@RequestMapping("freeMain.go") // 자유게시판 메인- 목록보기 List
	public String freeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			Model model) throws Exception {

		// 로그 남기기
		System.out.println(page + " / " + field + " / " + query);

		// 맵퍼 설정하여 DAO 변수 선언
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);

		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardFreeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
																// 갯수
		int start = (page - 1) * pageSize;

		BoardPager pager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
		} else {// 검색 값이 없을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		}

		// 게시물 불러오기
		List<BoardFreeDTO> list = boardFreeDAO.getNotices(start, field, query, pageSize);

		// 메인 리스트 제목에 댓글 숫자 출력
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		for (int i = 0; i < list.size(); i++) {//list에 추가하여 담기
			list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", query, list.get(i).getBoardno(), 1));
		}

		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("자유게시판 메인 출력 완료");

		return "home.boardFree.freeMain";
	}

	// 2. 자유게시판 상세보기
	@RequestMapping("freeView.go") // 자유게시판 상세보기 - 페이지
	public String freeView(@RequestParam("bno") int boardno, // 게시글 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "rpg", required = false, defaultValue = "1") int replypage, // 현재 페이지 번호
			HttpSession session, Model model) throws Exception {
		
		// 로그남기기
		System.out.println("자유게시판 상세보기 페이지 이동");
		System.out.println(page + " / " + field + " / " + query);

		// 게시판 상세 정보 가져오기
		System.out.println("게시판 상세 정보 가져오기");
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);

		// 클릭한 사람이 게시자가 아니라면 조회수 증가
		if (((MemberDTO) session.getAttribute("memberInfo")).getMemberno() != boardFreeDTO.getMemberno()) {
			System.out.println("조회수 증가");
			boardFreeDAO.updateCountno(boardno);
			boardFreeDTO.setCountno(boardFreeDTO.getCountno() + 1);
		}
		
		// 해당 게시판의 글쓴이 사진 가져오기
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		boardFreeDTO.setPhoto(memberDAO.getPhoto(boardFreeDTO.getMemberno()));
		
		// 게시글에 달린 리플들 정보값을 불러오기 위한 변수 선언 및 가져오기
		System.out.println("리플 정보 가져오기");
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		int replycount = replyDAO.getBoardReplyCount("content", "%%", boardno, 1);



		// 리플 페이징 처리
		int rstart = (replypage - 1) * 10;
		ReplyPager rpager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			rpager = new ReplyPager(replycount, replypage, 10, 10, "freeView.go", boardno, page, field, query);
		}else{// 검색값이 없을 경우
			rpager = new ReplyPager(replycount, replypage, 10, 10, "freeView.go", boardno, page);
		}
		
		// 해당 게시판에 대한 리플들 불러오기
		List<ReplyDTO> replyDTO = replyDAO.getBoardReply("content", "%%", boardno, rstart, 1);

		// 리플에 있는 사진 불러오기
		for(int i = 0; i < replyDTO.size(); i++){
			replyDTO.get(i).setPhoto(memberDAO.getPhoto(replyDTO.get(i).getMemberno()));
		}
		
		// 상세보기 화면 밑에 메인 화면 리스트 뿌려주기
		boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);

		// 페이징 처리
		String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardFreeDAO.getCount(field, query);// 검색 결과에 따른 게시글 총
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
		List<BoardFreeDTO> list = boardFreeDAO.getNotices(start, field, query, pageSize);

		// 메인 리스트에 댓글 숫자 출력
		for (int i = 0; i < list.size(); i++) {//리스트에 담기
			list.get(i).setBoardReplyCount(replyDAO.getBoardReplyCount("content", "%%", list.get(i).getBoardno(), 1));
		}
		
		MemberDTO writerMemberDTO = memberDAO.getMemberStat(boardFreeDTO.getMemberno());
		
		// DB값 model 객체에 담기
		model.addAttribute("writerMemberDTO", writerMemberDTO);
		model.addAttribute("boardFreeDTO", boardFreeDTO);
		model.addAttribute("replycount", replycount);
		model.addAttribute("rpager", rpager);
		model.addAttribute("replyDTO", replyDTO);
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		return "home.boardFree.freeView";
	}

	// 3. 자유게시판 글쓰기(페이지 이동)
	@RequestMapping(value = "freeWrite.go", method = RequestMethod.GET)
	public String freeWrite() {
		
		//로그 남기기
		System.out.println("자유게시판 글쓰기 페이지 이동");
		return "home.boardFree.freeWrite";
	}

	// 4. 자유게시판 글쓰기(실제 글 등록)
	@RequestMapping(value = "freeWrite.go", method = RequestMethod.POST)
	public void freeWrite(BoardFreeDTO boardFreeDTO, HttpServletResponse response) throws Exception {
		
		//로그남기기
		System.out.println("실제 글 등록 처리");

		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		//글 등록 진행
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		int groupno = boardFreeDAO.LAST_INSERT_ID();
		boardFreeDTO.setGroupno(groupno+1);
		int result = boardFreeDAO.insert(boardFreeDTO);

		if (result != 0) {
			System.out.println("자유게시판 글쓰기 완료");
			out.print(
					"<script type='text/javascript'>alert('글이 성공적으로 등록되었습니다.'); location.replace('freeMain.go?pg=1');</script>");
		} else {
			System.out.println("자유게시판 글쓰기 등록 실패");
			out.print(
					"<script type='text/javascript'>alert('글을 등록하는데 실패하였습니다.'); location.replace('freeMain.go?pg=1');</script>");
		}
		out.close();
	}

	// 5. 게시물 수정 (페이지 이동)
	@RequestMapping(value = "freeEdit.go", method = RequestMethod.GET)
	public String freeEdit(@RequestParam("bno") int boardno, Model model) throws Exception {

		// 로그 남기기
		System.out.println("게시물 수정 페이지로 이동");

		// 수정할 게시물의 정보 가져오기
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);

		// 모델 객체에 담기
		model.addAttribute("boardFreeDTO", boardFreeDTO);
		
		return "home.boardFree.freeEdit";
	}

	// 5-1. 게시물 수정 (수정 처리)
	@RequestMapping(value = "freeEdit.go", method = RequestMethod.POST)
	public void freeEdit(@RequestParam("pg") int page, 
			@RequestParam(value="f", required = false, defaultValue = "title") String field,
			@RequestParam(value="q", required = false, defaultValue = "%%") String query,
			BoardFreeDTO boardFreeDTO,
			HttpServletResponse response) throws Exception {

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
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		int result = boardFreeDAO.update(boardFreeDTO);
		
		if(result == 1){
			System.out.println("자유게시판 수정완료");
			out.print("<script>alert('게시물 수정이 성공적으로 처리되었습니다.');location.replace('freeMain.go?pg=" + page + addURI + "');</script>");
		}else{
			out.print("<script>alert('게시물 수정에 실패하였습니다.');location.replace('index.go');</script>");
		}
		out.close();
	}

	// 6. 게시물 삭제
	@RequestMapping("freeDelete.go")
	public void freeDelete(@RequestParam("bno") int boardno, HttpServletResponse response) throws Exception {

		// 로그 남기기
		System.out.println("게시물 삭제");
		
		// 경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
				
		// 글 삭제 진행
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		int result = boardFreeDAO.updateActive(boardno);
		
		if(result == 1){
			System.out.println("자유게시판 게시물 삭제(active 수정)완료");
			out.print("<script>alert('게시물 삭제가 성공적으로 처리되었습니다.');location.replace('freeMain.go?pg=1');</script>");
		}else{
			System.out.println("자유게시판 게시물을 삭제(active 수정)할 수 없습니다.");
			out.print("<script>alert('게시물 삭제에 실패하였습니다.');location.replace('freeMain.go?pg=1');</script>");
		}
		out.close();
	}

	// 7. 답글 입력_창이동
	@RequestMapping(value = "freeAnswer.go", method = RequestMethod.GET)
	public String freeAnswer(@RequestParam("bno") int boardno, Model model) throws Exception {
		
		// 로그 남기기
		System.out.println("자유게시판 답글 글쓰기 창");

		// 페이지 이동 전 게시글 정보 가져오기
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		BoardFreeDTO boardFreeDTO = boardFreeDAO.getNotice(boardno);
		
		// DB값 model 객체에 담기
		model.addAttribute("boardFreeDTO", boardFreeDTO);
		return "home.boardFree.freeAnswer";
	}

	// 7-2. 답글 실제 등록
	@RequestMapping(value = "freeAnswer.go", method = RequestMethod.POST)
	public void freeAnswer(BoardFreeDTO boardFreeDTO, HttpServletResponse response) throws Exception {

		// 로그 남기기
		System.out.println("자유 게시판 답글 처리");

		// 경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
				
		// DB 로직
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		// 글 쓰기전 step update를 통한 순서 정리
		boardFreeDAO.updateStep(boardFreeDTO.getGroupno(), boardFreeDTO.getStep());
		int result = boardFreeDAO.answer(boardFreeDTO);

		if(result == 1){
			out.print("<script>alert('답글 등록이 성공적으로 처리되었습니다.');location.replace('freeMain.go?pg=1');</script>");
		}else{
			out.print("<script>alert('답글 등록에 실패하였습니다.');location.replace('freeMain.go?pg=1);</script>");
		}
		out.close();
	}
}