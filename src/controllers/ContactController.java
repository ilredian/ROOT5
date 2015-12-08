package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
	
	@RequestMapping("contact.go")
	public String contMain(){
		return "contact.contactmain";
	}

	@RequestMapping("question.go")
	public String contQuest(){
		return "contact.contactquestion";
	}
	
	@RequestMapping("deletepls.go")
	public String contDelete(){
		return "contact.contactdeletepls";
	}
	
	@RequestMapping("contlist.go")
	public String contList(){
		return "contact.contactlist";
	}
}
