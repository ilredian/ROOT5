package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {
	
	@RequestMapping("contacted.go")
	public String contMain(){
	
		return "contact.contactmain";
	}

	@RequestMapping(value="question.go", method=RequestMethod.GET)
	public String contQuestform(){
		//문의하기 페이지 이동
		return "contact.contactquestion";
	}
	
	
	@RequestMapping(value="question.go", method=RequestMethod.POST)
	public String contQuest(){
		//문의하기 보내기
		return "contact.contactquestion";
	}
	
	
	@RequestMapping(value="deletepls.go" , method=RequestMethod.GET)
	public String contDeleteform(){
		//피해사례 삭제요청 폼으로 가기
		return "contact.contactdeletepls";
		
	}
	
	@RequestMapping(value="deletepls.go" , method=RequestMethod.POST)
	public String contDelete(){
		//피해사례 삭제요청
		
		
		
		return "contact.contactdeletepls";
	}

	
	@RequestMapping("contlist.go")
	public String contList(){
		return "contact.contactlist";
	}
}
