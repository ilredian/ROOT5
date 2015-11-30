package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "join.signin";
	}
	
	@RequestMapping(value="signin.go" , method=RequestMethod.POST)
	public String Singin(MemberDTO memberDTO, HttpServletResponse response) throws Exception{
		//가입처리 (DAO 단)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String go = "";
		
		System.out.println(memberDTO.toString());
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int row = memberDAO.insert(memberDTO);
		System.out.println(row);
		if(row>0){
			out.print("<script type='text/javascript'>alert('회원가입 성공');</script>");
			go = "redirect:index.go";
		}else{
			out.print("<script type='text/javascript'>alert('DB등록 오류');</script>");
			go = "join.signin";
		}

		return go;
	}
	
	@RequestMapping("pwSearch.go")
	public String pwSearch(){
		return "join.pwSearch";
	}
	
	/*
	@RequestMapping("member.go")
	public String SingOK(MemberDTO member) throws ClassNotFoundException, SQLException{
		System.out.println("회원가입완료");
		memberDAO.insert(member);
		return "redirect:../index.htm";
	}
*/

}
