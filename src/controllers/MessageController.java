package controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.MemberDAO;
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
	public String messageOpen(@RequestParam(value = "pg", required = false, defaultValue = "1") int page,
			@RequestParam(value = "ps", required = false, defaultValue = "10") int pageSize, HttpSession session,
			Model model) throws Exception {

		// 로그 남기기
		System.out.println("My message 창으로 이동");

		// 마이바티스 DAO 변수 선언 및 세션 값 가져오기
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int memberno = ((MemberDTO) session.getAttribute("memberInfo")).getMemberno();

		// 페이징 처리
		int pagerSize = 10;// 한 번에 보여줄 페이지 번호 갯수
		String linkUrl = "messageWindow.go";// 페이지번호를 누르면 이동할 경로
		int messageCount = messageDAO.getMyMessageCount(memberno);
		int start = (page - 1) * pageSize;

		MessagePager pager = new MessagePager(messageCount, page, pageSize, pagerSize, linkUrl);

		// 리스트 뿌려주기
		List<MessageDTO> list = messageDAO.getMyMessage(start, pageSize, memberno);

		// 보낸이 이름 가져오기 및 메일 변환 처리
		List<MemberDTO> memberDTO = new ArrayList<MemberDTO>();
		for (int index = 0; index < list.size(); index++) {
			memberDTO.add(memberDAO.getMemberStat(list.get(index).getFrommemberno()));
			String email = memberDTO.get(index).getEmail();
			int location = email.indexOf("@");
			String star = "";
			if (location > 3) {
				for (int i = 3; i < location; i++) {
					star += "*";
				}
			}
			memberDTO.get(index).setEmail(email.substring(0, 3) + star + email.substring(location));
		}

		// Model 객체에 담기
		model.addAttribute("memberno", memberno);
		model.addAttribute("pager", pager);
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("list", list);
		session.setAttribute("memberInfo", ((MemberDTO) session.getAttribute("memberInfo")));

		return "message.messageMain";
	}

	// 자기 메시지 열기
	@RequestMapping("messageOpen.go")
	public String getMessage(@RequestParam("msno") int messageno,
			@RequestParam(value = "open", required = false, defaultValue = "1") int isopen, Model model)
					throws Exception {

		// 로그 남기기
		System.out.println("My message View 로 이동");

		// DB 연결
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);

		// 읽음 처리
		messageDAO.isopen(messageno);

		// 메시지 가져오기
		MessageDTO messageDTO = messageDAO.getMessage(messageno);

		// 보낸이 이름 가져오기 및 이메일 변환 처리
		MemberDTO memberDTO = memberDAO.getMemberStat(messageDTO.getFrommemberno());
		String email = memberDTO.getEmail();
		int location = email.indexOf("@");
		String star = "";
		if (location > 3) {
			for (int i = 3; i < location; i++) {
				star += "*";
			}
		}
		memberDTO.setEmail(email.substring(0, 3) + star + email.substring(location));

		// Model 객체에 담기
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("messageDTO", messageDTO);

		return "message.messageView";
	}

	// 메시지 삭제
	@RequestMapping("messageDelete.go")
	public void deleteMessage(@RequestParam("msno") int messageno, HttpServletResponse response) throws Exception {

		// 로그 남기기
		System.out.println("메시지 삭제");

		// 스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DB 연결
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		int result = messageDAO.deleteMessage(messageno);

		// 결과 후 메인으로 이동
		if (result == 1) {
			out.print("<script>alert('메시지가 성공적으로 삭제되었습니다.');location.replace('messageWindow.go');</script>");
		} else {
			out.print("<script>alert('메시지 삭제에 실패하였습니다.');location.replace('messageWindow.go');</script>");
		}
		out.close();
	}

	// 메시지 보내기 창으로 이동
	@RequestMapping(value = "messageSend.go", method = RequestMethod.GET)
	public String writeMessage() throws Exception {

		// 로그 남기기
		System.out.println("메시지 보내기 창으로 이동");

		return "message.messageWrite";
	}

	// 메시지 보내기
	@RequestMapping(value = "messageSend.go", method = RequestMethod.POST)
	public void sendMessage(
			@RequestParam("name") String query,
			HttpServletResponse response, MessageDTO messageDTO)
			throws Exception {

		// 로그 남기기
		System.out.println("메시지 보내기");

		// 스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DAO 변수 선언
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		
		// name값에서 frommemberno 값 가져오기
		String[] queryArray = query.split(" ");
		int frommemberno = Integer.parseInt(queryArray[queryArray.length-1]);
		messageDTO.setFrommemberno(frommemberno);

		// 존재하면 쪽지 보내기
		int result = messageDAO.sendMessage(messageDTO);
		System.out.println("보내기 완료");
		
		// 결과 후 메인으로 이동
		if (result == 1) {
			out.print("<script>alert('메시지가 성공적으로 전송되었습니다.');location.replace('messageWindow.go');</script>");
		} else {
			out.print("<script>alert('메시지 전송에 실패하였습니다.');location.replace('messageWindow.go');</script>");
		}
		out.close();
	}

	// 메시지 답장 쓰기
	@RequestMapping(value = "messageReply.go", method = RequestMethod.GET)
	public String replyMessagePage(@RequestParam("msno") int messageno, Model model) throws Exception {

		// 로그 남기기
		System.out.println("메시지 답장 쓰기 창으로 이동");

		// DB 연결
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);

		// 메시지 가져오기
		MessageDTO messageDTO = messageDAO.getMessage(messageno);

		// 보낸이 이름 가져오기 및 이메일 변환 처리
		MemberDTO memberDTO = memberDAO.getMemberStat(messageDTO.getFrommemberno());
		String email = memberDTO.getEmail();
		int location = email.indexOf("@");
		String star = "";
		if (location > 3) {
			for (int i = 3; i < location; i++) {
				star += "*";
			}
		}
		memberDTO.setEmail(email.substring(0, 3) + star + email.substring(location));

		// Model 객체에 담기
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("messageDTO", messageDTO);

		return "message.messageReply";
	}

	// 메시지 답장 쓰기
	@RequestMapping(value = "messageReply.go", method = RequestMethod.POST)
	public void replyMessage(HttpServletResponse response, HttpSession session, MessageDTO messageDTO) throws Exception {

		// 로그 남기기
		System.out.println("메시지 답장 보내기");

		// 스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		// DAO 변수 선언
		MessageDAO messageDAO = sqlSession.getMapper(MessageDAO.class);
		
		// 보내는 사람 회원번호 등록 후 메시지 보내기
		messageDTO.setMemberno(((MemberDTO)session.getAttribute("memberInfo")).getMemberno());
		int result = messageDAO.sendMessage(messageDTO);
		System.out.println("답장 보내기 완료");
		
		// 결과 후 메인으로 이동
		if (result == 1) {
			out.print("<script>alert('메시지가 성공적으로 전송되었습니다.');location.replace('messageWindow.go');</script>");
		} else {
			out.print("<script>alert('메시지 전송에 실패하였습니다.');location.replace('messageWindow.go');</script>");
		}
		out.close();
	}

}
