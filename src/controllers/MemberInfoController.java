package controllers;

import java.io.PrintWriter;
import java.util.List;

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
import DAO.MemberDAO;
import DAO.MemberInfoDAO;
import DTO.BoardFreeDTO;
import DTO.BoardLawDTO;
import DTO.BoardNoticeDTO;
import DTO.MemberDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class MemberInfoController {
	
	PrintWriter out;
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value="memberMessage.go", method=RequestMethod.GET)
	public String message(){//쪽지
		
		//로그 남기기
		System.out.println("내 쪽지 페이지로 이동");
		
		return "memberInfo.memberMessage";
	}
	
	////////////////////////내가 쓴 글 모두 가져오기 START///////////////////////////
	@RequestMapping(value="memberBoard.go", method=RequestMethod.GET)
	public String board(	
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
									HttpSession session,
									Model model,
									ReplyDTO replyDTO
									) throws Exception{

		//Free / Law / Notice 꺼 모두 가져오기_
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		BoardLawDAO boardLawDAO = sqlSession.getMapper(BoardLawDAO.class);
		BoardNoticeDAO boardNoticeDAO = sqlSession.getMapper(BoardNoticeDAO.class);
		
		//로그 남기기
		System.out.println("내 게시물 페이지로 이동");
		
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "adminMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = boardLawDAO.getCount(field, query)
						+ boardNoticeDAO.getCount(field, query)
						+ boardFreeDAO.getCount(field, query)	 ;	// 검색 결과에 따른 게시글 총갯수
		
		int start = (page - 1) * pageSize;
		BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);

		List<BoardLawDTO> listLaw = boardLawDAO.getNotices(start, field, query, pageSize);
		List<BoardFreeDTO> listFree = boardFreeDAO.getNotices(start, field, query, pageSize);
		List<BoardNoticeDTO> listNotice = boardNoticeDAO.getNotices(start, field, query, pageSize);
		
		//뷰단에서 각각에 뿌려주면 된다.
		model.addAttribute("listLaw", listLaw);
		model.addAttribute("listFree", listFree);
		model.addAttribute("listNotice", listNotice);
		
		return "memberInfo.memberBoard";
	}
	////////////////////////내가 쓴 글 모두 가져오기 END///////////////////////////
	
	
	@RequestMapping(value="memberComment.go", method=RequestMethod.GET)
	public String comment(){//댓글
		
		//로그 남기기
		System.out.println("내 댓글 페이지로 이동");

		return "memberInfo.memberComment";
	}
	
	@RequestMapping(value="memberReply.go", method=RequestMethod.GET)
	public String reply(){//답글
		
		//로그 남기기
		System.out.println("내 답글 페이지로 이동");
		
		return "memberInfo.memberReply";
	}
	
	


	
	
	
	@RequestMapping(value="memberConnect.go", method=RequestMethod.GET)
	public String connect(){//접속기록
		
		//로그 남기기
		System.out.println("내 접속기록 페이지로 이동");
		
		return "memberInfo.memberConnect";
	}
	
	@RequestMapping(value="memberModify.go", method=RequestMethod.GET)
	public String modify(){//정보수정
		
		//로그 남기기
		System.out.println("내 정보수정 페이지로 이동");
		
		return "memberInfo.memberModify";
	}
	
	
	// 5-1. 메세지 수정 (실제 처리(update)
	@RequestMapping(value = "memberModify.go", method = RequestMethod.POST)
	public String modify( MemberDTO memberDTO, HttpSession session,
			HttpServletRequest request) throws Exception {
		// 로그 남기기
		System.out.println("게시물 수정 작업 시작");
		// 페이지 이동 변수 선언
		String go = "";
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();

		if (memberno == memberDTO.getMemberno()) {
			MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
			memberInfoDAO.update(memberDTO);
			System.out.println("변호사게시판 수정완료");
			go = "redirect:memberModify.go";
		} else {
			go = "redirect:memberModify.go";
		}
		return go;
	}
	
	
	
	@RequestMapping(value="memberPwdChange.go", method=RequestMethod.GET)
	public String pwdChange(){//비번변경
		
		//로그 남기기
		System.out.println("내 비번변경 페이지로 이동");
		
		return "memberInfo.memberPwdChange";
	}
	
	@RequestMapping(value="memberWithdrawal.go", method=RequestMethod.GET)
	public String updateActive(){//회원탈퇴
		
		//로그 남기기
		System.out.println("내 회원탈퇴 페이지로 이동");
		return "memberInfo.memberWithdrawal";
		
	}
	
	@RequestMapping(value="memberWithdrawal.go", method=RequestMethod.POST)
	public String updateActive(MemberDTO memberDTO, 
			HttpSession session) throws Exception{//실제회원탈퇴
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		String originpwd = ((MemberDTO)session.getAttribute("memberInfo")).getPassword();
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		System.out.println(originpwd);
		System.out.println(memberno);
		
		if(memberDTO.getPassword().equals(originpwd)){
			memberDAO.delete(memberno);
			session.invalidate();
			System.out.println("회원탈퇴 완료");
		}else{
			return "memberInfo.memberWithdrawal";
		}

		
		//로그 남기기
		System.out.println("내 회원탈퇴 페이지로 이동");
		return "home.home.home";	
		
		
	}
}
