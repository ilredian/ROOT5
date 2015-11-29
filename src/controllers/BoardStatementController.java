package controllers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import DAO.CheaterDAO;
import DTO.CheaterDTO;

//피해 사례 현황 게시판
@Controller
public class BoardStatementController {

	@Autowired
	private SqlSession sqlSession;

	// 피해 사례 페이지 이동
	@RequestMapping("statementMain.go")
	public String gameMain(String pg, String f, String q, String cno, Model model) throws Exception {

		// 페이지 이동 경로 변수 선언
		String go = "";

		// 게시판 기본 설정(기본값)
		int page = 1; // 페이지 번호
		String field = "cheatername"; // 검색 카테고리
		String query = "%%"; // 검색 내용
		int cheatno = 1; // 사기종류 카테고리 번호

		// 페이지에서 불러온 값이 있다면 변경
		if (pg != null && pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		if (f != null && f.equals("")) {
			field = f;
		}
		if (q != null && q.equals("")) {
			query = q;
		}
		if (cno != null && cno.equals("")) {
			cheatno = Integer.parseInt(cno);
		}

		// 로그 남기기
		System.out.println(page + " / " + field + " / " + query + " / " + cheatno);
/*
		// 마이바티스로 넘기기
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		List<CheaterDTO> list = cheaterDAO.getSearchCheater(page, field, query, cheatno);

		// 모델에 담기
		model.addAttribute("list", list);
*/
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

		case 3:// 사기종류 카테고리번호가 3일 경우 스팸 피해 사례 페이지로 이동
			System.out.println("스팸 피해 사례 페이지 이동");
			go = "home.boardStatement.spamMain";
			break;

		case 4:// 사기종류 카테고리번호가 4일 경우 비매너 피해 사례 페이지로 이동
			System.out.println("비매너 피해 사례 페이지 이동");
			go = "home.boardStatement.mannerMain";
			break;
		}

		return go;
	}

	// 게임 피해 사례 상세 내용
	@RequestMapping("statementView.go")
	public String gameView(String sno, String cno, Model model) throws Exception {

		// 페이지 이동 경로 변수 선언
		String go = "";

		// 진술서 번호 int로 변환
		int stateno = Integer.parseInt(sno);
		int cheatno = Integer.parseInt(cno);
/*
		// 마이바티스로 넘기기
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		CheaterDTO cheaterDTO = cheaterDAO.getCheater(stateno);

		// 모델에 담기
		model.addAttribute("cheaterDTO", cheaterDTO);
*/
		// 페이지 이동 구분하기
		switch (cheatno) {
		
		case 1:// 사기종류 카테고리번호가 1일 경우 직거래 피해 사례 상세 내용 이동
			go = "home.boardStatement.tradeView";
			break;

		case 2:// 사기종류 카테고리번호가 2일 경우 게임 피해 사례 상세 내용 이동
			go = "home.boardStatement.gameView";
			break;

		case 3:// 사기종류 카테고리번호가 3일 경우 스팸 피해 사례 상세 내용 이동
			go = "home.boardStatement.spamView";
			break;

		case 4:// 사기종류 카테고리번호가 4일 경우 비매너 피해 사례 상세 내용 이동
			go = "home.boardStatement.mannerView";
			break;
		}

		return go;
	}
}
