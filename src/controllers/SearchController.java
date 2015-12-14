package controllers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CheaterDAO;
import DAO.QueryDAO;
import DTO.CheaterDTO;

@Controller
public class SearchController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("boardFreeSearch.go")
	public String boardFreeSearch(
			@RequestParam("field") String field,
			@RequestParam("query") String query
			){//자유게시판 검색	
		//로그 남기기
		System.out.println("자유 게시판 검색");
		
		//경로 설정 변수 선언
		String go = "";
		
		//검색 URI 설정
		go = "redirect:freeMain.go?pg=1&f="+field+"&q="+query;
		
		return go;
	}
	
	@RequestMapping("boardNoticeSearch.go")
	public String boardNoticeSearch(
			@RequestParam("field") String field,
			@RequestParam("query") String query
			){//공지사항 게시판 검색
		//로그 남기기
		System.out.println("공지사항 게시판 검색");
		
		//경로 설정 변수 선언
		String go = "";
		
		//검색 URI 설정
		go = "redirect:noticeMain.go?pg=1&f="+field+"&q="+query;
		
		return go;
	}
	
	@RequestMapping("totalSearch.go")
	public String totalSearch(
			@RequestParam(value = "f", required = false, defaultValue = "cheatername") String field,
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query,
			Model model) throws Exception{
		//로그 남기기
		System.out.println("모든 진술서 검색");
		System.out.println("검색 필드 및 쿼리 값 : "+field+ " / " + query);
		
		//DAO 변수 선언
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		
		//검색 query 값 db에 저장 
		QueryDAO queryDAO = sqlSession.getMapper(QueryDAO.class);
		int result = queryDAO.getQuery(query, 1);
		if(result == 1){
			queryDAO.updateQuery(query, 1);
		}else{
			queryDAO.insertQuery(query, 1);
		}
		
		//직거래///////
		int cheatno = 1;
		int tradeboardCount = cheaterDAO.getCheaterCount(field, query, cheatno);
		
		//DB 처리
		List<CheaterDTO> tradelist = cheaterDAO.getSearchCheater(0, field, query, cheatno, 10);
		
		model.addAttribute("tradeboardCount", tradeboardCount);
		model.addAttribute("tradelist", tradelist);
		
		//게임///////
		cheatno = 2;
		int gameboardCount = cheaterDAO.getCheaterCount(field, query, cheatno);
		
		//DB 처리
		List<CheaterDTO> gamelist = cheaterDAO.getSearchCheater(0, field, query, cheatno, 10);
				
		model.addAttribute("gameboardCount", gameboardCount);
		model.addAttribute("gamelist", gamelist);
				
		//비매너 페이징 처리///////
		int mannerboardCount = cheaterDAO.getCheaterCount(field, query, cheatno);
		
		//DB 처리
		List<CheaterDTO> mannerlist = cheaterDAO.getSearchCheater(0, field, query, cheatno, 10);
				
		model.addAttribute("mannerboardCount", mannerboardCount);
		model.addAttribute("mannerlist", mannerlist);
		
		return "search.totalStatement";
	}
}
