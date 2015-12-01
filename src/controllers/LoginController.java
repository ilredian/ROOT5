package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class LoginController {

	PrintWriter out = null;

	@Autowired
	private SqlSession sqlSession;

	// 로그인 정보 DB 확인
	@RequestMapping(value = "login.go", method=RequestMethod.POST)
	public String Login(MemberDTO memberDTO, HttpServletResponse response, HttpSession session) throws Exception {
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
		
		if(result == null){//아이디가 없음
			//경고창 띄우기
			out.print("<script type='text/javascript'>alert('해당 이메일은 가입되어 있지 않습니다.')</script>");
		}else {//아이디가 있음
			if(result.getPassword().equals(memberDTO.getPassword())){//비밀번호가 같음
				System.out.println("로그인 값 저장");
				System.out.println("로그인 세션 설정");
				session.setAttribute("MemberInfo", result);
			}else{//비밀번호가 틀림
				//경고창 띄우기
				out.print("<script type='text/javascript'>alert('비밀번호가 틀립니다.')</script>");
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