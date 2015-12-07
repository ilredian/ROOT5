package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.InterestStatementDAO;
import DTO.InterestStatementDTO;
import DTO.MemberDTO;

@Controller
public class InterestController {

	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;
	
	// 관심 지정해놨던 진술서 DB 와 비교
	@RequestMapping("")
	public String interestSelect(
			HttpSession session,
			Model model) throws Exception {
		
		// 로그 남기기
		System.out.println("관심 지정 진술서 비교");
		
		// 필요 변수 값 가져오기
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		
		// DB 선언
		InterestStatementDAO isDAO = sqlSession.getMapper(InterestStatementDAO.class);
		
		// 비교 로직/cheatername/account/phone/cheaterid
		InterestStatementDTO isDTO = isDAO.getInterestStatement(memberno);
		List<InterestStatementDTO> result = isDAO.compareDB(isDTO);
		List<InterestStatementDTO> list = new ArrayList<InterestStatementDTO>();
		
		for(int i = 0; i < result.size(); i++){
			int score = 0;
			if(result.get(i).getCheatername() != null){
				score += 10;
			}
			if(result.get(i).getAccount() != null){
				score += 40;
			}
			if(result.get(i).getPhone() != null){
				score += 30;
			}
			if(result.get(i).getCheaterid() != null){
				score += 20;
			}
			if(score >= 30){
				result.get(i).setScore(score);
				list.add(result.get(i));
			}
		}
		
		Comparator<InterestStatementDTO> comparator = new Comparator<InterestStatementDTO>(){

			@Override
			public int compare(InterestStatementDTO o1, InterestStatementDTO o2) {
				if(o1.getScore() < o2.getScore()) return 1;
				else if(o1.getScore() > o2.getScore()) return -1;
				else return 0;
			}
			
		};
		
		Collections.sort(list, comparator);
		
		model.addAttribute("list", list);
		
		return "";
	}
}
