package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.BoardFreeDAO;
import DAO.BoardNoticeDAO;
import DTO.BoardFreeDTO;

@Controller
public class BoardController {

	@Autowired(required=false)
	private BoardFreeDAO boardFreeDAO;
	
	@Autowired(required=false)
	private BoardNoticeDAO boardNoticeDAO;
	
	@RequestMapping("freeMain.go")
	public String freeMain(){
		System.out.println("자유게시판 메인 창 이동");
		return "home.boardFree.freeMain";
	}
	
	@RequestMapping()
	public String freeView(){
		System.out.println("자유게시판 뷰 이동");
		return "home.boardFree.freeView";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String freeWrite(BoardFreeDTO boardDTO){
		System.out.println("자유게시판 글쓰기 이동");
		return "home.boardFree.freeWrite";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String freeWrite(){
		System.out.println("자유게시판 글쓰기 DB 작업 수행");
		return "home.boardFree.freeMain";
	}
	
	@RequestMapping("noticeMain.go")
	public String noticeMain(){
		System.out.println("공지사항 메인 창 이동");
		return "home.boardNotice.noticeMain";
	}
	
	@RequestMapping()
	public String noticeView(){
		System.out.println("공지사항 뷰 이동");
		return "home.boardNotice.noticeView";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String noticeWriteView(BoardFreeDTO boardDTO){
		System.out.println("공지사항 글쓰기 이동");
		return "home.boardNotice.noticeWrite";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String noticeWrite(){
		System.out.println("공지사항 글쓰기 DB 작업 수행");
		return "home.boardNotice.noticeMain";
	}
}
