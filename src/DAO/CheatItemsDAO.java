package DAO;

import java.util.List;

import DTO.CheatItemsDTO;

public interface CheatItemsDAO {
	
	// 아이템 리스트 불러오기
	// 직거래 및 비매너인 경우 0,26
	// 게임인 경우 27,29
	public List<CheatItemsDTO> getList(int start, int end) throws Exception;
	
	// 아이템 리스트 수정하기
	public int updateCheatItem(CheatItemsDTO cheatItemsDTO) throws Exception;
	
	// 아이템 리스트 추가하기
	public int insertCheatItem(CheatItemsDTO cheatItemsDTO) throws Exception;
	
	// 아이템 리스트 삭제하기
	public int deleteCheatItem(CheatItemsDTO cheatItemsDTO) throws Exception;
}