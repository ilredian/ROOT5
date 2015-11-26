package DAO;

import java.sql.SQLException;

import DTO.MemberDTO;


public interface MemberDAO {

	//회원정보 얻기
	public MemberDTO getMember(String userid) throws ClassNotFoundException, SQLException;
	//회원가입
	public int insert(MemberDTO member) throws ClassNotFoundException, SQLException;
	
	
	
}

