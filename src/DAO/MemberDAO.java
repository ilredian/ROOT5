package DAO;

import java.util.List;

import DTO.MemberDTO;


public interface MemberDAO {
	
	//회원 전체 목록
	public List<MemberDTO> getAllMember() throws Exception;

	//회원 한명 정보
	public MemberDTO getMember(String email) throws Exception;
	
	//회원가입
	public int insert(MemberDTO member) throws Exception;
	
	//로그인하기
	public MemberDTO login(String email, String pwd) throws Exception;
	
	//회원탈퇴
	public MemberDTO delete(int memberno) throws Exception;
}

