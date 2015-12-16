package controllers;


import java.io.PrintWriter;

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
	public String contQuest(
			HttpSession session,
			@RequestParam("content") String content,
			@RequestParam("title") String title
			){
		
		//문의하기 보내기
		System.out.println("문의하기 보내기");
		
		//문의하기 메일 처리
		String name = ((MemberDTO)session.getAttribute("memberInfo")).getName();
		String from = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();
		String to = "ilredian@ilredian.xyz";
		String tar = "html";
		String filename = "";
		//보내는사람 이름, 보내는사람 주소, 받는사람 주소, 제목, 내용, 형식, 첨부파일
		SendMailDTO sendMailDTO = new SendMailDTO(name, from, to, title, content, tar, filename);
		SendMail mail = new SendMail();
		mail.sendMail(sendMailDTO);

		return "contact.contactquestion";
	}
	
	
	@RequestMapping(value="deletepls.go" , method=RequestMethod.GET)
	public String contDeleteform(){
		//피해사례 삭제요청 폼으로 가기
		return "contact.contactdeletepls";
		
	}
	
	//피해사례 삭제요청
	@RequestMapping(value="deletepls.go" , method=RequestMethod.POST)
	public String contDelete(
			HttpSession session,
			@RequestParam("content") String content,
			@RequestParam("title") String title
			){
		
		//피해사례 삭제요청
		System.out.println("피해사례 삭제요청");
		
		//삭제요청 메일 처리
		String name = ((MemberDTO)session.getAttribute("memberInfo")).getName();
		String from = ((MemberDTO)session.getAttribute("memberInfo")).getEmail();
		String to = "ilredian@ilredian.xyz";
		String tar = "html";
		String filename = "";
		//보내는사람 이름, 보내는사람 주소, 받는사람 주소, 제목, 내용, 형식, 첨부파일
		SendMailDTO sendMailDTO = new SendMailDTO(name, from, to, title, content, tar, filename);
		SendMail mail = new SendMail();
		mail.sendMail(sendMailDTO);
		
		return "contact.contactdeletepls";
	}

	
	@RequestMapping("contlist.go")
	public String contList(){
		return "contact.contactlist";
	}
}
