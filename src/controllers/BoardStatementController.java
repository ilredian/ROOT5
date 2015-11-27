package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardStatementController {

	@RequestMapping()
	public String gameMain(){
		System.out.println("피해사례 게임 메인 이동");
		return "";
	}
	
	@RequestMapping()
	public String gameView(){
		System.out.println("피해사례 게임 뷰 이동");
		return "";
	}
	
	@RequestMapping()
	public String mannerMain(){
		System.out.println("피해사례 비매너 메인 이동");
		return "";
	}
	
	@RequestMapping()
	public String mannerView(){
		System.out.println("피해사례 비매너 뷰 이동");
		return "";
	}
	
	@RequestMapping()
	public String spamMain(){
		System.out.println("피해사례 스팸 메인 이동");
		return "";
	}
	
	@RequestMapping()
	public String spamView(){
		System.out.println("피해사례 스팸 뷰 이동");
		return "";
	}
	
	@RequestMapping()
	public String tradeMain(){
		System.out.println("피해사례 거래 메인 이동");
		return "";
	}
	
	@RequestMapping()
	public String tradeView(){
		System.out.println("피해사례 거래 뷰 이동");
		return "";
	}
}
