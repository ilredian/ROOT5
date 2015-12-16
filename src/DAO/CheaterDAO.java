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
	
	//진술서 등록 정보 중 회원정보로 불러오기
	public List<CheaterDTO> getCheaterMemberno(int memberno) throws Exception;
	
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
	public int getAllCheaterCount(String start, String end) throws Exception;
	
	//카테고리별 진술서 수 불러오기
	public int getCountCategory(String start, String end,int cheatno) throws Exception;
	
	//휴대폰 번호 수
	public int getCountPhone(String start, String end) throws Exception;
	
	//계좌번호 수
	public int getCountAccount(String start, String end) throws Exception;
	
	//피해금액
	public Integer getCountSum(String start, String end) throws Exception;
	
	//피해물품 TOP 10
	public List<chartItemsDTO> getCountItems(String start, String end) throws Exception;
	
	//용의자
	public List<CheaterDTO> getCountCheaterName(String start, String end) throws Exception;
	
	//사이트
	public List<CheaterDTO> getCountDomain(String start, String end) throws Exception;
	
	//은행
	public List<CheaterDTO> getCountBankName(String start, String end) throws Exception;
	
	//날짜별
	public chartDTO getChart(chartDTO chartdto) throws Exception;
	
	//날짜별-한달일 경우
	public List<chartDTO> getChartMonth(String start, String end) throws Exception;
	
	//경찰 회원 접수 시 해당 정보와 비슷한 모든 stateno 불러오기
	public List<CheaterDTO> getStatenoCompleteMatch(CheaterDTO cheaterDTO) throws Exception;
	
	//경찰 회원 넘버 저장
	public int policeUpdateMember(int memberno, int staticStateno, int stateno) throws Exception;
	
	//추적 사항 저장
	public int policeUpdateTrace(int groupno, String trace) throws Exception;
	
	//추적 내용 조회
	public String getPoliceUpdateTrace(int groupno) throws Exception;
	
	//검거 완료 사항 저장
	public int policeUpdateComplete(int groupno, String complete) throws Exception;
	
	//검거 내용 조회
	public String getPoliceUpdateComplete(int groupno) throws Exception;
}
