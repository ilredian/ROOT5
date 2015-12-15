package DAO;

import java.util.List;

import DTO.CheaterDTO;
import DTO.InterestStatementDTO;

public interface InterestStatementDAO {

	// 등록된 정보인지 확인
	public int getResist(int memberno) throws Exception;
	
	// 관심 목록에 등록한 정보 불러오기
	public InterestStatementDTO getInterestStatement(int memberno) throws Exception;
	
	// 관심 목록에 등록
	public int insertInterest(CheaterDTO cheaterDTO) throws Exception;
	
	// 관심 목록 삭제
	public int deleteInterest(int memberno) throws Exception;
	
	// 관심 목록과 전체 DB 비교하기
	public List<InterestStatementDTO> compareDB(InterestStatementDTO interestStatementDTO) throws Exception;

	// 등록된 진술서와 관심목록 비교하기
	public List<InterestStatementDTO> compareInterestStatementDB(InterestStatementDTO interestStatementDTO) throws Exception;
	
}
