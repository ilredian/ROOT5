package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("index.go")
	public String index(){
		System.out.println("메인 이동");
		return "home.index";
	}
	
	@RequestMapping("home.go")
	public String Home(){
		System.out.println("홈 이동");
		return "home.home";
	}
}
