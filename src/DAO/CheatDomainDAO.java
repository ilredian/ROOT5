package DAO;

import java.util.List;

import DTO.CheatDomainDTO;

public interface CheatDomainDAO {

	// 리스트 가져오기
	public List<CheatDomainDTO> getList() throws Exception;
	
	// 도메인 하나만 가져오기
	public int getDomain(CheatDomainDTO cheatDomainDTO) throws Exception;
	
	// 리스트 수정
	public int updateDomain(String domain, String domainname, String origin) throws Exception;
	
	// 리스트 추가
	public int insertDomain(CheatDomainDTO cheatDomainDTO) throws Exception;
	
	// 리스트 삭제
	public int deleteDomain(CheatDomainDTO cheatDomainDTO) throws Exception;
}