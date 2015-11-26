package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.BoardDAO;
import DTO.BoardDTO;

@Controller
public class BoardController {

	@Autowired(required=false)
	private BoardDAO boardDAO;
	
	@RequestMapping()
	public String freeMain(){
		System.out.println("자유게시판 메인 창 이동");
		return "";
	}
	
	@RequestMapping()
	public String freeView(){
		System.out.println("자유게시판 뷰 이동");
		return "";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String freeWrite(BoardDTO boardDTO){
		System.out.println("자유게시판 글쓰기 이동");
		return "";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String freeWrite(){
		System.out.println("자유게시판 글쓰기 DB 작업 수행");
		return "";
	}
	
	@RequestMapping()
	public String noticeMain(){
		System.out.println("공지사항 메인 창 이동");
		return "";
	}
	
	@RequestMapping()
	public String noticeView(){
		System.out.println("공지사항 뷰 이동");
		return "";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String noticeWriteView(BoardDTO boardDTO){
		System.out.println("공지사항 글쓰기 이동");
		return "";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String noticeWrite(){
		System.out.println("공지사항 글쓰기 DB 작업 수행");
		return "";
	}
}
