package DAO;

import java.util.List;

import DTO.BoardCategoryDTO;

public interface BoardCategoryDAO {

	// 모든 게시판 카테고리 가져오기
	public List<BoardCategoryDTO> getBoardCategoryList() throws Exception;
	
	// 게시판 카테고리 수정
	public int updateBoardCategory(BoardCategoryDTO boardCategoryDTO) throws Exception;
	
	// 게시판 카테고리 추가
	public int insertBoardCategory(BoardCategoryDTO boardCategoryDTO) throws Exception;
	
	// 게시판 카테고리 삭제
	public int deleteBoardCategory(BoardCategoryDTO boardCategoryDTO) throws Exception;
}
