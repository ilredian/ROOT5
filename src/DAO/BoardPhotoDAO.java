package DAO;

import java.util.List;

import DTO.BoardPhotoDTO;

public interface BoardPhotoDAO {
	
	// 게시물 개수
	public int BoardPhotoCount() throws Exception;
	
	//전체 게시물
	public List<BoardPhotoDTO> BoardPhotoList(int start, int pagerSize) throws Exception;
	
	// 게시물 완전 삭제
	public int BoardPhotodelete(int boardno) throws Exception;
	
	//게시물 삭제(active값만 수정하여 보이지 않게함)
	public int BoardPhotoupdateActive(int boardno) throws Exception;
	
	// 게시물 수정
	public int BoardPhotoupdate (BoardPhotoDTO boardPhotoDTO)throws Exception;
	
	// 게시물 조회수 증가
	public int BoardPhotoupdateCountno (int boardno)throws Exception;
	
	// 게시물 상세
	public BoardPhotoDTO BoardPhotoDetail (int boardno)throws Exception;
	
	// 게시물 입력
	public int insert(BoardPhotoDTO n) throws Exception;
}
