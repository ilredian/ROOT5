package DAO;

import java.util.List;

import DTO.CheatDomainDTO;

public interface CheatDomainDAO {

	public List<CheatDomainDTO> getList() throws Exception;
}