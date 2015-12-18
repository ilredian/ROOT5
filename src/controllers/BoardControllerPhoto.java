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

import DAO.BoardPhotoDAO;
import DAO.MemberDAO;
import DAO.MemberTypeDAO;
import DAO.ReplyDAO;
import DTO.BoardPhotoDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;

@Controller
public class BoardControllerPhoto {

	// 자바스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;

	@Autowired
	private SqlSession sqlSession;

	// 사진게시판 메인
	@RequestMapping(value = "PhotoMain.go", method = RequestMethod.GET)
	public String freeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
			// 현재 페이지 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, 
			// 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "ps", required = false, defaultValue = "12") int pageSize, 
			Model model) throws Exception {

		System.out.println("사진게시판 이동");

		BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);

		// 페이징 처리
		int pagerSize = 12;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "PhotoMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardPhotoDAO.BoardPhotoCount();// 검색 결과에 따른 게시글 총
		// 갯수
		int start = (page - 1) * pageSize;

		BoardPager pager = null;
		// 검색 값이 없을 경우
		pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);

		// 게시물 불러오기
		List<BoardPhotoDTO> list = boardPhotoDAO.BoardPhotoList(start, pageSize);

		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("사진게시판 메인 출력 완료");

		return "home.boardPhoto.photoMain";
	}

	// 사진 게시판 글쓰기
	@RequestMapping(value = "PhotoWrite.go", method = RequestMethod.GET)
	public String photowrite() {

		System.out.println("사진글 등록");

		return "home.boardPhoto.photowrite";
	}

	//사진게시판 실제 글쓰기
	   @RequestMapping(value = "PhotoWrite.go", method = RequestMethod.POST)
	   public void freeWrite(BoardPhotoDTO boardphotoDTO, HttpServletResponse response) throws Exception {
	      
	      System.out.println("글아 써져라");
	      
	    //경고문 띄우기 전 한글 처리
	    response.setContentType("text/html;charset=UTF-8");
	    out = response.getWriter();
	    
	    //컨텐츠에서 이미지값 가져오기
	    int row1 = boardphotoDTO.getContent().indexOf("src=\"");
		int row2 = boardphotoDTO.getContent().indexOf("\"", row1+5);
		String mainImg="";
		if(row1<row2){
			mainImg = boardphotoDTO.getContent().substring(row1+5, row2);
		}else{
			mainImg="/images/common/no_img.gif";
		}
		
		boardphotoDTO.setUploadfile(mainImg);
		
		//사진 사이즈 조정
		boardphotoDTO.setContent(boardphotoDTO.getContent().replaceAll("<img","<img style=\"max-width:100% !important;=&quot;&quot; height:auto;\" "));
	    
	    //글 등록 진행
	    BoardPhotoDAO boardphotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
	    int result = boardphotoDAO.insert(boardphotoDTO);
	    
	    if (result != 0) {
	         System.out.println("사진게시판 글쓰기 완료");
	         out.print(
	               "<script type='text/javascript'>alert('글이 성공적으로 등록되었습니다.'); location.replace('PhotoMain.go?pg=1');</script>");
	      } else {
	         System.out.println("사진게시판 글쓰기 등록 실패");
	         out.print(
	               "<script type='text/javascript'>alert('글을 등록하는데 실패하였습니다.'); location.replace('PhotoMain.go?pg=1');</script>");
	      }
	      out.close();
	   }
	
	// 사진게시판 자세히 보기
	@RequestMapping(value="PhotoView.go", method = RequestMethod.GET)
	public String PhotoView(
			// 현재 페이지 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, 
			// 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "ps", required = false, defaultValue = "12") int pageSize, 
			@RequestParam("bno") int boardno, Model model,
			HttpSession session
			) throws Exception {
		
		//로그 남기기
		System.out.println("Photoview");
		
		//DAO 변수 선언
		BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		
		//포토 게시물 DB 가져오기
		BoardPhotoDTO boardPhotoDTO = boardPhotoDAO.BoardPhotoDetail(boardno);
		model.addAttribute("BoardPhotoDTO",boardPhotoDTO);
		
		// 글쓴이 정보 가져오기
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO writerMemberDTO = memberDAO.getMemberStat(boardPhotoDTO.getMemberno());
		model.addAttribute("writerMemberDTO", writerMemberDTO);
		
		// 회원 타입 가져오기
		MemberTypeDAO memberTypeDAO = sqlSession.getMapper(MemberTypeDAO.class);
		String typetext = memberTypeDAO.getMemberType(writerMemberDTO.getTypeno());
		model.addAttribute("typetext", typetext);
		
		//리플 정보 가져오기
		List<ReplyDTO> rlist = replyDAO.getCategoryReply("content", "%%", 3);
		int replyCount = replyDAO.getCategoryReplyCount("content", "%%", 3);
		
		model.addAttribute("replyDTO", rlist);
		model.addAttribute("replycount",replyCount);
		
		
		////////////밑에 메인 내용
		// 페이징 처리
		int pagerSize = 12;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "PhotoMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardPhotoDAO.BoardPhotoCount();// 검색 결과에 따른 게시글 총
		
		// 갯수
		int start = (page - 1) * pageSize;
		BoardPager pager = null;
		
		// 검색 값이 없을 경우
		pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		
		// 게시물 불러오기
		List<BoardPhotoDTO> list = boardPhotoDAO.BoardPhotoList(start, pageSize);
		
		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);
		
		//자신을 제외하고 조회수 증가
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		if(boardPhotoDTO.getMemberno() != memberno){
			boardPhotoDAO.BoardPhotoupdateCountno(boardno);
		}
		
		// 로그 남기기
		System.out.println("사진게시판 메인 출력 완료");
		
		return "home.boardPhoto.photoView";
	}

	//사진 게시판 수정
	@RequestMapping(value="photoEdit.go", method = RequestMethod.GET)
	public String photoEditMain(
			@RequestParam("bno") int boardno, Model model
			) throws Exception{
		

		//로그 남기기
		System.out.println("Photoview");
		
		//DAO 변수 선언
		BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
		
		//포토 게시물 DB 가져오기
		BoardPhotoDTO boardPhotoDTO = boardPhotoDAO.BoardPhotoDetail(boardno);
		model.addAttribute("boardPhotoDTO",boardPhotoDTO);
		
		return "home.boardPhoto.photoEdit";
	}
	
	//사진 게시판 수정
	@RequestMapping(value="photoEdit.go", method = RequestMethod.POST)
	public void photoEdit(
			@RequestParam("pg") int page, 
			BoardPhotoDTO boardPhotoDTO,
			HttpServletResponse response) throws Exception {

		// 로그 남기기
		System.out.println("게시물 수정 작업 시작");
		
		// 경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// 글 수정 진행
		BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
		int result = boardPhotoDAO.BoardPhotoupdate(boardPhotoDTO);
		
		if(result == 1){
			System.out.println("자유게시판 수정완료");
			out.print("<script>alert('게시물 수정이 성공적으로 처리되었습니다.');location.replace('PhotoMain.go?pg=" + page + "');</script>");
		}else{
			out.print("<script>alert('게시물 수정에 실패하였습니다.');location.replace('index.go');</script>");
		}
		out.close();
	}
	
	// 포토 게시판 삭제
	@RequestMapping("photoDelete.go")
	public void photoDelete(
			@RequestParam("bno") int boardno, HttpServletResponse response) throws Exception {
		
		// 로그 남기기
		System.out.println("게시물 삭제");
			
		// 경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
						
		// 글 삭제 진행
		BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
		int result = boardPhotoDAO.BoardPhotoupdateActive(boardno);
				
		if(result == 1){
			System.out.println("사진게판 게시물 삭제(active 수정)완료");
			out.print("<script>alert('게시물 삭제가 성공적으로 처리되었습니다.');location.replace('PhotoMain.go?pg=1');</script>");
		}else{
			System.out.println("사진게판 게시물을 삭제(active 수정)할 수 없습니다.");
			out.print("<script>alert('게시물 삭제에 실패하였습니다.');location.replace('PhotoMain.go?pg=1');</script>");
		}
		out.close();
	}
}