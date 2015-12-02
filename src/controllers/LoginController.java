package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class LoginController {

	PrintWriter out = null;

	@Autowired
	private SqlSession sqlSession;

	// 로그인 정보 DB 확인
	@RequestMapping(value = "login.go", method=RequestMethod.POST)
	public String Login(
			MemberDTO memberDTO,
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			Model data ) throws Exception {
		System.out.println("로그인 실행");
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//로그 남기기
		System.out.println("login.go POST");
		System.out.println(memberDTO.toString());

		//로그인 폼 정보를 마이바티스로 넘김
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO result = memberDAO.getMember(memberDTO.getEmail());
		
		System.out.println("result : " + result);
		if(result == null){//아이디가 없음
			//경고창 띄우기
			System.out.println("해당 이메일은 가입되어 있지 않습니다");
			String alert2 = "<script type='text/javascript'>alert('해당 이메일은 가입되어 있지 않습니다')</script>";
			return "redirect:index.go";
		
		}else {//아이디가 있음
			if(result.getPassword().equals(memberDTO.getPassword())){//비밀번호가 같음
				System.out.println("로그인 값 저장");
				System.out.println("로그인 세션 설정");
				
				session.setAttribute("memberInfo", result);
				session.setMaxInactiveInterval(60*60) ;
				
				String id = request.getParameter("email");
				String pwd = request.getParameter("password");
				
				/* 로그인 세션값 유지하려면..?
				Cookie cookie = new Cookie("email", "bbb@naver.com");
				cookie.setMaxAge(60*5);
				response.addCookie(cookie);
				System.out.println("bbb@ 쿠키 생성");
				*/		
				System.out.println("email : " + id + " / password : " + pwd);

			}else{//비밀번호가 틀림  //내가 입력한 값과 DB에 값이 틀리면
 				//경고창 띄우기
				String id = request.getParameter("email");
				String pwd = request.getParameter("password");
				System.out.println("email : " + id + " / password : " + pwd);
				
				System.out.println("비밀번호 땡");
				data.addAttribute("pwd", "fail");
				return "main.index";
			}
		}		
		return "main.index";
	}
	// 로그아웃
	@RequestMapping(value = "logout.go")
	public String Logout(HttpServletResponse response, HttpSession session) throws IOException {
		// 세션 삭제
		session.invalidate();
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//경고창 띄우기
		out.print("<script type='text/javascript'>alert('로그아웃 되었습니다.')</script>");
		return "main.index";
	}
}