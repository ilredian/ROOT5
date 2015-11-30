package DAO;

import java.util.List;

import DTO.CheaterDTO;

public interface CheaterDAO {
	
	//진술서 등록된 수 불러오기
	public int getAllCheaterCount() throws Exception;
	
	//진술서 카테고리별 등록된 수 불러오기
	public int getCheaterCount(String field, String query, int cheatno) throws Exception;
	
	//진술서 등록 정보 전체 중 특정하여 불러오기
	public List<CheaterDTO> getAllCheater(int page, String field, String query, int pageSize) throws Exception;
	
	//진술서 등록 정보 카테고리별 특정하여 불러오기
	public List<CheaterDTO> getSearchCheater(int page, String field, String query, int cheatno, int pageSize) throws Exception;
	
	//진술서 등록 정보 특정 한개만 불러오기
	public CheaterDTO getCheater(int stateno) throws Exception;
	
	//진술서 등록하기
	public int insert(CheaterDTO cheaterDTO) throws Exception;
	
	//진술서 수정하기
	public int update(CheaterDTO cheaterDTO) throws Exception;
	
	//진술서 삭제하기
	public int delete(int stateno) throws Exception;
}
