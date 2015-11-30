package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.BoardNoticeDTO;

public interface BoardNoticeDAO {
	//게시물 개수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	//전체 게시물
	public List<BoardNoticeDTO> getNotices() throws Exception;
	//게시물 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	//게시물 수정
	public int update(BoardNoticeDTO BoardDTO) throws ClassNotFoundException, SQLException;
	//게시물 상세
	public BoardNoticeDTO getNotice(String seq) throws ClassNotFoundException, SQLException;
	//게시물 입력
	public int insert(BoardNoticeDTO n) throws ClassNotFoundException, SQLException;
	
	
	
	
}
