package controllers;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DTO.LoginDTO;

@Controller
@RequestMapping("login.go")
public class LoginController {
	//1. 로그인 모달
	@RequestMapping(method=RequestMethod.GET)
	public String login(LoginDTO loginDTO,
			@CookieValue(value="REMEMBER",required=false)Cookie rCookie){
			if(rCookie !=null){
				loginDTO.setEmail(rCookie.getValue());
				loginDTO.setRememberEmail(true);
			}
			
			return "home.boardFree.freeMain";
	}
	
/*	//2. 로그인 완료
	@RequestMapping(method=RequestMethod.POST)
	public String form(LoginDTO loginDTO, Error erros, HttpSession session,
						HttpServletResponse res){
							}
*/			
}

