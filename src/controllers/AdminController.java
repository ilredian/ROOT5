package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import DTO.MemberTypeDTO;
import DTO.ReplyDTO;
import common.BoardPager;

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
			@RequestParam("cno") int categoryno, Model model) throws Exception {

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
	public void deleteBoardFree(
			@RequestParam("bno") int boardno) throws Exception{
		
		// 로그 남기기
		System.out.print("신고 게시글 삭제");
		
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
			@RequestParam(value = "f", required = false, defaultValue = "title") String field,
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
		
		return "admin.adminCommment";
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
	@RequestMapping(value = "updateDomainList.go", method = RequestMethod.GET)
	public void updateSite(
			@RequestParam("do") String domain,
			@RequestParam("dn") String domainname,
			CheatDomainDTO cheatDomainDTO) throws Exception {
		
		// 파라미터 값 세팅
		cheatDomainDTO.setDomain(domain);
		cheatDomainDTO.setDomainname(domainname);
		
		// 로그 남기기
		System.out.print("사기 사이트 설정 수정");
		
		// DB 연결
		CheatDomainDAO cheatDomainDAO = sqlSession.getMapper(CheatDomainDAO.class);
		int result = cheatDomainDAO.updateDomain(cheatDomainDTO);
		
		if (result == 1) {
			out.print("<script>alert('사기 사이트가 성공적으로 수정되었습니다.');location.replace('adminDomainList.go');</script>");
		} else {
			out.print("<script>alert('사기 사이트 수정에 실패하였습니다.');location.replace('adminDomainList.go');</script>");
		}
		out.close();
	}
	
	// 사기 사이트 추가
	@RequestMapping(value = "insertDomainList.go", method = RequestMethod.GET)
	public void insertSite(
			@RequestParam("do") String domain,
			@RequestParam("dn") String domainname,
			CheatDomainDTO cheatDomainDTO) throws Exception {
		
		// 파라미터 값 세팅
		cheatDomainDTO.setDomain(domain);
		cheatDomainDTO.setDomainname(domainname);
		
		// 로그 남기기
		System.out.print("사기 사이트 설정 추가");
		
		// DB 연결
		CheatDomainDAO cheatDomainDAO = sqlSession.getMapper(CheatDomainDAO.class);
		int result = cheatDomainDAO.insertDomain(cheatDomainDTO);
		
		if (result == 1) {
			out.print("<script>alert('사기 사이트가 성공적으로 추가되었습니다.');location.replace('adminDomainList.go');</script>");
		} else {
			out.print("<script>alert('사기 사이트 추가에 실패하였습니다.');location.replace('adminDomainList.go');</script>");
		}
		out.close();
	}
	
	// 사기 사이트 삭제
	@RequestMapping(value = "updateDomainList.go", method = RequestMethod.GET)
	public void deleteSite(
			@RequestParam("do") String domain,
			@RequestParam("dn") String domainname,
			CheatDomainDTO cheatDomainDTO) throws Exception {
		
		// 파라미터 값 세팅
		cheatDomainDTO.setDomain(domain);
		cheatDomainDTO.setDomainname(domainname);
		
		// 로그 남기기
		System.out.print("사기 사이트 설정 삭제");
		
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
	@RequestMapping("updateItem.go")
	public void updateItem(
			@RequestParam("gn") String goodskind,
			@RequestParam("gk") String goodsname,
			@RequestParam("gs") String goodsspan,
			CheatItemsDTO cheatItemsDTO) throws Exception{ 
		
		// 파라미터 값 세팅
		cheatItemsDTO.setGoodskind(goodskind);
		cheatItemsDTO.setGoodsname(goodsname);
		cheatItemsDTO.setGoodsspan(goodsspan);
		
		// 로그 남기기
		System.out.println("사기 물품 종류 수정");
		
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
			@RequestParam("gn") String goodskind,
			@RequestParam("gk") String goodsname,
			@RequestParam("gs") String goodsspan,
			CheatItemsDTO cheatItemsDTO) throws Exception{ 
		
		// 파라미터 값 세팅
		cheatItemsDTO.setGoodskind(goodskind);
		cheatItemsDTO.setGoodsname(goodsname);
		cheatItemsDTO.setGoodsspan(goodsspan);
		
		// 로그 남기기
		System.out.println("사기 물품 종류 추가");
		
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
			@RequestParam("gn") String goodskind,
			@RequestParam("gk") String goodsname,
			@RequestParam("gs") String goodsspan,
			CheatItemsDTO cheatItemsDTO) throws Exception{ 
		
		// 파라미터 값 세팅
		cheatItemsDTO.setGoodskind(goodskind);
		cheatItemsDTO.setGoodsname(goodsname);
		cheatItemsDTO.setGoodsspan(goodsspan);
		
		// 로그 남기기
		System.out.println("사기 물품 종류 삭제");
		
		// DB 가져오기
		CheatItemsDAO cheatItemsDAO = sqlSession.getMapper(CheatItemsDAO.class);
		int result = cheatItemsDAO.updateCheatItem(cheatItemsDTO);
		
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
		List<CheatBankDTO> list = cheatBankDAO.getList(0, 29);
		
		// Model 객체에 담기
		model.addAttribute("list", list);
		
		return "admin.adminBank";
	}

	// 은행 리스트 수정
	@RequestMapping("updateBank.go")
	public void updateBank(
			@RequestParam("bank") String bankname,
			CheatBankDTO cheatBankDTO) throws Exception {

		// 파라미터값 세팅
		cheatBankDTO.setBankname(bankname);
		
		// 로그 남기기
		System.out.println("은행 리스트 수정");

		// DB 세팅
		CheatBankDAO cheatBankDAO = sqlSession.getMapper(CheatBankDAO.class);
		int result = cheatBankDAO.updateBank(cheatBankDTO);
		
		if (result == 1) {
			out.print("<script>alert('은행 종류가 성공적으로 수정되었습니다.');location.replace('adminBank.go');</script>");
		} else {
			out.print("<script>alert('은행 종류 수정에 실패하였습니다.');location.replace('adminBank.go');</script>");
		}
		out.close();
	}
	
	// 은행 리스트 추가
	@RequestMapping("updateBank.go")
	public void insertBank(
			@RequestParam("bank") String bankname,
			CheatBankDTO cheatBankDTO) throws Exception {

		// 파라미터값 세팅
		cheatBankDTO.setBankname(bankname);
		
		// 로그 남기기
		System.out.println("은행 리스트 추가");

		// DB 세팅
		CheatBankDAO cheatBankDAO = sqlSession.getMapper(CheatBankDAO.class);
		int result = cheatBankDAO.insertBank(cheatBankDTO);
		
		if (result == 1) {
			out.print("<script>alert('은행 종류가 성공적으로 추가되었습니다.');location.replace('adminBank.go');</script>");
		} else {
			out.print("<script>alert('은행 종류 추가에 실패하였습니다.');location.replace('adminBank.go');</script>");
		}
		out.close();
	}
	
	// 은행 리스트 삭제
	@RequestMapping("deleteBank.go")
	public void deleteBank(
			@RequestParam("bank") String bankname,
			CheatBankDTO cheatBankDTO) throws Exception {

		// 파라미터값 세팅
		cheatBankDTO.setBankname(bankname);
		
		// 로그 남기기
		System.out.println("은행 리스트 삭제");

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
	@RequestMapping("updateMemberType.go")
	public void updateMemberType(
			@RequestParam("tn") String typename,
			@RequestParam("tno") int typeno,
			MemberTypeDTO memberTypeDTO) throws Exception {
		
		// 파라미터 값 세팅
		memberTypeDTO.setTypename(typename);
		memberTypeDTO.setTypeno(typeno);
		
		// 로그 남기기
		System.out.println("멤버종류 수정");

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
	@RequestMapping("insertMemberType.go")
	public void insertMemberType(
			@RequestParam("tn") String typename,
			@RequestParam("tno") int typeno,
			MemberTypeDTO memberTypeDTO) throws Exception {
		
		// 파라미터 값 세팅
		memberTypeDTO.setTypename(typename);
		memberTypeDTO.setTypeno(typeno);

		// 로그 남기기
		System.out.println("멤버종류 추가");

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
			MemberTypeDTO memberTypeDTO) throws Exception {
		
		// 파라미터 값 세팅
		memberTypeDTO.setTypename(typename);
		memberTypeDTO.setTypeno(typeno);
		
		// 로그 남기기
		System.out.println("멤버종류 삭제");

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
	@RequestMapping("updateStatementCategory.go")
	public void updateStatementCategory(
			@RequestParam("cn") String cheatname,
			@RequestParam("cno") int cheatno,
			CheatDTO cheatDTO) throws Exception {

		// 파라미터값 세팅
		cheatDTO.setCheatname(cheatname);
		cheatDTO.setCheatno(cheatno);
		
		// 로그 남기기
		System.out.println("사기 종류 수정");

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
	@RequestMapping("updateStatementCategory.go")
	public void insertStatementCategory(
			@RequestParam("cn") String cheatname,
			@RequestParam("cno") int cheatno,
			CheatDTO cheatDTO) throws Exception {

		// 파라미터값 세팅
		cheatDTO.setCheatname(cheatname);
		cheatDTO.setCheatno(cheatno);
		
		// 로그 남기기
		System.out.println("사기 종류 추가");

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
	@RequestMapping("updateStatementCategory.go")
	public void deleteStatementCategory(
			@RequestParam("cn") String cheatname,
			@RequestParam("cno") int cheatno,
			CheatDTO cheatDTO) throws Exception {

		// 파라미터값 세팅
		cheatDTO.setCheatname(cheatname);
		cheatDTO.setCheatno(cheatno);
		
		// 로그 남기기
		System.out.println("사기 종류 삭제");

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

	@RequestMapping(value = "adminMember.go", method = RequestMethod.GET)
	public String AdminMemberagent() {

		return "admin.adminMember";
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
	@RequestMapping("updateBoardCategory.go")
	public void updateBoardCategory(
			@RequestParam("cn") String categoryname,
			@RequestParam("cno") int categoryno,
			BoardCategoryDTO boardCategoryDTO) throws Exception {

		// 파라미터값 세팅
		boardCategoryDTO.setCategoryname(categoryname);
		boardCategoryDTO.setCategoryno(categoryno);
		
		// 로그 남기기
		System.out.println("게시판 종류 수정");

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
	@RequestMapping("insertBoardCategory.go")
	public void insertBoardCategory(
			@RequestParam("cn") String categoryname,
			@RequestParam("cno") int categoryno,
			BoardCategoryDTO boardCategoryDTO) throws Exception {

		// 파라미터값 세팅
		boardCategoryDTO.setCategoryname(categoryname);
		boardCategoryDTO.setCategoryno(categoryno);

		// 로그 남기기
		System.out.println("게시판 종류 추가");

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
			BoardCategoryDTO boardCategoryDTO) throws Exception {

		// 파라미터값 세팅
		boardCategoryDTO.setCategoryname(categoryname);
		boardCategoryDTO.setCategoryno(categoryno);

		// 로그 남기기
		System.out.println("게시판 종류 삭제");

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

	// 메일 관리자 페이지로 이동
	@RequestMapping(value = "adminMail.go", method = RequestMethod.GET)
	public String AdminMail() {

		// 로그 남기기
		System.out.println("메일 관리자 페이지로 이동");

		return "admin.adminMail";
	}

}
