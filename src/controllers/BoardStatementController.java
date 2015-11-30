package controllers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CheaterDAO;
import DTO.CheaterDTO;
import common.Pager;

//피해 사례 현황 게시판
@Controller
public class BoardStatementController {

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
      System.out.println(page + " / " + field + " / " + query + " / " + cheatno);

      // 마이바티스로 넘기기
      CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
      
      int pagerSize = 10;//한 번에 보여줄 페이지 번호 갯수
      String linkUrl = "statementMain.go";//페이지번호를 누르면 이동할 경로
      int boardCount = cheaterDAO.getCheaterCount(field, query, cheatno);
      System.out.println("게시글 갯수 : " + boardCount);
      int start = (page-1)*pageSize;
      int end = pageSize+(page-1)*pageSize;
      
      Pager pager = new Pager(boardCount, page, pageSize, pagerSize, linkUrl, cheatno);
      
      List<CheaterDTO> list = cheaterDAO.getSearchCheater(start, field, query, cheatno, end);

      String thePager = pager.toString();
      System.out.println(thePager);
      // 모델에 담기
      model.addAttribute("pager", pager);
      model.addAttribute("list", list);

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
   public String gameView(//get으로 들어오는 parameter값 선언 및 기본값 설정
                     @RequestParam(value="sno",required =false, defaultValue="1") int stateno, 
                     @RequestParam(value="cno",required =false, defaultValue="1") int cheatno, Model model) throws Exception {

      // 페이지 이동 경로 변수 선언
      String go = "";
      
      // 로그 남기기
      System.out.println(stateno + " / " + cheatno);
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
         System.out.println("직거래 피해 사례 상세보기 이동");
         go = "home.boardStatement.tradeView";
         break;

      case 2:// 사기종류 카테고리번호가 2일 경우 게임 피해 사례 상세 내용 이동
         System.out.println("게임 피해 사례 상세보기 이동");
         go = "home.boardStatement.gameView";
         break;

      case 3:// 사기종류 카테고리번호가 3일 경우 스팸 피해 사례 상세 내용 이동
         System.out.println("스팸 피해 사례 상세보기 이동");
         go = "home.boardStatement.spamView";
         break;

      case 4:// 사기종류 카테고리번호가 4일 경우 비매너 피해 사례 상세 내용 이동
         System.out.println("비매너 피해 사례 상세보기 이동");
         go = "home.boardStatement.mannerView";
         break;
      }

      return go;
   }
}