package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.ReportBoardDAO;
import DTO.BoardFreeDTO;
import DTO.ReportTempDTO;
import common.BoardPager;

@Controller
public class AdminController {// 관리자 페이지

	// 자바스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "adhome.go", method = RequestMethod.GET)
	public String AdminHome() {

		//로그 남기기
		System.out.println("관리자 페이지 홈으로 이동");
		
		return "admin.adminHome";
	}
	
	@RequestMapping(value = "free.go", method = RequestMethod.GET)
	public String AdminFree(@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
			@RequestParam("cno") int categoryno,
			Model model) throws Exception {
		
		// 로그 남기기
		System.out.println("신고 게시판");
		System.out.println(page + " / " + field + " / " + query);

		// 맵퍼 설정하여 DAO 변수 선언
		ReportBoardDAO reportBoardDAO = sqlSession.getMapper(ReportBoardDAO.class);

		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "reportBoardMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = reportBoardDAO.getReportBoardCount(field, query, categoryno);// 검색 결과에 따른 게시글 총 갯수
		int start = (page - 1) * pageSize;

		BoardPager pager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
		} else {// 검색 값이 없을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		}

		// 게시물 불러오기
		List<ReportTempDTO> boardnotemp = reportBoardDAO.getReportBoardno(start, categoryno, pageSize);
		List<BoardFreeDTO> list = new ArrayList<BoardFreeDTO>();
		for(int i=0; i < boardnotemp.size(); i++){
			list.add(reportBoardDAO.getReportBoard(boardnotemp.get(i).getBoardno()));
		}

		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("신고된 자유게시판 메인 출력 완료");

		return "admin.adminFree";
	}
	
	@RequestMapping(value = "poto.go", method = RequestMethod.GET)
	public String AdminPoto() {

		return "admin.adminPoto";
	}
	@RequestMapping(value = "commment.go", method = RequestMethod.GET)
	public String AdminCommment() {

		return "admin.adminComment";
	}
	@RequestMapping(value = "site.go", method = RequestMethod.GET)
	public String AdminSite() {

		return "admin.adminSite";
	}
	@RequestMapping(value = "deallist.go", method = RequestMethod.GET)
	public String AdminDealList() {

		return "admin.adminDealList";
	}
	@RequestMapping(value = "bank.go", method = RequestMethod.GET)
	public String AdminBank() {

		return "admin.adminBank";
	}
	@RequestMapping(value = "join.go", method = RequestMethod.GET)
	public String AdminJoin() {

		return "admin.adminJoin";
	}
	@RequestMapping(value = "fake.go", method = RequestMethod.GET)
	public String AdminFake() {

		return "admin.adminfake";
	}
	@RequestMapping(value = "memberagent.go", method = RequestMethod.GET)
	public String AdminMemberagent() {

		return "admin.adminMemberagent";
	}
	@RequestMapping(value = "catrgory.go", method = RequestMethod.GET)
	public String AdminCatrgory() {

		return "admin.adminCategory";
	}
	@RequestMapping(value = "mail.go", method = RequestMethod.GET)
	public String AdminMail() {

		return "admin.adminMail";
	}

}
