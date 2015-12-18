package controllers;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DTO.MemberDTO;
import mail.SendMail;
import mail.SendMailDTO;

@Controller
public class ContactController {

	// 자바스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;
	
	@RequestMapping("contacted.go")
	public String contMain(){
	
		return "contact.contactmain";
	}

	@RequestMapping(value="question.go", method=RequestMethod.GET)
	public String contQuestform(){
		//문의하기 페이지 이동
		return "contact.contactquestion";
	}
	
	//문의하기 보내기
	@RequestMapping(value="question.go", method=RequestMethod.POST)
	public void contQuest(
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("content") String content,
			@RequestParam("title") String title
			) throws Exception{
		
		//문의하기 보내기
		System.out.println("문의하기 보내기");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//문의하기 메일 처리
		String name = "REQUEST";
		String from = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();
		String to = "ilredian@ilredian.xyz";
		String tar = "html";
		String filename = "";
		
		//보내는사람 이름, 보내는사람 주소, 받는사람 주소, 제목, 내용, 형식, 첨부파일
		SendMailDTO sendMailDTO = new SendMailDTO(name, from, to, title, content, tar, filename);
		SendMail mail = new SendMail();
		int result = mail.sendMail(sendMailDTO);
		
		//메일 보낸 결과 스크립트 처리
		if (result != 0) {
			out.print(
					"<script type='text/javascript'>alert('관리자에게 문의를 성공적으로 보냈습니다.'); location.replace('home.go');</script>");
		} else {
			out.print(
					"<script type='text/javascript'>alert('관리자에게 문의를 보내는데 실패했습니다.'); location.replace('question.go');</script>");
		}
		out.close();
	}
	
	@RequestMapping(value="deletepls.go" , method=RequestMethod.GET)
	public String contDeleteform(){
		//피해사례 삭제요청 폼으로 가기
		return "contact.contactdeletepls";
		
	}
	
	//피해사례 삭제요청
	@RequestMapping(value="deletepls.go" , method=RequestMethod.POST)
	public void contDelete(
			HttpServletResponse response,
			HttpSession session,
			@RequestParam("content") String content,
			@RequestParam("title") String title
			) throws Exception{
		
		//피해사례 삭제요청
		System.out.println("피해사례 삭제요청");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//삭제요청 메일 처리
		String name = "DELETE";
		String from = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();
		String to = "ilredian@ilredian.xyz";
		String tar = "html";
		String filename = "";
		//보내는사람 이름, 보내는사람 주소, 받는사람 주소, 제목, 내용, 형식, 첨부파일
		SendMailDTO sendMailDTO = new SendMailDTO(name, from, to, title, content, tar, filename);
		SendMail mail = new SendMail();
		int result = mail.sendMail(sendMailDTO);
		
		//메일 보내기 결과
		if (result != 0) {
			out.print(
					"<script type='text/javascript'>alert('관리자에게 문의를 성공적으로 보냈습니다.'); location.replace('home.go');</script>");
		} else {
			out.print(
					"<script type='text/javascript'>alert('관리자에게 문의를 보내는데 실패했습니다.'); location.replace('deletepls.go');</script>");
		}
		out.close();
	}

	@RequestMapping("contlist.go")
	public String contList(){
		return "contact.contactlist";
	}
}
