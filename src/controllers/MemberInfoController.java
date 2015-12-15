package controllers;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardFreeDAO;
import DAO.BoardLawDAO;
import DAO.BoardNoticeDAO;
import DAO.CheatBankDAO;
import DAO.CheatDomainDAO;
import DAO.CheatItemsDAO;
import DAO.CheaterDAO;
import DAO.InterestStatementDAO;
import DAO.MemberDAO;
import DAO.MemberInfoDAO;
import DAO.QueryDAO;
import DTO.BoardFreeDTO;
import DTO.BoardLawDTO;
import DTO.BoardNoticeDTO;
import DTO.CheaterDTO;
import DTO.InterestStatementDTO;
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
	
		MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
		
		//로그 남기기
		System.out.println("내 게시물 페이지로 이동");
		
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "adminMain.go";// 페이지번호를 누르면 이동할 경로
		
		//회원 번호 가져오기
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();

		
		//게시물 갯수 가져오기
		int boardCountLaw = memberInfoDAO.getFreeCount(memberno);	// 검색 결과에 따른 게시글 총갯수
		int boardCountFree=  memberInfoDAO.getLawCount(memberno);
		int boardCount = boardCountFree + boardCountLaw;
		
		model.addAttribute("boardCountLaw", boardCountLaw);
		model.addAttribute("boardCountFree", boardCountFree);
		
		int start = (page - 1) * pageSize;
		BoardPager pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);

		
		//게시물 가져오기
		List<BoardLawDTO> listLaw = memberInfoDAO.getLawNotice(memberno);
		List<BoardFreeDTO> listFree = memberInfoDAO.getFreeNotice(memberno);
		
		//단, 내글만 가져오게해야한다_ 접속한 로그인값을 비교해서, 내 글이 아니면 가져오지 않게하기
		//xml에서 설정하자
		
		//뷰단에서 각각에 뿌려주면 된다.
		model.addAttribute("listLaw", listLaw);
		model.addAttribute("listFree", listFree);
		
		model.addAttribute("pgLaw", 1);
		model.addAttribute("pgFree", 1);
		
		return "memberInfo.memberBoard";
	}
	////////////////////////내가 쓴 글 모두 가져오기 END///////////////////////////
	
	
	/////////////////////댓글
	@RequestMapping(value="memberComment.go", method=RequestMethod.GET)
	public String comment(
			MemberDTO memberDTO,
			HttpSession session,
			HttpServletRequest request,
			Model model) throws Exception{//댓글
		System.out.println("내 댓글 페이지로 이동");
		//기존 메세지 가져오기
	/*	String message = ((MemberDTO) session.getAttribute("memberInfo")).getMessage();
*/		/*System.out.println("message : " + message);
		model.addAttribute("message",message);
		*/
/*		ReplyDTO replyDTO = sqlSession.getMapper(ReplyDTO.class);
		MemberDTO memberDTO = sqlSession.getMapper(MemberDTO.class);
		model.addAttribute("replyDTO",replyDTO);
		model.addAttribute("memberDTO",memberDTO);*/
		
		//로그인한 세션에 있는 회원번호 가져오기_
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		
		System.out.println("memberno : " + memberno);
		model.addAttribute("memberno",memberno);
		
		//매퍼에 있는 함수사용
		MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
		List<ReplyDTO> list = memberInfoDAO.getAllReply(memberno);
		System.out.println("내 댓글 가져오기 성공");
		System.out.println(list.toString());
		model.addAttribute("list", list);
		return "memberInfo.memberComment";
	}
	
	
	@RequestMapping(value="memberConnect.go", method=RequestMethod.GET)
	public String connect(){//접속기록
		
		//로그 남기기
		System.out.println("내 접속기록 페이지로 이동");
		
		return "memberInfo.memberConnect";}
	
	
	
	
	////////////// 메세지수정 ! (정보 가져오기.)
	@RequestMapping(value="memberModify.go", method=RequestMethod.GET)
	public String modify(
			HttpSession session,
			HttpServletRequest request,
			MemberDTO memberDTO,
			Model model){//정보수정
		System.out.println("내 정보수정 페이지로 이동");

		//기존 메세지 가져오기
		String message = ((MemberDTO) session.getAttribute("memberInfo")).getMessage();
		System.out.println("message : " + message);
		model.addAttribute("message",message);

		//기존 사진 가져오기
		String photo = ((MemberDTO) session.getAttribute("memberInfo")).getPhoto();
		System.out.println("photo : " + photo);
		model.addAttribute("photo",photo);
		
		return "memberInfo.memberModify";
	}
	
	// 5-1. 메세지 수정 (실제 처리(update)
	@RequestMapping(value = "memberModify.go", method = RequestMethod.POST)
	public String modify( 
			MemberDTO memberDTO, 
			HttpSession session,
			HttpServletRequest request
			) throws Exception {
		// 로그 남기기
		System.out.println(memberDTO.getMessage());
		
		//현재 로그인한 세션의 회원번호를 가져와 memberDTO안에 넣어준다.
		memberDTO.setMemberno(((MemberDTO)session.getAttribute("memberInfo")).getMemberno());
		System.out.println("회원 정보 수정 작업 시작");
		
		//매퍼를 가져와, update 함수를 가져온다.
		MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
		memberInfoDAO.update(memberDTO);
		System.out.println("회원 정보 수정완료");
		
		//update가 완료돠면, MemberDTO에 저장된 정보를 가져와서  sessionDTO에 넣어준다.
		MemberDTO sessionDTO=((MemberDTO)session.getAttribute("memberInfo"));
		sessionDTO.setMessage(memberDTO.getMessage());
		sessionDTO.setPhoto(memberDTO.getPhoto());
		
		//memberInfo 속성에 메시지정보가 있는 sessionDTO를 넣어준다.
		session.setAttribute("memberInfo", sessionDTO);
			
		return "memberInfo.memberModify";
	}
	
	@RequestMapping(value="memberPwdChange.go", method=RequestMethod.GET)
	public String pwdChangebefore(MemberDTO memberDTO, 
			HttpSession session,
			HttpServletRequest request, 
			Model model) throws ParseException{//비번변경
	
		// 페이지 이동 변수 선언
		//로그 남기기
		System.out.println("내 비번변경 페이지로 이동");

		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		System.out.println("memberno : " + memberno);
		String Password = ((MemberDTO) session.getAttribute("memberInfo")).getPassword();
		System.out.println("Password: " + Password);
		String regdate = ((MemberDTO) session.getAttribute("memberInfo")).getRegdate();
		System.out.println("regdate : " + regdate);
		String regpwd = ((MemberDTO) session.getAttribute("memberInfo")).getRegpwd();
		
		//regpwd 에 등록했던 날짜_
		model.addAttribute("regpwd",regpwd);

		Date date = new Date();
		date.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("현재날짜 : " + sdf.format(date));
		//현재 날짜 - 등록 날짜 (Day 일수)
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date beginDate = formatter.parse(regpwd);
	        Date endDate = date;
	        
	        long diff = endDate.getTime() - beginDate.getTime();
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	 
	        System.out.println("날짜차이=" + diffDays);
	        model.addAttribute("diffDay", diffDays);
	        
		return "memberInfo.memberPwdChange";
	}
	
	@RequestMapping(value="memberPwdChange.go", method=RequestMethod.POST)
	public String pwdChangeafter(
			@ModelAttribute MemberDTO memberDTO, 
			HttpSession session,
			HttpServletRequest request, 
			Model model) throws Exception{//비번변경
		//비밀번호를 변경하려면
		//원래 비밀번호값과 같은지 비교하고 맞다면
		//변경할 비밀번호를 업데이트한다.
		System.out.println("비밀번호 변경시작");
		String oldpwd = ((MemberDTO) session.getAttribute("memberInfo")).getPassword();
		System.out.println("원래저장된 pwd : "+  oldpwd );
	
		
		String Newpwd = memberDTO.getNpassword();
		System.out.println("바뀐 pwd : "+  Newpwd );
		
		//멤버 번호를 집어넣기
		memberDTO.setMemberno(((MemberDTO)session.getAttribute("memberInfo")).getMemberno());
		//	public MemberDTO changepassword(MemberDTO memberDTO) throws Exception;

		if((memberDTO.getPassword()).equals(oldpwd)){
			MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
			System.out.println("비밀번호 변경완료");
			memberInfoDAO.changepassword(memberDTO);
		}else{
			System.out.println("비밀번호가 일치하지 않습니다");
		}
		//update가 완료돠면, MemberDTO에 저장된 메시지 정보를 가져와서  sessionDTO에 넣어준다.
		MemberDTO sessionDTO=((MemberDTO)session.getAttribute("memberInfo"));
		sessionDTO.setNpassword(memberDTO.getNpassword());
		System.out.println("여기까지?");
		//memberInfo 속성에 메시지정보가 있는 sessionDTO를 넣어준다.
		session.setAttribute("memberInfo", sessionDTO);
		
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

	// 관심등록 진술서와 자신이 쓴 피해 사례 진술서 내역으로 이동
	@RequestMapping(value="memberStatement.go", method=RequestMethod.GET)
	public String memberStatement(
			@RequestParam(value = "f", required = false, defaultValue = "cheatername") String field,
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query,
			MemberDTO memberDTO,
			HttpSession session,
			Model model) throws Exception{
		
		// 로그 남기기
		System.out.println("진술서 게시판 이동");
		
		//DAO 변수 선언
		MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
		InterestStatementDAO interestStatementDAO = sqlSession.getMapper(InterestStatementDAO.class);
			
		//로그인한 세션에 있는 회원번호 가져오기
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		
			
		//내가 쓴 진술서 목록들 가져오기
		List<InterestStatementDTO> listState = memberInfoDAO.getStatement(memberno);
		//기록 추적 진술서 가져오기
		InterestStatementDTO interestStatementDTO = interestStatementDAO.getInterestStatement(memberno);
		
		//db값 view에 보내주기
		model.addAttribute("isDTO", interestStatementDTO);
		model.addAttribute("list", listState);
			
		return "memberInfo.memberStatement";
	}
	
	// 관심등록 진술서와 자신이 쓴 피해 사례 진술서 내역으로 이동
	@RequestMapping(value="memberStatement2.go", method=RequestMethod.GET)
	public String memberStatement2(
			@RequestParam(value = "f", required = false, defaultValue = "cheatername") String field,
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query,
			MemberDTO memberDTO,
			HttpSession session,
			Model model) throws Exception{
		
		// 로그 남기기
		System.out.println("진술서 게시판 이동");
		
		//DAO 변수 선언
		MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
		InterestStatementDAO interestStatementDAO = sqlSession.getMapper(InterestStatementDAO.class);
			
		//로그인한 세션에 있는 회원번호 가져오기
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		
			
		//내가 쓴 진술서 목록들 가져오기
		List<InterestStatementDTO> listState = memberInfoDAO.getStatement(memberno);
		//기록 추적 진술서 가져오기
		InterestStatementDTO interestStatementDTO = interestStatementDAO.getInterestStatement(memberno);
		
		//db값 view에 보내주기
		model.addAttribute("isDTO", interestStatementDTO);
		model.addAttribute("list", listState);
			
		return "memberInfo.memberStatement2";
	}
	
	// 관심 진술서 등록
	@RequestMapping(value="regInterestStatement.go", method=RequestMethod.GET)
	public void regInterestStatement(
			@RequestParam("stateno") int stateno,
			HttpSession session,
			HttpServletResponse response
			) throws Exception{
		
		// 로그 남기기
		System.out.println("관심 진술서 등록");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//DAO 변수 선언
		InterestStatementDAO interestStatementDAO = sqlSession.getMapper(InterestStatementDAO.class);
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
			
		//로그인한 세션에 있는 회원번호 가져오기
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		
		//해당 진술서 정보 가져오기
		CheaterDTO cheaterDTO = cheaterDAO.getCheater(stateno);
		
		//등록 전 이미 등록했는지 확인 - 회원당 하나만 등록 가능
		int isReg = interestStatementDAO.getResist(memberno);
		
		if(isReg == 0){
			
			//기록 추적 진술서 등록하기
			int result = interestStatementDAO.insertInterest(cheaterDTO);
			
			if(result == 1){
				out.print("<script>alert('정상적으로 등록되었습니다.');location.replace('memberStatement.go')</script>");
			}else{
				out.print("<script>alert('등록에 실패하였습니다.');location.replace('memberStatement.go')</script>");
			}
		}else{
			out.print("<script>alert('이미 관심 등록된 진술서가 있습니다.');location.replace('memberStatement.go')</script>");
		}
		out.close();
	}
	
	// 관심 진술서 등록
	@RequestMapping(value="deleteInterestStatement.go", method=RequestMethod.GET)
	public void deleteInterestStatement(
			HttpSession session,
			HttpServletResponse response
			) throws Exception{
		
		// 로그 남기기
		System.out.println("관심 진술서 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//DAO 변수 선언
		InterestStatementDAO interestStatementDAO = sqlSession.getMapper(InterestStatementDAO.class);
			
		//로그인한 세션에 있는 회원번호 가져오기
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		
		//기록 추적 진술서 등록하기
		int result = interestStatementDAO.deleteInterest(memberno);
		
		if(result == 1){
			out.print("<script>alert('정상적으로 삭제되었습니다.');location.replace('memberStatement.go')</script>");
		}else{
			out.print("<script>alert('삭제에 실패하였습니다.');location.replace('memberStatement.go')</script>");
		}
		out.close();
	}
}