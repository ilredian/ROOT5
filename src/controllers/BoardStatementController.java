package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CheatItemsDAO;
import DAO.CheaterDAO;
import DAO.MemberDAO;
import DTO.CheatItemsDTO;
import DTO.CheaterDTO;
import DTO.MemberDTO;
import common.StatementPager;

//피해 사례 현황 게시판
@Controller
public class BoardStatementController {

	// 자바스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;

	// 피해 사례 페이지 이동
	@RequestMapping("statementMain.go")
	public String gameMain(//get으로 들어오는 parameter값 선언 및 기본값 설정
							@RequestParam(value="pg",required =false, defaultValue="1") int page, // 현재 페이지 번호
							@RequestParam(value="f",required =false, defaultValue="cheatername") String field, // 검색 카테고리
							@RequestParam(value="q",required =false, defaultValue="%%") String query, // 검색 내용
							@RequestParam(value="cno",required =false, defaultValue="1") int cheatno, // 사기종류 카테고리 번호
							@RequestParam(value="ps",required =false, defaultValue="10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
							Model model
							) throws Exception {

		// 페이지 이동 경로 변수 선언
		String go = "";

		// 로그 남기기
		System.out.println(page + " / " + field + " / " + query + " / " + cheatno + " / " + pageSize);

		// 마이바티스로 넘기기
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "statementMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = cheaterDAO.getCheaterCount(field, query, cheatno);// 검색 결과에 따른 게시글 총 갯수
		int start = (page - 1) * pageSize;
		StatementPager pager = new StatementPager(boardCount, page, pageSize, pagerSize, linkUrl, cheatno);
		List<CheaterDTO> list = cheaterDAO.getSearchCheater(start, field, query, cheatno, pageSize);
		
		// 모델에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("list", list);
		model.addAttribute("boardCount", boardCount);

		// 페이지 이동 구분하기
		switch (cheatno) {
		
		case 1:// 사기종류 카테고리번호가 1일 경우 직거래 피해 사례 페이지로 이동
			System.out.println("직거래 피해 사례 페이지 이동");
			go = "home.boardStatement.tradeMain";
			break;

		case 2:// 사기종류 카테고리번호가 2일 경우 게임 피해 사례 페이지로 이동
			System.out.println("게임 피해 사례 페이지 이동");
			go = "home.boardStatement.gameMain";
			break;

		case 3:// 사기종류 카테고리번호가 4일 경우 비매너 피해 사례 페이지로 이동
			System.out.println("비매너 피해 사례 페이지 이동");
			go = "home.boardStatement.mannerMain";
			break;
		}
		return go;
	}

	// 피해 사례 상세 내용
	@RequestMapping("statementView.go")
	public String gameView(//get으로 들어오는 parameter값 선언 및 기본값 설정
			@RequestParam(value="pg",required =false, defaultValue="1") int page, // 현재 페이지 번호
			@RequestParam(value="f",required =false, defaultValue="cheatername") String field, // 검색 카테고리
			@RequestParam(value="q",required =false, defaultValue="%%") String query, // 검색 내용
			@RequestParam(value="ps",required =false, defaultValue="10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
							@RequestParam(value="sno",required =false, defaultValue="1") int stateno, 
							@RequestParam(value="cno",required =false, defaultValue="1") int cheatno, Model model) throws Exception {

		// 페이지 이동 경로 변수 선언
		String go = "";
		
		// 로그 남기기
		System.out.println(stateno + " / " + cheatno);
		
		// 상세 페이지 밑에 있는 목록에 뿌려줄 정보 가져오기
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "statementMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = cheaterDAO.getCheaterCount(field, query, cheatno);// 검색 결과에 따른 게시글 총 갯수
		int start = (page - 1) * pageSize;
		StatementPager pager = new StatementPager(boardCount, page, pageSize, pagerSize, linkUrl, cheatno);
		List<CheaterDTO> list = cheaterDAO.getSearchCheater(start, field, query, cheatno, pageSize);
				
		// 모델에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("list", list);
		model.addAttribute("boardCount", boardCount);

		// 해당 진술서 정보 및 그 진술서를 쓴 회원 정보 불러오기
		CheaterDTO cheaterDTO = cheaterDAO.getCheater(stateno);
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO memberDTO = memberDAO.getMemberStat(cheaterDTO.getMemberno());
		CheatItemsDAO cheatItemsDAO = sqlSession.getMapper(CheatItemsDAO.class);
		CheatItemsDTO cheatItemsDTO = cheatItemsDAO.getItem(cheaterDTO.getGoodskind(), cheaterDTO.getGoodsname());
		
		// 진술서 내용 중 가격 , 작업
		String deposit = String.format("%,d", cheaterDTO.getDeposit());
		
		// 모델에 담기
		model.addAttribute("cheaterDTO", cheaterDTO);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("cheatItemsDTO", cheatItemsDTO);
		model.addAttribute("deposit", deposit);

		// 페이지 이동 구분하기
		switch (cheatno) {
		
		case 1:// 사기종류 카테고리번호가 1일 경우 직거래 피해 사례 상세 내용 이동
			System.out.println("직거래 피해 사례 상세보기 이동");
			go = "home.boardStatement.tradeView";
			break;

		case 2:// 사기종류 카테고리번호가 2일 경우 게임 피해 사례 상세 내용 이동
			System.out.println("게임 피해 사례 상세보기 이동");
			go = "home.boardStatement.gameView";
			break;

		case 3:// 사기종류 카테고리번호가 3일 경우 비매너 피해 사례 상세 내용 이동
			System.out.println("비매너 피해 사례 상세보기 이동");
			go = "home.boardStatement.mannerView";
			break;
		}
		return go;
	}
	
	@RequestMapping("regStatementPolice.go")
	public void regStatementPolice(
			@RequestParam("stateno") int stateno,
			HttpServletResponse response,
			HttpSession session
			) throws Exception{
		
		//로그 남기기
		System.out.println("진술서 접수 처리");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//DAO 선언
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		
		//경찰 회원인지 확인
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		int typeno = ((MemberDTO)session.getAttribute("memberInfo")).getTypeno();
		
		if(typeno == 2){
			
			// 사건과 유사한 사건정보 모두 불러오기
			CheaterDTO cheaterDTO = cheaterDAO.getCheater(stateno);
			List<CheaterDTO> result = cheaterDAO.getStatenoCompleteMatch(cheaterDTO);
			List<CheaterDTO> list = new ArrayList<CheaterDTO>();

			// 주요 항목 점수 측정
			for (int i = 0; i < result.size(); i++) {
				int score = 0;
				if (result.get(i).getCheatername() != null) {
					if (result.get(i).getCheatername().equals(cheaterDTO.getCheatername())) {
						score += 20;
					}
				}
				if (result.get(i).getAccount() != null) {
					if (result.get(i).getAccount().equals(cheaterDTO.getAccount())) {
						score += 40;
					}
				}
				if (result.get(i).getPhone() != null) {
					if (result.get(i).getPhone().equals(cheaterDTO.getPhone())) {
						score += 20;
					}
				}
				if (result.get(i).getCheaterid() != null) {
					if (result.get(i).getCheaterid().equals(cheaterDTO.getCheaterid())) {
						score += 20;
					}
				}
				
				//60% 이상 일치하면 리스트에 담기
				if (score >= 60) {
					list.add(result.get(i));
				}
			}
			
			for(int i=0; i<list.size(); i++){
				cheaterDAO.policeUpdateMember(memberno, stateno, list.get(i).getStateno());
			}
			
			out.print("<script>alert('성공적으로 접수 처리 되었습니다.');location.replace('statementMain.go');</script>");
		}else{
			out.print("<script>alert('경찰 회원이 아닙니다.');location.replace('statementMain.go');</script>");
		}
		out.close();
	}
}