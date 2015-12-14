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
import org.springframework.web.util.WebUtils;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class LoginController {

	PrintWriter out = null;

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="login.go" , method=RequestMethod.GET)
	public String Login(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws Exception{
		
		// 로그 남기기
		System.out.println("로그인 페이지로 이동");
		
		// remember체크 확인 전 DAO변수 선언
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		// 쿠키 검색
		String isRemember = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i != cookies.length; ++i) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equalsIgnoreCase("rememberCheck")) {
					isRemember = cookie.getValue();
				}
			}
		}
		
		// remember 쿠키가 있으면 회원정보 가져오기
		if(!isRemember.equals("")){
			String email = isRemember;
			MemberDTO tempDTO = new MemberDTO();
			tempDTO.setEmail(email);
			MemberDTO memberDTO = memberDAO.getMember(tempDTO);
			model.addAttribute("loginRemember", memberDTO);
		}
		
		return "join.login";
	}
	
	// 로그인 정보 DB 확인
	/*
	@PreAuthorize("hasRole('ROLE_USER'")
	*/
	@RequestMapping(value = "login.go", method=RequestMethod.POST)
	public void Login(
			@RequestParam(value="remember", required=false, defaultValue="0") int remember,
			MemberDTO memberDTO,
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			Model data ) throws Exception {
		
		//로그 남기기
		System.out.println("로그인 실행");
		System.out.println(remember);
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//로그인 폼 정보를 마이바티스로 넘김
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO result = memberDAO.getMember(memberDTO);
		
		//유효성 검사
		if(result != null){//아이디가 있음
			if(result.getPassword().equals(memberDTO.getPassword())){//비밀번호가 같음
				
				//memberInfo 속성에 로그인 세션값을 넣기, 즉 로그인하기 
				session.setAttribute("memberInfo", result);
				session.setMaxInactiveInterval(60*60*24) ;
				
				//remember가 체크 되어 있다면 쿠키 생성
				if(remember == 1){
					Cookie rememberCheck = new Cookie("rememberCheck", memberDTO.getEmail());
					rememberCheck.setPath("/");
					rememberCheck.setMaxAge(60*60*24*7);
					response.addCookie(rememberCheck);
				}

				out.print("<script type='text/javascript'>alert('로그인 하셨습니다.');location.replace('index.go');</script>");
			}else{//비밀번호가 틀림
				
				//내가 입력한 값과 DB에 값이 틀리면 경고창 띄우기
				out.print("<script type='text/javascript'>alert('비밀번호가 틀렸습니다.');location.replace('login.go');</script>");
			}
		}else{
			
			//이메일이 없으면 경고창 띄우기
			out.print("<script type='text/javascript'>alert('해당 이메일은 가입되어 있지 않습니다');location.replace('login.go');</script>");
		}
		out.close();
	}
	
	@RequestMapping(value="/ERROR.go" , method=RequestMethod.GET)
	public void Denied(){
		
		System.out.println("접근 거부");
	}
	
	// 로그아웃
	@RequestMapping(value = "logout.go")
	public void Logout(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		
		// 세션 삭제
		session.removeAttribute("login");
		session.invalidate();
		
		
		// 쿠키 삭제
		
		//Cookie cookie = new Cookie("rememberCheck", "");
		Cookie rememberCheck = WebUtils.getCookie(request, "rememberCheck");
		
		if(rememberCheck != null){
			rememberCheck.setPath("/");
			rememberCheck.setMaxAge(0);
			response.addCookie(rememberCheck);
			
		}
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//경고창 띄우기
		out.print("<script type='text/javascript'>alert('로그아웃 되었습니다.');location.replace('index.go');</script>");
		out.close();
	}
}