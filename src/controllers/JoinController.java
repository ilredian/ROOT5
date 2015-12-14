package controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class JoinController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="signin.go" , method=RequestMethod.GET)
	public String Singin(){
		System.out.println("회원가입 페이지로 이동");
		return "join.signin";
	}
	
	@RequestMapping(value="signin.go" , method=RequestMethod.POST)
	public String Singin(MemberDTO memberDTO, HttpServletResponse response) throws Exception{
		//가입처리 (DAO 단)
		System.out.println("회원가입 실행");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String go = "";
		
		System.out.println(memberDTO.toString());
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		//DB에 정보가 없다면 회원 등록
		if(memberDAO.getMember(memberDTO)==null){
			int row = memberDAO.insert(memberDTO);
			System.out.println(row);
			out.print("<script type='text/javascript'>alert('회원가입 성공');location.replace('index.go');</script>");
			go="redirect:index.go";
		}else{
			out.print("<script type='text/javascript'>alert('이미 존재하는 아이디입니다.');location.replace('signin.go');</script>");
			go="join.signin";
		}
		out.close();
		return go;
	}
	
	@RequestMapping("pwSearch.go")
	public String pwSearch(){
		return "join.pwSearch";
	}
}
