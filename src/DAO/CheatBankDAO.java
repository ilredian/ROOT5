package DAO;

import java.util.List;

import DTO.CheatBankDTO;

public interface CheatBankDAO {

	public List<CheatBankDTO> getList(int start, int end) throws Exception;
}
