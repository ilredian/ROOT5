package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
	
	@RequestMapping("contact.go")
	public String contMain(){
		return "contact.contactmain";
	}

}
