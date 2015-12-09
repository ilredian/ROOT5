package controllers;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CheatBankDAO;
import DAO.CheatDomainDAO;
import DAO.CheatItemsDAO;
import DAO.CheaterDAO;
import DTO.CheatBankDTO;
import DTO.CheatDomainDTO;
import DTO.CheatItemsDTO;
import DTO.CheaterDTO;
import DTO.MemberDTO;

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
		
		// 로그 남기기
		System.out.println("직거래 진술서 작성 페이지 이동");
		
		//시간 값 설정			
		Date date = new Date();
		int year = date.getYear()+1900;
		int month = date.getMonth()+1;
		int d = date.getDate();
		
		// DB 연결을 위한 변수 선언
		CheatDomainDAO cdDAO = sqlSession.getMapper(CheatDomainDAO.class);
		CheatItemsDAO ciDAO = sqlSession.getMapper(CheatItemsDAO.class);
		CheatBankDAO cbDAO = sqlSession.getMapper(CheatBankDAO.class);
		
		// 필요한 값들 불러오기
		// domianList
		List<CheatDomainDTO> domainlist = cdDAO.getList();
		// itemList
		List<CheatItemsDTO> itemslist = ciDAO.getList(0, 26);
		// bankList
		List<CheatBankDTO> banklist1 = cbDAO.getList(0,25);
		List<CheatBankDTO> banklist2 = cbDAO.getList(25,13);
		List<CheatBankDTO> banklist3 = cbDAO.getList(38,25);
		
		// DB 값 model 객체에 담기
		model.addAttribute("domainlist",domainlist);
		model.addAttribute("itemslist", itemslist);
		model.addAttribute("banklist1", banklist1);
		model.addAttribute("banklist2", banklist2);
		model.addAttribute("banklist3", banklist3);
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("date", d);
		
		return "registration.trade";
	}
	
	//직거래 피해 등록
	@RequestMapping(value = "trade.go", method = RequestMethod.POST)
	public void regTrade(
			@RequestParam("cheat_site") String domain,
			@RequestParam("cheat_item_temp") String goodskind,
			@RequestParam("subject") String goodsname,
			@RequestParam("cheat_id") String cheaterid,
			@RequestParam("link") String link,
			@RequestParam("cheat_bank") String bankname,
			@RequestParam("cheat_suspect") String cheatername,
			@RequestParam("cheat_account") String account,
			@RequestParam("cheat_price") int deposit,
			@RequestParam("cheat_date_temp_1") String depositdate1,
			@RequestParam("cheat_date_temp_2") String depositdate2,
			@RequestParam("cheat_date_temp_3") String depositdate3,
			@RequestParam("cheat_phone1") String phone1,
			@RequestParam("cheat_phone2") String phone2,
			@RequestParam("cheat_phone3") String phone3,
			@RequestParam("cheat_sex") String sex,
			@RequestParam("content") String content,
			@RequestParam("cheat_character") String feature,
			HttpServletResponse response,
			HttpSession session
			) throws Exception{
		
		// 로그 남기기
		System.out.println("직거래 피해 등록");
		
		// DB에 넣기 위한 변수 선언
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		
		// 폼값 DTO에 넣기
		int cheatno = 1;
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		String depositdate = depositdate1+"-"+depositdate2+"-"+depositdate3;
		String phone = phone1+"-"+phone2+"-"+phone3;
		CheaterDTO cheaterDTO = new CheaterDTO(bankname, cheatername, account, deposit, depositdate, phone, sex, feature, domain, goodskind, goodsname, cheaterid, link, content, cheatno, memberno);
		
		// DB에 저장
		int result = cheaterDAO.insert(cheaterDTO);
		
		// 경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// 경고창으로 결과 알림
		if(result == 1){
			System.out.println("직거래 진술서 등록 완료");
			out.print("<script>alter('진술서가 정상적으로 등록되었습니다.');location.replace('trade.go');</script>");
		}else{
			System.out.println("직거래 진술서 등록 실패");
			out.print("<script>alter('진술서 등록에 실패하였습니다.');location.replace('trade.go');</script>");
		}
		out.close();
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
