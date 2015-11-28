package controllers;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class LoginController {
	
	@Autowired
	private SqlSession sqlSession;
	HashMap<String,MemberDTO> map;
	//1-1. 로그인 창
		@RequestMapping(value="login.go")
		public String Login(MemberDTO memberDTO, Model model){

				System.out.println(memberDTO.toString());
			
		//	xml -> 이아디가 니 아이디냐? true or false
		//  xml -> 이아디의 비번이 니 비번이냐? //
		
		// all yes -> 로그인 성공 -> 홈으로 이동   ->// 이동시, 알림문구 , 로그인에 성공하셨습니다.
			
		// NO! ->  로그인 실패 -> 홈으로 이동	-> 이동시, 너님 아이디나 비번 잘못됨 ㅇㅇ 똥멍충아 다시 확인 ㄱㄱ
			
				MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
			
//				List<memberDTO> list = memberDAO.
						
			 memberDAO.login(email, pwd);
			
				
				model.addAttribute("map", map); //자동 forward
			return "home;
		}
		
		//. 로그아웃
		public String Logout(){
			
//			invalid_
			
			
			return "";
		}
				
}
