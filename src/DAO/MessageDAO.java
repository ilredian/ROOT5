package DAO;

import java.util.List;

import DTO.MessageDTO;

public interface MessageDAO {
	
	// 모든 메시지 가져오기
	public List<MessageDTO> getAllMessage(int start, int pageSize) throws Exception;
	
	// 자신의 메시지 가져오기
	public List<MessageDTO> getMyMessage(int memberno) throws Exception;
	
	// 메시지 열기
	public MessageDTO getMessage(int messageno) throws Exception;
	
	// 메시지를 열면 isopen을 1로 변경
	public int isopen(int messageno) throws Exception;
	
	// 메시지 삭제
	public int deleteMessage(int messageno) throws Exception;
	
	// 메시지 보내기insert
	public int sendMessage(int messageno) throws Exception;
}
