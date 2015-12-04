package controllers;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.CheatBankDAO;
import DAO.CheatDomainDAO;
import DAO.CheatItemsDAO;
import DAO.CheaterDAO;
import DTO.CheatBankDTO;
import DTO.CheatDomainDTO;
import DTO.CheatItemsDTO;
import DTO.CheaterDTO;

//진술서 등록 (피해 등록) 컨트롤러
@Controller
public class RegistrationController {

	//스크립트 구문 작업 변수 선언
	PrintWriter out = null;

	@Autowired
	private SqlSession sqlSession;

	//피해 등록 페이지로 이동
	@RequestMapping("registration.go")
	public String registration() throws Exception {
		
		return "registration.registration";
	}

	//게임 피해 등록 페이지로 이동
	@RequestMapping(value = "game.go", method = RequestMethod.GET)
	public String game() {
		System.out.println("게임 진술서 작성 페이지 이동");
		return "registration.game";
	}

	//직거래 피해 등록 페이지로 이동
	@RequestMapping(value = "trade.go", method = RequestMethod.GET)
	public String trade(Model model) throws Exception {
		System.out.println("직거래 진술서 작성 페이지 이동");
		
		CheatDomainDAO cdDAO = sqlSession.getMapper(CheatDomainDAO.class);
		CheatItemsDAO ciDAO = sqlSession.getMapper(CheatItemsDAO.class);
		CheatBankDAO cbDAO = sqlSession.getMapper(CheatBankDAO.class);
		
		List<CheatDomainDTO> domainlist = cdDAO.getList();
		List<CheatItemsDTO> itemslist = ciDAO.getList(0);
		List<CheatBankDTO> banklist1 = cbDAO.getList(0,25);
		List<CheatBankDTO> banklist2 = cbDAO.getList(25,13);
		List<CheatBankDTO> banklist3 = cbDAO.getList(38,25);
		
		model.addAttribute("domainlist",domainlist);
		model.addAttribute("itemslist", itemslist);
		model.addAttribute("banklist1", banklist1);
		model.addAttribute("banklist2", banklist2);
		model.addAttribute("banklist3", banklist3);
		
		return "registration.trade";
	}

	//비매너 피해 등록 페이지로 이동
	@RequestMapping(value = "manner.go", method = RequestMethod.GET)
	public String manner() {
		System.out.println("비매너 진술서 작성 페이지 이동");
		return "registration.manner";
	}
	
	//진술서 DB 작업
	@RequestMapping(value = "registration.go", method = RequestMethod.POST)
	public String game(CheaterDTO cheaterDTO, HttpServletResponse response) throws Exception {

		// 로그 남기기
		System.out.println("진술서 등록");
		System.out.println(cheaterDTO.toString());

		// 스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// 마이바티스로 넘기기
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		int result = cheaterDAO.insert(cheaterDTO);

		if (result > 0) {// 등록 성공
			out.print("<script type='text/javascript'>alert('진술서를 등록하였습니다.')</script>");
		} else {// 등록 실패
			out.print("<script type='text/javascript'>alert('진술서 등록에 실패하였습니다.')</script>");
		}
		return "home.index";
	}
}
