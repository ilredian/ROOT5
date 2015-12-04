package controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.email.Email;
import spring.email.EmailSender;

@Controller
public class EmailController {
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;

	@RequestMapping("email.go")
	public String sendEmailAction(
			@RequestParam("id") String id1,
			@RequestParam("pw") String pw2,
			@RequestParam("e_mail") String e_mail3
			) throws Exception {
		

		String id = id1;
		String e_mail = e_mail3;
		String pw = pw2;
		System.out.println(pw);
		
		String go ="";
		if (pw != null) {
			email.setContent("비밀번호는 " + pw + " 입니다.");
			email.setReceiver(e_mail);
			email.setSubject(id + "님 비밀번호 찾기 메일입니다.");
			System.out.println("1");
			emailSender.SendEmail(email);
			System.out.println("2");
			go= "redirect:/index.go";

		} else {
			go = "redirect:/index.go";
		}
		return go;
	}
}