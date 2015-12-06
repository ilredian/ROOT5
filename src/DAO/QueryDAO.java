package DAO;

import java.util.List;

import DTO.QueryDTO;

public interface QueryDAO {//검색어 빅 데이터

	// 모든 검색어 불러오기
	public List<QueryDTO> getSearchQuery(String query, int count) throws Exception;
	
	// 특정 검색어 횟수 불러오기
	public int getQuery(String query, int count) throws Exception;
	
	// 새로운 검색어 등록하기
	public int insertQuery(String query, int count) throws Exception;
	
	// 기존 검색어 검색 횟수 증가시키기
	public int updateQuery(String query, int count) throws Exception;
}
