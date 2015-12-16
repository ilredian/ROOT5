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

@Controller
public class ContactController {

	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("contacted.go")
	public String contMain(){
	
		return "contact.contactmain";
	}

	@RequestMapping(value="question.go", method=RequestMethod.GET)
	public String contQuestform(){
		//문의하기 페이지 이동
		return "contact.contactquestion";
	}
	
	
	@RequestMapping(value="question.go", method=RequestMethod.POST)
	public String contQuest(
			HttpServletResponse response,
			HttpSession session
			) throws IOException{
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();	
		//문의하기 보내기
		
	//	MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
	//	memberDAO.delete(memberno);
		
		
		
		
		return "contact.contactquestion";
	}
	
	
	@RequestMapping(value="deletepls.go" , method=RequestMethod.GET)
	public String contDeleteform(){
		//피해사례 삭제요청 폼으로 가기
		return "contact.contactdeletepls";
		
	}
	
	@RequestMapping(value="deletepls.go" , method=RequestMethod.POST)
	public String contDelete(
			HttpServletResponse response,
			HttpSession session
			) throws IOException{
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();	
		//문의하기 보내기
		
		
		
		return "contact.contactdeletepls";
	}

	
	@RequestMapping("contlist.go")
	public String contList(){
		return "contact.contactlist";
	}
}
