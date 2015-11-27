package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	
	private String main = "home.index"; //메인화면 이동

	@RequestMapping("registration.go")
	public String registration(){
		System.out.println("피해 진술 이동");
		return "registration.registration";
	}
	
	@RequestMapping(value="game.go" , method=RequestMethod.GET)
	public String game(){
		System.out.println("게임 피해 등록 이동");
		return "registration.game";
	}
	
	@RequestMapping(value="game.go" , method=RequestMethod.POST)
	public String game(String string){
		System.out.println("게임 피해 DB 등록");
		return main;
	}
	
	@RequestMapping(value="trade.go" , method=RequestMethod.GET)
	public String trade(){
		System.out.println("거래 피해 등록 이동");
		return "registration.trade";
	}
	
	@RequestMapping(value="trade.go" , method=RequestMethod.POST)
	public String trade(String string){
		System.out.println("거래 피해 DB 등록");
		return main;
	}
	
	@RequestMapping(value="manner.go" , method=RequestMethod.GET)
	public String manner(){
		System.out.println("비매너 피해 등록 이동");
		return "registration.manner";
	}
	
	@RequestMapping(value="manner.go" , method=RequestMethod.POST)
	public String manner(String string){
		System.out.println("비매너 피해 DB 등록");
		return main;
	}
}
