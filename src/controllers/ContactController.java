package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {
	@RequestMapping(value="contmain.go", method=RequestMethod.GET)
	public String contMain(){
		
		//로그남기기
		System.out.println("컨택센터 메인으로 이동");
		
		return "contact.contactmain";
	}

}
