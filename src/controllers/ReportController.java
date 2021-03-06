package controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.ReportBoardDAO;
import DAO.ReportPhotoDAO;
import DAO.ReportReplyDAO;
import DTO.MemberDTO;
import DTO.ReportBoardDTO;
import DTO.ReportPhotoDTO;
import DTO.ReportReplyDTO;

@Controller
public class ReportController {
	
	PrintWriter out;
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "reportboardfree.go", method = RequestMethod.POST)
	public String reportBoardFree(){
		System.out.println("보드 메인으로 이동");
		return "redirect:freeMain.go?pg=1";
	}
	
	@RequestMapping(value = "reportboardfree.go", method = RequestMethod.GET)
	public void reportBoardFree(
			@RequestParam(value = "cno", defaultValue="1") int cno,
			@RequestParam(value = "bno") int bno,
			ReportBoardDTO reportBoardDTO, HttpSession session,HttpServletResponse response) throws Exception{
		System.out.println(bno);
		/*int mno2 = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();*/

		response.setContentType("text/html;charset=UTF-8");
	    out = response.getWriter();
	    
		int mno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
		reportBoardDTO.setCategoryno(cno);
		reportBoardDTO.setBoardno(bno);
		reportBoardDTO.setMemberno(mno);
		ReportBoardDAO reportBoardDAO = sqlSession.getMapper(ReportBoardDAO.class);
		
		int result = reportBoardDAO.isReportBoard(bno, mno);
		
		//이동할 경로 설정
		String go ="";
		switch(cno){
		case 1:
			go = "freeMain.go";
			break;
		case 2:
			go = "noticeMain.go";
			break;
		case 3:
			go = "PhotoMain.go";
			break;
		case 4:
			go = "lawMain.go";
			break;
		}
		
		if(result>0){
			out.print("<script type='text/javascript'>alert('이미등록됬습니다.'); location.replace('"+go+"?pg=1');</script>");
		}else{
			int row = reportBoardDAO.insertReportBoard(reportBoardDTO);
			out.print("<script type='text/javascript'>alert('등록됬습니다.'); location.replace('"+go+"?pg=1');</script>");
		}
		out.close();
	}
	
	@RequestMapping(value = "reportPhoto.go", method = RequestMethod.GET)
	public void reportBoardPhoto(
			@RequestParam(value = "cno", defaultValue="1") int cno,
			@RequestParam(value = "bno") int bno,
			ReportPhotoDTO reportPhotoDTO, HttpSession session,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
	    out = response.getWriter();
	    
	    int mno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();
	    reportPhotoDTO.setCategoryno(cno);
	    reportPhotoDTO.setBoardno(bno);
	    reportPhotoDTO.setMemberno(mno);
	    ReportPhotoDAO reportPhotoDAO = sqlSession.getMapper(ReportPhotoDAO.class);
	    
	    int result = reportPhotoDAO.isReportPhoto(bno, mno);
	    
	    String go ="";
		switch(cno){
		case 1:
			go = "freeMain.go";
			break;
		case 2:
			go = "noticeMain.go";
			break;
		case 3:
			go = "PhotoMain.go";
			break;
		case 4:
			go = "lawMain.go";
			break;
		}
	    
		if(result>0){
			out.print("<script type='text/javascript'>alert('이미등록됬습니다.'); location.replace('"+go+"?pg=1');</script>");
		}else{
			int row = reportPhotoDAO.insertReportPhoto(reportPhotoDTO);
			out.print("<script type='text/javascript'>alert('등록됬습니다.'); location.replace('"+go+"?pg=1');</script>");
		}
		out.close();
	}
	
	@RequestMapping(value = "reportreply.go", method = RequestMethod.GET)
	public void reportReplyFree(
			@RequestParam(value ="cno") int cno,
			@RequestParam(value ="bno") int bno,
			@RequestParam(value ="rno") int rno,
			@RequestParam(value ="mno") int mno,
			ReportReplyDTO reportreplyDTO, HttpServletResponse response ) throws Exception{

		response.setContentType("text/html;charset=UTF-8");
	    out = response.getWriter();
		ReportReplyDAO reportreplyDAO = sqlSession.getMapper(ReportReplyDAO.class);

		reportreplyDTO.setCategoryno(cno);
		reportreplyDTO.setBoardno(bno);
		reportreplyDTO.setReplyno(rno);
		reportreplyDTO.setMemberno(mno);
		int result = reportreplyDAO.getInsertReply(reportreplyDTO);
		
		//이동할 경로 설정
		String go ="";
		switch(cno){
		case 1:
			go = "freeMain.go";
			break;
		case 2:
			go = "noticeMain.go";
			break;
		case 3:
			go = "PhotoMain.go";
			break;
		case 4:
			go = "lawMain.go";
			break;
		}
		
		if(result>0){
			out.print("<script type='text/javascript'>alert('이미 등록됬습니다.'); location.replace('"+go+"?pg=1');</script>");
		}else{
			reportreplyDAO.insertReportReply(reportreplyDTO);
			out.print("<script type='text/javascript'>alert('등록됬습니다.'); location.replace('"+go+"?pg=1');</script>");
		}
		out.close();;
	}
}

