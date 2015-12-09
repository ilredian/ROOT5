package DAO;

import java.util.List;

import DTO.CheaterDTO;
import DTO.chartDTO;
import DTO.chartItemsDTO;

public interface CheaterDAO {
	
	//진술서 카테고리별 등록된 수 불러오기
	public int getCheaterCount(String field, String query, int cheatno) throws Exception;
	
	//진술서 등록 정보 전체 중 특정하여 불러오기
	public List<CheaterDTO> getAllCheater(int start, String field, String query, int pagerSize) throws Exception;
	
	//진술서 등록 정보 카테고리별 특정하여 불러오기
	public List<CheaterDTO> getSearchCheater(int start, String field, String query, int cheatno, int pagerSize) throws Exception;
	
	//진술서 등록 정보 특정 한개만 불러오기
	public CheaterDTO getCheater(int stateno) throws Exception;
	
	//진술서 등록하기
	public int insert(CheaterDTO cheaterDTO) throws Exception;
	
	//진술서 수정하기
	public int update(CheaterDTO cheaterDTO) throws Exception;
	
	//진술서 삭제하기
	public int delete(int stateno) throws Exception;
	
	/* 차트용 count 모음 */
	//피해사례 수
	//진술서 등록된 수 불러오기
	public int getAllCheaterCount() throws Exception;
	
	//카테고리별 진술서 수 불러오기
	public int getCountCategory(String start, String end,int cheatno) throws Exception;
	
	//휴대폰 번호 수
	public int getCountPhone() throws Exception;
	
	//계좌번호 수
	public int getCountAccount() throws Exception;
	
	//피해금액
	public int getCountSum() throws Exception;
	
	//피해물품 TOP 10
	public List<chartItemsDTO> getCountItems(String start, String end) throws Exception;
	
	//용의자
	public List<CheaterDTO> getCountCheaterName() throws Exception;
	
	//사이트
	public List<CheaterDTO> getCountDomain() throws Exception;
	
	//은행
	public List<CheaterDTO> getCountBankName() throws Exception;
	
	//날짜별
	public chartDTO getChart(chartDTO chartdto) throws Exception;
}
