package controllers;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.ReportBoardDAO;
import DTO.MemberDTO;
import DTO.ReportBoardDTO;

@Controller
public class ReportController {

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "reportboardfree.go", method = RequestMethod.POST)
	public String reportBoardFree(){
		System.out.println("보드 메인으로 이동");
		return "redirect:freeMain.go?pg=1";
	}
	
	@RequestMapping(value = "reportboardfree.go", method = RequestMethod.GET)
	public String reportBoardFree(
			@RequestParam(value = "cho",defaultValue = "1") int cno,
			@RequestParam(value = "bno") int bno,
			ReportBoardDTO reportBoardDTO, HttpSession session) throws Exception{
		System.out.println(cno);
		System.out.println(bno);
		/*int mno2 = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();*/

		int mno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		reportBoardDTO.setCategoryno(cno);
		reportBoardDTO.setBoardno(bno);
		reportBoardDTO.setMemberno(mno);
		ReportBoardDAO reportBoardDAO = sqlSession.getMapper(ReportBoardDAO.class);
		int row = reportBoardDAO.insertReportBoard(reportBoardDTO);

		if(row==1){
			System.out.println("성공");
		}else{
			System.out.println("실패");
		}
		return "redirect:freeMain.go?pg=1";
	}
}
