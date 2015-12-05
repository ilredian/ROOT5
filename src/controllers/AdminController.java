package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value = "adhome.go", method = RequestMethod.GET)
	public String AdmainHome() {

		return "admin.adminHome";
	}
	@RequestMapping(value = "free.go", method = RequestMethod.GET)
	public String AdmainFree() {

		return "admin.adminFree";
	}
	@RequestMapping(value = "poto.go", method = RequestMethod.GET)
	public String AdmainPoto() {

		return "admin.adminPoto";
	}
	@RequestMapping(value = "commment.go", method = RequestMethod.GET)
	public String AdmainCommment() {

		return "admin.adminComment";
	}
	@RequestMapping(value = "site.go", method = RequestMethod.GET)
	public String AdmainSite() {

		return "admin.adminSite";
	}
	@RequestMapping(value = "deallist.go", method = RequestMethod.GET)
	public String AdmainDealList() {

		return "admin.adminDealList";
	}
	@RequestMapping(value = "bank.go", method = RequestMethod.GET)
	public String AdmainBank() {

		return "admin.adminBank";
	}
	@RequestMapping(value = "join.go", method = RequestMethod.GET)
	public String AdmainJoin() {

		return "admin.adminJoin";
	}
	@RequestMapping(value = "fake.go", method = RequestMethod.GET)
	public String AdmainFake() {

		return "admin.adminfake";
	}
	@RequestMapping(value = "memberagent.go", method = RequestMethod.GET)
	public String AdmainMemberagent() {

		return "admin.adminMemberagent";
	}
	@RequestMapping(value = "catrgory.go", method = RequestMethod.GET)
	public String AdmainCatrgory() {

		return "admin.adminCategory";
	}
	@RequestMapping(value = "mail.go", method = RequestMethod.GET)
	public String AdmainMail() {

		return "admin.adminMail";
	}

}
