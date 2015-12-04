package DAO;

import java.util.List;

import DTO.QueryDTO;

public interface QueryDAO {

	public List<QueryDTO> getSearchQuery(String query, int count) throws Exception;
	
	public int getQuery(String query, int count) throws Exception;
	
	public int insertQuery(String query, int count) throws Exception;
	
	public int updateQuery(String query, int count) throws Exception;
}
