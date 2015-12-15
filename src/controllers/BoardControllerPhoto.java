package controllers;

import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardPhotoDAO;
import DAO.ReplyDAO;
import DTO.BoardFreeDTO;
import DTO.BoardPhotoDTO;
import common.BoardPager;

@Controller
public class BoardControllerPhoto {
	
	// 자바스크립트 쓰기위한 전역 변수 선언
		PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;
   
	//사진게시판 메인
   @RequestMapping(value="PhotoMain.go", method = RequestMethod.GET)
   public String freeMain(// get으로 들어오는 parameter값 선언 및 기본값 설정
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			Model model) throws Exception {
      
      System.out.println("사진게시판 이동");
      
      BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
      
      	// 페이징 처리
   		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
   		String linkUrl = "freeMain.go";// 페이지번호를 누르면 이동할 경로
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
   //사진 게시판 글쓰기
   @RequestMapping(value="PhotoWrite.go", method = RequestMethod.GET)
   public String photowrite(){
      
      System.out.println("사진글 등록");
      
      return "home.boardPhoto.photowrite";
   }
   //사진게시판 상세보기
   @RequestMapping(value="PhotoView.go", method = RequestMethod.GET)
   public String photoView(){
      
      System.out.println("사진글 등록");
      
      return "home.boardPhoto.photowrite";
   }
}