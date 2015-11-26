package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

	@RequestMapping("registration.go")
	public String registration(){
		System.out.println("피해 진술 이동");
		return "registration.registration";
	}
}
