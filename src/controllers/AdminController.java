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
import DAO.CheatDAO;
import DAO.MemberTypeDAO;
import DAO.ReportBoardDAO;
import DTO.BoardCategoryDTO;
import DTO.BoardFreeDTO;
import DTO.CheatDTO;
import DTO.MemberTypeDTO;
import DTO.ReportTempDTO;
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

	@RequestMapping(value = "adminBoardFree.go", method = RequestMethod.GET)
	public String AdminFree(@RequestParam(value = "pg", required = false, defaultValue = "1") int page, // 현재
																										// 페이지
																										// 번호
			@RequestParam(value = "f", required = false, defaultValue = "title") String field, // 검색
																								// 카테고리
			@RequestParam(value = "q", required = false, defaultValue = "%%") String query, // 검색
																							// 내용
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, // 한
																								// 페이지에
																								// 보여줄
																								// 게시글
																								// 갯수
			@RequestParam("cno") int categoryno, Model model) throws Exception {

		// 로그 남기기
		System.out.println("신고 게시판");
		System.out.println(page + " / " + field + " / " + query);

		// 맵퍼 설정하여 DAO 변수 선언
		ReportBoardDAO reportBoardDAO = sqlSession.getMapper(ReportBoardDAO.class);

		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "reportBoardMain.go";// 페이지번호를 누르면 이동할 경로
		int boardCount = reportBoardDAO.getReportBoardCount(field, query, categoryno);// 검색
																						// 결과에
																						// 따른
																						// 게시글
																						// 총
																						// 갯수
		int start = (page - 1) * pageSize;

		BoardPager pager = null;
		if (!query.equals("%%")) {// 검색값이 있을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl, field, query);
		} else {// 검색 값이 없을 경우
			pager = new BoardPager(boardCount, page, pageSize, pagerSize, linkUrl);
		}

		// 게시물 불러오기
		List<ReportTempDTO> boardnotemp = reportBoardDAO.getReportBoardno(start, categoryno, pageSize);
		List<BoardFreeDTO> list = new ArrayList<BoardFreeDTO>();
		for (int i = 0; i < boardnotemp.size(); i++) {
			list.add(reportBoardDAO.getReportBoard(boardnotemp.get(i).getBoardno()));
		}

		// DB값 model 객체에 담기
		model.addAttribute("pager", pager);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);

		// 로그 남기기
		System.out.println("신고된 자유게시판 메인 출력 완료");

		return "admin.adminBoardFree";
	}

	@RequestMapping(value = "adminBoardPhoto.go", method = RequestMethod.GET)
	public String AdminPoto() {

		return "admin.adminBoardPhoto";
	}

	@RequestMapping(value = "adminBoardPhoto.go", method = RequestMethod.POST)
	public String AdminPotodd() {

		return "admin.adminBoardPhoto";
	}

	@RequestMapping(value = "adminCommment.go", method = RequestMethod.GET)
	public String AdminCommment() {

		return "admin.adminCommment";
	}

	@RequestMapping(value = "adminDomainList.go", method = RequestMethod.GET)
	public String AdminSite() {

		return "admin.adminDomainList";
	}

	@RequestMapping(value = "adminItemList.go", method = RequestMethod.GET)
	public String AdminDealList() {

		return "admin.adminItemList";
	}

	@RequestMapping(value = "adminBank.go", method = RequestMethod.GET)
	public String BankPage() {

		// 로그 남기기
		System.out.println("관리자-은행 탭으로 이동");

		return "admin.adminBank";
	}

	@RequestMapping(value = "adminBank.go", method = RequestMethod.POST)
	public String updateBank() {

		// 로그 남기기
		System.out.println("관리자-은행 탭에서 처리 중");

		return "admin.adminBank";
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
	public void updateMemberType(MemberTypeDTO memberTypeDTO) throws Exception {

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
	public void insertMemberType(MemberTypeDTO memberTypeDTO) throws Exception {

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
	public void deleteMemberType(MemberTypeDTO memberTypeDTO) throws Exception {

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
	public void updateStatementCategory(CheatDTO cheatDTO) throws Exception {

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
	public void insertStatementCategory(CheatDTO cheatDTO) throws Exception {

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
	public void deleteStatementCategory(CheatDTO cheatDTO) throws Exception {

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
	public void updateBoardCategory(BoardCategoryDTO boardCategoryDTO) throws Exception {

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
	public void insertBoardCategory(BoardCategoryDTO boardCategoryDTO) throws Exception {

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
	public void deleteBoardCategory(BoardCategoryDTO boardCategoryDTO) throws Exception {

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
