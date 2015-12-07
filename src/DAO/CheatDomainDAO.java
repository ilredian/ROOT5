package DAO;

import java.util.List;

import DTO.CheatDomainDTO;

public interface CheatDomainDAO {

	// 리스트 가져오기
	public List<CheatDomainDTO> getList() throws Exception;
	
	// 리스트 수정
	public int updateDomain(CheatDomainDTO cheatDomainDTO) throws Exception;
	
	// 리스트 추가
	public int insertDomain(CheatDomainDTO cheatDomainDTO) throws Exception;
	
	// 리스트 삭제
	public int deleteDomain(CheatDomainDTO cheatDomainDTO) throws Exception;
}