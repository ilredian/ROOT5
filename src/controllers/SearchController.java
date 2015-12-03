package controllers;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
