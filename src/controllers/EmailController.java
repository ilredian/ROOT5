package controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mail.Email;
import mail.SendMail;

@Controller
public class EmailController {
	
	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;
	
	@RequestMapping(value="email.go")
	public void sendEmailMethod(
			HttpServletResponse response,
			Email email) throws Exception {
		
		//로그 남기기
		System.out.println("메일 보내기");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//메일 보내기
		SendMail sendMail = new SendMail();
		int result = sendMail.sendMail(email);
		
		//메일 결과
		if(result == 1){
			System.out.println("이메일 보내기 완료");
			out.print(
					"<script type='text/javascript'>alert('메일이 성공적으로 발송되었습니다.'); location.replace('');</script>");
		}else{
			System.out.println("이메일 보내기 실패");
			out.print(
					"<script type='text/javascript'>alert('메일 발송에 실패하였습니다.'); location.replace('');</script>");
		}
		out.close();
	}
}