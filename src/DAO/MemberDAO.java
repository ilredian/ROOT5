package DAO;

import java.util.List;

import DTO.MemberDTO;


public interface MemberDAO {
	
	//회원 전체 목록
	public List<MemberDTO> getAllMember() throws Exception;

	//회원 한명 정보
	public MemberDTO getMember(String email) throws Exception;
	
	//회원 정보 불러오기
	public MemberDTO getMemberStat(int memberno) throws Exception;
	
	//회원가입
	public int insert(MemberDTO member) throws Exception;
	
	//로그인하기
	public MemberDTO login(String email, String pwd) throws Exception;
	
	//회원탈퇴 	UPDATE MEMBERDB  SET ACTIVE = 1  WHERE MEMBERNO = #{MEMBERNO}
	public int delete(int memberno) throws Exception;
	
	//회원 사진 정보
	public String getPhoto(int memberno) throws Exception;
}

