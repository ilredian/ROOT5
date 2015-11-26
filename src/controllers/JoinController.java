package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {

	@RequestMapping("signin.go")
	public String Singin(){
		System.out.println("회원가입 창 이동");
		return "join.signin";
	}
}
