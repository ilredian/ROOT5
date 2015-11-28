package controllers;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DTO.MemberDTO;

@Controller
public class LoginController {
	
	@Autowired
	private SqlSession sqlSession;
	
		//1-1. 로그인 창
		@RequestMapping(value="login.go")
		public String Login(MemberDTO memberDTO){
			
		/*	
			BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
			List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
			model.addAttribute("list", list); //자동 forward
		*/	
			
			return "login.login";
		}
		
		//. 로그아웃
		public String Logout(){
			
//			invalid_
			
			
			return "";
		}
				
}
