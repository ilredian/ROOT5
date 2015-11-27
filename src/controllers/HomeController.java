package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("index.go")
	public String index(){
		return "main.index";
	}
	
	@RequestMapping("home.go")
	public String Home(){
		return "home.home.home";
	}
}
