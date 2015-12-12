package DAO;

import java.util.List;

import DTO.InterestStatementDTO;

public interface InterestStatementDAO {

	// 등록된 정보인지 확인
	public int getResist(InterestStatementDTO interestStatementDTO) throws Exception;
	
	// 관심 목록에 등록한 정보 불러오기
	public InterestStatementDTO getInterestStatement(int memberno) throws Exception;
	
	// 관심 목록에 등록
	public int insertInterest(InterestStatementDTO interestStatementDTO) throws Exception;
	
	// 관심 목록 삭제
	public int deleteInterest(InterestStatementDTO interestStatementDTO) throws Exception;
	
	// 관심 목록과 전체 DB 비교하기
	public List<InterestStatementDTO> compareDB(InterestStatementDTO interestStatementDTO) throws Exception;



}
