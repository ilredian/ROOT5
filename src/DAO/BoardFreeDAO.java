package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.BoardFreeDTO;

public interface BoardFreeDAO {
		//게시물 개수
		public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
		//전체 게시물
		public List<BoardFreeDTO> getNotices(int start, String field, String query, int pagerSize) throws Exception;
		//게시물 삭제int start, String field, String query, int pagerSize
		public int delete(String boardno) throws ClassNotFoundException, SQLException;
		//게시물 수정
		public int update(BoardFreeDTO boardFreeDTO) throws ClassNotFoundException, SQLException;
		//게시물 상세
		public BoardFreeDTO getNotice(String boardno) throws ClassNotFoundException, SQLException;
		//게시물 입력
		public int insert(BoardFreeDTO n) throws ClassNotFoundException, SQLException;
	}	
