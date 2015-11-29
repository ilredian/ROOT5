package DAO;

import java.util.List;

import DTO.CheaterDTO;

public interface CheaterDAO {
	
	//진술서 등록 정보 모두 불러오기
	public List<CheaterDTO> getAllCheater() throws Exception;
	
	//진술서 등록 정보 특정 한개만 불러오기
	public CheaterDTO getCheater() throws Exception;
	
	//진술서 등록하기
	public int insert() throws Exception;
	
	//진술서 수정하기
	public int update() throws Exception;
	
	//진술서 삭제하기
	public int delete() throws Exception;
}
