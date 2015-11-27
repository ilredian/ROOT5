package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DTO.BoardFreeDTO;

@Controller
public class BoardController {
	
	@RequestMapping("freeMain.go")
	public String freeMain(){
		return "home.boardFree.freeMain";
	}
	
	@RequestMapping()
	public String freeView(){
		return "home.boardFree.freeView";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String freeWrite(BoardFreeDTO boardDTO){
		return "home.boardFree.freeWrite";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String freeWrite(){
		return "home.boardFree.freeMain";
	}
	
	@RequestMapping("noticeMain.go")
	public String noticeMain(){
		return "home.boardNotice.noticeMain";
	}
	
	@RequestMapping()
	public String noticeView(){
		return "home.boardNotice.noticeView";
	}
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String noticeWriteView(BoardFreeDTO boardDTO){
		return "home.boardNotice.noticeWrite";
	}
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String noticeWrite(){
		return "home.boardNotice.noticeMain";
	}
}
