package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.BoardLawDAO;
import DAO.BoardNoticeDAO;
import DAO.ReplyDAO;
import DTO.BoardFreeDTO;
import DTO.BoardNoticeDTO;

import DTO.BoardLawDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import common.ReplyPager;

@Controller
public class BoardController {
	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;
	@Autowired
	private SqlSession sqlSession;
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	//////////// 리플 ////////////////
	@RequestMapping(value="reply.go", method = RequestMethod.POST)
	public String reply(@RequestParam("pg") int page,
						@RequestParam("bno") int boardno,
						@RequestParam("cno") int categoryno,
						HttpSession session,
						ReplyDTO replyDTO
						) throws Exception{
		// 로그남기기
		System.out.println("리플 등록 시작");
		
		// 페이지 이동을 위한 변수 선언
		String go = "";
		
		// 리플 DB 넘기기
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		replyDTO.setName(((MemberDTO)session.getAttribute("memberInfo")).getName());
		replyDTO.setBoardno(boardno);
		replyDTO.setMemberno(((MemberDTO)session.getAttribute("memberInfo")).getMemberno());
		replyDTO.setCategoryno(categoryno);
		int result = replyDAO.insertReply(replyDTO);
		
		if(result>0){
			System.out.println("리플 등록 완료");
		}else{
			System.out.println("리플 등록 실패");
		}
		
		// 페이지 이동 스위치 문
		switch(categoryno){
			case 1 :
				go="redirect:freeView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 2 :
				go="redirect:noticeView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 3 :
				go="redirect:photoView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 4 :
				go="redirect:lawView.go?pg="+page+"&bno="+boardno;
			break;
		}
		
		return go;
	}

	@RequestMapping("updateReply.go")
	public String updateReply(
			@RequestParam("pg") int page,
			@RequestParam("bno") int boardno,
			@RequestParam("cno") int categoryno,
			@RequestParam("rno") int replyno,
			ReplyDTO replyDTO
			) throws Exception{
		// 로그남기기
		System.out.println("리플 수정 시작");
				
		// 페이지 이동을 위한 변수 선언
		String go = "";
		
		// 리플 DB 넘기기
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		replyDTO.setReplyno(replyno);
		int result = replyDAO.updateReply(replyDTO);
		
		if(result>0){
			System.out.println("리플 수정 성공");
		}else{
			System.out.println("리플 수정 실패");
		}
		
		// 페이지 이동 스위치 문
		switch(categoryno){
			case 1 :
				go="redirect:freeView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 2 :
				go="redirect:noticeView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 3 :
				go="redirect:photoView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 4 :
				go="redirect:lawView.go?pg="+page+"&bno="+boardno;
			break;
		}
		
		System.out.println("리플 수정 완료");
				
		return go;
	}
	
	@RequestMapping("updateReplyActive.go")
	public String updateReplyActive(@RequestParam("pg") int page,
									@RequestParam("bno") int boardno,
									@RequestParam("cno") int categoryno,
									@RequestParam("rno") int replyno,
									HttpSession session,
									ReplyDTO replyDTO
									) throws Exception{
		// 로그남기기
		System.out.println("리플 삭제 (active 값 변경) 시작");
		
		// 페이지 이동을 위한 변수 선언
		String go = "";
				
		// 리플 DB 넘기기
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		replyDAO.updateReplyActive(replyno);
		
		// 페이지 이동 스위치 문
		switch(categoryno){
			case 1 :
				go="redirect:freeView.go?pg="+page+"&bno="+boardno;
			break;
			
			case 2 :
				go="redirect:noticeView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 3 :
				go="redirect:photoView.go?pg="+page+"&bno="+boardno;
			break;
					
			case 4 :
				go="redirect:lawView.go?pg="+page+"&bno="+boardno;
			break;
		}
		
		System.out.println("리플 삭제(active값 변경) 성공");
		
		return go;
	}
	
	
}//End BoardController
