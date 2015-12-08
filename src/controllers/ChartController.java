package controllers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.CheaterDAO;
import DTO.CheaterDTO;

@Controller
public class ChartController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="chartMain.go", method=RequestMethod.GET)
	public String chartMain(
			Model model) throws Exception{
		
		//로그남기기
		System.out.println("피해사례 통계 메인으로 이동");
		
		//통계 리스트 뿌려줄 db 변수 선언
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		
		//통계 값 가져오기
		//피해사례 수
		String allCheaterCount = String.format("%,d", cheaterDAO.getAllCheaterCount());
		//카테고리 별 피해 사례 수
		int countTrade = cheaterDAO.getCountCategory(1);
		int countGame = cheaterDAO.getCountCategory(2);
		int countManner = cheaterDAO.getCountCategory(3);
		//휴대폰 번호 수
		String countPhone = String.format("%,d", cheaterDAO.getCountPhone());
		//계좌번호 수
		String countAccount = String.format("%,d", cheaterDAO.getCountAccount());
		//피해금액
		String countSum = String.format("%,d", cheaterDAO.getCountSum());
		//피해물품 TOP 10
		List<CheaterDTO> countItems = cheaterDAO.getCountItems();
		//용의자
		List<CheaterDTO> countCheaterName = cheaterDAO.getCountCheaterName();
		//사이트
		List<CheaterDTO> countDomain = cheaterDAO.getCountDomain();
		//은행
		List<CheaterDTO> countBankName = cheaterDAO.getCountBankName();
		
		//시간 값 설정			
		Date date = new Date();
		int year = date.getYear();
		int month = date.getMonth();
		
		// 차트 값 model 객체에 담기
		model.addAttribute("allCheaterCount", allCheaterCount);
		model.addAttribute("countPhone", countPhone);
		model.addAttribute("countAccount", countAccount);
		model.addAttribute("countSum", countSum);
		model.addAttribute("countItems", countItems);
		model.addAttribute("countCheaterName", countCheaterName);
		model.addAttribute("countDomain", countDomain);
		model.addAttribute("countBankName", countBankName);
		model.addAttribute("countTrade", countTrade);
		model.addAttribute("countGame", countGame);
		model.addAttribute("countManner", countManner);
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		
		return "chart.chartMain";
	}

}
