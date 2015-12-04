package DAO;

import java.util.List;

import DTO.CheatItemsDTO;

public interface CheatItemsDAO {
	
	//아이템 리스트 불러오기
	public List<CheatItemsDTO> getList(int start) throws Exception;
}
/*
private String goodskind;  물품명영어 
private String goodsname;  물품명한글 
private String goodsspan;  물품태그 
*/