package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import DAO.MemberDAO;
import DAO.MemberInfoDAO;
import DTO.MemberDTO;
import mail.SendMail;
import mail.SendMailDTO;

@Controller
public class LoginController {

	PrintWriter out = null;

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="login.go" , method=RequestMethod.GET)
	public String Login(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws Exception{
		// remember체크 확인 전 DAO변수 선언
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		// 쿠키 검색
		String isRemember = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i != cookies.length; ++i) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equalsIgnoreCase("rememberCheck")) {
					isRemember = cookie.getValue();
				}
			}
		}
		
		// remember 쿠키가 있으면 회원정보 가져오기
		if(!isRemember.equals("")){
			String email = isRemember;
			MemberDTO tempDTO = new MemberDTO();
			tempDTO.setEmail(email);
			MemberDTO memberDTO = memberDAO.getMember(tempDTO);
			model.addAttribute("loginRemember", memberDTO);
		}
		
		return "join.login";
	}
	
	// 로그인 정보 DB 확인
	/*
	@PreAuthorize("hasRole('ROLE_USER'")
	*/
	@RequestMapping(value = "login.go", method=RequestMethod.POST)
	public void Login(
			@RequestParam(value="remember", required=false, defaultValue="0") int remember,
			MemberDTO memberDTO,
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			Model data ) throws Exception {
		
		//로그 남기기
		System.out.println("로그인 실행");
		System.out.println(remember);
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//로그인 폼 정보를 마이바티스로 넘김
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO result = memberDAO.getMember(memberDTO);
		
		//유효성 검사
		if(result != null){//아이디가 있음
			int active = result.getActive();
			//회원탈퇴 여부
			if(active!=1){
				
				if(result.getPassword().equals(memberDTO.getPassword())){//비밀번호가 같음
					
					//memberInfo 속성에 로그인 세션값을 넣기, 즉 로그인하기 
					session.setAttribute("memberInfo", result);
					session.setMaxInactiveInterval(60*60*24) ;
					
					//remember가 체크 되어 있다면 쿠키 생성
					if(remember == 1){
						Cookie rememberCheck = new Cookie("rememberCheck", memberDTO.getEmail());
						rememberCheck.setPath("/");
						rememberCheck.setMaxAge(60*60*24*7);
						response.addCookie(rememberCheck);
					}
					out.print("<script type='text/javascript'>alert('로그인 하셨습니다.');location.replace('index.go');</script>");
				}else{//비밀번호가 틀림
					//내가 입력한 값과 DB에 값이 틀리면 경고창 띄우기
					out.print("<script type='text/javascript'>alert('비밀번호가 틀렸습니다.');location.replace('login.go');</script>");
				}
			}else{
				out.print("<script type='text/javascript'>alert('해당 이메일은 탈퇴한 회원입니다. 재가입은 관리자에게 직접문의');location.replace('index.go');</script>");
			}
		}else{
			//이메일이 없으면 경고창 띄우기
			out.print("<script type='text/javascript'>alert('해당 이메일은 가입되어 있지 않습니다');location.replace('login.go');</script>");
		}
		out.close();
	}
	
	@RequestMapping(value="/ERROR.go" , method=RequestMethod.GET)
	public void Denied(){
		
		System.out.println("접근 거부");
	}
	
	// 로그아웃
	@RequestMapping(value = "logout.go")
	public void Logout(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		
		// 세션 삭제
		session.removeAttribute("login");
		session.invalidate();
		
		
		// 쿠키 삭제
		
		//Cookie cookie = new Cookie("rememberCheck", "");
		Cookie rememberCheck = WebUtils.getCookie(request, "rememberCheck");
		
		if(rememberCheck != null){
			rememberCheck.setPath("/");
			rememberCheck.setMaxAge(0);
			response.addCookie(rememberCheck);
			
		}
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//경고창 띄우기
		out.print("<script type='text/javascript'>alert('로그아웃 되었습니다.');location.replace('index.go');</script>");
		out.close();
	}
	
	//이메일 찾기
	@RequestMapping("emailSearch.go")
	public ModelAndView passwordSearch(
			@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			HttpServletResponse	response
			) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//이메일이 있는지 확인
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		String email = memberDAO.getMemberEmailSearch(name, phone);
		
		if(email != null && !email.equals("")){
			
			int location = email.indexOf("@");
			String star = "";
			if (location > 3) {
				for (int i = 3; i < location; i++) {
					star += "*";
				}
			}
			
			String emailTemp = email.substring(0, 3) + star + email.substring(location);
			
			//해당 이메일을 새 창으로 띄우기
			mav.addObject("result", "sucess");
			mav.addObject("email",emailTemp);
		}else{
			//가입된 이메일이 없다고 띄우기
			mav.addObject("result", "fail");
		}
		mav.setViewName("jsonView");
		return mav;
	}
	
	//패스워드 찾기
	@RequestMapping("passwordSearch.go")
	public void emailSearch(
			@RequestParam("email") String email,
			@RequestParam("name") String membername,
			HttpServletResponse	response
			) throws Exception{
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//이메일이 있는지 확인
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO memberEmailDTO = new MemberDTO();
		MemberDTO memberDTO = new MemberDTO();
		memberEmailDTO.setEmail(email);
		memberDTO = memberDAO.getMember(memberEmailDTO);
		 
		if(memberDTO != null){
			
			//임시 비밀번호 발급
			StringBuffer buffer = new StringBuffer();
		    Random random = new Random();
		    String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9".split(",");
		    for (int i1 = 0; i1 < 10; i1++) {
			    buffer.append(chars[random.nextInt(chars.length)]);
		    }
		    String tempPw = buffer.toString();
		    
		    //비밀번호 세팅 후 변경
			MemberInfoDAO memberInfoDAO = sqlSession.getMapper(MemberInfoDAO.class);
			memberDTO.setNpassword(tempPw);
			memberInfoDAO.changepassword(memberDTO);
			
			//임시 비밀번호 이메일로 보내기
			String name = "AhnCheat 관리자";
			String from = "admin@ilredian.xyz";
			String to = email;
			String title = "AhnCheat 비밀번호 찾기 메일입니다.";
			String content_mail = "임시 비밀번호는 "+tempPw+"입니다.<br>본 메일은 발신 전용입니다.";
			String tar = "html";
			String filename = "";
			
			//보내는사람 이름, 보내는사람 주소, 받는사람 주소, 제목, 내용, 형식, 첨부파일
			SendMailDTO sendMailDTO = new SendMailDTO(name, from, to, title, content_mail, tar, filename);
			SendMail mail = new SendMail();
			mail.sendMail(sendMailDTO);
			
			//이메일 발송 경고문 띄우기
			out.print("<script type='text/javascript'>alert('이메일로 임시 비밀번호가 전송되었습니다.');location.replace('login.go');</script>");
			
		}else{
			//해당 이메일이 없다는 경고문 띄우기
			out.print("<script type='text/javascript'>alert('해당 이메일이 존재하지 않습니다.');location.replace('index.go');</script>");
		}
		out.close();
	}
}