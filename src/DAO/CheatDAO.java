package DAO;

import java.util.List;

import DTO.CheatDTO;

public interface CheatDAO {

	//모든 사기종류 카테고리 가져오기
	public List<CheatDTO> getAllCheat() throws Exception;
	
	//하나의 사기종류 카테고리 가져오기
	public CheatDTO getCheat(int cheatno) throws Exception;
	
	//사기 종류 추가하기
	public int insert(CheatDTO cheatDTO) throws Exception;
	
	//사기 종류 수정하기
	public int update(CheatDTO cheatDTO) throws Exception;
	
	//사기 종류 삭제하기
	public int delete(CheatDTO cheatDTO) throws Exception;
}
