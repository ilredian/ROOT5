package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.BoardCategoryDAO;
import DAO.BoardFreeDAO;
import DAO.CheatBankDAO;
import DAO.CheatDAO;
import DAO.CheatDomainDAO;
import DAO.CheatItemsDAO;
import DAO.MemberDAO;
import DAO.MemberTypeDAO;
import DAO.ReplyDAO;
import DAO.ReportBoardDAO;
import DAO.ReportReplyDAO;
import DTO.BoardCategoryDTO;
import DTO.BoardFreeDTO;
import DTO.CheatBankDTO;
import DTO.CheatDTO;
import DTO.CheatDomainDTO;
import DTO.CheatItemsDTO;
import DTO.MemberDTO;
import DTO.MemberTypeDTO;
import DTO.ReplyDTO;
import common.BoardPager;
import mail.SendMailDTO;
import mail.ReceiveMail;
import mail.ReceiveMailDTO;
import mail.SendMail;

@Controller
public class AdminController {// 관리자 페이지

	// 자바스크립트 쓰기위한 전역 변수 선언
	PrintWriter out;

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "adhome.go", method = RequestMethod.GET)
	public String AdminHome() {

		// 로그 남기기
		System.out.println("관리자 페이지 홈으로 이동");

		return "admin.adminHome";
	}

	// 신고 게시판 관리자 페이지 이동
	@RequestMapping(value = "adminBoardFree.go", method = RequestMethod.GET)
	public String AdminFree(
			// 현재 페이지 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, 
			// 검색 카테고리
			@RequestParam(value = "f", required = false, defaultValue = "title") String field,
			// 검색 내용
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, 
			// 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, 
			@RequestParam(value = "cno", defaultValue = "1") int categoryno, Model model) throws Exception {

		// 로그 남기기
		System.out.println("신고 게시판 관리자 페이지 이동");
		System.out.println(page + " / " + field + " / " + query);
		
		// 맵퍼 설정하여 DAO 변수 선언
		ReportBoardDAO reportBoardDAO = sqlSession.getMapper(ReportBoardDAO.class);

		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "adminBoardFree.go";// 페이지번호를 누르면 이동할 경로
		// 검색 결과에 따른 게시글 총 갯수
		int boardCount = reportBoardDAO.getReportBoardCount(field, query, categoryno);
		int start = (page - 1) * pageSize;
		BoardPager pager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
		} else {// 검색 값이 없을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		}

		// 게시물 불러오기
		List<Integer> boardno = reportBoardDAO.getReportBoardno(start, categoryno, pageSize);
		List<BoardFreeDTO> list = new ArrayList<BoardFreeDTO>();
		for (int i = 0; i < boardno.size(); i++) {
			list.add(reportBoardDAO.getReportBoard(boardno.get(i)));
		}

		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("신고된 자유게시판 메인 출력 완료");

		return "admin.adminBoardFree";
	}
	
	// 신고 게시판 삭제
	@RequestMapping("deleteReportBoardFree.go")
	@Transactional	//게시글을 불러오고 , 게시글을 삭제_ 12월 7일
	public void deleteBoardFree(
			HttpServletResponse response,
			@RequestParam("bno") int boardno) throws Exception{
		// 로그 남기기
		System.out.print("신고 게시글 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 접속
		BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
		ReportBoardDAO reportBoardDAO = sqlSession.getMapper(ReportBoardDAO.class);
		
		reportBoardDAO.deleteReportBoard(boardno); 
		int result = boardFreeDAO.delete(boardno);
		
		if (result == 1) {
			out.print("<script>alert('게시글이 성공적으로 삭제되었습니다.');location.replace('adminBoardFree.go');</script>");
		} else {
			out.print("<script>alert('게시글 삭제에 실패하였습니다.');location.replace('adminBoardFree.go');</script>");
		}
		out.close();
	}

	@RequestMapping(value = "adminBoardPhoto.go", method = RequestMethod.GET)
	public String AdminPoto() {

		return "admin.adminBoardPhoto";
	}

	// 신고된 댓글 관리자 페이지 이동
	@RequestMapping(value = "adminCommment.go", method = RequestMethod.GET)
	public String AdminCommment(// 현재 페이지 번호
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page, 
			// 검색 카테고리
			@RequestParam(value = "f", required = false, defaultValue = "content") String field,
			// 검색 내용
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, 
			// 한 페이지에 보여줄 게시글 갯수
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, 
			Model model) throws Exception {
		
		// 로그 남기기
		System.out.println("신고된 댓글 관리자 페이지 이동");
		
		// DB 연결 선언
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		ReportReplyDAO reportReplyDAO = sqlSession.getMapper(ReportReplyDAO.class);
		
		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "adminCommment.go";// 페이지번호를 누르면 이동할 경로
		
		// 검색 결과에 따른 게시글 총 갯수
		int boardCount = reportReplyDAO.getReportReplyCount(field, query);
		int start = (page - 1) * pageSize;
		BoardPager pager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
		} else {// 검색 값이 없을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		}
		
		// 리스트 뿌려주기
		List<Integer> replyno = reportReplyDAO.getReportReplyno(start, pageSize);
		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		for(int i=0; i<replyno.size(); i++){
			list.add(replyDAO.getReply(replyno.get(i)));
		}
		
		// Model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("신고된 리플 출력 완료");
		
		return "admin.adminComment";
	}

	// 사기 사이트 설정 관리자 창으로 이동
	@RequestMapping(value = "adminDomainList.go", method = RequestMethod.GET)
	public String selectSite(Model model) throws Exception {
		
		// 로그 남기기
		System.out.println("사기 사이트 설정 관리자 창으로 이동");
		
		// 리스트 뿌려주기
		CheatDomainDAO cheatDomainDAO = sqlSession.getMapper(CheatDomainDAO.class);
		List<CheatDomainDTO> list = cheatDomainDAO.getList();
		
		// Model 객체에 담기
		model.addAttribute("list", list);

		return "admin.adminDomainList";
	}
	
	// 사기 사이트 수정
	@RequestMapping(value = "updateDomainList.go", method = RequestMethod.POST)
	public void updateSite(
			@RequestParam("do") String domain,
			@RequestParam("dn") String domainname,
			@RequestParam("origin") String origin,
			HttpServletResponse response) throws Exception {
		
		// 로그 남기기
		System.out.print("사기 사이트 설정 수정");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 연결
		CheatDomainDAO cheatDomainDAO = sqlSession.getMapper(CheatDomainDAO.class);
		int result = cheatDomainDAO.updateDomain(domain, domainname, origin);
		
		if (result == 1) {
			out.print("<script>alert('사기 사이트가 성공적으로 수정되었습니다.');location.replace('adminDomainList.go');</script>");
		} else {
			out.print("<script>alert('사기 사이트 수정에 실패하였습니다.');location.replace('adminDomainList.go');</script>");
		}
		out.close();
	}
	
	// 사기 사이트 추가
	@RequestMapping(value = "insertDomainList.go", method = RequestMethod.POST)
	public void insertSite(
			@RequestParam("do") String domain,
			@RequestParam("dn") String domainname,
			HttpServletResponse response,
			CheatDomainDTO cheatDomainDTO) throws Exception {
		
		// 파라미터 값 세팅
		cheatDomainDTO.setDomain(domain);
		cheatDomainDTO.setDomainname(domainname);
		
		// 로그 남기기
		System.out.print("사기 사이트 설정 추가");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 연결
		CheatDomainDAO cheatDomainDAO = sqlSession.getMapper(CheatDomainDAO.class);
		
		// 같은 것이 있나 확인
		int isDomain = cheatDomainDAO.getDomain(cheatDomainDTO);
		
		if(isDomain != 0){
			out.print("<script>alert('이미 동일한 도메인이 존재합니다.');location.replace('adminDomainList.go');</script>");
		}else{
			int result = cheatDomainDAO.insertDomain(cheatDomainDTO);
			
			if (result == 1) {
				out.print("<script>alert('사기 사이트가 성공적으로 추가되었습니다.');location.replace('adminDomainList.go');</script>");
			} else {
				out.print("<script>alert('사기 사이트 추가에 실패하였습니다.');location.replace('adminDomainList.go');</script>");
			}
		}
		out.close();
	}
	
	// 사기 사이트 삭제
	@RequestMapping(value = "deleteDomainList.go", method = RequestMethod.GET)
	public void deleteSite(
			@RequestParam("do") String domain,
			@RequestParam("dn") String domainname,
			HttpServletResponse response,
			CheatDomainDTO cheatDomainDTO) throws Exception {
		
		// 파라미터 값 세팅
		cheatDomainDTO.setDomain(domain);
		cheatDomainDTO.setDomainname(domainname);
		
		// 로그 남기기
		System.out.print("사기 사이트 설정 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 연결
		CheatDomainDAO cheatDomainDAO = sqlSession.getMapper(CheatDomainDAO.class);
		int result = cheatDomainDAO.deleteDomain(cheatDomainDTO);
		
		if (result == 1) {
			out.print("<script>alert('사기 사이트가 성공적으로 삭제되었습니다.');location.replace('adminDomainList.go');</script>");
		} else {
			out.print("<script>alert('사기 사이트 삭제에 실패하였습니다.');location.replace('adminDomainList.go');</script>");
		}
		out.close();
	}

	// 사기 물품 종류 관리자 창으로 이동
	@RequestMapping(value = "adminItemList.go", method = RequestMethod.GET)
	public String seleteItemList(Model model) throws Exception{
		
		// 로그 남기기
		System.out.println("사기 물품 종류 관리자 창으로 이동");
		
		// 리스트 뿌려주기
		CheatItemsDAO cheatItemsDAO = sqlSession.getMapper(CheatItemsDAO.class);
		List<CheatItemsDTO> list = cheatItemsDAO.getList(0, 29);

		// Model 객체에 담기
		model.addAttribute("list", list);
		
		return "admin.adminItemList";
	}
	
	// 사기 물품 종류 수정
	@RequestMapping(value="updateItem.go", method = RequestMethod.POST)
	public void updateItem(
			@RequestParam("gk") String goodskind,
			@RequestParam("gn") String goodsname,
			@RequestParam("gs") String goodsspan,
			HttpServletResponse response,
			CheatItemsDTO cheatItemsDTO) throws Exception{ 

		// 파라미터 값 세팅
		cheatItemsDTO.setGoodskind(goodskind);
		cheatItemsDTO.setGoodsname(goodsname);
		cheatItemsDTO.setGoodsspan(goodsspan);

		// 로그 남기기
		System.out.println("사기 물품 종류 수정");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 가져오기
		CheatItemsDAO cheatItemsDAO = sqlSession.getMapper(CheatItemsDAO.class);
		int result = cheatItemsDAO.updateCheatItem(cheatItemsDTO);
		
		if (result == 1) {
			out.print("<script>alert('사기 물품 종류가 성공적으로 수정되었습니다.');location.replace('adminItemList.go');</script>");
		} else {
			out.print("<script>alert('사기 물품 종류 수정에 실패하였습니다.');location.replace('adminItemList.go');</script>");
		}
		out.close();
	}
	
	// 사기 물품 종류 추가
	@RequestMapping("insertItem.go")
	public void insertItem(
			@RequestParam("gk") String goodskind,
			@RequestParam("gn") String goodsname,
			@RequestParam("gs") String goodsspan,
			HttpServletResponse response,
			CheatItemsDTO cheatItemsDTO) throws Exception{ 
		
		// 파라미터 값 세팅
		cheatItemsDTO.setGoodskind(goodskind);
		cheatItemsDTO.setGoodsname(goodsname);
		cheatItemsDTO.setGoodsspan(goodsspan);
		
		// 로그 남기기
		System.out.println("사기 물품 종류 추가");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 가져오기
		CheatItemsDAO cheatItemsDAO = sqlSession.getMapper(CheatItemsDAO.class);
		int result = cheatItemsDAO.insertCheatItem(cheatItemsDTO);
		
		if (result == 1) {
			out.print("<script>alert('사기 물품 종류가 성공적으로 추가되었습니다.');location.replace('adminItemList.go');</script>");
		} else {
			out.print("<script>alert('사기 물품 종류 추가에 실패하였습니다.');location.replace('adminItemList.go');</script>");
		}
		out.close();
	}
	
	// 사기 물품 종류 삭제
	@RequestMapping("deleteItem.go")
	public void deleteItem(
			@RequestParam("gk") String goodskind,
			@RequestParam("gn") String goodsname,
			@RequestParam("gs") String goodsspan,
			HttpServletResponse response,
			CheatItemsDTO cheatItemsDTO) throws Exception{ 
		
		// 파라미터 값 세팅
		cheatItemsDTO.setGoodskind(goodskind);
		cheatItemsDTO.setGoodsname(goodsname);
		cheatItemsDTO.setGoodsspan(goodsspan);
		
		// 로그 남기기
		System.out.println("사기 물품 종류 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		// DB 가져오기
		CheatItemsDAO cheatItemsDAO = sqlSession.getMapper(CheatItemsDAO.class);
		int result = cheatItemsDAO.deleteCheatItem(cheatItemsDTO);
		
		if (result == 1) {
			out.print("<script>alert('사기 물품 종류가 성공적으로 삭제되었습니다.');location.replace('adminItemList.go');</script>");
		} else {
			out.print("<script>alert('사기 물품 종류 삭제에 실패하였습니다.');location.replace('adminItemList.go');</script>");
		}
		out.close();
	}

	// 은행 리스트 관리자 페이지로 이동
	@RequestMapping(value = "adminBank.go", method = RequestMethod.GET)
	public String selectBank(Model model) throws Exception {

		// 로그 남기기
		System.out.println("은행 리스트 관리자 페이지로 이동");

		// 리스트 뿌려주기
		CheatBankDAO cheatBankDAO = sqlSession.getMapper(CheatBankDAO.class);
		List<CheatBankDTO> list = cheatBankDAO.getList(0, 100);
		
		// Model 객체에 담기
		model.addAttribute("list", list);
		
		return "admin.adminBank";
	}

	// 은행 리스트 수정
	@RequestMapping(value="updateBank.go", method = RequestMethod.POST)
	public void updateBank(
			@RequestParam("bank") String bankname,
			@RequestParam("origin") String origin,
			HttpServletResponse response,
			CheatBankDTO cheatBankDTO) throws Exception {

		// 파라미터값 세팅
		cheatBankDTO.setBankname(bankname);
		
		// 로그 남기기
		System.out.println("은행 리스트 수정");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 세팅
		CheatBankDAO cheatBankDAO = sqlSession.getMapper(CheatBankDAO.class);
		int result = cheatBankDAO.updateBank(bankname, origin);
		
		if (result == 1) {
			out.print("<script>alert('은행 종류가 성공적으로 수정되었습니다.');location.replace('adminBank.go');</script>");
		} else {
			out.print("<script>alert('은행 종류 수정에 실패하였습니다.');location.replace('adminBank.go');</script>");
		}
		out.close();
	}
	
	// 은행 리스트 추가
	@RequestMapping(value="insertBank.go", method = RequestMethod.POST)
	public void insertBank(
			@RequestParam("bank") String bankname,
			HttpServletResponse response,
			CheatBankDTO cheatBankDTO) throws Exception {

		// 파라미터값 세팅
		cheatBankDTO.setBankname(bankname);
		
		// 로그 남기기
		System.out.println("은행 리스트 추가");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 세팅
		CheatBankDAO cheatBankDAO = sqlSession.getMapper(CheatBankDAO.class);
		
		int isBank = cheatBankDAO.getBank(cheatBankDTO);
		if(isBank != 0){
			out.print("<script>alert('이미 동일한 은행이 존재합니다.');location.replace('adminBank.go');</script>");
		}else{
			int result = cheatBankDAO.insertBank(cheatBankDTO);
			
			if (result == 1) {
				out.print("<script>alert('은행 종류가 성공적으로 추가되었습니다.');location.replace('adminBank.go');</script>");
			} else {
				out.print("<script>alert('은행 종류 추가에 실패하였습니다.');location.replace('adminBank.go');</script>");
			}
		}
		out.close();
	}
	
	// 은행 리스트 삭제
	@RequestMapping("deleteBank.go")
	public void deleteBank(
			@RequestParam("bank") String bankname,
			HttpServletResponse response,
			CheatBankDTO cheatBankDTO) throws Exception {

		// 파라미터값 세팅
		cheatBankDTO.setBankname(bankname);
		
		// 로그 남기기
		System.out.println("은행 리스트 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 세팅
		CheatBankDAO cheatBankDAO = sqlSession.getMapper(CheatBankDAO.class);
		int result = cheatBankDAO.deleteBank(cheatBankDTO);
		
		if (result == 1) {
			out.print("<script>alert('은행 종류가 성공적으로 삭제되었습니다.');location.replace('adminBank.go');</script>");
		} else {
			out.print("<script>alert('은행 종류 삭제에 실패하였습니다.');location.replace('adminBank.go');</script>");
		}
		out.close();
	}

	// 멤버 종류 관리자 페이지 이동
	@RequestMapping(value = "adminMemberType.go", method = RequestMethod.GET)
	public String selectMemberTypePage(Model model) throws Exception {

		// 로그 남기기
		System.out.println("관리자-멤버종류 페이지로 이동");

		// 리스트 뿌려주기
		MemberTypeDAO memberTypeDAO = sqlSession.getMapper(MemberTypeDAO.class);
		List<MemberTypeDTO> list = memberTypeDAO.getMemberTypeList();

		// Model 객체에 담기
		model.addAttribute("list", list);

		return "admin.adminMemberType";
	}

	// 멤버 종류 수정
	@RequestMapping(value="updateMemberType.go", method = RequestMethod.POST)
	public void updateMemberType(
			@RequestParam("tn") String typename,
			@RequestParam("tno") int typeno,
			HttpServletResponse response,
			MemberTypeDTO memberTypeDTO) throws Exception {
		
		// 파라미터 값 세팅
		memberTypeDTO.setTypename(typename);
		memberTypeDTO.setTypeno(typeno);
		
		// 로그 남기기
		System.out.println("멤버종류 수정");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		MemberTypeDAO memberTypeDAO = sqlSession.getMapper(MemberTypeDAO.class);
		int result = memberTypeDAO.updateMemberType(memberTypeDTO);

		if (result == 1) {
			out.print("<script>alert('멤버 종류가 성공적으로 수정되었습니다.');location.replace('adminMemberType.go');</script>");
		} else {
			out.print("<script>alert('멤버 종류 수정에 실패하였습니다.');location.replace('adminMemberType.go');</script>");
		}
		out.close();
	}

	// 멤버 종류 추가
	@RequestMapping(value="insertMemberType.go", method = RequestMethod.POST)
	public void insertMemberType(
			@RequestParam("tn") String typename,
			@RequestParam("tno") int typeno,
			HttpServletResponse response,
			MemberTypeDTO memberTypeDTO) throws Exception {
		
		// 파라미터 값 세팅
		memberTypeDTO.setTypename(typename);
		memberTypeDTO.setTypeno(typeno);

		// 로그 남기기
		System.out.println("멤버종류 추가");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		MemberTypeDAO memberTypeDAO = sqlSession.getMapper(MemberTypeDAO.class);
		int result = memberTypeDAO.insertMemberType(memberTypeDTO);

		if (result == 1) {
			out.print("<script>alert('멤버 종류가 성공적으로 추가되었습니다.');location.replace('adminMemberType.go');</script>");
		} else {
			out.print("<script>alert('멤버 종류 추가에 실패하였습니다.');location.replace('adminMemberType.go');</script>");
		}
		out.close();
	}

	// 멤버 종류 삭제
	@RequestMapping("deleteMemberType.go")
	public void deleteMemberType(
			@RequestParam("tn") String typename,
			@RequestParam("tno") int typeno,
			HttpServletResponse response,
			MemberTypeDTO memberTypeDTO) throws Exception {
		
		// 파라미터 값 세팅
		memberTypeDTO.setTypename(typename);
		memberTypeDTO.setTypeno(typeno);
		
		// 로그 남기기
		System.out.println("멤버종류 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		MemberTypeDAO memberTypeDAO = sqlSession.getMapper(MemberTypeDAO.class);
		int result = memberTypeDAO.deleteMemberType(memberTypeDTO);

		if (result == 1) {
			out.print("<script>alert('멤버 종류가 성공적으로 삭제되었습니다.');location.replace('adminMemberType.go');</script>");
		} else {
			out.print("<script>alert('멤버 종류 삭제에 실패하였습니다.');location.replace('adminMemberType.go');</script>");
		}
		out.close();
	}

	// 사기 종류 관리자 페이지 이동
	@RequestMapping(value = "adminStatementCategory.go", method = RequestMethod.GET)
	public String selectStatementCategory(Model model) throws Exception {

		// 로그 남기기
		System.out.println("사기 종류 관리자 페이지 이동");

		// 리스트 뿌려주기
		CheatDAO cheatDAO = sqlSession.getMapper(CheatDAO.class);
		List<CheatDTO> list = cheatDAO.getAllCheat();

		// Model 객체에 담기
		model.addAttribute("list", list);

		return "admin.adminStatementCategory";
	}

	// 게시판 종류 수정
	@RequestMapping(value="updateStatementCategory.go", method = RequestMethod.POST)
	public void updateStatementCategory(
			@RequestParam("cn") String cheatname,
			@RequestParam("cno") int cheatno,
			HttpServletResponse response,
			CheatDTO cheatDTO) throws Exception {

		// 파라미터값 세팅
		cheatDTO.setCheatname(cheatname);
		cheatDTO.setCheatno(cheatno);
		
		// 로그 남기기
		System.out.println("사기 종류 수정");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		CheatDAO cheatDAO = sqlSession.getMapper(CheatDAO.class);
		int result = cheatDAO.update(cheatDTO);

		if (result == 1) {
			out.print("<script>alert('사기 종류가 성공적으로 수정되었습니다.');location.replace('adminStatementCategory.go');</script>");
		} else {
			out.print("<script>alert('사기 종류 수정에 실패하였습니다.');location.replace('adminStatementCategory.go');</script>");
		}
		out.close();
	}

	// 게시판 종류 추가
	@RequestMapping(value="insertStatementCategory.go", method = RequestMethod.POST)
	public void insertStatementCategory(
			@RequestParam("cn") String cheatname,
			@RequestParam("cno") int cheatno,
			HttpServletResponse response,
			CheatDTO cheatDTO) throws Exception {

		// 파라미터값 세팅
		cheatDTO.setCheatname(cheatname);
		cheatDTO.setCheatno(cheatno);
		
		// 로그 남기기
		System.out.println("사기 종류 추가");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		CheatDAO cheatDAO = sqlSession.getMapper(CheatDAO.class);
		int result = cheatDAO.insert(cheatDTO);

		if (result == 1) {
			out.print("<script>alert('사기 종류가 성공적으로 추가되었습니다.');location.replace('adminStatementCategory.go');</script>");
		} else {
			out.print("<script>alert('사기 종류 추가에 실패하였습니다.');location.replace('adminStatementCategory.go');</script>");
		}
		out.close();
	}

	// 게시판 종류 삭제
	@RequestMapping("deleteStatementCategory.go")
	public void deleteStatementCategory(
			@RequestParam("cn") String cheatname,
			@RequestParam("cno") int cheatno,
			HttpServletResponse response,
			CheatDTO cheatDTO) throws Exception {

		// 파라미터값 세팅
		cheatDTO.setCheatname(cheatname);
		cheatDTO.setCheatno(cheatno);
		
		// 로그 남기기
		System.out.println("사기 종류 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		CheatDAO cheatDAO = sqlSession.getMapper(CheatDAO.class);
		int result = cheatDAO.delete(cheatDTO);

		if (result == 1) {
			out.print("<script>alert('사기 종류가 성공적으로 삭제되었습니다.');location.replace('adminStatementCategory.go');</script>");
		} else {
			out.print("<script>alert('사기 종류 삭제에 실패하였습니다.');location.replace('adminStatementCategory.go');</script>");
		}
		out.close();
	}

	// 회원 관리 목록
	@RequestMapping(value = "adminMember.go", method = RequestMethod.GET)
	public String AdminMemberagent(
		@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재 페이지 번호
		@RequestParam(value = "f", required = false, defaultValue = "name") String field, // 검색 카테고리
		@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색 내용
		@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한 페이지에 보여줄 게시글 갯수
		Model model) throws Exception {

		// 로그 남기기
		System.out.println("회원 관리 관리자 페이지 이동");
		
		// DB 연결을 위한 DAO 변수 선언
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "adminMember.go";// 페이지번호를 누르면 이동할 경로
		int memberCount = memberDAO.getAllMemberCount(field, query);// 검색 결과에 따른 게시글 총 갯수
		int start = (page - 1) * pageSize;

		BoardPager pager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			pager = new BoardPager(memberCount, page, pageSize, pagerSize, linkUrl, field, query);
		} else {// 검색 값이 없을 경우
			pager = new BoardPager(memberCount, page, pageSize, pagerSize, linkUrl);
		}
		
		List<MemberDTO> list = memberDAO.getAllMember(start, field, query, pageSize);
		
		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("회원 관리 메인 출력 완료");
		
		return "admin.adminMember";
	}
	
	// 회원 타입 수정
	@RequestMapping(value = "updateMember.go", method = RequestMethod.POST)
	public void updateMemberagent(
		@RequestParam("mno") int memberno,
		@RequestParam("tn") int typeno,
		@RequestParam("active") int active,
		HttpServletResponse response,
		MemberDTO memberDTO) throws Exception {

	// 파라미터값 세팅
	memberDTO.setMemberno(memberno);
	memberDTO.setTypeno(typeno);
	memberDTO.setActive(active);
	
	// 로그 남기기
	System.out.println("회원 타입 수정");
	
	//경고문 띄우기 전 한글 처리
	response.setContentType("text/html;charset=UTF-8");
	out = response.getWriter();

	// DB 연결
	MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
	int result = memberDAO.updateType(memberDTO);

	if (result == 1) {
		out.print("<script>alert('회원 정보가 성공적으로 수정되었습니다.');location.replace('adminMember.go');</script>");
	} else {
		out.print("<script>alert('회원 정보 수정에 실패하였습니다.');location.replace('adminMember.go');</script>");
	}
	out.close();
	}

	// 게시판 종류 관리자 페이지 이동
	@RequestMapping(value = "adminBoardCategory.go", method = RequestMethod.GET)
	public String selectBoardCatrgory(Model model) throws Exception {

		// 로그 남기기
		System.out.println("게시판 종류 관리자 페이지 이동");

		// 리스트 뿌려주기
		BoardCategoryDAO boardCategoryDAO = sqlSession.getMapper(BoardCategoryDAO.class);
		List<BoardCategoryDTO> list = boardCategoryDAO.getBoardCategoryList();

		// Model 객체에 담기
		model.addAttribute("list", list);

		return "admin.adminBoardCategory";
	}

	// 게시판 종류 수정
	@RequestMapping(value="updateBoardCategory.go", method = RequestMethod.POST)
	public void updateBoardCategory(
			@RequestParam("cn") String categoryname,
			@RequestParam("cno") int categoryno,
			HttpServletResponse response,
			BoardCategoryDTO boardCategoryDTO) throws Exception {

		// 파라미터값 세팅
		boardCategoryDTO.setCategoryname(categoryname);
		boardCategoryDTO.setCategoryno(categoryno);
		
		// 로그 남기기
		System.out.println("게시판 종류 수정");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		BoardCategoryDAO boardCategoryDAO = sqlSession.getMapper(BoardCategoryDAO.class);
		int result = boardCategoryDAO.updateBoardCategory(boardCategoryDTO);

		if (result == 1) {
			out.print("<script>alert('게시판 종류가 성공적으로 수정되었습니다.');location.replace('adminBoardCategory.go');</script>");
		} else {
			out.print("<script>alert('게시판 종류 수정에 실패하였습니다.');location.replace('adminBoardCategory.go');</script>");
		}
		out.close();
	}

	// 게시판 종류 추가
	@RequestMapping(value="insertBoardCategory.go", method = RequestMethod.POST)
	public void insertBoardCategory(
			@RequestParam("cn") String categoryname,
			@RequestParam("cno") int categoryno,
			HttpServletResponse response,
			BoardCategoryDTO boardCategoryDTO) throws Exception {

		// 파라미터값 세팅
		boardCategoryDTO.setCategoryname(categoryname);
		boardCategoryDTO.setCategoryno(categoryno);

		// 로그 남기기
		System.out.println("게시판 종류 추가");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		BoardCategoryDAO boardCategoryDAO = sqlSession.getMapper(BoardCategoryDAO.class);
		int result = boardCategoryDAO.insertBoardCategory(boardCategoryDTO);

		if (result == 1) {
			out.print("<script>alert('게시판 종류가 성공적으로 추가되었습니다.');location.replace('adminBoardCategory.go');</script>");
		} else {
			out.print("<script>alert('게시판 종류 추가에 실패하였습니다.');location.replace('adminBoardCategory.go');</script>");
		}
		out.close();
	}

	// 게시판 종류 삭제
	@RequestMapping("deleteBoardCategory.go")
	public void deleteBoardCategory(
			@RequestParam("cn") String categoryname,
			@RequestParam("cno") int categoryno,
			HttpServletResponse response,
			BoardCategoryDTO boardCategoryDTO) throws Exception {

		// 파라미터값 세팅
		boardCategoryDTO.setCategoryname(categoryname);
		boardCategoryDTO.setCategoryno(categoryno);

		// 로그 남기기
		System.out.println("게시판 종류 삭제");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		BoardCategoryDAO boardCategoryDAO = sqlSession.getMapper(BoardCategoryDAO.class);
		int result = boardCategoryDAO.deleteBoardCategory(boardCategoryDTO);

		if (result == 1) {
			out.print("<script>alert('게시판 종류가 성공적으로 삭제되었습니다.');location.replace('adminBoardCategory.go');</script>");
		} else {
			out.print("<script>alert('게시판 종류 삭제에 실패하였습니다.');location.replace('adminBoardCategory.go');</script>");
		}
		out.close();
	}

	// 메일보내기 관리자 페이지로 이동
	@RequestMapping(value = "adminMail.go", method = RequestMethod.GET)
	public String AdminMail() {

		// 로그 남기기
		System.out.println("메일보내기 관리자 페이지로 이동");

		return "admin.adminMail";
	}
	
	// 메일보내기 관리자 페이지로 이동
	@RequestMapping(value = "adminMail.go", method = RequestMethod.POST)
	public void sendEmailMethod(
			HttpServletResponse response,
			SendMailDTO sendMailDTO) throws Exception {
		
		//로그 남기기
		System.out.println("메일 보내기");
		
		//경고문 띄우기 전 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//메일 보내기
		sendMailDTO.setName("관리자");
		sendMailDTO.setFrom("ilredian@ilredian.xyz");
		sendMailDTO.setTar("html");
		SendMail sendMail = new SendMail();
		int result = sendMail.sendMail(sendMailDTO);
		
		//메일 결과
		if(result == 1){
			System.out.println("이메일 보내기 완료");
			out.print(
					"<script type='text/javascript'>alert('메일이 성공적으로 발송되었습니다.'); location.replace('');</script>");
		}else{
			System.out.println("이메일 보내기 실패");
			out.print(
					"<script type='text/javascript'>alert('메일 발송에 실패하였습니다.'); location.replace('');</script>");
		}
		out.close();
	}
	
	// 받은 메일 관리자 페이지로 이동
	@RequestMapping(value = "adminMailRE.go", method = RequestMethod.GET)
	public String AdminMailRE(Model model) {

		// 로그 남기기
		System.out.println("받은 메일 관리자 페이지로 이동");

		// 메일 가져오기
		ReceiveMail mailList = new ReceiveMail();
		List<ReceiveMailDTO> list = mailList.reveiceMail();
		
		//
		for(int i=0; i<list.size(); i++){
			System.out.println(i + "<번호------보낸이>" + list.get(i).getFrom());
		}
		
		// 가져온 메일 객체에 담기
		model.addAttribute("list", list);
		
		return "admin.adminMailRE";
	}

}
