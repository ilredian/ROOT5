package DAO;

import java.sql.SQLException;

import DTO.MemberDTO;


public interface MemberDAO {

	//회원 정보 가져오기
	public MemberDTO getMember(String userid) throws ClassNotFoundException, SQLException;
	
	//회원 가입
	public int insert(MemberDTO member) throws ClassNotFoundException, SQLException;
	
	
	
}

