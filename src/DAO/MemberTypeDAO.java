package DAO;

import java.util.List;

import DTO.MemberTypeDTO;

public interface MemberTypeDAO {

	// 모든 멤버 종류 가져오기
	public List<MemberTypeDTO> getMemberTypeList() throws Exception;
	
	// 특정 멤버 종류 가져오기
	public String getMemberType(int typeno) throws Exception;
	
	// 멤버 종류 수정
	public int updateMemberType(MemberTypeDTO memberTypeDTO) throws Exception;
	
	// 멤버 종류 추가
	public int insertMemberType(MemberTypeDTO memberTypeDTO) throws Exception;
	
	// 멤버 종류 삭제
	public int deleteMemberType(MemberTypeDTO memberTypeDTO) throws Exception;
}
