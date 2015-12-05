package controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.MemberDAO;
import DTO.BoardFreeDTO;
import DTO.MemberDTO;

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
	
	@RequestMapping(value="memberBoard.go", method=RequestMethod.GET)
	public String board(){//게시물
		
		//로그 남기기
		System.out.println("내 게시물 페이지로 이동");
		
		return "memberInfo.memberBoard";
	}
	
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
	public String updateActive(@RequestParam("mno") int memberno, HttpSession session) throws Exception{//실제회원탈퇴
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO memberDTO = memberDAO.delete(memberno);
		
		//로그 남기기
		System.out.println("내 회원탈퇴 페이지로 이동");
		return "memberInfo.memberWithdrawal";	
		
		
	}
}
