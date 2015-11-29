package DAO;

import java.sql.SQLException;

import DTO.MemberDTO;


public interface MemberDAO {

	//회원목록
	public MemberDTO getMember(String userid) throws ClassNotFoundException, SQLException;
	
	//회원가입
	public int insert(MemberDTO member) throws ClassNotFoundException, SQLException;
	
	//로그인하기
	public MemberDTO login(String email, String pwd) throws ClassNotFoundException, SQLException;
	
}

