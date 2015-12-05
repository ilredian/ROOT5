package DAO;

import java.util.List;

import DTO.BoardFreeDTO;

public interface BoardFreeDAO {
	
	// 게시물 개수
	public int getCount(String field, String query) throws Exception;

	// 전체 게시물
	public List<BoardFreeDTO> getNotices(int start, String field, String query, int pagerSize) throws Exception;

	// 게시물 완전 삭제
	public int delete(int boardno) throws Exception;
	
	//게시물 삭제(active값만 수정하여 보이지 않게함)
	public int updateActive(int boardno) throws Exception;

	// 게시물 수정
	public int update(BoardFreeDTO boardFreeDTO) throws Exception;

	// 게시물 조회수 증가
	public int updateCountno(int boardno) throws Exception;

	// 게시물 상세
	public BoardFreeDTO getNotice(int boardno) throws Exception;

	// 게시물 입력
	public int insert(BoardFreeDTO n) throws Exception;
	
	//게시물 답글 달기
	public int answer(BoardFreeDTO boardFreeDTO) throws Exception;
	
}
