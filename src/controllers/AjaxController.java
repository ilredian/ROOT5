package controllers;


import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import DAO.CheaterDAO;
import DAO.MemberDAO;
import DAO.MessageDAO;
import DAO.QueryDAO;
import DAO.VisitDAO;
import DTO.MemberDTO;
import DTO.QueryDTO;

@Controller
public class AjaxController {
	
	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;
	
	//메인 화면에서 자동완성 검색
	@RequestMapping(value = "totalSearchAjax.go", method = RequestMethod.POST)
	public ModelAndView totalSearchAjax(
			@RequestParam("totalSearchAjax") String query) throws Exception {

		ModelAndView mav = new ModelAndView();
		QueryDAO queryDAO = sqlSession.getMapper(QueryDAO.class);

		List<QueryDTO> querylist = queryDAO.getSearchQuery(query, 1);
		List<String> list = new ArrayList<String>();
		if(!querylist.isEmpty()){
			for(int i=0; i<querylist.size(); i++){
				list.add(URLEncoder.encode(querylist.get(i).getQuery() , "UTF-8"));
			}
		}
		
		mav.addObject("auto", list);
		mav.setViewName("jsonView");
		return mav;
	}

	//쪽지 오면 깜빡이기
	@RequestMapping(value = "msgAjax.go", method = RequestMethod.POST)
	public ModelAndView msg(
			@RequestParam("memberno") int memberno,
			HttpServletRequest requset) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		MessageDAO messagedao = sqlSession.getMapper(MessageDAO.class);
		
		int isopen = messagedao.getIsOpen(memberno);
		if(isopen>=1){
			mav.addObject("result", "success");
		}else{
			mav.addObject("result", "fail");
		}
		mav.setViewName("jsonView");
		return mav;
	}

	// 쪽지 작성에서 이름 자동 완성 검색
	@RequestMapping(value = "searchMemberNoAjax.go", method = RequestMethod.POST)
	public ModelAndView searchMemberNoAjax(
			@RequestParam("searchMemberNoAjax") String name) throws Exception {
		
		// 로그 남기기
		System.out.println("이름 자동 완성 검색");
		ModelAndView mav = new ModelAndView();
		
		// view에서 보내준 name 값을 DTO에 저장
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		
		// name 값으로 DB 검색 후 list 뽑아내기
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		List<MemberDTO> listMember = memberDAO.getMemberName(memberDTO);
		
		// 뽑아낸 list를 자동완성을 위해 변환해줌
		List<String> list = new ArrayList<String>();
		
		for(int i=0; i<listMember.size(); i++){
			
			//이름
			String memberName =  listMember.get(i).getName();
			
			//이메일 및 변환처리
			String email = listMember.get(i).getEmail();
			int location = email.indexOf("@");
			String star = "";
			if (location > 3) {
				for (int y = 3; y < location; y++) {
					star += "*";
				}
			}
			String starEmail = email.substring(0, 3) + star + email.substring(location);
			
			//회원번호
			int memberno = listMember.get(i).getMemberno();
			
			//합치기
			String query = memberName + " " + starEmail + " " + "회원번호: " + memberno;

			//한글 인코딩
			//리스트에 담기
			list.add(URLEncoder.encode(query , "UTF-8"));
		}
		mav.addObject("result", list);
		mav.setViewName("jsonView");
		return mav;
	}
	
	// 존재하는 회원인지 확정하기 위한 메소드
	@RequestMapping(value="resultOkAjax.go", method=RequestMethod.POST)
	public ModelAndView resultOkAjax(
			@RequestParam("resultOkAjax") String longName
			) throws Exception {
		
		// 로그 남기기
		System.out.println("회원 확인");
		System.out.println(longName);
		ModelAndView mav = new ModelAndView();
		
		if(longName.length() > 0){
			String[] temp = longName.split(" ");
			int memberno = Integer.parseInt(temp[temp.length-1]);
			
			MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
			MemberDTO memberDTO = memberDAO.getMemberStat(memberno);
			
			if(temp[0].equals(memberDTO.getName())){
				mav.addObject("result", "seccess");
			}else{
				mav.addObject("result", "error");
			}
		}else{
			mav.addObject("result", "error");
		}
		mav.setViewName("jsonView");
		return mav;
	}
	
	// 방문자수 호출
	@RequestMapping(value="visitCountAjax.go", method=RequestMethod.POST)
	public ModelAndView visitCountAjax(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws Exception {
		
		// 로그 남기기
		System.out.println("방문자수 확인");
		
		// Ajax 처리를 위한 modelAndView 선언
		ModelAndView mav = new ModelAndView();	
		
		// 방문자수 처리를 위한 변수 선언
		VisitDAO visitDAO = sqlSession.getMapper(VisitDAO.class);
		
		// 쿠키 검색
		String isVist = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i != cookies.length; ++i) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equalsIgnoreCase("visitCheck")) {
					isVist = cookie.getValue();
				}
			}
		}

		// 처음 방문했다면 쿠키생성 후 방문자수 +1
		if(!isVist.equals("visit")){
			Cookie visitCookie = new Cookie("visitCheck", "visit");
			visitCookie.setPath("/");
			Date date = new Date();
			visitCookie.setMaxAge(((23-date.getHours())*60*60)+((59-date.getMinutes())*60)+(59-date.getSeconds()));
			response.addCookie(visitCookie);
			
			// 전체 방문자 수 +1
			visitDAO.setVisitTotalCount();
		}
		
		// 오늘 방문자 수
	    int todayCount = visitDAO.getVisitTodayCount();
	       
	    // 전체 방문자 수
	    int totalCount = visitDAO.getVisitTotalCount();
	      
	    model.addAttribute("totalCount", totalCount);// 전체 방문자 수
		model.addAttribute("todayCount", todayCount); // 오늘 방문자 수
		
		//왔던 곳으로 되돌아가기
		mav.setViewName("jsonView");
		return mav;
	}
	
	// remember 체크했으면 로그인 유지
	@RequestMapping(value="rememberAjax.go", method=RequestMethod.POST)
	public ModelAndView rememberAjax(
			HttpServletRequest request,
			HttpSession session
			) throws Exception{

		// 로그 남기기
		System.out.println("remember 체크 확인");
		
		// Ajax 처리를 위한 modelAndView 선언
		ModelAndView mav = new ModelAndView();	
		
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
				
		// remember 쿠키가 있으면 회원정보 가져와서 세션 저장
		if(!isRemember.equals("")){
			//DB 중복 검색 방지를 위한 세션 확인
			if(session.getAttribute("memberInfo") == null){
				String email = isRemember;
				MemberDTO tempDTO = new MemberDTO();
				tempDTO.setEmail(email);
				MemberDTO memberDTO = memberDAO.getMember(tempDTO);
				session.setAttribute("memberInfo", memberDTO);
				mav.addObject("result", "refresh");
			}
		}
		
		//왔던 곳으로 되돌아가기
		mav.setViewName("jsonView");
		return mav;
	}
	
	// index 페이지와 join 폴더를 제외한 나머지 구역 봉인
	@RequestMapping(value="sessionCheckAjax.go", method=RequestMethod.POST)
	public ModelAndView sessionCheckAjax(
			@RequestParam(value="location", required=false, defaultValue="notIndex") String location,
			HttpServletRequest request,
			HttpSession session
			){

			// 로그 남기기
			System.out.println("세션 확인 후 없으면 강제로 메인으로 보내기");
			
			// Ajax 처리를 위한 modelAndView 선언
			ModelAndView mav = new ModelAndView();	
			
			if(location.equals("notIndex")){
				mav.addObject("result", "success");
			}else{
				mav.addObject("result", "index");
			}
			
			//왔던 곳으로 되돌아가기
			mav.setViewName("jsonView");
			return mav;
	}
	
	//policeUpdateTraceAjax.go?gno=
	//경찰 추적현황 조회
	@RequestMapping(value="policeUpdateTraceAjax.go", method=RequestMethod.POST)
	public ModelAndView policeUpdateTraceAjax(
			@RequestParam("gno") int groupno,
			HttpSession session
			) throws Exception {
		
		// 로그 남기기
		System.out.println("경찰 추적현황 조회");
					
		// Ajax 처리를 위한 modelAndView 선언
		ModelAndView mav = new ModelAndView();
		
		// DAO 선언
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		String result = cheaterDAO.getPoliceUpdateTrace(groupno);
		System.out.println(groupno);
		System.out.println(result);
		
		if(result != null && !result.equals("")){
			mav.addObject("result", "success");
			mav.addObject("String", URLEncoder.encode(result , "UTF-8"));
			mav.addObject("gno", groupno);
		}else{
			mav.addObject("result", "index");
			mav.addObject("gno", groupno);
		}
					
		//왔던 곳으로 되돌아가기
		mav.setViewName("jsonView");
		return mav;
	}
	
	//경찰 검거 현황 조회
	@RequestMapping(value="policeUpdateCompleteAjax.go", method=RequestMethod.POST)
	public ModelAndView policeUpdateCompleteAjax(
			@RequestParam("gno") int groupno,
			HttpSession session
			) throws Exception {
		
		// 로그 남기기
		System.out.println("경찰 검거 현황 조회");
					
		// Ajax 처리를 위한 modelAndView 선언
		ModelAndView mav = new ModelAndView();
		
		// DAO 선언
		CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
		String result = cheaterDAO.getPoliceUpdateComplete(groupno);
		System.out.println(groupno);
		System.out.println(result);
		
		if(result != null && !result.equals("")){
			mav.addObject("result", "success");
			mav.addObject("String", URLEncoder.encode(result , "UTF-8"));
			mav.addObject("gno", groupno);
		}else{
			mav.addObject("result", "index");
			mav.addObject("gno", groupno);
		}
					
		//왔던 곳으로 되돌아가기
		mav.setViewName("jsonView");
		return mav;
	}
}
