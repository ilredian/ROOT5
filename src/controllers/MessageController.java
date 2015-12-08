package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.MessageDAO;
import DTO.MemberDTO;
import DTO.MessageDTO;
import common.MessagePager;

@Controller
public class MessageController {

	// 자바스크립트 쓰기위한 전역 변수 설정
	PrintWriter out;
	
	@Autowired
	private SqlSession sqlSession;
	
	// 자신의 메시지 창 열기
	@RequestMapping("messageWindow.go")
	public String messageOpen(
			@RequestParam(value="pg", required = false, defaultValue = "1") int page,
			@RequestParam(value="ps", required = false, defaultValue = "10") int pageSize,
			HttpSession session,
			Model model) throws Exception{
		
		// 로그 남기기
		System.out.println("My message 창으로 이동");
		
		// 마이바티스 DAO 변수 선언 및 세션 값 가져오기
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		int memberno = ((MemberDTO)session.getAttribute("memberInfo")).getMemberno();
		
		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "messageWindow.go";// 페이지번호를 누르면 이동할 경로
		int messageCount = messageDAO.getMyMessageCount(memberno);
		int start = (page - 1) * pageSize;
		
		MessagePager pager = new MessagePager(messageCount, page, pageSize, pagerSize, linkUrl);
		
		// 리스트 뿌려주기
		List<MessageDTO> list = messageDAO.getMyMessage(start, pageSize, memberno);
		
		// 보낸이 이름 가져오기
		List<MemberDTO> memberDTO = new ArrayList<MemberDTO>();
		for(int index = 0; index < list.size(); index++){
			memberDTO.add(messageDAO.getFromName(list.get(index).getFrommemberno()));
		}
		
		// Model 객체에 담기
		model.addAttribute("memberno", memberno);
		model.addAttribute("pager", pager);
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("list", list);
		
		return "message.messageMain";
	}
	
	// 자기 메시지 열기
	@RequestMapping("messageOpen.go")
	public String getMessage(
			@RequestParam("msno") int messageno,
			@RequestParam(value="open", required = false, defaultValue = "1") int isopen,
			Model model) throws Exception{
		
		// 로그 남기기
		System.out.println("My message View 로 이동");
		
		// DB 연결
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		// 읽음 처리
		messageDAO.isopen(messageno);
		// 메시지 가져오기
		MessageDTO messageDTO = messageDAO.getMessage(messageno);
		
		// 보낸이 이름 가져오기
		MemberDTO memberDTO = messageDAO.getFromName(messageDTO.getFrommemberno());
		
		// Model 객체에 담기
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("messageDTO", messageDTO);
		
		return "message.messageView";
	}
	
	// 메시지 삭제
	@RequestMapping("messageDelete.go")
	public void deleteMessage(@RequestParam("msno") int messageno) throws Exception{
		
		// 로그 남기기
		System.out.println("메시지 삭제");
		
		// DB 연결
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		int result = messageDAO.deleteMessage(messageno);
		
		// 결과 후 메인으로 이동
		if(result == 1){
			out.print("<script>alert('메시지가 성공적으로 삭제되었습니다.');location.replace('message.messageMain');</script>");
		}else{
			out.print("<script>alert('메시지 삭제에 실패하였습니다.');location.replace('message.messageMain');</script>");
		}
		out.close();
	}
	
	// 메시지 보내기 창으로 이동
	@RequestMapping(value="messageSend.go", method = RequestMethod.GET)
	public String writeMessage() throws Exception{
		
		// 로그 남기기
		System.out.println("메시지 보내기 창으로 이동");
		
		return "message.messageWrite";
	}
	
	// 메시지 보내기
	@RequestMapping(value="messageSend.go", method = RequestMethod.POST)
	public void sendMessage(
			@RequestParam("name") String name,
			MessageDTO messageDTO) throws Exception{
		
		// 로그 남기기
		System.out.println("메시지 보내기");

		// DAO 변수 선언
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		
		// 받는이가 존재하는 지 확인
		int frommemberno = messageDTO.getFrommemberno();
		
		//존재하면 쪽지 보내기
		if(name.equals(messageDAO.getFromName(frommemberno))){
			int result = messageDAO.sendMessage(messageDTO);
			
			// 결과 후 메인으로 이동
			if(result == 1){
				out.print("<script>alert('메시지가 성공적으로 전송되었습니다.');location.replace('message.messageMain');</script>");
			}else{
				out.print("<script>alert('메시지 전송에 실패하였습니다.');location.replace('message.messageMain');</script>");
			}
		}else{
			out.print("<script>alert('회원번호 혹은 이름이 잘못 입력되었습니다.');location.replace('message.messageMain');</script>");
		}
		out.close();
	}
}
