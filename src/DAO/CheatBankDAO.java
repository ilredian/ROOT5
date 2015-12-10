package DAO;

import java.util.List;

import DTO.CheatBankDTO;

public interface CheatBankDAO {

	// 은행 리스트 가져오기
	// 0,25 - 리스트 첫번째
	// 25,13 - 리스트 두번째
	// 38,25 - 리스트 세번째
	// 0,63 - 모든 리스트
	public List<CheatBankDTO> getList(int start, int end) throws Exception;
	
	// 은행 정보 있나 확인
	public int getBank(CheatBankDTO cheatBankDTO) throws Exception;
	
	// 은행 리스트 수정
	public int updateBank(String bankname, String origin) throws Exception;
	
	// 은행 리스트 추가
	public int insertBank(CheatBankDTO cheatBankDTO) throws Exception;
	
	// 은행 리스트 삭제
	public int deleteBank(CheatBankDTO cheatBankDTO) throws Exception;
}
