package controllers;

import java.util.ArrayList;
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

import DAO.BoardFreeDAO;
import DAO.BoardLawDAO;
import DAO.BoardNoticeDAO;
import DAO.InterestStatementDAO;
import DAO.VisitDAO;
import DTO.InterestStatementDTO;
import DTO.MemberDTO;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("index.go")
	public String index(	HttpSession session,
			Model model)throws Exception{
		
		  // 전체 방문자 수 +1
		
		VisitDAO visitDAO = sqlSession.getMapper(VisitDAO.class);
		visitDAO.setVisitTotalCount();
		System.out.println("메세지 수정완료");
     
		
		
      // 오늘 방문자 수
      int todayCount = visitDAO.getVisitTodayCount();
       
      // 전체 방문자 수
      int totalCount = visitDAO.getVisitTotalCount();
      
      System.out.println("todayCount: "+todayCount);
      System.out.println("totalCount: "+totalCount);
      
		model.addAttribute("totalCount", totalCount);// 전체 방문자 수
		model.addAttribute("todayCount", todayCount); // 오늘 방문자 수
		
      
		return "main.index";
	}
	
	@RequestMapping("home.go")
	public String Home(
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
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
				if(result.get(i).getCheatername().equals(isDTO.getCheatername())){
				score += 10;
				}
			}
			if(result.get(i).getAccount() != null){
				if(result.get(i).getAccount().equals(isDTO.getAccount())){
				score += 40;
				}
			}
			if(result.get(i).getPhone() != null){
				if(result.get(i).getPhone().equals(isDTO.getPhone())){
				score += 30;
				}
			}
			if(result.get(i).getCheaterid() != null){
				if(result.get(i).getCheaterid().equals(isDTO.getCheaterid())){
				score += 30;
				}
			}
			if(score >= 30){
				result.get(i).setScore(score);
				list.add(result.get(i));
			}
		}
		
		// 점수가 높은 순으로 정렬하기
		Comparator<InterestStatementDTO> comparator = new Comparator<InterestStatementDTO>(){

			@Override
			public int compare(InterestStatementDTO o1, InterestStatementDTO o2) {
				if(o1.getScore() < o2.getScore()) return 1;
				else if(o1.getScore() > o2.getScore()) return -1;
				else return 0;
			}
		};
		
		Collections.sort(list, comparator);
		// model에 담기
		model.addAttribute("list", list);
		
		//////자유게시판 글 갯수
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		int boardCountF = boardFreeDAO.getCount(field, query);
		System.out.println("boardCountF");
		model.addAttribute("boardCountF", boardCountF);
		
		//////변호사게시판 글 개수
		BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		int boardCountL = boardLawDAO.getCount(field, query);
		System.out.println("boardCountL");
		model.addAttribute("boardCountL", boardCountL);
		
		//// 공지게시판 글 개수
		BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		int boardCountN = boardNoticeDAO.getCount(field, query);
		System.out.println("boardCountN");
		model.addAttribute("boardCountN", boardCountN);
		
		
		
		
		///////////////////차트 / 통계 자료 가져오기
		
		
		
		
		
		
		
		
		
		
		// 로그 남기기
		System.out.println("홈으로 이동");
		
		return "home.home.home";
	}
}
