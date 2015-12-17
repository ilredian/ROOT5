package controllers;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.BoardLawDAO;
import DAO.BoardNoticeDAO;
import DAO.BoardPhotoDAO;
import DAO.CheaterDAO;
import DAO.InterestStatementDAO;
import DAO.MemberDAO;
import DTO.BoardPhotoDTO;
import DTO.CheaterDTO;
import DTO.InterestStatementDTO;
import DTO.MemberDTO;
import DTO.chartDTO;
import DTO.chartItemsDTO;
import mail.SendMail;
import mail.SendMailDTO;

@Controller
public class HomeController {
	PrintWriter out = null;
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("index.go")
	public String index(HttpSession session, Model model) throws Exception {

		////////////////////////////////
		// home.jsp -- AJAX data에 들어갈 차트 정보

		/////////////////////////////
		///////////////////////////
		// 타임라인으로 보낼 정보

		///////////////////////////

		return "main.index";
	}
	
	@RequestMapping(value = "home.go", method = RequestMethod.GET)
	public String Home(
			// 현재 페이지 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, 
			// 검색 카테고리
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, 
			// 검색 내용
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, 
			// 한페이지에 보여줄 게시글 갯수
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, 
			HttpSession session, ServletResponse response, HttpServletRequest request, Model model)
					throws Exception {
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();	

		// 기본 경로 잡기
		String go = "main.index";

		if (session.getAttribute("memberInfo") != null) {
			// 로그 남기기
			System.out.println("관심 지정 진술서 비교");
			
			// DB 선언
			InterestStatementDAO isDAO = sqlSession.getMapper(InterestStatementDAO.class);
			CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
			
			int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();

			//시간 값 설정		
			Date date = new Date();
			int start_year = date.getYear()+1900-5;
			int start_month = date.getMonth()+1;
			int end_year = date.getYear()+1900;
			int end_month = date.getMonth()+1;
			int size = ((end_month+12*(end_year-start_year)) - start_month)+1;
			
			// 피해물품 TOP 10
			// 시간 계산
			List<chartDTO> regdate = new ArrayList<chartDTO>();
			int z=0;
			for(int y=0; y<(size)/12+1; y++){
				for(int i=z; i<size; i++){
					if((end_month+12*(y))-i <= 0){
						z=i;
						break;
					}else{
						chartDTO chartdto = new chartDTO();
						chartdto.setRegdate((end_year-y)+"-"+((end_month+12*(y))-i));
						regdate.add(chartdto);
					}
				}
			}
			//차트에 넣을 값
			List<chartItemsDTO> countItems = new ArrayList<chartItemsDTO>();
			chartItemsDTO chartItemsdto = new chartItemsDTO();
			chartItemsdto.setGoodskind("Task");
			chartItemsdto.setCount("'Hours per Day'");
			countItems.add(chartItemsdto);
			List<chartItemsDTO> countItemsTemp = cheaterDAO.getCountItems(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			for (int i = 0; i < countItemsTemp.size(); i++) {
				countItems.add(countItemsTemp.get(i));
			}
			model.addAttribute("countItems", countItems);
			
			//테이블 표에 넣을 값
			// 용의자
			List<CheaterDTO> countCheaterName = cheaterDAO.getCountCheaterName(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			// 사이트
			List<CheaterDTO> countDomain = cheaterDAO.getCountDomain(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			// 은행
			List<CheaterDTO> countBankName = cheaterDAO.getCountBankName(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			model.addAttribute("countCheaterName", countCheaterName);
			model.addAttribute("countDomain", countDomain);
			model.addAttribute("countBankName", countBankName);
						
			// 비교 로직/cheatername/account/phone/cheaterid
			InterestStatementDTO isDTO = isDAO.getInterestStatement(memberno);
			List<InterestStatementDTO> result = isDAO.compareDB(isDTO);
			List<InterestStatementDTO> list = new ArrayList<InterestStatementDTO>();
			
			for (int i = 0; i < result.size(); i++) {
				int score = 0;
				if (!isDTO.getCheatername().equals("모름") && result.get(i).getCheatername() != null) {
					if (result.get(i).getCheatername().equals(isDTO.getCheatername())) {
						score += 20;
					}
				}
				if (!isDTO.getCheatername().equals("모름") && result.get(i).getAccount() != null) {
					if (result.get(i).getAccount().equals(isDTO.getAccount())) {
						score += 40;
					}
				}
				if (!isDTO.getCheatername().equals("010-0000-0000") && result.get(i).getPhone() != null) {
					if (result.get(i).getPhone().equals(isDTO.getPhone())) {
						score += 20;
					}
				}
				if (!isDTO.getCheatername().equals("") && result.get(i).getCheaterid() != null) {
					if (result.get(i).getCheaterid().equals(isDTO.getCheaterid())) {
						score += 20;
					}
				}
				if (score >= 40) {
					result.get(i).setScore(score);
					list.add(result.get(i));
				}
			}

			// 점수가 높은 순으로 정렬하기
			Comparator<InterestStatementDTO> comparator = new Comparator<InterestStatementDTO>() {

				@Override
				public int compare(InterestStatementDTO o1, InterestStatementDTO o2) {
					if (o1.getScore() < o2.getScore())
						return 1;
					else if (o1.getScore() > o2.getScore())
						return -1;
					else
						return 0;
				}
			};
			Collections.sort(list, comparator);
			
			//멤버 이름 설정
			MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
			
			for(int i=0; i<list.size(); i++){
				MemberDTO memberDTO = new MemberDTO();
				memberDTO= memberDAO.getMemberStat(list.get(i).getMemberno());
				list.get(i).setMembername(memberDTO.getName());
			}
			
			// model에 담기
			model.addAttribute("list", list);

			////// 자유게시판 글 갯수
			BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
			int boardCountF = boardFreeDAO.getCount(field, query);
			System.out.println("boardCountF");
			model.addAttribute("boardCountF", boardCountF);

			////// 변호사게시판 글 개수
			BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
			int boardCountL = boardLawDAO.getCount(field, query);
			System.out.println("boardCountL");
			model.addAttribute("boardCountL", boardCountL);

			//// 공지게시판 글 개수
			BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
			int boardCountN = boardNoticeDAO.getCount(field, query);
			System.out.println("boardCountN");
			model.addAttribute("boardCountN", boardCountN);
			
			////사진게시판 글 개수
            BoardPhotoDAO boardPhotoDAO = sqlSession.getMapper(BoardPhotoDAO.class);
            int boardCount = boardPhotoDAO.BoardPhotoCount();// 검색 결과에 따른 게시글 총
            System.out.println("boardCountP");
            model.addAttribute("boardCountP", boardCount);
            
    		
			///추가 __ 사진게시판이 들어갈 사진이미지 정보_
			List<BoardPhotoDTO> photolist = boardPhotoDAO.BoardPhotoList(0, 10);
			System.out.println("photolist");
			model.addAttribute("photolist", photolist);
			
            
			
			//// 관심등록정보 있는지 확인
			int regResult = isDAO.getResist(memberno);
			//있으면 타임라인 값 가져오기
			if(regResult != 0){
				
				InterestStatementDTO isResultDTO = isDAO.getInterestStatement(memberno);
				CheaterDTO cheaterResultDTO = cheaterDAO.getCheater(isResultDTO.getStateno());
				
				if(cheaterResultDTO.getPolice() != 0){
				
					//검거완료
					model.addAttribute("complete", cheaterResultDTO.getComplete());
					
					//추적중
					model.addAttribute("trace", cheaterResultDTO.getTrace());
					
					//의뢰접수
					//접수 정보 가져오기 전 경찰 정보 불러오기
					MemberDTO memberDTO = memberDAO.getMemberStat(cheaterResultDTO.getPolice());
					String comment = "'"+cheaterResultDTO.getRegpolice()+"' " + memberDTO.getCompany() + " 담당 "+memberDTO.getName()+"형사 ("+memberDTO.getPhone()+")";
					model.addAttribute("policeCompany", memberDTO.getCompany());
					model.addAttribute("policeName", comment);
				}
				
				//접수대기날짜계산
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
				String timeLineEnd = sf.format(date);
				String timeLineStart = sf.format(cheaterResultDTO.getRegdate());
				long timeLinedate = (sf.parse(timeLineEnd).getTime()-sf.parse(timeLineStart).getTime())/(24*60*60*1000);
				model.addAttribute("timeLinedate", timeLinedate);
				
				//접수했다면 접수 날짜도 표시
				if(cheaterResultDTO.getRegpolice() != null){
					String timeLineRegpoliceEnd = sf.format(date);
					String[] timeLineRegpoliceStart = cheaterResultDTO.getRegpolice().split(" ");
					long timeLineRegpoliceDate = (sf.parse(timeLineRegpoliceEnd).getTime()-sf.parse(timeLineRegpoliceStart[0].replaceAll("-", "")).getTime())/(24*60*60*1000);
					model.addAttribute("timeLineRegpoliceDate", timeLineRegpoliceDate);
				}
				
				//진술서 등록 날짜
				model.addAttribute("regdate", cheaterResultDTO.getRegdate());
			}
			go = "home.home.home";
			return go;
		}else{
			out.print("<script type='text/javascript'>alert('로그인을 하셔야합니다.');location.replace('index.go');</script>");
			out.close();
		}
		return go;
	}
}