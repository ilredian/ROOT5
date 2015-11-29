package DAO;

import java.util.List;

import DTO.CheaterDTO;

public interface CheaterDAO {
	
	//진술서 등록 정보 모두 불러오기
	public List<CheaterDTO> getAllCheater() throws Exception;
	
	//진술서 등록 정보 특정 한개만 불러오기
	public CheaterDTO getCheater(int stateno) throws Exception;
	
	//진술서 등록하기
	public int insert(CheaterDTO cheaterDTO) throws Exception;
	
	//진술서 수정하기
	public int update(CheaterDTO cheaterDTO) throws Exception;
	
	//진술서 삭제하기
	public int delete(int stateno) throws Exception;
}
